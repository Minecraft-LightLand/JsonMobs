package dev.xkmc.ymlmobs.content.skill.condition.evaluation;

import dev.xkmc.ymlmobs.content.skill.execution.SkillCaster;

public interface ICasterCondition {

	boolean check(SkillCaster entity);

}
