package dev.xkmc.ymlmobs.content.skill.condition.action;

import dev.xkmc.ymlmobs.content.skill.condition.core.ConditionAction;
import dev.xkmc.ymlparser.argument.Argument;
import dev.xkmc.ymlparser.holder.DataHolder;

public class PowerAction extends ConditionAction {

	@Argument(name = "multiplier", aliases = {"m", "mult"}, description = "Power multiplier")
	public DataHolder<Double> multiplier;
	
}
