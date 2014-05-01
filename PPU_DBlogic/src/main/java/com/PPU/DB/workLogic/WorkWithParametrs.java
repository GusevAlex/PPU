package com.PPU.DB.workLogic;

import com.PPU.DB.tables.CorrectionsMZ;
import com.PPU.DB.tables.LimitsProject;
import com.PPU.DB.tables.Parametrs;

import java.util.List;
import java.util.Set;

/**
 * Created by user on 23.04.14.
 */
public class WorkWithParametrs extends WorkWithTable {

	public static String COLUMN_ID = "Id";
	public static String COLUMN_NAME = "Name";
	public static String COLUMN_TYPE = "Type";

	public WorkWithParametrs()
	{
		super();
	}

	@Override
	public List<Parametrs> findAndGetAllRow(String fields, String fieldValue)
	{
		return ppuDao.findParametrs(fields, fieldValue);
	}

	@Override
	public Object getColumnValue(Object obj, String columnName) throws IllegalAccessException {
		if (obj == null)
		{
			throw new IllegalAccessException("Не было передано параметра в Parametrs");
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
		if (obj instanceof Parametrs)
			ppuDao.saveParametrs((Parametrs) obj);
		else
			throw new Exception("В метод Parametrs.addEntity передан неверный параметр");
	}

	@Override
	public void changeEntity(Object obj) throws Exception {
		if (obj instanceof Parametrs)
			ppuDao.updateParametrs((Parametrs) obj);
		else
			throw new Exception("В метод LimitsProject.addEntity передан неверный параметр");
	}

	@Override
	public void deleteEntity(Object obj) throws Exception {
		if (obj instanceof Parametrs)
			ppuDao.deleteParametrs((Parametrs) obj);
		else
			throw new Exception("В метод Parametrs.addEntity передан неверный параметр");
	}

	@Override
	public Object getEntity(int id) {
		return ppuDao.getParametrs(id);
	}

	@Override
	public List getListRows() {
		return findAndGetAllRow("", "");
	}

	@Override
	public Object getEmptyEntity() {
		return new Parametrs();
	}
}
