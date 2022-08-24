package com.main.annotations;

import java.lang.reflect.Field;

public class MaskValue {
	public static <T> void maskValue(T obj) throws IllegalArgumentException, IllegalAccessException {
		Class clazz = obj.getClass();
		Field[] fields = clazz.getDeclaredFields();
		for (Field f : fields) {
			if (f.isAnnotationPresent(Mask.class)) {
				f.setAccessible(true);
				if(f.get(obj) == null) continue;
				StringBuilder fieldValue = new StringBuilder((String) f.get(obj));
				for (int i = 2; i < fieldValue.length() - 2; i++) {
					fieldValue.setCharAt(i, 'X');
				}
				f.set(obj, fieldValue.toString());
				f.setAccessible(false);
			}
		}
	}

}
