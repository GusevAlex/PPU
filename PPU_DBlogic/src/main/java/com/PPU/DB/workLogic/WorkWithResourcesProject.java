package com.PPU.DB.workLogic;


import com.PPU.DB.tables.CorrectionsMZ;
import com.PPU.DB.tables.ResourcesProject;

import java.util.List;

/**
 * Created by user on 23.04.14.
 */
public class WorkWithResourcesProject extends WorkWithTable {


	public static String COLUMN_ID = "Id";
	public static String COLUMN_ID_MZ = "IdMZ";
	public static String COLUMN_ID_PROVEDER_RESOURCES = "IdProviderResources";


	public WorkWithResourcesProject()
	{
		super();
	}

	@Override
	public List<ResourcesProject> findAndGetAllRow(String fields, String fieldValue)
	{
		return ppuDao.findResourcesProject(fields, fieldValue);
	}

	@Override
	public Object getColumnValue(Object obj, String columnName) throws IllegalAccessException {
		if (obj == null)
		{
			throw new IllegalAccessException("Не было передано параметра в ResourcesProject");
		}
		else
			return ClassInvokeCall.callMethod(obj, "get"+columnName);
	}

	@Override
	public Object setColumnValueFromList(Object obj, String columnName, Object ... listValue) throws IllegalAccessException {
		if (obj == null)
		{
			throw new IllegalAccessException("Не было передано параметра в ResourcesProject");
		}
		else
			return ClassInvokeCall.callMethod(obj, "set"+columnName, listValue);
	}

	@Override
	public void addEntity(Object obj) throws Exception {
		if (obj instanceof ResourcesProject)
			ppuDao.saveResourcesProject((ResourcesProject) obj);
		else
			throw new Exception("В метод ResourcesMZ.addEntity передан неверный параметр");
	}

	@Override
	public void changeEntity(Object obj) throws Exception {
		if (obj instanceof ResourcesProject)
			ppuDao.updateResourcesProject((ResourcesProject) obj);
		else
			throw new Exception("В метод ResourcesMZ.addEntity передан неверный параметр");
	}

	@Override
	public void deleteEntity(Object obj) throws Exception {
		if (obj instanceof ResourcesProject)
			ppuDao.deleteResourcesProject((ResourcesProject) obj);
		else
			throw new Exception("В метод ResourcesProject.addEntity передан неверный параметр");
	}

	@Override
	public Object getEntity(int id) {
		return ppuDao.getResourcesProject(id);
	}

	@Override
	public List getListRows() {
		return findAndGetAllRow("", "");
	}

	@Override
	public Object getEmptyEntity() {
		return new ResourcesProject();
	}
}
