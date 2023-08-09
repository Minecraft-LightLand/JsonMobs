package dev.xkmc.ymlmobs.content.type;

import dev.xkmc.ymlmobs.content.core.*;
import dev.xkmc.ymlparser.type.DataType;
import dev.xkmc.ymlparser.type.MetaDataType;

public class YMDataTypeRegistry {

	public static final MetaTypeRegistry<Skill> SKILL = new MetaTypeRegistry<>();
	public static final MetaTypeRegistry<SkillCondition> CONDITION = new MetaTypeRegistry<>();
	public static final MetaTypeRegistry<SkillTarget> TARGET = new MetaTypeRegistry<>();
	public static final MetaTypeRegistry<SkillTrigger> TRIGGER = new MetaTypeRegistry<>();

	public static final DataType<HealthModifier> HEALTH_MODIFIER = new HealthModifierType();

	public static final MetaDataType<MetaSkill> META_SKILL = new MetaSkillType();
	public static final MetaDataType<SkillCondition> META_CONDITION = new ConditionType();
	// meta target
	// meta trigger

}
