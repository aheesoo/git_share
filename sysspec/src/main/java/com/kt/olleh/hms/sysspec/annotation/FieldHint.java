package com.kt.olleh.hms.sysspec.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)

public @interface FieldHint {
	public int index();
	public int length() default 0;
	public double minVer() default 0;
	public double maxVer() default Double.MAX_VALUE;
}
