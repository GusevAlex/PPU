package com.PPU.DB.workLogic;

import com.PPU.DB.DAO.PpuDaoInterface;
import com.PPU.DB.tables.MZ;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 15.04.14
 * Time: 22:34
 * To change this template use File | Settings | File Templates.
 */
public abstract class WorkWithTable {

    private static ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
    protected static PpuDaoInterface ppuDao = (PpuDaoInterface) context.getBean("dataDao");;

    public WorkWithTable()
    {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        ppuDao = (PpuDaoInterface) context.getBean("dataDao");
    }

    public abstract List getListRows();

    public abstract List findAndGetAllRow(String fields, String fieldValue);

    public abstract void setRows(Object obj);

    public abstract void setRowById(int id);

    public abstract Object getColumnValue(String columnName) throws IllegalAccessException;

    public abstract Object setColumnValueFromList(String columnName, Object ... listValue) throws IllegalAccessException;
}
