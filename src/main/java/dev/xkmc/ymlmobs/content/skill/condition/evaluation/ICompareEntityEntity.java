package dev.xkmc.ymlmobs.content.skill.condition.evaluation;

import dev.xkmc.ymlmobs.content.skill.execution.context.EntityDataContext;
import dev.xkmc.ymlmobs.content.skill.execution.context.SkillCaster;

public interface ICompareEntityEntity {

	boolean check(SkillCaster entity, EntityDataContext targetEntity);

}
