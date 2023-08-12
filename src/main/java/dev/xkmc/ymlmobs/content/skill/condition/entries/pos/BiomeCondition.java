package dev.xkmc.ymlmobs.content.skill.condition.entries.pos;

import com.google.common.collect.Sets;
import dev.xkmc.ymlmobs.content.skill.condition.core.SkillCondition;
import dev.xkmc.ymlmobs.content.skill.condition.core.ConditionType;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.EvaluationType;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.IPosCondition;
import dev.xkmc.ymlmobs.util.LevelPosYaw;
import dev.xkmc.ymlparser.argument.Argument;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;

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
	public Set<ResourceLocation> biomes = Sets.newConcurrentHashSet();

	@Argument(
			name = "tag",
			aliases = {"t", "btag", "biometag"},
			description = "A list of biome tags"
	)
	public Set<ResourceLocation> biometags = Sets.newConcurrentHashSet();

	@Override
	public boolean check(LevelPosYaw target) {
		var biome = target.level().getBiome(target.asBlockPos());
		if (biome.unwrapKey().map(e -> biomes.contains(e.location())).orElse(false)) {
			return true;
		}
		for (var rl : biometags) {
			if (biome.is(TagKey.create(Registries.BIOME, rl))) {
				return true;
			}
		}
		return false;
	}
}
