package com.PPU.DB.workLogic;

import com.PPU.DB.tables.CorrectionsMZ;
import com.PPU.DB.tables.CorrectionsProject;
import com.PPU.DB.tables.DefaultParametrsServiceMZ;

import java.util.List;
import java.util.Set;

/**
 * Created by user on 23.04.14.
 */
public class WorkWithDefaultParametrsServiceMZ extends WorkWithTable {

	public static String COLUMN_ID = "Id";
	public static String COLUMN_ID_MZ = "IdMZ";
	public static String COLUMN_CORRECTION_DATE = "CorrectionDate";
	public static String COLUMN_PARAMETR = "IdParametr";
	public static String COLUMN_VALUE_BEFORE = "ValueBefore";
	public static String COLUMN_VALUE_AFTER = "ValueAfter";

	public WorkWithDefaultParametrsServiceMZ()
	{
		super();
	}

	@Override
	public Set<DefaultParametrsServiceMZ> findAndGetAllRow(String fields, String fieldValue)
	{
		return ppuDao.findDefaultParametrsServiceMz(fields, fieldValue);
	}

	@Override
	public Object getColumnValue(Object obj, String columnName) throws IllegalAccessException {
		if (obj == null)
		{
			throw new IllegalAccessException("�� ���� �������� ��������� � DefaultParametrsServiceMz");
		}
		else
			return ClassInvokeCall.callMethod(obj, "get"+columnName);
	}

	@Override
	public Object setColumnValueFromList(Object obj, String columnName, Object ... listValue) throws IllegalAccessException {
		if (obj == null)
		{
			throw new IllegalAccessException("�� ���� �������� ��������� � DefaultParametrsServiceMz");
		}
		else
			return ClassInvokeCall.callMethod(obj, "set"+columnName, listValue);
	}

	@Override
	public void addEntity(Object obj) throws Exception {
		if (obj instanceof DefaultParametrsServiceMZ)
			ppuDao.saveDefaultParametrsServiceMz((DefaultParametrsServiceMZ) obj);
		else
			throw new Exception("� ����� DefaultParametrsServiceMz.addEntity ������� �������� ��������");
	}

	@Override
	public void changeEntity(Object obj) throws Exception {
		if (obj instanceof DefaultParametrsServiceMZ)
			ppuDao.updateDefaultParametrsServiceMz((DefaultParametrsServiceMZ) obj);
		else
			throw new Exception("� ����� DefaultParametrsServiceMz.addEntity ������� �������� ��������");
	}

	@Override
	public void deleteEntity(Object obj) throws Exception {
		if (obj instanceof DefaultParametrsServiceMZ)
			ppuDao.deleteDefaultParametrsServiceMz((DefaultParametrsServiceMZ) obj);
		else
			throw new Exception("� ����� DefaultParametrsServiceMz.addEntity ������� �������� ��������");
	}

	@Override
	public Object getEntity(int id) {
		return ppuDao.getDefaultParametrsServiceMz(id);
	}

	@Override
	public List getListRows() {
		return findAndGetAllRow("", "");
	}

	@Override
	public Object getEmptyEntity() {
		return new DefaultParametrsServiceMZ();
	}

}
