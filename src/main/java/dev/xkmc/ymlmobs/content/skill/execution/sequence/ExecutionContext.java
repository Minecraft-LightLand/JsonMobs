package dev.xkmc.ymlmobs.content.skill.execution.sequence;

import dev.xkmc.ymlmobs.content.skill.execution.context.SkillTargetingData;

public record ExecutionContext(SkillTargetingData targeting, boolean targetIsOrigin, boolean forceSync) {

}
