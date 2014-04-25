package com.PPU.Logic;

import com.PPU.DB.tables.TableAnnot.FieldType;
import com.PPU.DB.tables.TableAnnot.HeaderName;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Alex on 25.04.2014.
 */
public class AnotationService {

    public static int ANNOT_FIELD_TYPE = 1;
    public static int ANNOT_BUTTON_TYPE = 2;
    public static int ANNOT_COUNT_TYPE = 3;

    public static Map getMapAnnotationFieldTypeByClass(Object obj) {
        Map<String, Object []> map = new HashMap<String, Object []>();
        Class cl = obj.getClass();

        Method[] methods = cl.getMethods();

        for (Method method : methods) {

            if (method.isAnnotationPresent(FieldType.class)) {
                if (method.isAnnotationPresent(HeaderName.class)) {
                    FieldType annot1 = method.getAnnotation(FieldType.class);

                    HeaderName annot2 = method.getAnnotation(HeaderName.class);

                    map.put(method.getName(), new Object[]{annot1.type(),annot2.name()});
                }
            }
        }
        return map;
    }

    public static void main(String[] args) {
      getMapAnnotationFieldTypeByClass(new Cl());

    }
}
