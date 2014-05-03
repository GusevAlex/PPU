package com.PPU.DB.workLogic;

import com.PPU.DB.tables.ProgramCommerc;
import com.PPU.DB.tables.ProgramMZ;

import java.util.List;

/**
 * Created by user on 22.04.14.
 */
public class WorkWithProgramCommerc extends WorkWithTable {

	private static String COLUMN_ID = "Id";
	private static String COLUMN_NAME = "Name";
	private static String COLUMN_TARGET = "Target";
	private static String COLUMN_DESCRIPTION = "Description";
    private static String COLUMN_ID_PARTNER = "IdPartnerCommerc";

	@Override
	public List getListRows() {
		return ppuDao.findProgramCommerc("", "");
	}

	@Override
	public List findAndGetAllRow(String fields, String fieldValue) {
		return ppuDao.findProgramCommerc(fields, fieldValue);
	}

	@Override
	public Object getColumnValue(Object obj, String columnName) throws IllegalAccessException {
		if (obj == null)
		{
            throw new IllegalAccessException("Не было передано параметра в getColumnValue");
		}
		else
			return ClassInvokeCall.callMethod(obj, "get"+columnName);
	}

	@Override
	public Object setColumnValueFromList(Object obj, String columnName, Object ... listValue) throws IllegalAccessException {
		if (obj == null)
		{
            throw new IllegalAccessException("Не было передано параметра в programCommerc");
		}
		else
			return ClassInvokeCall.callMethod(obj, "set"+columnName, listValue);
	}

	@Override
	public void addEntity(Object obj) throws Exception {
		if (obj instanceof ProgramCommerc)
			ppuDao.saveProgramCommerc((ProgramCommerc) obj);
		else
            throw new Exception("В метод addEntity передан неверный параметр");
	}

	@Override
	public void changeEntity(Object obj) throws Exception {
		if (obj instanceof ProgramCommerc)
			ppuDao.updateProgramCommerc((ProgramCommerc) obj);
		else
            throw new Exception("В метод changeEntity передан неверный параметр");
	}

	@Override
	public void deleteEntity(Object obj) throws Exception {
		if (obj instanceof ProgramCommerc)
			ppuDao.deleteProgramCommerc((ProgramCommerc) obj);
		else
            throw new Exception("В метод deleteEntity передан неверный параметр");
	}

	@Override
	public Object getEntity(int id) {
		return ppuDao.getProgramCommerc(id);
	}

	@Override
	public Object getEmptyEntity() {
		return new ProgramCommerc();
	}
}
