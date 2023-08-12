package dev.xkmc.ymlmobs.content.skill.condition.entries.entity;

import dev.xkmc.ymlmobs.content.skill.condition.core.SkillCondition;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.ConditionType;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.EvaluationType;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.IEntityCondition;
import dev.xkmc.ymlmobs.content.skill.execution.EntityDataContext;
import dev.xkmc.ymlparser.argument.Argument;
import dev.xkmc.ymlparser.primitive.calc.IRange;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

@ConditionType(
		type = EvaluationType.ENTITY,
		author = "Ash",
		name = "hasEnchantment",
		aliases = {"hasEnchant"},
		description = "Tests if the target entity has an equipped enchantment"
)
public class HasEnchantmentCondition extends SkillCondition implements IEntityCondition {

	@Argument(name = "type", aliases = {"t", "enchantment", "ench", "e", "id"}, description = "The enchantment type")
	public Enchantment enchantment;

	@Argument(name = "level", aliases = {"lvl", "l"}, description = "An optional level range to match", optional = true)
	public IRange level = IRange.POSITIVE;

	@Override
	public boolean check(EntityDataContext entity) {
		return level.test(EnchantmentHelper.getEnchantmentLevel(enchantment, entity.get()));
	}
}
