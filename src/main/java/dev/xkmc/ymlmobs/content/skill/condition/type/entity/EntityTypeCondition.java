package dev.xkmc.ymlmobs.content.skill.condition.type.entity;

import dev.xkmc.ymlmobs.content.skill.condition.core.SkillCondition;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.ConditionType;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.EvaluationType;
import dev.xkmc.ymlmobs.content.skill.condition.evaluation.IEntityCondition;
import dev.xkmc.ymlparser.argument.Argument;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;

import java.util.HashSet;
import java.util.Set;

@ConditionType(
		author = "Ashijin",
		value = EvaluationType.ENTITY,
		name = "entityType",
		aliases = {"mobtype"},
		description = "Tests the entity type of the target"
)
public class EntityTypeCondition extends SkillCondition implements IEntityCondition {

	@Argument(
			name = "type",
			aliases = {"types", "t"},
			description = "A list of entity types to match"
	)
	protected Set<EntityType<?>> entityTypes = new HashSet<>();

	@Override
	public boolean check(LivingEntity targetEntity) {
		return this.entityTypes.contains(targetEntity.getType());
	}
}
