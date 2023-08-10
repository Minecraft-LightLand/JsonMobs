package dev.xkmc.ymlmobs.content.skill.execution;

import dev.xkmc.ymlparser.holder.DataContext;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;

public record SkillMetaData(LivingEntity caster) implements DataContext {

	@Override
	public RandomSource getRandom() {
		return caster.getRandom();
	}

}
