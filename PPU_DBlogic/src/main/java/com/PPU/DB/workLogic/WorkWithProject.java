package com.PPU.DB.workLogic;

import com.PPU.DB.tables.*;

import java.util.Date;
import java.util.Set;
import java.util.List;

/**
 * Created by Alex on 21.04.2014.
 */
public class WorkWithProject extends WorkWithTable  {

	private static String COLUMN_ID = "Id";
	private static String COLUMN_ID_PROGRAM = "Id_program";
	private static String COLUMN_NAME = "Name";
	private static String COLUMN_ID_CUSTOMER = "Id_customer";
	private static String COLUMN_ID_LEADER = "Id_leader";
	private static String COLUMN_START_DATE = "Start_date";
	private static String COLUMN_EXPIREATION_DATE = "Expiration_date";
	private static String COLUMN_DESCRIPTION = "Description";
	private static String COLUMN_BUDGET = "Budget";
	private static String COLUMN_STATUS = "Status";

	@Override
	public List getListRows() {
		return ppuDao.findProject("", "");
	}

	@Override
	public List findAndGetAllRow(String fields, String fieldValue) {
		return ppuDao.findProject(fields, fieldValue);
	}

	@Override
	public Object getColumnValue(Object obj, String columnName) throws IllegalAccessException {
		if (obj == null)
		{
			throw new IllegalAccessException("?? ???? ???????? ????????? ? partnersCommercial");
		}
		else
			return ClassInvokeCall.callMethod(obj, "get"+columnName);
	}

	@Override
	public Object setColumnValueFromList(Object obj, String columnName, Object ... listValue) throws IllegalAccessException {
		if (obj == null)
		{
			throw new IllegalAccessException("?? ???? ???????? ????????? ? userMunCommercial");
		}
		else
			return ClassInvokeCall.callMethod(obj, "set"+columnName, listValue);
	}

	@Override
	public void addEntity(Object obj) throws Exception {
		if (obj instanceof Project)
			ppuDao.saveProject((Project) obj);
		else
			throw new Exception("? ????? Project.addEntity ??????? ???????? ????????");
	}

	@Override
	public void changeEntity(Object obj) throws Exception {
		if (obj instanceof Project)
			ppuDao.updateProject((Project) obj);
		else
			throw new Exception("? ????? Project.addEntity ??????? ???????? ????????");
	}

	@Override
	public void deleteEntity(Object obj) throws Exception {
		if (obj instanceof Project)
			ppuDao.deleteProject((Project) obj);
		else
			throw new Exception("? ????? Project.addEntity ??????? ???????? ????????");
	}

	@Override
	public Object getEntity(int id) {
		return ppuDao.getProject(id);
	}

	@Override
	public Object getEmptyEntity() {
		return new Project();
	}

    /** определяем уровень доступа для юзера
     * 1 - заказчик
     * 2 - руководитель
     * 3 - заказчик, руководитель
     * 4 - команда (исполнитель)
     * @param project
     * @param user
     */
    public int getUserRole(Project project, UsersComMan user)
    {
        int id = user.getId();
        int role = 0;

        PartnerCommercialMan partnerCom = user.getPartnerProject();

        PartnerCommercialMan progrPartner = project.getProgram().getPartnerCommercialMan();

        if (progrPartner.equals(partnerCom)) role+=1;

        PartnerCommercialMan leaderPartner = project.getLeader();

        if (leaderPartner.equals(partnerCom)) role+=2;

        boolean isFind = false;
        for (ComandProject com : project.getComandProject())
            if (com.getPartnerProject().equals(partnerCom))
                isFind = true;

        if (isFind) role+=3;

        return role;
    }
}
