package dev.xkmc.ymlmobs.content.type;

import dev.xkmc.ymlmobs.content.skill.core.SkillMechanic;
import dev.xkmc.ymlmobs.content.skill.core.SkillCondition;
import dev.xkmc.ymlmobs.content.skill.core.SkillTargeter;
import dev.xkmc.ymlmobs.content.skill.core.SkillTrigger;
import dev.xkmc.ymlparser.type.MetaTypeRegistry;

public class YMDataTypeRegistry {

	public static final MetaTypeRegistry<SkillMechanic> MECHANIC = new MetaTypeRegistry<>("Skill");
	public static final MetaTypeRegistry<SkillCondition> CONDITION = new MetaTypeRegistry<>("Condition");
	public static final MetaTypeRegistry<SkillTargeter> TARGET = new MetaTypeRegistry<>("Targeter");
	public static final MetaTypeRegistry<SkillTrigger> TRIGGER = new MetaTypeRegistry<>("Trigger");

	public static final HealthModifierType HEALTH_MODIFIER = new HealthModifierType("HealthModifier");

	public static final SkillInsType MECHANIC_INSTANCE = new SkillInsType();
	public static final ConditionInsType CONDITION_INSTANCE = new ConditionInsType();
	// meta target
	// meta trigger

}
