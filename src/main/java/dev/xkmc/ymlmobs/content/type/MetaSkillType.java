package dev.xkmc.ymlmobs.content.type;

import dev.xkmc.ymlmobs.content.core.*;
import dev.xkmc.ymlparser.parser.core.ParserLogger;
import dev.xkmc.ymlparser.parser.line.StringElement;
import dev.xkmc.ymlparser.registry.DataTypeMetaRegistries;
import dev.xkmc.ymlparser.type.MetaDataType;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

public class MetaSkillType extends MetaDataType<MetaSkill, Skill> {

	protected MetaSkillType() {
		super(YMDataTypeRegistry.SKILL);
	}

	@Override
	protected MetaSkill createSimple(Skill type) {
		return MetaSkill.of(type);
	}

	@Override
	protected MetaSkill createWithParams(ParserLogger logger, Skill type, List<StringElement.ListElem> other) {
		if (other.size() == 0) {
			return createSimple(type);
		}
		Queue<StringElement.ListElem> queue = new ArrayDeque<>(other);
		SkillTarget target = null;
		SkillTrigger trigger = null;
		HealthModifier healthMod = HealthModifier.NULL;
		double chance = 1;
		if (!queue.isEmpty() && queue.peek().startsWith("@")) {
			var e = queue.poll();
			assert e != null;
			target = YMDataTypeRegistry.TARGET.parse(logger, e.subElem(1));
		}
		if (!queue.isEmpty() && queue.peek().startsWith("~on")) {
			var e = queue.poll();
			assert e != null;
			trigger = YMDataTypeRegistry.TRIGGER.parse(logger, e.subElem(3));
		}
		if (!queue.isEmpty()) {
			if (YMDataTypeRegistry.HEALTH_MODIFIER.isValidStart(queue.peek())) {
				var e = queue.poll();
				assert e != null;
				healthMod = YMDataTypeRegistry.HEALTH_MODIFIER.parse(logger, e);
			}
		}
		if (!queue.isEmpty()) {
			chance = DataTypeMetaRegistries.DOUBLE.parseStatic(logger, queue.poll());
		}
		if (!queue.isEmpty()) {
			logger.error(queue.peek().start, "Config contains extra data: " + queue);
		}
		return MetaSkill.of(type, target, trigger, healthMod, chance);
	}
}
