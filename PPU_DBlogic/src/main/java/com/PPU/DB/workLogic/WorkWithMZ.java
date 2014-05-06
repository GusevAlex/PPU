package com.PPU.DB.workLogic;

import com.PPU.DB.DAO.PpuDaoInterface;
import com.PPU.DB.tables.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by Alex on 14.04.2014.
 */
public class WorkWithMZ extends WorkWithTable{

    public static String COLUMN_ID = "Id";
    public static String COLUMN_ID_PROGRAM = "IdProgram";
    public static String COLUMN_NAME = "Name";
    public static String COLUMN_ID_CUSTOMER = "IdCustomer";
    public static String COLUMN_ID_LEADER = "IdLeader";
    public static String COLUMN_START_DATE = "StartDate";
    public static String COLUMN_EXPIRATION_DATE = "ExpirationDate";
    public static String COLUMN_DESCRIPTION = "Description";
    public static String COLUMN_BUDGET = "Budget";
    public static String COLUMN_STATUS = "Status";
    public static String COLUMN_SERVICE_TYPE = "ServiceType";

    public WorkWithMZ()
    {
        super();
    }

    @Override
    public List<MZ> findAndGetAllRow(String fields, String fieldValue)
    {
       return ppuDao.findMz(fields, fieldValue);
    }

    @Override
    public Object getColumnValue(Object obj, String columnName) throws IllegalAccessException {
        if (obj == null)
        {
            throw new IllegalAccessException("Не было передано параметра в mz");
        }
        else
            return ClassInvokeCall.callMethod(obj, "get"+columnName);
    }

    @Override
    public Object setColumnValueFromList(Object obj, String columnName, Object ... listValue) throws IllegalAccessException {
        if (obj == null)
        {
            throw new IllegalAccessException("Не было передано параметра в mz");
        }
        else
            return ClassInvokeCall.callMethod(obj, "set"+columnName, listValue);
    }

    @Override
    public void addEntity(Object obj) throws Exception {
        if (obj instanceof MZ)
            ppuDao.saveMz((MZ) obj);
        else
            throw new Exception("В метод WorkWithMZ.addEntity передан неверный параметр");
    }

    @Override
    public void changeEntity(Object obj) throws Exception {
        if (obj instanceof MZ)
            ppuDao.updateMz((MZ) obj);
        else
            throw new Exception("В метод WorkWithMZ.addEntity передан неверный параметр");
    }

    @Override
    public void deleteEntity(Object obj) throws Exception {
        if (obj instanceof MZ)
            ppuDao.deleteMz((MZ) obj);
        else
            throw new Exception("В метод WorkWithMZ.addEntity передан неверный параметр");
    }

    @Override
    public Object getEntity(int id) {
        return ppuDao.getMz(id);
    }

	@Override
	public List getListRows() {
		return findAndGetAllRow("", "");
	}

	@Override
	public Object getEmptyEntity() {
		return new MZ();
	}

	/** определяем уровень доступа для юзера
	 * 1 - заказчик
	 * 2 - руководитель
	 * 3 - команда (исполнитель)
	 * @param mz
	 * @param user
	 */
	public int getUserRole(MZ mz, UsersMunMan user)
	{
		int id = user.getId();

		PartnersMZ partnerMZ = user.getPartnerMZ();

		PartnersMZ progrPartner = mz.getProgram().getPartnersMZ();

		if (progrPartner.equals(partnerMZ)) return 1;

		PartnersMZ leaderPartner = mz.getLeader();

		if (leaderPartner.equals(partnerMZ)) return 2;

		boolean isFind = false;
		for (ComandMZ com : mz.getComandMZ())
			if (com.getPartnerMZ().equals(partnerMZ))
				isFind = true;

		if (isFind) return 3;

		return 0;
	}
}
