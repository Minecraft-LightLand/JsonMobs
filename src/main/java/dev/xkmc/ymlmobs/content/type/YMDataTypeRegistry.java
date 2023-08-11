package dev.xkmc.ymlmobs.content.type;

import dev.xkmc.ymlmobs.content.skill.condition.action.*;
import dev.xkmc.ymlmobs.content.skill.condition.core.ConditionAction;
import dev.xkmc.ymlmobs.content.skill.condition.core.ConditionInstance;
import dev.xkmc.ymlmobs.content.skill.condition.parse.ConditionInsType;
import dev.xkmc.ymlmobs.content.skill.condition.parse.ConditionMetaType;
import dev.xkmc.ymlmobs.content.skill.core.MechanicInstance;
import dev.xkmc.ymlmobs.content.skill.core.SkillMechanic;
import dev.xkmc.ymlmobs.content.skill.core.SkillTargeter;
import dev.xkmc.ymlmobs.content.skill.core.SkillTrigger;
import dev.xkmc.ymlmobs.init.YmlMobs;
import dev.xkmc.ymlparser.registry.DataTypeLangGen;
import dev.xkmc.ymlparser.registry.DataTypeMetaRegistries;
import dev.xkmc.ymlparser.type.MetaTypeEntry;
import dev.xkmc.ymlparser.type.MetaTypeRegistry;

public class YMDataTypeRegistry {

	// floating registries
	public static final MetaTypeRegistry<SkillMechanic> MECHANIC = new MetaTypeRegistry<>("Skill");
	public static final ConditionMetaType CONDITION = new ConditionMetaType("Condition");
	public static final MetaTypeRegistry<ConditionAction> CONDITION_ACTION = new MetaTypeRegistry<>("ConditionAction");
	public static final MetaTypeRegistry<SkillTargeter> TARGET = new MetaTypeRegistry<>("Targeter");
	public static final MetaTypeRegistry<SkillTrigger> TRIGGER = new MetaTypeRegistry<>("Trigger");
	public static final HealthModifierType HEALTH_MODIFIER = new HealthModifierType("HealthModifier");

	// registered registries
	public static final SkillInsType MECHANIC_INSTANCE = new SkillInsType();
	public static final ConditionInsType CONDITION_INSTANCE = new ConditionInsType();

	static {
		//Data Type Meta-registries
		{
			DataTypeMetaRegistries.registerDataType(MechanicInstance.class, MECHANIC_INSTANCE);
			DataTypeMetaRegistries.registerDataType(ConditionInstance.class, CONDITION_INSTANCE);
		}

		DataTypeLangGen reg = YmlMobs.REGISTRATE;

		// condition actions
		{
			regConditionAction("required", RequiredAction.class, "true", "t").desc(reg, "If condition is not met, cancel the execution");
			regConditionAction("cancel", CancelAction.class, "false", "f").desc(reg, "If condition is met, cancel the execution");
			regConditionAction("power", PowerAction.class).desc(reg, "If condition is met, multiply power by a factor");
			regConditionAction("cast", CastAction.class).desc(reg, "If condition is met, execute an extra skill");
			regConditionAction("castinstead", CastInsteadAction.class).desc(reg, "If condition is met, replace skill with another skill");
			regConditionAction("orelsecast", OrElseCastAction.class).desc(reg, "If condition is not met, replace skill with another skill");
		}

		{

		}
	}

	protected static <T extends ConditionAction> MetaTypeEntry<ConditionAction> regConditionAction(String id, Class<T> cls, String... alias) {
		return CONDITION_ACTION.register(new MetaTypeEntry<>(CONDITION_ACTION, id, cls, alias));
	}

	public static void register() {

	}

}
