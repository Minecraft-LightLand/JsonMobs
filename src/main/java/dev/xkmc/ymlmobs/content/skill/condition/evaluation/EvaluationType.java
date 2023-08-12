package dev.xkmc.ymlmobs.content.skill.condition.evaluation;

import java.util.function.BiPredicate;

public enum EvaluationType {
	CASTER(new Type<>(ICasterCondition.class, (a, b) -> a.check(b.caster()))),
	ENTITY(new Type<>(IEntityCondition.class, (a, b) -> b.focus() != null && a.check(b.focus()))),
	POS(new Type<>(IPosCondition.class, (a, b) -> b.pos() != null && a.check(b.pos()))),
	CASTER_ENTITY(new Type<>(ICompareEntityEntity.class, (a, b) -> b.focus() != null && a.check(b.caster(), b.focus()))),
	CASTER_POS(new Type<>(ICompareEntityPos.class, (a, b) -> b.pos() != null && a.check(b.caster(), b.pos()))),
	CUSTOM(new Type<>(ICustomCondition.class, ICustomCondition::check)),

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
