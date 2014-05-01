package com.PPU.DB.workLogic;

import com.PPU.DB.tables.CorrectionsMZ;
import com.PPU.DB.tables.CorrectionsProject;

import java.util.Set;

/**
 * Created by user on 23.04.14.
 */
public class WorkWithCorrectionProject extends WorkWithTable {


public static String COLUMN_ID = "Id";
public static String COLUMN_ID_MZ = "IdMZ";
public static String COLUMN_CORRECTION_DATE = "CorrectionDate";
public static String COLUMN_PARAMETR = "IdParametr";
public static String COLUMN_VALUE_BEFORE = "ValueBefore";
public static String COLUMN_VALUE_AFTER = "ValueAfter";

	public WorkWithCorrectionProject()
	{
		super();
	}

	@Override
	public Set<CorrectionsProject> findAndGetAllRow(String fields, String fieldValue)
	{
		return ppuDao.findCorrectionsProject(fields, fieldValue);
	}

	@Override
	public Object getColumnValue(Object obj, String columnName) throws IllegalAccessException {
		if (obj == null)
		{
			throw new IllegalAccessException("�� ���� �������� ��������� � CorrectionsProject");
		}
		else
			return ClassInvokeCall.callMethod(obj, "get"+columnName);
	}

	@Override
	public Object setColumnValueFromList(Object obj, String columnName, Object ... listValue) throws IllegalAccessException {
		if (obj == null)
		{
			throw new IllegalAccessException("�� ���� �������� ��������� � CorrectionsProject");
		}
		else
			return ClassInvokeCall.callMethod(obj, "set"+columnName, listValue);
	}

	@Override
	public void addEntity(Object obj) throws Exception {
		if (obj instanceof CorrectionsProject)
			ppuDao.saveCorrectionsProject((CorrectionsProject) obj);
		else
			throw new Exception("� ����� CorrectionsProject.addEntity ������� �������� ��������");
	}

	@Override
	public void changeEntity(Object obj) throws Exception {
		if (obj instanceof CorrectionsProject)
			ppuDao.updateCorrectionsProject((CorrectionsProject) obj);
		else
			throw new Exception("� ����� CorrectionsProject.addEntity ������� �������� ��������");
	}

	@Override
	public void deleteEntity(Object obj) throws Exception {
		if (obj instanceof CorrectionsProject)
			ppuDao.deleteCorrectionsProject((CorrectionsProject) obj);
		else
			throw new Exception("� ����� CorrectionsProject.addEntity ������� �������� ��������");
	}

	@Override
	public Object getEntity(int id) {
		return ppuDao.getCorrectionsProject(id);
	}

	@Override
	public List getListRows() {
		return findAndGetAllRow("", "");
	}

	@Override
	public Object getEmptyEntity() {
		return new CorrectionsProject();
	}

}
