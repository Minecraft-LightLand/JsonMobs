package dev.xkmc.ymlmobs.content.skill.condition.parse;

import dev.xkmc.ymlmobs.content.skill.condition.core.ISkillCondition;
import dev.xkmc.ymlmobs.content.type.YMDataTypeRegistry;
import dev.xkmc.ymlparser.parser.core.ParserLogger;
import dev.xkmc.ymlparser.parser.line.CharSupplier;
import dev.xkmc.ymlparser.parser.line.StringElement;
import dev.xkmc.ymlparser.parser.line.StringHierarchy;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

final class BoolParser {

	private static boolean isSpecialChar(char ch) {
		return ch == ' ' || ch == '!' || ch == '^' || ch == '=' || ch == '&' || ch == '|';
	}

	private final ParserLogger logger;
	private final StringElement.ListElem elem;

	private final Stack<ISkillCondition> conditions = new Stack<>();
	private final Stack<BoolOp> operations = new Stack<>();

	private boolean expectValue = true;

	private int index;

	public BoolParser(ParserLogger logger, StringElement.ListElem elem) {
		this.logger = logger;
		this.elem = elem;
		this.index = elem.start;
	}

	public ISkillCondition parse() {
		if (elem.list.size() == 0) {
			throw logger.fatal(elem.start, "Empty condition for " + elem);
		}
		StringElement.ListElem lingering = null;
		for (var e : elem.list) {
			index = e.start;
			if (e instanceof StringElement.Hierarchy hier) {
				if (lingering != null) {
					if (hier.hierarchy == StringHierarchy.CURVE) {
						lingering.list.add(hier);
						addVal(YMDataTypeRegistry.CONDITION.parseRaw(logger, lingering));
						lingering = null;
						continue;
					} else {
						throw logger.fatal(hier.start, "Illegal State with lingering string " + lingering);
					}
				}
				if (hier.hierarchy == StringHierarchy.PAREN) {
					if (hier.list.size() != 1) {
						throw logger.fatal(hier.start, "Illegal condition format for " + hier);
					}
					addVal(new BoolParser(logger, hier.list.get(0)).parse());
				} else {
					//TODO variables
					throw logger.fatal(hier.start, "Illegal condition format for " + hier);
				}
			} else {
				if (lingering != null) {
					throw logger.fatal(getIndex(), "Illegal State with lingering string " + lingering);
				}
				CharSupplier sup = new CharSupplier(e.toString());
				while (!sup.isEmpty()) {
					index = e.start + sup.getIndex();
					index++;
					char ch = sup.pop();
					if (ch == ' ') {
						continue;
					}
					if (ch == '!') {
						if (!sup.isEmpty() && sup.peek() == '=') {
							throw logger.fatal(getIndex(), "Use ^ for XOR, don't use !=. Aborting");
						}
						addOp(BoolOp.NOT);
					} else if (ch == '^') {
						addOp(BoolOp.XOR);
					} else if (ch == '&') {
						if (sup.isEmpty() || sup.pop() != '&') {
							throw logger.fatal(getIndex(), "Use && for AND. Aborting");
						}
						addOp(BoolOp.AND);
					} else if (ch == '|') {
						if (sup.isEmpty() || sup.pop() != '|') {
							throw logger.fatal(getIndex(), "Use || for OR. Aborting");
						}
						addOp(BoolOp.OR);
					} else if (ch == '=') {
						if (sup.isEmpty() || sup.pop() != '=') {
							throw logger.fatal(getIndex(), "Use == for EQUAL. Aborting");
						}
						addOp(BoolOp.EQ);
					} else {
						StringBuilder builder = new StringBuilder();
						int subIndex = e.start + sup.getIndex();
						builder.append(ch);
						while (!sup.isEmpty() && !isSpecialChar(sup.peek())) {
							builder.append(sup.pop());
						}
						var text = StringElement.wrapSimple(subIndex, builder.toString());
						if (!sup.isEmpty()) {
							addVal(YMDataTypeRegistry.CONDITION.parseRaw(logger, text));
						} else {
							lingering = text;
						}
					}
				}
			}
		}
		while (!operations.isEmpty()) {
			resolve();
		}
		if (conditions.size() != 1) {
			throw logger.fatal(elem.start, "Illegal State");
		}
		return conditions.pop();
	}

	private int getIndex() {
		return index;
	}

	private void addVal(ISkillCondition cond) {
		if (!expectValue) {
			throw logger.fatal(getIndex(), "Expects operation, condition found");
		}
		if (!operations.isEmpty() && operations.peek() == BoolOp.NOT) {
			operations.pop();
			cond = CompositeCondition.inverse(cond);
		}
		conditions.push(cond);
		expectValue = false;
	}

	private void addOp(BoolOp op) {
		if (op != BoolOp.NOT && expectValue) {
			throw logger.fatal(getIndex(), "Expects condition, operation found");
		}
		if (op == BoolOp.NOT && !expectValue) {
			throw logger.fatal(getIndex(), "NOT operation (!) cannot combine 2 conditions");
		}
		if (op == BoolOp.NOT && !operations.isEmpty() && operations.peek() == BoolOp.NOT) {
			throw logger.fatal(getIndex(), "Double NOT operation not supported");
		}
		if (op != BoolOp.NOT) {
			while (!operations.isEmpty() && operations.peek().ordinal() < op.ordinal()) {
				resolve();
			}
		}
		operations.push(op);
		expectValue = true;
	}

	private void resolve() {
		if (operations.isEmpty()) return;
		List<ISkillCondition> cond = new ArrayList<>();
		cond.add(conditions.pop());
		BoolOp op = operations.pop();
		cond.add(conditions.pop());
		if (!op.isList()) {
			conditions.push(CompositeCondition.binary(op, cond.get(0), cond.get(1)));
		}
		while (!operations.isEmpty() && operations.peek() == op) {
			operations.pop();
			cond.add(conditions.pop());
		}
		conditions.push(CompositeCondition.listOp(op, cond));
	}

}
