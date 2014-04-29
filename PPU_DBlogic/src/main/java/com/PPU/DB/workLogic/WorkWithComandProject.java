package com.PPU.DB.workLogic;

import com.PPU.DB.tables.ComandProject;
import com.PPU.DB.tables.CorrectionsMZ;
import com.PPU.DB.tables.CorrectionsProject;

import java.util.List;

/**
 * Created by user on 22.04.14.
 */
public class WorkWithComandProject extends WorkWithTable {
	private static String COLUMN_ID = "Id";
	private static String COLUMN_ID_MZ = "IdMZ";
	private static String COLUMN_ID_PARTNER_MZ = "IdPartnerMZ";

	@Override
	public List getListRows() {
		return ppuDao.findComandProject("", "");
	}

	@Override
	public List findAndGetAllRow(String fields, String fieldValue) {
		return ppuDao.findComandProject(fields, fieldValue);
	}

	@Override
	public Object getColumnValue(Object obj, String columnName) throws IllegalAccessException {
		if (obj == null)
		{
			throw new IllegalAccessException("Не было передано параметра в ComandProject");
		}
		else
			return ClassInvokeCall.callMethod(obj, "get"+columnName);
	}

	@Override
	public Object setColumnValueFromList(Object obj, String columnName, Object ... listValue) throws IllegalAccessException {
		if (obj == null)
		{
			throw new IllegalAccessException("Не было передано параметра в ComandProject");
		}
		else
			return ClassInvokeCall.callMethod(obj, "set"+columnName, listValue);
	}

	@Override
	public void addEntity(Object obj) throws Exception {
		if (obj instanceof ComandProject)
			ppuDao.saveComandProject((ComandProject) obj);
		else
			throw new Exception("В метод ComandProject.addEntity передан неверный параметр");
	}

	@Override
	public void changeEntity(Object obj) throws Exception {
		if (obj instanceof ComandProject)
			ppuDao.updateComandProject((ComandProject) obj);
		else
			throw new Exception("В метод ComandProject.addEntity передан неверный параметр");
	}

	@Override
	public void deleteEntity(Object obj) throws Exception {
		if (obj instanceof ComandProject)
			ppuDao.deleteComandProject((ComandProject) obj);
		else
			throw new Exception("В метод ComandProject.addEntity передан неверный параметр");
	}

	@Override
	public Object getEntity(int id) {
		return ppuDao.getComandProject(id);
	}

	@Override
	public Object getEmptyEntity() {
		return new ComandProject();
	}

}
