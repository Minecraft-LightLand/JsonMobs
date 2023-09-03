package dev.xkmc.ymlmobs.content.skill.mechanic.core;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface MechanicType {

	SkillType type();

	String[] author() default "";

	String name();

	String[] aliases() default {};

	String description();

}
