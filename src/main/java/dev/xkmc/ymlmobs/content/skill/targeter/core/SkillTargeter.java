package dev.xkmc.ymlmobs.content.skill.targeter.core;

import com.mojang.datafixers.util.Either;
import dev.xkmc.ymlmobs.content.skill.condition.core.ConditionInstance;
import dev.xkmc.ymlmobs.content.skill.execution.EntityDataContext;
import dev.xkmc.ymlmobs.content.skill.execution.SkillInitiateData;
import dev.xkmc.ymlmobs.util.LevelPosYaw;
import dev.xkmc.ymlparser.argument.Argument;
import net.minecraft.world.entity.OwnableEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class SkillTargeter {

	@Argument(name = "conditions", optional = true, description = "in-line target conditions")
	public List<ConditionInstance> targetConditions = new ArrayList<>();

	@Argument(name = "sudoparent", aliases = {"fromparent", "ofparent", "asparent", "parent", "ofparent"}, optional = true,
			description = "the targeter will be parsed as if it was the Parent of the casting entity executing the mechanic")
	protected boolean sudoParent;

	@Argument(name = "sudoowner", aliases = {"fromowner", "ofowner", "asowner", "owner", "ofowner"}, optional = true,
			description = "the targeter will be parsed as if it was the Owner of the casting entity executing the mechanic")
	protected boolean sudoOwner;

	@Argument(name = "sudoTrigger", aliases = {"fromtrigger", "oftrigger", "astrigger", "trigger", "oftrigger"}, optional = true,
			description = "If this attribute is set to true, the targeter will be parsed as if it was the Trigger of the skilltree executing the mechanic")
	protected boolean sudoTrigger;

	public Either<Collection<EntityDataContext>, Collection<LevelPosYaw>> search(SkillInitiateData init) {
		EntityDataContext caster = init.caster();
		if (sudoParent) {
			//TODO parent
		}
		if (sudoOwner) {
			if (caster.get() instanceof OwnableEntity own) {
				if (own.getOwner() != null) {
					caster = EntityDataContext.of(own.getOwner());
				}
			}
		}
		if (sudoTrigger) {
			if (init.getTriggerEntity() != null) {
				caster = init.getTriggerEntity();
			}
		}
		return searchInternal(new TargeterContext(init, caster));
	}

	protected abstract Either<Collection<EntityDataContext>, Collection<LevelPosYaw>> searchInternal(TargeterContext init);

	public TargetTypes getType() {
		return getClass().getAnnotation(TargeterType.class).type();
	}


}
