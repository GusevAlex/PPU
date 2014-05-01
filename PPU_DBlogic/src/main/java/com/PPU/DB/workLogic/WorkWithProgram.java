package com.PPU.DB.workLogic;

import com.PPU.DB.tables.CorrectionsMZ;
import com.PPU.DB.tables.Program;

import java.util.List;
import java.util.Set;

/**
 * Created by user on 22.04.14.
 */
public class WorkWithProgram extends WorkWithTable {

	private static String COLUMN_ID = "Id";
	private static String COLUMN_NAME = "Name";
	private static String COLUMN_TARGET = "Target";
	private static String COLUMN_DESCRIPTION = "Description";

	@Override
	public List getListRows() {
		return ppuDao.findProgram("", "");
	}

	@Override
	public List findAndGetAllRow(String fields, String fieldValue) {
		return ppuDao.findProgram(fields, fieldValue);
	}

	@Override
	public Object getColumnValue(Object obj, String columnName) throws IllegalAccessException {
		if (obj == null)
		{
			throw new IllegalAccessException("�� ���� �������� ��������� � Program");
		}
		else
			return ClassInvokeCall.callMethod(obj, "get"+columnName);
	}

	@Override
	public Object setColumnValueFromList(Object obj, String columnName, Object ... listValue) throws IllegalAccessException {
		if (obj == null)
		{
			throw new IllegalAccessException("�� ���� �������� ��������� � Program");
		}
		else
			return ClassInvokeCall.callMethod(obj, "set"+columnName, listValue);
	}

	@Override
	public void addEntity(Object obj) throws Exception {
		if (obj instanceof Program)
			ppuDao.saveProgram((Program) obj);
		else
			throw new Exception("� ����� Program.addEntity ������� �������� ��������");
	}

	@Override
	public void changeEntity(Object obj) throws Exception {
		if (obj instanceof Program)
			ppuDao.updateProgram((Program) obj);
		else
			throw new Exception("� ����� Program.addEntity ������� �������� ��������");
	}

	@Override
	public void deleteEntity(Object obj) throws Exception {
		if (obj instanceof Program)
			ppuDao.deleteProgram((Program) obj);
		else
			throw new Exception("� ����� Program.addEntity ������� �������� ��������");
	}

	@Override
	public Object getEntity(int id) {
		return ppuDao.getProgram(id);
	}

	@Override
	public Object getEmptyEntity() {
		return new Program();
	}
}
