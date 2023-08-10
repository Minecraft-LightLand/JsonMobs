package dev.xkmc.ymlmobs.content.core;

import dev.xkmc.ymlparser.argument.Argument;
import dev.xkmc.ymlparser.holder.DataHolder;
import dev.xkmc.ymlparser.primitive.core.StaticData;

public class SkillMechanic {

	@Argument(name = "cooldown", aliases = {"cd", "Cooldown"}, optional = true, description = "The cooldown of the mechanic in seconds. Accepts decimals.")
	protected DataHolder<Double> cooldown = new StaticData<>(0d);

	@Argument(name = "power", optional = true, description = "The power multiplier of the mechanic")
	protected double power = 1d;

	@Argument(name = "delay", optional = true, description = "Delays the execution of the mechanic by a set number of ticks")
	protected DataHolder<Integer> delay = new StaticData<>(0);

	@Argument(name = "repeat", optional = true, description = "The amount of repetitions the mechanic will do")
	protected DataHolder<Integer> repeat = new StaticData<>(0);

	@Argument(name = "repeatInterval", aliases = {"repeatI"}, optional = true, description = "The amount of ticks must elapse between repetitions")
	protected DataHolder<Integer> repeatInterval = new StaticData<>(0);

	@Argument(name = "targetInterval", aliases = {"targetI"}, optional = true, description = "The amount of ticks must elapse between target selection")
	protected DataHolder<Double> targetInterval = new StaticData<>(0d);

	@Argument(name = "chance", optional = true, description = "The likelihood of the mechanic executing. 1 = 100%, 0.5 = 50%")
	protected double chance = 1d;

	@Argument(name = "splitPower", aliases = {"powersplitbetweentargets", "powersplit"}, optional = true, description = "Whether to split the power between targets")
	protected boolean powerSplitBetweenTargets = false;

	@Argument(name = "sourceIsOrigin", aliases = {"powersplitbetweentargets", "powersplit"}, optional = true, description = "Whether to split the power between targets")
	protected boolean sourceIsOrigin = false;

	@Argument(name = "targetCreative", optional = true, description = "Whether to target creative players")
	protected boolean target_creative = false;

}
