package com.PPU.DB.workLogic;

import com.PPU.DB.tables.CorrectionsMZ;
import com.PPU.DB.tables.MZ;
import com.PPU.DB.tables.Parametrs;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by user on 23.04.14.
 */
public class WorkCorrectionMZ extends WorkWithTable {

	public static String COLUMN_ID = "Id";
	public static String COLUMN_ID_PROJECT = "IdProject";
	public static String COLUMN_CORRECTION_DATE = "CorrectionDate";
	public static String COLUMN_PARAMETR = "IdParametr";
	public static String COLUMN_VALUE_BEFORE = "ValueBefore";
	public static String COLUMN_VALUE_AFTER = "ValueAfter";

	public WorkCorrectionMZ()
	{
		super();
	}

	@Override
	public Set<CorrectionsMZ> findAndGetAllRow(String fields, String fieldValue)
	{
		return ppuDao.findCorrectionsMz(fields, fieldValue);
	}

	@Override
	public Object getColumnValue(Object obj, String columnName) throws IllegalAccessException {
		if (obj == null)
		{
			throw new IllegalAccessException("�� ���� �������� ��������� � CorrectionsMZ");
		}
		else
			return ClassInvokeCall.callMethod(obj, "get"+columnName);
	}

	@Override
	public Object setColumnValueFromList(Object obj, String columnName, Object ... listValue) throws IllegalAccessException {
		if (obj == null)
		{
			throw new IllegalAccessException("�� ���� �������� ��������� � CorrectionsMZ");
		}
		else
			return ClassInvokeCall.callMethod(obj, "set"+columnName, listValue);
	}

	@Override
	public void addEntity(Object obj) throws Exception {
		if (obj instanceof CorrectionsMZ)
			ppuDao.saveCorrectionsMz((CorrectionsMZ) obj);
		else
			throw new Exception("� ����� CorrectionsMZ.addEntity ������� �������� ��������");
	}

	@Override
	public void changeEntity(Object obj) throws Exception {
		if (obj instanceof CorrectionsMZ)
			ppuDao.updateCorrectionsMz((CorrectionsMZ) obj);
		else
			throw new Exception("� ����� CorrectionsMZ.addEntity ������� �������� ��������");
	}

	@Override
	public void deleteEntity(Object obj) throws Exception {
		if (obj instanceof CorrectionsMZ)
			ppuDao.deleteCorrectionsMz((CorrectionsMZ) obj);
		else
			throw new Exception("� ����� CorrectionsMZ.addEntity ������� �������� ��������");
	}

	@Override
	public Object getEntity(int id) {
		return ppuDao.getCorrectionsMz(id);
	}

	@Override
	public List getListRows() {
		return findAndGetAllRow("", "");
	}

	@Override
	public Object getEmptyEntity() {
		return new CorrectionsMZ();
	}
}
