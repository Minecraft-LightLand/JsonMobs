package dev.xkmc.ymlmobs.content.type;

import dev.xkmc.ymlmobs.content.skill.core.ConditionInstance;
import dev.xkmc.ymlmobs.content.skill.core.MechanicInstance;
import dev.xkmc.ymlmobs.content.skill.core.SkillMechanic;
import dev.xkmc.ymlparser.parser.core.ParserLogger;
import dev.xkmc.ymlparser.parser.line.StringElement;
import dev.xkmc.ymlparser.registry.DataTypeMetaRegistries;
import dev.xkmc.ymlparser.type.MetaDataType;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

public class SkillInsType extends MetaDataType<MechanicInstance, SkillMechanic> {

	protected SkillInsType() {
		super(YMDataTypeRegistry.MECHANIC);
	}

	@Override
	protected MechanicInstance createSimple(SkillMechanic type) {
		return MechanicInstance.of(type);
	}

	@Override
	protected MechanicInstance createWithParams(ParserLogger logger, SkillMechanic type, List<StringElement.ListElem> other) {
		if (other.size() == 0) {
			return createSimple(type);
		}
		Queue<StringElement.ListElem> queue = new ArrayDeque<>(other);
		MechanicInstance.Builder builder = MechanicInstance.builder(type);
		if (!queue.isEmpty() && queue.peek().startsWith("@")) {
			var e = queue.poll();
			assert e != null;
			builder.setTarget(YMDataTypeRegistry.TARGET.parse(logger, e.subElem(1)));
		}
		if (!queue.isEmpty() && queue.peek().startsWith("~on")) {
			var e = queue.poll();
			assert e != null;
			builder.setTrigger(YMDataTypeRegistry.TRIGGER.parse(logger, e.subElem(3)));
		}
		while (!queue.isEmpty() && queue.peek().startsWith("?")) {
			var e = queue.poll();
			assert e != null;
			if (e.startsWith("?~!")) {
				builder.addTriggerCond(ConditionInstance.of(YMDataTypeRegistry.CONDITION.parse(logger, e.subElem(3)), false));
			} else if (e.startsWith("?~")) {
				builder.addTriggerCond(ConditionInstance.of(YMDataTypeRegistry.CONDITION.parse(logger, e.subElem(2)), true));
			} else if (e.startsWith("?!")) {
				builder.addCasterCond(ConditionInstance.of(YMDataTypeRegistry.CONDITION.parse(logger, e.subElem(2)), false));
			} else {
				builder.addCasterCond(ConditionInstance.of(YMDataTypeRegistry.CONDITION.parse(logger, e.subElem(1)), true));
			}
		}
		if (!queue.isEmpty()) {
			if (YMDataTypeRegistry.HEALTH_MODIFIER.isValidStart(queue.peek())) {
				var e = queue.poll();
				assert e != null;
				builder.setHealthMod(YMDataTypeRegistry.HEALTH_MODIFIER.parse(logger, e));
			}
		}
		if (!queue.isEmpty()) {
			builder.setChance(DataTypeMetaRegistries.DOUBLE.parseStatic(logger, queue.poll()));
		}
		if (!queue.isEmpty()) {
			logger.error(queue.peek().start, "Config contains extra data: " + queue);
		}
		return builder.build();
	}

}
