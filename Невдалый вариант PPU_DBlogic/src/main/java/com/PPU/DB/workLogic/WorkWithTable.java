package com.PPU.DB.workLogic;

import com.PPU.DB.DAO.PpuDaoInterface;
import com.PPU.DB.tables.PartnersMZ;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 15.04.14
 * Time: 22:34
 * To change this template use File | Settings | File Templates.
 */
public abstract class WorkWithTable {

    private static ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:applicationContext.xml");
    protected static PpuDaoInterface ppuDao = (PpuDaoInterface) context.getBean("dataDao");

    public WorkWithTable()
    {

    }

    public abstract List getListRows();

    public abstract List findAndGetAllRow(String fields, String fieldValue);

    public abstract Object getColumnValue(Object obj, String columnName)  throws IllegalAccessException;

    public abstract Object setColumnValueFromList(Object obj, String columnName, Object ... listValue) throws IllegalAccessException;

    public abstract void addEntity(Object obj) throws Exception;
    public abstract void changeEntity(Object obj) throws Exception;
    public abstract void deleteEntity(Object obj) throws Exception;
    public abstract Object getEntity(int id);

	public abstract Object getEmptyEntity();


	public static Object callDAOMethod(String nameFunc, Object ... values)
	{
		return ClassInvokeCall.callMethod(ppuDao, nameFunc, values);
	}

    public static Object invokeCallMethode(Object obj, String funcName, Object ... listValue) throws Exception {
        if (obj == null || funcName == null)
            throw new IllegalAccessException("Не было передано параметра в invokeCallMethode");
        Object obj1 = new Object();
        try
        {
            obj1 = ClassInvokeCall.callMethod(obj, funcName, listValue);
        }
        catch (Exception e){
            throw new Exception("В метод invokeCallMethode передан неверный параметр");
        }

        return obj1;
    }

    public static Object invokeCallMethode(Object obj, String funcName) throws Exception {
        if (obj == null || funcName == null)
            throw new IllegalAccessException("Не было передано параметра в invokeCallMethode");
        Object obj1 = new Object();
        try
        {
            obj1 = ClassInvokeCall.callMethod(obj, funcName);
        }
        catch (Exception e){
            throw new Exception("В метод invokeCallMethode передан неверный параметр");
        }

        return obj1;
    }

    public static PpuDaoInterface getPpuDao() {
        return ppuDao;
    }
}
