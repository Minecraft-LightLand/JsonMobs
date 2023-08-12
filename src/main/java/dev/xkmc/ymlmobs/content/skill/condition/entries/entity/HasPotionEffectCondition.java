package dev.xkmc.ymlmobs.content.skill.condition.entries.entity;

import dev.xkmc.ymlmobs.content.skill.condition.core.SkillCondition;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.ConditionType;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.EvaluationType;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.IEntityCondition;
import dev.xkmc.ymlmobs.content.skill.execution.EntityDataContext;
import dev.xkmc.ymlparser.argument.Argument;
import dev.xkmc.ymlparser.primitive.calc.IRange;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;

@ConditionType(
		type = EvaluationType.ENTITY,
		author = {"Joikd", "lcy0x1"},
		name = "hasPotionEffect",
		aliases = {"hasPotion"},
		description = "Tests if the target entity has a potion effect"
)
public class HasPotionEffectCondition extends SkillCondition implements IEntityCondition {

	@Argument(name = "type", aliases = {"t", "effect", "e"}, description = "The potion effect type. Leave empty to skip test", optional = true)
	public MobEffect effectType = null;

	@Argument(name = "category", aliases = "c", description = "The potion effect category. If it's not empty, skip all other tests", optional = true)
	public MobEffectCategory category = null;

	@Argument(name = "level", aliases = {"lvl", "l"}, description = "An optional level range to match", optional = true)
	public IRange level = IRange.NULL;

	@Argument(name = "duration", aliases = "d", description = "An optional duration range to match", optional = true)
	public IRange duration = IRange.NULL;

	@Override
	public boolean check(EntityDataContext entity) {
		if (category != null) {
			return entity.get().getActiveEffects().stream().anyMatch(e -> e.getEffect().getCategory() == category);
		}
		if (effectType != null) {
			MobEffectInstance ins = entity.get().getEffect(effectType);
			if (ins == null) {
				return false;
			}
			return level.test(ins.getAmplifier()) && duration.test(ins.getDuration());
		}
		return !entity.get().getActiveEffects().isEmpty();
	}

}
