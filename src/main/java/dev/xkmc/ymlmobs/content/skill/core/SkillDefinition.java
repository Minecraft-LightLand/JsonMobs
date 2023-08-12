package dev.xkmc.ymlmobs.content.skill.core;

import java.util.LinkedList;
import java.util.List;

public class SkillDefinition extends SkillMechanic {

	protected List<MechanicInstance> skills = new LinkedList<>();
	protected boolean stopIfNoTargets = false;
	protected boolean inlineSkill = false;
	protected List<String> killMessages;

	//protected Optional<Skill> onCooldownSkill = Optional.empty();

	public SkillDefinition() {

	}


}
