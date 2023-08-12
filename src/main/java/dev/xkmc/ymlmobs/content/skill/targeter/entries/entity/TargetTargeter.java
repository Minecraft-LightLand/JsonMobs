package dev.xkmc.ymlmobs.content.skill.targeter.entries.entity;

import dev.xkmc.ymlmobs.content.skill.execution.EntityDataContext;
import dev.xkmc.ymlmobs.content.skill.execution.SkillInitiateData;
import dev.xkmc.ymlmobs.content.skill.targeter.core.SkillTargeter;
import dev.xkmc.ymlmobs.content.skill.targeter.core.TargeterType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;

import java.util.Collection;
import java.util.List;

@TargeterType(
		author = "Ashijin",
		name = "target",
		aliases = {"T"},
		description = "Targets the caster's target"
)
public class TargetTargeter extends SkillTargeter {

	@Override
	public Collection<EntityDataContext> getEntities(SkillInitiateData data) {
		Entity entity = null;
		if (data.caster().get() instanceof Mob mob) {
			// TODO targets.add(am.getThreatTable().getTopThreatHolder());
			entity = mob.getTarget();
		}
		if (data.caster().get() instanceof Player player) {
			// TODO  LivingEntity le = MythicUtil.getTargetedEntity((Player)BukkitAdapter.adapt(caster.getEntity()));
		}
		return entity == null ? List.of() : List.of(EntityDataContext.of(entity));
	}

}
