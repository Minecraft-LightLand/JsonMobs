package dev.xkmc.ymlmobs.content.skill.condition.evaluation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ConditionType {

	String[] author() default "";

	EvaluationType[] value();

	String name();

	String[] aliases() default {};

	String description();

}
