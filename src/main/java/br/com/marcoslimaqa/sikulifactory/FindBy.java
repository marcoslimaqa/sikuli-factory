package br.com.marcoslimaqa.sikulifactory;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(FIELD)
public @interface FindBy {
	String image() default "";
	String[] images() default {""};
	float similarity() default 70;
	int timeout() default 0;
	int x() default 0;
	int y() default 0;
}