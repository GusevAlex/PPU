package com.PPU.DB.workLogic;

import com.PPU.DB.tables.ComandMZ;

import java.util.List;

/**
 * Created by user on 22.04.14.
 */
public class WorkWithCommandMz extends WorkWithTable {

	private static String COLUMN_ID = "Id";
	private static String COLUMN_ID_MZ = "IdMZ";
	private static String COLUMN_ID_PARTNER_MZ = "IdPartnerMZ";

	@Override
	public List getListRows() {
		return ppuDao.findComandMZ("", "");
	}

	@Override
	public List findAndGetAllRow(String fields, String fieldValue) {
		return ppuDao.findComandMZ(fields, fieldValue);
	}

	@Override
	public Object getColumnValue(Object obj, String columnName) throws IllegalAccessException {
		if (obj == null)
		{
			throw new IllegalAccessException("Не было передано параметра в ComandMZ");
		}
		else
			return ClassInvokeCall.callMethod(obj, "get"+columnName);
	}

	@Override
	public Object setColumnValueFromList(Object obj, String columnName, Object ... listValue) throws IllegalAccessException {
		if (obj == null)
		{
			throw new IllegalAccessException("Не было передано параметра в ComandMZ");
		}
		else
			return ClassInvokeCall.callMethod(obj, "set"+columnName, listValue);
	}

	@Override
	public void addEntity(Object obj) throws Exception {
		if (obj instanceof ComandMZ)
			ppuDao.saveComandMZ((ComandMZ) obj);
		else
			throw new Exception("В метод ComandMZ.addEntity передан неверный параметр");
	}

	@Override
	public void changeEntity(Object obj) throws Exception {
		if (obj instanceof ComandMZ)
			ppuDao.updateComandMZ((ComandMZ) obj);
		else
			throw new Exception("В метод ComandMZ.addEntity передан неверный параметр");
	}

	@Override
	public void deleteEntity(Object obj) throws Exception {
		if (obj instanceof ComandMZ)
			ppuDao.deleteComandMZ((ComandMZ) obj);
		else
			throw new Exception("В метод ComandMZ.addEntity передан неверный параметр");
	}

	@Override
	public Object getEntity(int id) {
		return ppuDao.getComandMZ(id);
	}

}
