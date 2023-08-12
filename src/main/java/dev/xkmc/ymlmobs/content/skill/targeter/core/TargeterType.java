package dev.xkmc.ymlmobs.content.skill.targeter.core;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface TargeterType {

	TargetTypes type();

	String[] author() default "";

	String name();

	String[] aliases() default {};

	String description();

}
