package dev.xkmc.ymlmobs.content.skill.condition.evaluation;

import java.util.function.BiPredicate;

public enum EvaluationType {
	CASTER(new Type<>(ICasterCondition.class, (a, b) -> a.check(b.getCaster()))),
	ENTITY(new Type<>(IEntityCondition.class, (a, b) -> a.check(b.getTargetEntity()))),
	POS(new Type<>(IPosCondition.class, (a, b) -> a.check(b.getTargetPos()))),
	ENTITY_COMPARE(new Type<>(ICompareEntityEntity.class, (a, b) -> a.check(b.getCaster(), b.getTargetEntity()))),
	ENTITY_POS_COMPARE(new Type<>(ICompareEntityPos.class, (a, b) -> a.check(b.getCaster(), b.getTargetPos()))),

	//POS_COMPARE(),
	//META(cls),
	//ENTITY_META_COMPARE(cls)
	;

	private final Type<?> type;

	EvaluationType(Type<?> type) {
		this.type = type;
	}

	public Class<?> getTypeClass() {
		return type.cls();
	}

	private record Type<T>(Class<T> cls, BiPredicate<T, EvaluationContext> pred) {

	}

}
