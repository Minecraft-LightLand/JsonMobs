package dev.xkmc.ymlmobs.content.skill.condition.action;

import dev.xkmc.ymlmobs.content.skill.condition.core.ConditionAction;
import dev.xkmc.ymlmobs.content.skill.core.SkillMechanic;
import dev.xkmc.ymlparser.argument.Argument;

public class OrElseCastAction extends ConditionAction {

	@Argument(name = "skill", aliases = {"s"}, description = "The skill to cast instead")
	public SkillMechanic skill;

}
