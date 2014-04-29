package com.PPU.DB.workLogic;

import com.PPU.DB.tables.CorrectionsMZ;
import com.PPU.DB.tables.PartnersMZ;
import com.PPU.DB.tables.UsersComMan;
import com.PPU.DB.tables.UsersMunMan;

import java.util.List;

/**
 * Created by Alex on 21.04.2014.
 */
public class WorkWithPartnerMZ extends WorkWithTable {

    private static String COLUMN_ID = "Id";
    private static String COLUMN_NAME = "Name";
    private static String COLUMN_ADDRESS = "Address";
    private static String COLUMN_DESCRIPTION = "Description";
    private static String COLUMN_TYPE_MU = "TypeMU";

    @Override
    public List getListRows() {
        return ppuDao.findPartnersMz("","");
    }

    @Override
    public List findAndGetAllRow(String fields, String fieldValue) {
        return ppuDao.findPartnersMz(fields,fieldValue);
    }

    @Override
    public Object getColumnValue(Object obj, String columnName) throws IllegalAccessException {
        if (obj == null)
        {
            throw new IllegalAccessException("Не было передано параметра в partnersMZ");
        }
        else
            return ClassInvokeCall.callMethod(obj, "get"+columnName);
    }

    @Override
    public Object setColumnValueFromList(Object obj, String columnName, Object ... listValue) throws IllegalAccessException {
        if (obj == null)
        {
            throw new IllegalAccessException("Не было передано параметра в userMunMan");
        }
        else
            return ClassInvokeCall.callMethod(obj, "set"+columnName, listValue);
    }

    @Override
    public void addEntity(Object obj) throws Exception {
        if (obj instanceof PartnersMZ)
            ppuDao.savePartnersMz((PartnersMZ) obj);
        else
            throw new Exception("В метод PartnersMZ.addEntity передан неверный параметр");
    }

    @Override
    public void changeEntity(Object obj) throws Exception {
        if (obj instanceof PartnersMZ)
            ppuDao.updatePartnersMz((PartnersMZ) obj);
        else
            throw new Exception("В метод PartnersMZ.addEntity передан неверный параметр");
    }

    @Override
    public void deleteEntity(Object obj) throws Exception {
        if (obj instanceof PartnersMZ)
            ppuDao.deletePartnersMz((PartnersMZ) obj);
        else
            throw new Exception("В метод PartnersMZ.addEntity передан неверный параметр");
    }

    @Override
    public Object getEntity(int id) {
        return ppuDao.getPartnersMz(id);
    }

    public static Object invokeCallMethode(Object obj, String funcName, Object ... listValue) throws Exception {
        if (obj == null || funcName == null)
            throw new IllegalAccessException("Не было передано параметра в partnersMZ");

        if (!(obj instanceof PartnersMZ))
        {
            throw new Exception("В метод PartnersMZ.addEntity передан неверный параметр");
        }

        return ClassInvokeCall.callMethod(obj, funcName, listValue);
    }

    public static Object invokeCallMethode(Object obj, String funcName) throws Exception {
        if (obj == null || funcName == null)
            throw new IllegalAccessException("Не было передано параметра в partnersMZ");

        if (!(obj instanceof PartnersMZ))
        {
            throw new Exception("В метод PartnersMZ.addEntity передан неверный параметр");
        }

        return ClassInvokeCall.callMethod(obj, funcName);
    }

	@Override
	public Object getEmptyEntity() {
		return new PartnersMZ();
	}

}