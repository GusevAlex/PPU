package com.PPU.DB.workLogic;


import com.PPU.DB.tables.CorrectionsMZ;
import com.PPU.DB.tables.ResourcesMZ;

import java.util.List;

/**
 * Created by user on 23.04.14.
 */
public class WorkWithResourcesMZ extends WorkWithTable {

	public static String COLUMN_ID = "Id";
	public static String COLUMN_ID_MZ = "IdMZ";
	public static String COLUMN_ID_PROVEDER_RESOURCES = "IdProviderResources";


	public WorkWithResourcesMZ()
	{
		super();
	}

	@Override
	public List<ResourcesMZ> findAndGetAllRow(String fields, String fieldValue)
	{
		return ppuDao.findResourcesMz(fields, fieldValue);
	}

	@Override
	public Object getColumnValue(Object obj, String columnName) throws IllegalAccessException {
		if (obj == null)
		{
			throw new IllegalAccessException("Не было передано параметра в ResourcesMZ");
		}
		else
			return ClassInvokeCall.callMethod(obj, "get"+columnName);
	}

	@Override
	public Object setColumnValueFromList(Object obj, String columnName, Object ... listValue) throws IllegalAccessException {
		if (obj == null)
		{
			throw new IllegalAccessException("Не было передано параметра в ResourcesMZ");
		}
		else
			return ClassInvokeCall.callMethod(obj, "set"+columnName, listValue);
	}

	@Override
	public void addEntity(Object obj) throws Exception {
		if (obj instanceof ResourcesMZ)
			ppuDao.saveResourcesMz((ResourcesMZ) obj);
		else
			throw new Exception("В метод ResourcesMZ.addEntity передан неверный параметр");
	}

	@Override
	public void changeEntity(Object obj) throws Exception {
		if (obj instanceof ResourcesMZ)
			ppuDao.updateResourcesMz((ResourcesMZ) obj);
		else
			throw new Exception("В метод ResourcesMZ.addEntity передан неверный параметр");
	}

	@Override
	public void deleteEntity(Object obj) throws Exception {
		if (obj instanceof ResourcesMZ)
			ppuDao.deleteResourcesMz((ResourcesMZ) obj);
		else
			throw new Exception("В метод ResourcesMZ.addEntity передан неверный параметр");
	}

	@Override
	public Object getEntity(int id) {
		return ppuDao.getResourcesMz(id);
	}

	@Override
	public List getListRows() {
		return findAndGetAllRow("", "");
	}

	@Override
	public Object getEmptyEntity() {
		return new ResourcesMZ();
	}

}
