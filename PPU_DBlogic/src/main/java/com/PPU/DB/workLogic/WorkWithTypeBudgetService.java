package com.PPU.DB.workLogic;


import com.PPU.DB.tables.CorrectionsMZ;
import com.PPU.DB.tables.TypeBudgetService;

import java.util.List;
import java.util.Set;

/**
 * Created by user on 23.04.14.
 */
public class WorkWithTypeBudgetService extends WorkWithTable {

	public static String COLUMN_ID = "Id";
	public static String COLUMN_ID_PARAMETR = "Name";
	public static String COLUMN_VALUE = "Description";


	public WorkWithTypeBudgetService()
	{
		super();
	}

	@Override
	public Set<TypeBudgetService> findAndGetAllRow(String fields, String fieldValue)
	{
		return ppuDao.findTypeBudgetService(fields, fieldValue);
	}

	@Override
	public Object getColumnValue(Object obj, String columnName) throws IllegalAccessException {
		if (obj == null)
		{
			throw new IllegalAccessException("�� ���� �������� ��������� � TypeBudgetService");
		}
		else
			return ClassInvokeCall.callMethod(obj, "get"+columnName);
	}

	@Override
	public Object setColumnValueFromList(Object obj, String columnName, Object ... listValue) throws IllegalAccessException {
		if (obj == null)
		{
			throw new IllegalAccessException("�� ���� �������� ��������� � TypeBudgetService");
		}
		else
			return ClassInvokeCall.callMethod(obj, "set"+columnName, listValue);
	}

	@Override
	public void addEntity(Object obj) throws Exception {
		if (obj instanceof TypeBudgetService)
			ppuDao.saveTypeBudgetService((TypeBudgetService) obj);
		else
			throw new Exception("� ����� ResourcesMZ.addEntity ������� �������� ��������");
	}

	@Override
	public void changeEntity(Object obj) throws Exception {
		if (obj instanceof TypeBudgetService)
			ppuDao.updateTypeBudgetService((TypeBudgetService) obj);
		else
			throw new Exception("� ����� ResourcesMZ.addEntity ������� �������� ��������");
	}

	@Override
	public void deleteEntity(Object obj) throws Exception {
		if (obj instanceof TypeBudgetService)
			ppuDao.deleteTypeBudgetService((TypeBudgetService) obj);
		else
			throw new Exception("� ����� TypeBudgetService.addEntity ������� �������� ��������");
	}

	@Override
	public Object getEntity(int id) {
		return ppuDao.getTypeBudgetService(id);
	}

	@Override
	public List getListRows() {
		return findAndGetAllRow("", "");
	}

	@Override
	public Object getEmptyEntity() {
		return new TypeBudgetService();
	}
}
