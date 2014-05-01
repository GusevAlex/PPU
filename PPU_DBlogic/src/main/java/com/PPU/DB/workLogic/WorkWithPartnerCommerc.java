package com.PPU.DB.workLogic;

import com.PPU.DB.tables.CorrectionsMZ;
import com.PPU.DB.tables.PartnerCommercialMan;

import java.util.List;
import java.util.Set;

/**
 * Created by user on 22.04.14.
 */
public class WorkWithPartnerCommerc extends WorkWithTable {

	private static String COLUMN_ID = "Id";
	private static String COLUMN_NAME = "Name";
	private static String COLUMN_ADDRESS = "Address";
	private static String COLUMN_DESCRIPTION = "Description";


	public WorkWithPartnerCommerc()
	{
		super();
	}

	@Override
	public List getListRows() {
		return ppuDao.findPartnerCommercialMan("", "");
	}

	@Override
	public List findAndGetAllRow(String fields, String fieldValue) {
		return ppuDao.findPartnerCommercialMan(fields, fieldValue);
	}

	@Override
	public Object getColumnValue(Object obj, String columnName) throws IllegalAccessException {
		if (obj == null)
		{
			throw new IllegalAccessException("�� ���� �������� ��������� � partnersCommercial");
		}
		else
			return ClassInvokeCall.callMethod(obj, "get"+columnName);
	}

	@Override
	public Object setColumnValueFromList(Object obj, String columnName, Object ... listValue) throws IllegalAccessException {
		if (obj == null)
		{
			throw new IllegalAccessException("�� ���� �������� ��������� � userMunCommercial");
		}
		else
			return ClassInvokeCall.callMethod(obj, "set"+columnName, listValue);
	}

	@Override
	public void addEntity(Object obj) throws Exception {
		if (obj instanceof PartnerCommercialMan)
			ppuDao.savePartnerCommercialMan((PartnerCommercialMan) obj);
		else
			throw new Exception("� ����� PartnerCommercialMan.addEntity ������� �������� ��������");
	}

	@Override
	public void changeEntity(Object obj) throws Exception {
		if (obj instanceof PartnerCommercialMan)
			ppuDao.updatePartnerCommercialMan((PartnerCommercialMan) obj);
		else
			throw new Exception("� ����� PartnerCommercialMan.addEntity ������� �������� ��������");
	}

	@Override
	public void deleteEntity(Object obj) throws Exception {
		if (obj instanceof PartnerCommercialMan)
			ppuDao.deletePartnerCommercialMan((PartnerCommercialMan) obj);
		else
			throw new Exception("� ����� PartnerCommercialMan.addEntity ������� �������� ��������");
	}

	@Override
	public Object getEntity(int id) {
		return ppuDao.getComandProject(id);
	}

	@Override
	public Object getEmptyEntity() {
		return new PartnerCommercialMan();
	}

}
