package com.PPU.DB.workLogic;

import com.PPU.DB.tables.CorrectionsMZ;
import com.PPU.DB.tables.TypeMU;

import java.util.List;

/**
 * Created by user on 23.04.14.
 */
public class WorkWithTypeMU extends WorkWithTable {

	public static String COLUMN_ID = "Id";
	public static String COLUMN_ID_PARAMETR = "Name";
	public static String COLUMN_VALUE = "Description";


	public WorkWithTypeMU()
	{
		super();
	}

	@Override
	public List<TypeMU> findAndGetAllRow(String fields, String fieldValue)
	{
		return ppuDao.findTypeMu(fields, fieldValue);
	}

	@Override
	public Object getColumnValue(Object obj, String columnName) throws IllegalAccessException {
		if (obj == null)
		{
			throw new IllegalAccessException("Не было передано параметра в TypeMU");
		}
		else
			return ClassInvokeCall.callMethod(obj, "get"+columnName);
	}

	@Override
	public Object setColumnValueFromList(Object obj, String columnName, Object ... listValue) throws IllegalAccessException {
		if (obj == null)
		{
			throw new IllegalAccessException("Не было передано параметра в TypeMU");
		}
		else
			return ClassInvokeCall.callMethod(obj, "set"+columnName, listValue);
	}

	@Override
	public void addEntity(Object obj) throws Exception {
		if (obj instanceof TypeMU)
			ppuDao.saveTypeMu((TypeMU) obj);
		else
			throw new Exception("В метод ResourcesMZ.addEntity передан неверный параметр");
	}

	@Override
	public void changeEntity(Object obj) throws Exception {
		if (obj instanceof TypeMU)
			ppuDao.updateTypeMu((TypeMU) obj);
		else
			throw new Exception("В метод ResourcesMZ.addEntity передан неверный параметр");
	}

	@Override
	public void deleteEntity(Object obj) throws Exception {
		if (obj instanceof TypeMU)
			ppuDao.deleteTypeMu((TypeMU) obj);
		else
			throw new Exception("В метод TypeMU.addEntity передан неверный параметр");
	}

	@Override
	public Object getEntity(int id) {
		return ppuDao.getTypeMu(id);
	}

	@Override
	public List getListRows() {
		return findAndGetAllRow("", "");
	}

	@Override
	public Object getEmptyEntity() {
		return new TypeMU();
	}
}
