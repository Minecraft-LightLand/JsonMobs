package dev.xkmc.ymlmobs.content.skill.condition.entries.pos;

import dev.xkmc.ymlmobs.content.skill.condition.core.ConditionType;
import dev.xkmc.ymlmobs.content.skill.condition.core.SkillCondition;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.EvaluationType;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.IPosCondition;
import dev.xkmc.ymlmobs.util.LevelPosYaw;
import dev.xkmc.ymlparser.argument.Argument;
import net.minecraft.resources.ResourceLocation;

import java.util.HashSet;
import java.util.Set;

@ConditionType(
		type = EvaluationType.POS,
		author = "Ashijin",
		name = "dimension",
		aliases = {"environment"},
		description = "Tests if the target is within a certain dimension"
)
public class DimensionCondition extends SkillCondition implements IPosCondition {

	@Argument(
			name = "dimension",
			aliases = {"d", "environment", "env"},
			description = "A list of dimensions to check"
	)
	public Set<ResourceLocation> dimension = new HashSet<>();

	@Override
	public boolean check(LevelPosYaw target) {
		return dimension.contains(target.level().dimension().location());
	}

}
