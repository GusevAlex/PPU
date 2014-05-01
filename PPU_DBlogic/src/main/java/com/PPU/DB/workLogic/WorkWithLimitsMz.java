package com.PPU.DB.workLogic;

import com.PPU.DB.tables.CorrectionsMZ;
import com.PPU.DB.tables.LimitsMZ;

import java.util.List;
import java.util.Set;

/**
 * Created by user on 23.04.14.
 */
public class WorkWithLimitsMz extends WorkWithTable {

	public static String COLUMN_ID = "Id";
	public static String COLUMN_ID_PARAMETR = "IdParametr";
	public static String COLUMN_VALUE = "Value";
	public static String COLUMN_ID_MZ = "IdMZ";

	public WorkWithLimitsMz()
	{
		super();
	}

	@Override
	public Set<LimitsMZ> findAndGetAllRow(String fields, String fieldValue)
	{
		return ppuDao.findLimitsMz(fields, fieldValue);
	}

	@Override
	public Object getColumnValue(Object obj, String columnName) throws IllegalAccessException {
		if (obj == null)
		{
			throw new IllegalAccessException("�� ���� �������� ��������� � LimitsMZ");
		}
		else
			return ClassInvokeCall.callMethod(obj, "get"+columnName);
	}

	@Override
	public Object setColumnValueFromList(Object obj, String columnName, Object ... listValue) throws IllegalAccessException {
		if (obj == null)
		{
			throw new IllegalAccessException("�� ���� �������� ��������� � LimitsMZ");
		}
		else
			return ClassInvokeCall.callMethod(obj, "set"+columnName, listValue);
	}

	@Override
	public void addEntity(Object obj) throws Exception {
		if (obj instanceof LimitsMZ)
			ppuDao.saveLimitsMz((LimitsMZ) obj);
		else
			throw new Exception("� ����� DefaultParametrsServiceMz.addEntity ������� �������� ��������");
	}

	@Override
	public void changeEntity(Object obj) throws Exception {
		if (obj instanceof LimitsMZ)
			ppuDao.updateLimitsMz((LimitsMZ) obj);
		else
			throw new Exception("� ����� LimitsMZ.addEntity ������� �������� ��������");
	}

	@Override
	public void deleteEntity(Object obj) throws Exception {
		if (obj instanceof LimitsMZ)
			ppuDao.deleteLimitsMz((LimitsMZ) obj);
		else
			throw new Exception("� ����� LimitsMZ.addEntity ������� �������� ��������");
	}

	@Override
	public Object getEntity(int id) {
		return ppuDao.getLimitsMz(id);
	}

	@Override
	public List getListRows() {
		return findAndGetAllRow("", "");
	}

	@Override
	public Object getEmptyEntity() {
		return new LimitsMZ();
	}
}
