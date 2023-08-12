package dev.xkmc.ymlmobs.content.skill.condition.evaluation;

import dev.xkmc.ymlmobs.content.skill.core.execution.EntityDataContext;

public interface IEntityCondition {

	boolean check(EntityDataContext entity);

}
