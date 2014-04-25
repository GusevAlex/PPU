package com.PPU.DB.tables.TableAnnot;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Alex on 25.04.2014.
 */

@Target(value= ElementType.METHOD)
@Retention(value= RetentionPolicy.RUNTIME)
public @interface FieldType {
    /**1 - простое значение
     * 2 - кнопка для формы со списком
     * 3 - кол-во строк
     **/
    int type();
}
