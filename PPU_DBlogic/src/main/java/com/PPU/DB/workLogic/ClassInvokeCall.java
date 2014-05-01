package com.PPU.DB.workLogic;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Alex on 14.04.2014.
 */
public class ClassInvokeCall {

    private static Map<String,Class> classMap = new HashMap<String, Class>()
            { {
            put("LinkedHashSet",  Set.class);
            put("HashSet", Set.class);
            put("ArrayList",   List.class);
            }
            };

    public static Object callMethod(Object obj, String nameFunc, Object ... listValues)
    {
        Object retObj = new Object();

        try {
            Class c = obj.getClass();
            Class[] paramTypes = new Class[listValues.length];

            for (int i = 0; i < listValues.length; i++) {
                paramTypes[i] = listValues[i].getClass();

                if (classMap.containsKey(paramTypes[i].getSimpleName()))
                    paramTypes[i] = classMap.get(paramTypes[i].getSimpleName());
            }

            Method method = c.getMethod(nameFunc, paramTypes);
            retObj =  method.invoke(obj, listValues);
        }
        catch (NoSuchMethodException e)
        {
            throw new IllegalArgumentException("Ќеверно переданы параметры списка значений в метод ClassInvokeCall.callMethod(...) ");
        }
        catch (IllegalAccessException e1)
        {
            throw new IllegalArgumentException("Ќеверно передано название метода ClassInvokeCall.callMethod(...) ");
        }
        catch (InvocationTargetException e2)
        {
            throw new IllegalArgumentException("Ќеверно передано название метода ClassInvokeCall.callMethod(...) ");
        }

        return retObj;
    }

	public static Object returnObjectByName(String className)
	{
		Object obj = new Object();
		try
		{
			Class class1 = Class.forName(className);

			obj = class1.newInstance();
		}
		catch (Exception e)
		{
			System.out.println(e);
		}

		return obj;
	}



	public static Object returnWorkerByName(String className)
	{
		return returnObjectByName("com.PPU.DB.workLogic."+className);
	}
}
