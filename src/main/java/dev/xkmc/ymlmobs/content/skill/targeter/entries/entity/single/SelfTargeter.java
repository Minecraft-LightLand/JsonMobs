package dev.xkmc.ymlmobs.content.skill.targeter.entries.entity.single;

import dev.xkmc.ymlmobs.content.skill.targeter.core.TargetTypes;
import dev.xkmc.ymlmobs.content.skill.targeter.core.TargeterContext;
import dev.xkmc.ymlmobs.content.skill.targeter.core.TargeterType;
import dev.xkmc.ymlmobs.content.skill.targeter.types.SingleEntityTargeter;
import net.minecraft.world.entity.Entity;

import java.util.Optional;

@TargeterType(
		type = TargetTypes.SINGLE_ENTITY,
		author = "Ashijin",
		name = "self",
		aliases = {"caster", "boss", "mob"},
		description = "Targets the caster"
)
public class SelfTargeter extends SingleEntityTargeter {

	@Override
	public Optional<Entity> getEntity(TargeterContext data) {
		return Optional.of(data.caster().get());
	}

}
