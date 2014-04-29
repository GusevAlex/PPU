package com.PPU.DB.workLogic;

import com.PPU.DB.tables.CorrectionsMZ;
import com.PPU.DB.tables.PartnerCommercialMan;
import com.PPU.DB.tables.Project;

import java.util.Date;
import java.util.List;

/**
 * Created by Alex on 21.04.2014.
 */
public class WorkWithProject extends WorkWithTable  {

	private static String COLUMN_ID = "Id";
	private static String COLUMN_ID_PROGRAM = "Id_program";
	private static String COLUMN_NAME = "Name";
	private static String COLUMN_ID_CUSTOMER = "Id_customer";
	private static String COLUMN_ID_LEADER = "Id_leader";
	private static String COLUMN_START_DATE = "Start_date";
	private static String COLUMN_EXPIREATION_DATE = "Expiration_date";
	private static String COLUMN_DESCRIPTION = "Description";
	private static String COLUMN_BUDGET = "Budget";
	private static String COLUMN_STATUS = "Status";

	@Override
	public List getListRows() {
		return ppuDao.findProject("", "");
	}

	@Override
	public List findAndGetAllRow(String fields, String fieldValue) {
		return ppuDao.findProject(fields, fieldValue);
	}

	@Override
	public Object getColumnValue(Object obj, String columnName) throws IllegalAccessException {
		if (obj == null)
		{
			throw new IllegalAccessException("Не было передано параметра в partnersCommercial");
		}
		else
			return ClassInvokeCall.callMethod(obj, "get"+columnName);
	}

	@Override
	public Object setColumnValueFromList(Object obj, String columnName, Object ... listValue) throws IllegalAccessException {
		if (obj == null)
		{
			throw new IllegalAccessException("Не было передано параметра в userMunCommercial");
		}
		else
			return ClassInvokeCall.callMethod(obj, "set"+columnName, listValue);
	}

	@Override
	public void addEntity(Object obj) throws Exception {
		if (obj instanceof Project)
			ppuDao.saveProject((Project) obj);
		else
			throw new Exception("В метод Project.addEntity передан неверный параметр");
	}

	@Override
	public void changeEntity(Object obj) throws Exception {
		if (obj instanceof Project)
			ppuDao.updateProject((Project) obj);
		else
			throw new Exception("В метод Project.addEntity передан неверный параметр");
	}

	@Override
	public void deleteEntity(Object obj) throws Exception {
		if (obj instanceof Project)
			ppuDao.deleteProject((Project) obj);
		else
			throw new Exception("В метод Project.addEntity передан неверный параметр");
	}

	@Override
	public Object getEntity(int id) {
		return ppuDao.getProject(id);
	}

	@Override
	public Object getEmptyEntity() {
		return new Project();
	}
}
