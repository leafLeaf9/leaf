package com.gousade.util;

import java.lang.reflect.Field;
import java.util.*;

public class ReflectionUtil {
    public static List<Object> getFiledValues(Class<?> type, Object target, String[] fieldNames) throws IllegalAccessException {
        List<Field> fields = getFields(type, fieldNames);
        List<Object> valueList = new ArrayList();
        Iterator fieldIterator = fields.iterator();

        while (fieldIterator.hasNext()) {
            Field field = (Field) fieldIterator.next();
            if (!field.isAccessible()) {
                field.setAccessible(true);
            }

            Object value = field.get(target);
            valueList.add(value);
        }

        return valueList;
    }


    public static List<Field> getFields(Class<?> claszz, String[] fieldNames) {
        if (fieldNames != null && fieldNames.length != 0) {
            List<String> needFieldList = Arrays.asList(fieldNames);
            List<Field> matchFieldList = new ArrayList();
            List<Field> fields = getAllField(claszz);
            Iterator fieldIterator = fields.iterator();

            while (fieldIterator.hasNext()) {
                Field field = (Field) fieldIterator.next();
                if (needFieldList.contains(field.getName())) {
                    matchFieldList.add(field);
                }
            }

            return matchFieldList;
        } else {
            return Collections.EMPTY_LIST;
        }
    }

    public static List<Field> getAllField(Class<?> claszz) {
        if (claszz == null) {
            return Collections.EMPTY_LIST;
        } else {
            List<Field> list = new ArrayList();

            do {
                Field[] array = claszz.getDeclaredFields();
                list.addAll(Arrays.asList(array));
                claszz = claszz.getSuperclass();
            } while (claszz != null && claszz != Object.class);

            return list;
        }
    }
}
