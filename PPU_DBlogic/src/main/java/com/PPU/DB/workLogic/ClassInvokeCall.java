package com.PPU.DB.workLogic;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Alex on 14.04.2014.
 */
public class ClassInvokeCall {

    public Object callMethod(Object obj, Object ... listValues)
    {
        Object retObj = new Object();

        try {
            Class c = obj.getClass();
            Class[] paramTypes = new Class[listValues.length];

            for (int i = 0; i < listValues.length; i++) {
                paramTypes[i] = listValues.getClass();
            }

            Method method = c.getMethod("getHg", paramTypes);
            retObj =  method.invoke(obj, listValues);
        }
        catch (NoSuchMethodException e)
        {
            throw new IllegalArgumentException("Неверно переданы параметры списка значений в метод ClassInvokeCall.callMethod(...) ");
        }
        catch (IllegalAccessException e1)
        {
            throw new IllegalArgumentException("Неверно переданы параметры списка значений в метод ClassInvokeCall.callMethod(...) ");
        }
        catch (InvocationTargetException e2)
        {
            throw new IllegalArgumentException("Неверно переданы параметры списка значений в метод ClassInvokeCall.callMethod(...) ");
        }

        return retObj;
    }
}
