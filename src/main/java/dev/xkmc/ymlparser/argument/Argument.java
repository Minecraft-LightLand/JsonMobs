package dev.xkmc.ymlparser.argument;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Argument {

	String name();

	String[] aliases() default {};

	String descID();

	String description();

	boolean optional() default false;

	/**
	 * not used during decoding. Only used for template
	 */
	String defValue() default "";

}
