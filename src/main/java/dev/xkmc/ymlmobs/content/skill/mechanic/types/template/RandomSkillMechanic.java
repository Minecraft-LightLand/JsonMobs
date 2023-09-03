package dev.xkmc.ymlmobs.content.skill.mechanic.types.template;

import dev.xkmc.ymlmobs.content.skill.core.SkillMechanic;
import dev.xkmc.ymlmobs.content.skill.execution.sequence.ExecutionContext;
import dev.xkmc.ymlmobs.content.skill.execution.sequence.ExecutionResult;
import dev.xkmc.ymlmobs.content.skill.execution.sequence.ExecutionSequence;
import dev.xkmc.ymlmobs.content.skill.mechanic.core.MechanicType;
import dev.xkmc.ymlmobs.content.skill.mechanic.core.SkillType;
import dev.xkmc.ymlmobs.content.skill.mechanic.types.basic.IMetaSkill;
import dev.xkmc.ymlparser.argument.Argument;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collection;

@MechanicType(
		type = SkillType.META,
		author = "Ashijin",
		name = "randomskill",
		aliases = {"randommeta"},
		description = "Executes a random metaskill"
)
public class RandomSkillMechanic extends IMetaSkill {

	@Nullable
	@Argument(name = "fallbackskill", aliases = {"fbskill", "fbs"}, optional = true,
			description = "fallback skill in case no chosen skill matches")
	protected SkillMechanic fallbackSkill = null;

	@Argument(name = "skills", aliases = {"s", "metas", "meta", "m"},
			description = "list of skills, or skills with weights, to use")
	protected Collection<RandomSkillEntry> skills = new ArrayList<>();

	@Override
	public ExecutionResult run(ExecutionSequence seq, ExecutionContext ctx) {
		ArrayList<RandomSkillEntry> list = new ArrayList<>(this.skills);
		double total = 0;
		for (var e : list) {
			total += e.weight();
		}
		while (list.size() > 0) {
			double val = ctx.getRandom().nextDouble() * total;
			for (var e : list) {
				val -= e.weight();
				if (val <= 0) {
					ExecutionResult result = e.skill().run(seq, ctx);
					if (result.succeed()) {
						return result;
					} else {
						list.remove(e);
						break;
					}
				}
			}
		}
		if (fallbackSkill != null) {
			return fallbackSkill.run(seq, ctx);
		}
		return ExecutionResult.noTarget();
	}

}
