package dev.xkmc.ymlmobs.content.skill.condition.entries.cpos;

import dev.xkmc.ymlmobs.content.skill.condition.core.SkillCondition;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.ConditionType;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.EvaluationType;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.ICompareEntityPos;
import dev.xkmc.ymlmobs.content.skill.core.execution.EntityDataContext;
import dev.xkmc.ymlmobs.util.LevelPosYaw;
import dev.xkmc.ymlparser.argument.Argument;
import dev.xkmc.ymlparser.holder.DataHolder;
import dev.xkmc.ymlparser.primitive.calc.IRange;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.entity.EntityTypeTest;
import net.minecraft.world.phys.AABB;

@ConditionType(
		type = EvaluationType.POS,
		author = "Ashijin",
		name = "playersInRadius",
		aliases = {"pir", "playerInRadius"},
		description = "Checks for a given number of players within a radius of the target"
)
public class PlayersInRadiusCondition extends SkillCondition implements ICompareEntityPos {

	@Argument(
			name = "amount",
			aliases = {"a"},
			description = "The number of players to check for",
			optional = true
	)
	public IRange amount = IRange.POSITIVE;

	@Argument(
			name = "radius",
			aliases = {"range", "r"},
			description = "The radius to check in"
	)
	public DataHolder<Double> distance;

	@Argument(
			name = "ignoreSpectator",
			aliases = {"is"},
			description = "Whether to ignore non-survival players",
			optional = true
	)
	public boolean ignoreSpectator = true;

	@Override
	public boolean check(EntityDataContext target, LevelPosYaw location) {
		double r = distance.get(target);
		double radiusSq = r * r;
		return this.amount.test(location.level().getEntities(EntityTypeTest.forClass(Player.class),
				AABB.ofSize(location.asVec(), r, r, r),
				e -> (!ignoreSpectator || !e.isInvulnerable()) && e.distanceToSqr(location.asVec()) < radiusSq).size());
	}
}
