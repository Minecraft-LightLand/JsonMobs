package dev.xkmc.ymlmobs.content.skill.condition.evaluation;

import dev.xkmc.ymlmobs.content.skill.execution.EntityDataContext;
import dev.xkmc.ymlmobs.content.skill.execution.SkillCaster;

public interface ICompareEntityEntity {

	boolean check(SkillCaster entity, EntityDataContext targetEntity);

}
