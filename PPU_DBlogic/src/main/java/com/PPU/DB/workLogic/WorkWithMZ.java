package com.PPU.DB.workLogic;

import com.PPU.DB.DAO.PpuDaoInterface;
import com.PPU.DB.tables.MZ;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;
import java.util.List;

/**
 * Created by Alex on 14.04.2014.
 */
public class WorkWithMZ extends WorkWithTable{

    public static String COLUMN_ID = "Id";
    public static String COLUMN_ID_PROGRAM = "IdProgram";
    public static String COLUMN_NAME = "Name";
    public static String COLUMN_ID_CUSTOMER = "IdCustomer";
    public static String COLUMN_ID_LEADER = "IdLeader";
    public static String COLUMN_START_DATE = "StartDate";
    public static String COLUMN_EXPIRATION_DATE = "ExpirationDate";
    public static String COLUMN_DESCRIPTION = "Description";
    public static String COLUMN_BUDGET = "Budget";
    public static String COLUMN_STATUS = "Status";
    public static String COLUMN_SERVICE_TYPE = "ServiceType";

    private List<MZ> mzs;
    private MZ mz;

    public WorkWithMZ()
    {
        super();

        mz = new MZ();
        mzs = ppuDao.findMz("", "");
    }

    public WorkWithMZ(int idMZ)
    {
        this();

        mz = ppuDao.getMz(idMZ);
    }

    public WorkWithMZ(String fields, String fieldValue)
    {
        this();

        mzs = ppuDao.findMz(fields, fieldValue);
    }

    @Override
    public List getListRows()
    {
        return mzs;
    }

    @Override
    public List<MZ> findAndGetAllRow(String fields, String fieldValue)
    {
       return ppuDao.findMz(fields, fieldValue);
    }

    @Override
    public void setRows(Object obj)
    {
        if ((mz != null) && (mz instanceof MZ))
        {
            this.mz = (MZ) obj;
        }
        else
            throw new IllegalArgumentException("Неверно передан входной параметр");
    }

    @Override
    public void setRowById(int id)
    {
        mz = ppuDao.getMz(id);
    }
    
    public MZ getMZ()
    {
        return mz;    
    }

    @Override
    public Object getColumnValue(String columnName) throws IllegalAccessException {
        if (mz == null)
        {
            throw new IllegalAccessException("Не было передано параметра в mz");
        }
        else
            return ClassInvokeCall.callMethod(mz, "get"+columnName);
    }

    @Override
    public Object setColumnValueFromList(String columnName, Object ... listValue) throws IllegalAccessException {
        if (mz == null)
        {
            throw new IllegalAccessException("Не было передано параметра в mz");
        }
        else
            return ClassInvokeCall.callMethod(mz, "set"+columnName, listValue);
    }
    
    
}
