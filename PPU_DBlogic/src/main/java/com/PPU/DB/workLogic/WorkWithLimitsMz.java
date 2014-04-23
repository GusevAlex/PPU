package com.PPU.DB.workLogic;

import com.PPU.DB.tables.LimitsMZ;

import java.util.List;

/**
 * Created by user on 23.04.14.
 */
public class WorkWithLimitsMz extends WorkWithTable {

	public static String COLUMN_ID = "Id";
	public static String COLUMN_ID_PARAMETR = "IdParametr";
	public static String COLUMN_VALUE = "Value";
	public static String COLUMN_ID_MZ = "IdMZ";

	public WorkWithLimitsMz()
	{
		super();
	}

	@Override
	public List<LimitsMZ> findAndGetAllRow(String fields, String fieldValue)
	{
		return ppuDao.findLimitsMz(fields, fieldValue);
	}

	@Override
	public Object getColumnValue(Object obj, String columnName) throws IllegalAccessException {
		if (obj == null)
		{
			throw new IllegalAccessException("Не было передано параметра в LimitsMZ");
		}
		else
			return ClassInvokeCall.callMethod(obj, "get"+columnName);
	}

	@Override
	public Object setColumnValueFromList(Object obj, String columnName, Object ... listValue) throws IllegalAccessException {
		if (obj == null)
		{
			throw new IllegalAccessException("Не было передано параметра в LimitsMZ");
		}
		else
			return ClassInvokeCall.callMethod(obj, "set"+columnName, listValue);
	}

	@Override
	public void addEntity(Object obj) throws Exception {
		if (obj instanceof LimitsMZ)
			ppuDao.saveLimitsMz((LimitsMZ) obj);
		else
			throw new Exception("В метод DefaultParametrsServiceMz.addEntity передан неверный параметр");
	}

	@Override
	public void changeEntity(Object obj) throws Exception {
		if (obj instanceof LimitsMZ)
			ppuDao.updateLimitsMz((LimitsMZ) obj);
		else
			throw new Exception("В метод LimitsMZ.addEntity передан неверный параметр");
	}

	@Override
	public void deleteEntity(Object obj) throws Exception {
		if (obj instanceof LimitsMZ)
			ppuDao.deleteLimitsMz((LimitsMZ) obj);
		else
			throw new Exception("В метод LimitsMZ.addEntity передан неверный параметр");
	}

	@Override
	public Object getEntity(int id) {
		return ppuDao.getLimitsMz(id);
	}

	@Override
	public List getListRows() {
		return findAndGetAllRow("", "");
	}

}
