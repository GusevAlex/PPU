package com.PPU.DB.workLogic;

import com.PPU.DB.tables.CorrectionsMZ;
import com.PPU.DB.tables.LimitsMZ;
import com.PPU.DB.tables.LimitsProject;

import java.util.List;
import java.util.Set;

/**
 * Created by user on 23.04.14.
 */
public class WorkWithLimitsProject extends WorkWithTable {

	public static String COLUMN_ID = "Id";
	public static String COLUMN_ID_PARAMETR = "IdParametr";
	public static String COLUMN_VALUE = "Value";
	public static String COLUMN_ID_PROJECT = "IdProject";

	public WorkWithLimitsProject()
	{
		super();
	}

	@Override
	public Set<LimitsProject> findAndGetAllRow(String fields, String fieldValue)
	{
		return ppuDao.findLimitsProject(fields, fieldValue);
	}

	@Override
	public Object getColumnValue(Object obj, String columnName) throws IllegalAccessException {
		if (obj == null)
		{
			throw new IllegalAccessException("�� ���� �������� ��������� � LimitsProject");
		}
		else
			return ClassInvokeCall.callMethod(obj, "get"+columnName);
	}

	@Override
	public Object setColumnValueFromList(Object obj, String columnName, Object ... listValue) throws IllegalAccessException {
		if (obj == null)
		{
			throw new IllegalAccessException("�� ���� �������� ��������� � LimitsProject");
		}
		else
			return ClassInvokeCall.callMethod(obj, "set"+columnName, listValue);
	}

	@Override
	public void addEntity(Object obj) throws Exception {
		if (obj instanceof LimitsProject)
			ppuDao.saveLimitsProject((LimitsProject) obj);
		else
			throw new Exception("� ����� LimitsProject.addEntity ������� �������� ��������");
	}

	@Override
	public void changeEntity(Object obj) throws Exception {
		if (obj instanceof LimitsProject)
			ppuDao.updateLimitsProject((LimitsProject) obj);
		else
			throw new Exception("� ����� LimitsProject.addEntity ������� �������� ��������");
	}

	@Override
	public void deleteEntity(Object obj) throws Exception {
		if (obj instanceof LimitsProject)
			ppuDao.deleteLimitsProject((LimitsProject) obj);
		else
			throw new Exception("� ����� LimitsProject.addEntity ������� �������� ��������");
	}

	@Override
	public Object getEntity(int id) {
		return ppuDao.getLimitsProject(id);
	}

	@Override
	public List getListRows() {
		return findAndGetAllRow("", "");
	}

	@Override
	public Object getEmptyEntity() {
		return new LimitsProject();
	}

}
