package com.PPU.DB.workLogic;

import com.PPU.DB.tables.MZ;
import com.PPU.DB.tables.PartnersMZ;
import com.PPU.DB.tables.ProgramMZ;
import com.PPU.DB.tables.UsersMunMan;

import java.util.List;

/**
 * Created by user on 22.04.14.
 */
public class WorkWithProgramMZ extends WorkWithTable {

	private static String COLUMN_ID = "Id";
	private static String COLUMN_NAME = "Name";
	private static String COLUMN_TARGET = "Target";
	private static String COLUMN_DESCRIPTION = "Description";
    private static String COLUMN_ID_PARTNER = "IdPartnerMz";

	@Override
	public List getListRows() {
		return ppuDao.findProgramMZ("", "");
	}

	@Override
	public List findAndGetAllRow(String fields, String fieldValue) {
		return ppuDao.findProgramMZ(fields, fieldValue);
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
		if (obj instanceof ProgramMZ)
			ppuDao.saveProgramMZ((ProgramMZ) obj);
		else
            throw new Exception("В метод addEntity передан неверный параметр");
	}

	@Override
	public void changeEntity(Object obj) throws Exception {
		if (obj instanceof ProgramMZ)
			ppuDao.updateProgramMZ((ProgramMZ) obj);
		else
            throw new Exception("В метод changeEntity передан неверный параметр");
	}

	@Override
	public void deleteEntity(Object obj) throws Exception {
		if (obj instanceof ProgramMZ)
			ppuDao.deleteProgramMZ((ProgramMZ) obj);
		else
            throw new Exception("В метод deleteEntity передан неверный параметр");
	}

	@Override
	public Object getEntity(int id) {
		return ppuDao.getProgramMZ(id);
	}

	@Override
	public Object getEmptyEntity() {
		return new ProgramMZ();
	}

    /**
     * Проверка на участие в программе
     * 1 - участвие
     * 0 - нет
     * @param programMZmz
     * @param user
     * @return
     */
    public boolean isRoleByMZ(ProgramMZ programMZmz, UsersMunMan user)
    {
        boolean isFind = false;

        PartnersMZ part = programMZmz.getPartnersMZ();

        if (part.equals(user.getPartnerMZ()))
            isFind = true;
        else
            for (MZ mz : programMZmz.getMZ())
            {
                if (new WorkWithMZ().getUserRole(mz,user)>0)
                {
                    isFind = true;
                    break;
                }
            }

        return isFind;
    }
}
