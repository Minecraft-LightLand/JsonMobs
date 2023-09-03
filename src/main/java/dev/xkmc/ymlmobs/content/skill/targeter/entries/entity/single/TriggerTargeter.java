package dev.xkmc.ymlmobs.content.skill.targeter.entries.entity.single;

import dev.xkmc.ymlmobs.content.skill.execution.context.EntityDataContext;
import dev.xkmc.ymlmobs.content.skill.targeter.core.TargetTypes;
import dev.xkmc.ymlmobs.content.skill.targeter.core.TargeterContext;
import dev.xkmc.ymlmobs.content.skill.targeter.core.TargeterType;
import dev.xkmc.ymlmobs.content.skill.targeter.types.SingleEntityTargeter;
import net.minecraft.world.entity.Entity;

import java.util.Optional;

@TargeterType(
		type = TargetTypes.SINGLE_ENTITY,
		author = "Ashijin",
		name = "trigger",
		description = "Targets the entity that triggered the skill"
)
public class TriggerTargeter extends SingleEntityTargeter {

	@Override
	public Optional<Entity> getEntity(TargeterContext init) {
		return Optional.ofNullable(init.real().getTriggerEntity()).map(EntityDataContext::get);
	}

}
