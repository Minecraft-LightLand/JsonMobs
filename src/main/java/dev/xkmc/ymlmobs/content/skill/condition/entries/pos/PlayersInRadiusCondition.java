package dev.xkmc.ymlmobs.content.skill.condition.entries.pos;

import dev.xkmc.ymlmobs.content.skill.condition.core.SkillCondition;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.ConditionType;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.EvaluationType;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.IPosCondition;
import dev.xkmc.ymlmobs.util.LevelPosYaw;
import dev.xkmc.ymlparser.argument.Argument;
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
public class PlayersInRadiusCondition extends SkillCondition implements IPosCondition {

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
	public double distance;//TODO change to DataHolder

	@Argument(
			name = "ignoreSpectator",
			aliases = {"is"},
			description = "Whether to ignore non-survival players",
			optional = true
	)
	public boolean ignoreSpectator = true;

	@Override
	public boolean check(LevelPosYaw location) {
		double radiusSq = distance * distance;
		return this.amount.test(location.level().getEntities(EntityTypeTest.forClass(Player.class),
				AABB.ofSize(location.asVec(), distance, distance, distance),
				e -> (!ignoreSpectator || !e.isInvulnerable()) && e.distanceToSqr(location.asVec()) < radiusSq).size());
	}
}
