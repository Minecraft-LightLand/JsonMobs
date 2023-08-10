package dev.xkmc.ymlmobs.content.type;

import dev.xkmc.ymlmobs.content.core.Skill;
import dev.xkmc.ymlmobs.content.core.SkillCondition;
import dev.xkmc.ymlmobs.content.core.SkillTarget;
import dev.xkmc.ymlmobs.content.core.SkillTrigger;
import dev.xkmc.ymlparser.type.MetaTypeRegistry;

public class YMDataTypeRegistry {

	public static final MetaTypeRegistry<Skill> SKILL = new MetaTypeRegistry<>("Skill");
	public static final MetaTypeRegistry<SkillCondition> CONDITION = new MetaTypeRegistry<>("Condition");
	public static final MetaTypeRegistry<SkillTarget> TARGET = new MetaTypeRegistry<>("Target");
	public static final MetaTypeRegistry<SkillTrigger> TRIGGER = new MetaTypeRegistry<>("Trigger");

	public static final HealthModifierType HEALTH_MODIFIER = new HealthModifierType("HealthModifier");

	public static final MetaSkillType META_SKILL = new MetaSkillType();
	public static final ConditionType META_CONDITION = new ConditionType();
	// meta target
	// meta trigger

}
