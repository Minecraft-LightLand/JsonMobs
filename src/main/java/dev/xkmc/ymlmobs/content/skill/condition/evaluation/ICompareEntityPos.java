package dev.xkmc.ymlmobs.content.skill.condition.evaluation;

import dev.xkmc.ymlmobs.content.skill.execution.EntityDataContext;
import dev.xkmc.ymlmobs.content.skill.execution.SkillCaster;
import dev.xkmc.ymlmobs.util.LevelPosYaw;

public interface ICompareEntityPos {

	boolean check(SkillCaster entity, LevelPosYaw targetPos);

}
