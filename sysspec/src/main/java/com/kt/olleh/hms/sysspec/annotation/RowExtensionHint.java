package com.kt.olleh.hms.sysspec.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.kt.olleh.hms.sysspec.adaptor.HomeCameraAdaptorCode.RowExtension;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)


public @interface RowExtensionHint {
	public RowExtension rowExtension();
}
