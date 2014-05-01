package com.PPU.DB.tables.TableAnnot;

import com.PPU.DB.workLogic.WorkWithTable;

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
     * 2 - кнопка для формы со списком (попробуем пока двойной список список для many to one)
     * 3 - кол-во строк (попробуем пока одинарный список список для one to many)
     **/
    int type();
	String worker() default "";
}
