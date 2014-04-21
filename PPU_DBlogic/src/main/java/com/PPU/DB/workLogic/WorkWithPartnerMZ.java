package com.PPU.DB.workLogic;

import com.PPU.DB.tables.MZ;
import com.PPU.DB.tables.PartnersMZ;
import com.PPU.DB.tables.UsersComMan;
import com.PPU.DB.tables.UsersMunMan;

import java.util.List;

/**
 * Created by Alex on 21.04.2014.
 */
public class WorkWithPartnerMZ extends WorkWithTable {

    private String name;
    private String address;
    private String description;
    private int typeMU;

    private static String COLUMN_ID = "Id";
    private static String NAME_ID = "Name";
    private static String ADDRESS_ID = "Address";
    private static String DESCRIPTION_ID = "Description";
    private static String TYPE_MU_ID = "TypeMU";

    private PartnersMZ partnersMZ;
    private List<PartnersMZ> partnersMZs;

    @Override
    public List getListRows() {
        return ppuDao.findPartnersMz("","");
    }

    @Override
    public List findAndGetAllRow(String fields, String fieldValue) {
        return ppuDao.findPartnersMz(fields,fieldValue);
    }

    @Override
    public void setRows(Object obj) {
        if ((obj instanceof PartnersMZ))
        {
            this.partnersMZ = (PartnersMZ) obj;
        }
        else
            throw new IllegalArgumentException("Неверно передан входной параметр");
    }

    @Override
    public void setRowById(int id) {
        partnersMZ = ppuDao.getPartnersMz(id);
    }

    @Override
    public Object getColumnValue(String columnName) throws IllegalAccessException {
        if (partnersMZ == null)
        {
            throw new IllegalAccessException("Не было передано параметра в partnersMZ");
        }
        else
            return ClassInvokeCall.callMethod(partnersMZ, "get"+columnName);
    }

    @Override
    public Object setColumnValueFromList(String columnName, Object ... listValue) throws IllegalAccessException {
        if (partnersMZ == null)
        {
            throw new IllegalAccessException("Не было передано параметра в userMunMan");
        }
        else
            return ClassInvokeCall.callMethod(partnersMZ, "set"+columnName, listValue);
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
        return ppuDao.getMz(id);
    }
}
