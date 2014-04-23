package com.PPU.DB.workLogic;

import com.PPU.DB.tables.Providers;

import java.util.List;

/**
 * Created by user on 23.04.14.
 */
public class WorkWithProviders extends WorkWithTable {
	public static String COLUMN_ID = "Id";
	public static String COLUMN_ID_PARAMETR = "Name";
	public static String COLUMN_VALUE = "Description";
	public static String COLUMN_ID_PROJECT = "Address";

	public WorkWithProviders()
	{
		super();
	}

	@Override
	public List<Providers> findAndGetAllRow(String fields, String fieldValue)
	{
		return ppuDao.findProviders(fields, fieldValue);
	}

	@Override
	public Object getColumnValue(Object obj, String columnName) throws IllegalAccessException {
		if (obj == null)
		{
			throw new IllegalAccessException("Не было передано параметра в Providers");
		}
		else
			return ClassInvokeCall.callMethod(obj, "get"+columnName);
	}

	@Override
	public Object setColumnValueFromList(Object obj, String columnName, Object ... listValue) throws IllegalAccessException {
		if (obj == null)
		{
			throw new IllegalAccessException("Не было передано параметра в Parametrs");
		}
		else
			return ClassInvokeCall.callMethod(obj, "set"+columnName, listValue);
	}

	@Override
	public void addEntity(Object obj) throws Exception {
		if (obj instanceof Providers)
			ppuDao.saveProviders((Providers) obj);
		else
			throw new Exception("В метод Providers.addEntity передан неверный параметр");
	}

	@Override
	public void changeEntity(Object obj) throws Exception {
		if (obj instanceof Providers)
			ppuDao.updateProviders((Providers) obj);
		else
			throw new Exception("В метод Providers.addEntity передан неверный параметр");
	}

	@Override
	public void deleteEntity(Object obj) throws Exception {
		if (obj instanceof Providers)
			ppuDao.deleteProviders((Providers) obj);
		else
			throw new Exception("В метод Providers.addEntity передан неверный параметр");
	}

	@Override
	public Object getEntity(int id) {
		return ppuDao.getProviders(id);
	}

	@Override
	public List getListRows() {
		return findAndGetAllRow("", "");
	}
}
