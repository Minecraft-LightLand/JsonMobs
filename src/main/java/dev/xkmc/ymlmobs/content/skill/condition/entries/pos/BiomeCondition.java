package dev.xkmc.ymlmobs.content.skill.condition.entries.pos;

import com.google.common.collect.Sets;
import dev.xkmc.ymlmobs.content.skill.condition.core.ConditionType;
import dev.xkmc.ymlmobs.content.skill.condition.core.SkillCondition;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.EvaluationType;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.IPosCondition;
import dev.xkmc.ymlmobs.content.skill.utils.BiomeFilter;
import dev.xkmc.ymlmobs.util.LevelPosYaw;
import dev.xkmc.ymlparser.argument.Argument;

import java.util.Set;

@ConditionType(
		type = EvaluationType.POS,
		author = {"Ashijin", "lcy0x1"},
		name = "biome",
		description = "Tests if the target is within the given list of biomes, or a biome tags"
)
public class BiomeCondition extends SkillCondition implements IPosCondition {

	@Argument(
			name = "biome",
			aliases = {"b"},
			description = "A list of biomes to check"
	)
	public Set<BiomeFilter> biomes = Sets.newConcurrentHashSet();

	@Override
	public boolean check(LevelPosYaw target) {
		var biome = target.level().getBiome(target.asBlockPos());
		for (var b : biomes) {
			if (b.test(biome)) {
				return true;
			}
		}
		return false;
	}
}
