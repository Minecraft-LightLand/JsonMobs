package dev.xkmc.ymlmobs.content.skill.targeter.entries.entity.single;

import dev.xkmc.ymlmobs.content.skill.targeter.core.TargetTypes;
import dev.xkmc.ymlmobs.content.skill.targeter.core.TargeterContext;
import dev.xkmc.ymlmobs.content.skill.targeter.core.TargeterType;
import dev.xkmc.ymlmobs.content.skill.targeter.types.SingleEntityTargeter;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;

import java.util.Optional;

@TargeterType(
		type = TargetTypes.SINGLE_ENTITY,
		author = "Ashijin",
		name = "target",
		aliases = {"T"},
		description = "Targets the caster's target"
)
public class TargetTargeter extends SingleEntityTargeter {

	@Override
	public Optional<Entity> getEntity(TargeterContext data) {
		Entity entity = null;
		if (data.caster().get() instanceof Mob mob) {
			// TODO targets.add(am.getThreatTable().getTopThreatHolder());
			entity = mob.getTarget();
		}
		if (data.caster().get() instanceof Player player) {
			// TODO  LivingEntity le = MythicUtil.getTargetedEntity((Player)BukkitAdapter.adapt(caster.getEntity()));
		}
		return Optional.ofNullable(entity);
	}

}
