package dev.xkmc.ymlparser.argument;

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
