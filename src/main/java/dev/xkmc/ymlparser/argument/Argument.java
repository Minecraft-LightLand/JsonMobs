package dev.xkmc.ymlparser.argument;

import dev.xkmc.ymlparser.compound.CompoundValue;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Argument {

	String name();

	String[] aliases() default {};

	String description();

	boolean optional() default false;

	Class<? extends CompoundValue> compound() default CompoundValue.class;

}
