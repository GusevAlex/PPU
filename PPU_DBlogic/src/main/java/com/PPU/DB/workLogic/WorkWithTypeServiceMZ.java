package com.PPU.DB.workLogic;

import com.PPU.DB.tables.CorrectionsMZ;
import com.PPU.DB.tables.TypeServiceMZ;

import java.util.List;
import java.util.Set;

/**
 * Created by user on 23.04.14.
 */
public class WorkWithTypeServiceMZ extends WorkWithTable {

	public static String COLUMN_ID = "Id";
	public static String COLUMN_ID_PARAMETR = "Name";

	public WorkWithTypeServiceMZ()
	{
		super();
	}

	@Override
	public Set<TypeServiceMZ> findAndGetAllRow(String fields, String fieldValue)
	{
		return ppuDao.findTypeServiceMz(fields, fieldValue);
	}

	@Override
	public Object getColumnValue(Object obj, String columnName) throws IllegalAccessException {
		if (obj == null)
		{
			throw new IllegalAccessException("�� ���� �������� ��������� � TypeServiceMZ");
		}
		else
			return ClassInvokeCall.callMethod(obj, "get"+columnName);
	}

	@Override
	public Object setColumnValueFromList(Object obj, String columnName, Object ... listValue) throws IllegalAccessException {
		if (obj == null)
		{
			throw new IllegalAccessException("�� ���� �������� ��������� � TypeServiceMZ");
		}
		else
			return ClassInvokeCall.callMethod(obj, "set"+columnName, listValue);
	}

	@Override
	public void addEntity(Object obj) throws Exception {
		if (obj instanceof TypeServiceMZ)
			ppuDao.saveTypeServiceMz((TypeServiceMZ) obj);
		else
			throw new Exception("� ����� ResourcesMZ.addEntity ������� �������� ��������");
	}

	@Override
	public void changeEntity(Object obj) throws Exception {
		if (obj instanceof TypeServiceMZ)
			ppuDao.updateTypeServiceMz((TypeServiceMZ) obj);
		else
			throw new Exception("� ����� ResourcesMZ.addEntity ������� �������� ��������");
	}

	@Override
	public void deleteEntity(Object obj) throws Exception {
		if (obj instanceof TypeServiceMZ)
			ppuDao.deleteTypeServiceMz((TypeServiceMZ) obj);
		else
			throw new Exception("� ����� TypeServiceMZ.addEntity ������� �������� ��������");
	}

	@Override
	public Object getEntity(int id) {
		return ppuDao.getTypeServiceMz(id);
	}

	@Override
	public List getListRows() {
		return findAndGetAllRow("", "");
	}

	@Override
	public Object getEmptyEntity() {
		return new TypeServiceMZ();
	}

}