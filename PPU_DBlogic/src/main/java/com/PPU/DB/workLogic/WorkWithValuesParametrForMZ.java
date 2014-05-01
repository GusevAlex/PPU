package com.PPU.DB.workLogic;

import com.PPU.DB.tables.CorrectionsMZ;
import com.PPU.DB.tables.ValuesParametrForMZ;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by user on 23.04.14.
 */
public class WorkWithValuesParametrForMZ extends WorkWithTable {

	public static String COLUMN_ID = "Id";
	public static String COLUMN_ID_PARAMETR = "IdParametr";
	public static String COLUMN_ID_VALUE = "Value";
	public static String COLUMN_DATE_REC_VALUE = "DateRecValue";
	public static String COLUMN_ID_MZ = "IdMZ";


	public WorkWithValuesParametrForMZ()
	{
		super();
	}

	@Override
	public Set<ValuesParametrForMZ> findAndGetAllRow(String fields, String fieldValue)
	{
		return ppuDao.findValuesParametrForMz(fields, fieldValue);
	}

	@Override
	public Object getColumnValue(Object obj, String columnName) throws IllegalAccessException {
		if (obj == null)
		{
			throw new IllegalAccessException("�� ���� �������� ��������� � ValuesParametrForMZ");
		}
		else
			return ClassInvokeCall.callMethod(obj, "get"+columnName);
	}

	@Override
	public Object setColumnValueFromList(Object obj, String columnName, Object ... listValue) throws IllegalAccessException {
		if (obj == null)
		{
			throw new IllegalAccessException("�� ���� �������� ��������� � ValuesParametrForMZ");
		}
		else
			return ClassInvokeCall.callMethod(obj, "set"+columnName, listValue);
	}

	@Override
	public void addEntity(Object obj) throws Exception {
		if (obj instanceof ValuesParametrForMZ)
			ppuDao.saveValuesParametrForMz((ValuesParametrForMZ) obj);
		else
			throw new Exception("� ����� ValuesParametrForMZ.addEntity ������� �������� ��������");
	}

	@Override
	public void changeEntity(Object obj) throws Exception {
		if (obj instanceof ValuesParametrForMZ)
			ppuDao.updateValuesParametrForMz((ValuesParametrForMZ) obj);
		else
			throw new Exception("� ����� ValuesParametrForMZ.addEntity ������� �������� ��������");
	}

	@Override
	public void deleteEntity(Object obj) throws Exception {
		if (obj instanceof ValuesParametrForMZ)
			ppuDao.deleteValuesParametrForMz((ValuesParametrForMZ) obj);
		else
			throw new Exception("� ����� ValuesParametrForMZ.addEntity ������� �������� ��������");
	}

	@Override
	public Object getEntity(int id) {
		return ppuDao.getValuesParametrForMz(id);
	}

	@Override
	public List getListRows() {
		return findAndGetAllRow("", "");
	}

	@Override
	public Object getEmptyEntity() {
		return new ValuesParametrForMZ();
	}

}
