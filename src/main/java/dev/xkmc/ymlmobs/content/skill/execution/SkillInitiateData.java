package dev.xkmc.ymlmobs.content.skill.execution;

import dev.xkmc.ymlmobs.content.skill.core.SkillMechanic;

import javax.annotation.Nullable;
import java.util.List;

public class SkillInitiateData extends SkillModifiableData {

	private final TriggerContext trigger;

	public SkillInitiateData(TriggerContext trigger, SkillMechanic base) {
		super(List.of(base), 1);
		this.trigger = trigger;
	}

	public SkillCaster caster() {
		return trigger.getCaster();
	}

	@Nullable
	public EntityDataContext getTriggerEntity(){
		return trigger.getTriggerEntity();
	}

}
