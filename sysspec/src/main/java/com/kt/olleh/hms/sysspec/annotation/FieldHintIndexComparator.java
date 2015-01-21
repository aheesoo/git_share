package com.kt.olleh.hms.sysspec.annotation;

import java.lang.reflect.Field;
import java.util.Comparator;

public class FieldHintIndexComparator implements Comparator<Field> {

	public int compare(Field f1, Field f2) {
		if (!f1.isAnnotationPresent(FieldHint.class) || !f2.isAnnotationPresent(FieldHint.class))
			return 0;
		
		FieldHint anno1 = f1.getAnnotation(FieldHint.class);
		FieldHint anno2 = f2.getAnnotation(FieldHint.class);
		
		if (anno1.index() < anno2.index())
			return -1;
		else
			return 1;
	}

}
