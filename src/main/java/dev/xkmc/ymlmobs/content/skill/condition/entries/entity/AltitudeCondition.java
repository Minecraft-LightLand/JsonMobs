package dev.xkmc.ymlmobs.content.skill.condition.entries.entity;

import dev.xkmc.ymlmobs.content.skill.condition.core.SkillCondition;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.ConditionType;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.EvaluationType;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.IEntityCondition;
import dev.xkmc.ymlparser.argument.Argument;
import dev.xkmc.ymlparser.primitive.calc.IRange;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.state.BlockState;

@ConditionType(
		author = "Ashijin",
		type = EvaluationType.ENTITY,
		name = "altitude",
		aliases = {"heightfromsurface"},
		description = "Tests how far above the ground the target entity is"
)
public class AltitudeCondition extends SkillCondition implements IEntityCondition {

	@Argument(
			name = "height",
			aliases = {"altitude", "a", "h"},
			description = "The height range to check"
	)
	public IRange height;

	@Argument(
			name = "maxHeight",
			aliases = {"mH"},
			description = "The maximum height range to check",
			optional = true
	)
	public double maxHeight = 30d;

	@Override
	public boolean check(LivingEntity target) {
		BlockPos pos = target.blockPosition();
		BlockState state = target.level().getBlockState(pos);
		int diff = 0;
		while (state.getCollisionShape(target.level(), pos).isEmpty()) {
			pos = pos.below();
			state = target.level().getBlockState(pos);
			diff++;
			if (diff > maxHeight) {
				break;
			}
		}
		return this.height.test(diff);
	}
}
