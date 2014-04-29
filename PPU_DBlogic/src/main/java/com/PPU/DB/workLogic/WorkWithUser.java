package com.PPU.DB.workLogic;

import com.PPU.DB.security.MD5;
import com.PPU.DB.tables.CorrectionsMZ;
import com.PPU.DB.tables.UsersComMan;
import com.PPU.DB.tables.UsersMunMan;

import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 19.04.14
 * Time: 16:54
 * To change this template use File | Settings | File Templates.
 */
public class WorkWithUser extends WorkWithTable {

    public static String COLUMN_ID = "Id";
    public static String COLUMN_LOGIN = "Login";
    public static String COLUMN_HASH = "Hash";
    public static String COLUMN_NAME = "Name";


    public WorkWithUser()
    {
        super();
    }

    @Override
    public List getListRows()
    {
        return Collections.emptyList();
    }

	public List getUsersMunManList()
	{
		return ppuDao.findUsersMunMan("", "");
	}

	public List getUsersComManList()
	{
		return ppuDao.findUsersComMan("", "");
	}

    @Override
    public List findAndGetAllRow(String fields, String fieldValue)
    {
        List list1 = ppuDao.findUsersMunMan(fields, fieldValue);
        List list2 = ppuDao.findUsersComMan(fields, fieldValue);

        return (list1.size()!=0) ? list1 : list2;
    }

    @Override
    public Object getColumnValue(Object obj, String columnName) throws IllegalAccessException {
        if (obj == null)
        {
            throw new IllegalAccessException("Не было передано параметра в userMunMan");
        }
        else
            return ClassInvokeCall.callMethod(obj, "get"+columnName);
    }

    @Override
    public Object setColumnValueFromList(Object obj, String columnName, Object ... listValue) throws IllegalAccessException {
        if (obj == null)
        {
            throw new IllegalAccessException("Не было передано параметра в userMunMan");
        }
        else
            return ClassInvokeCall.callMethod(obj, "set"+columnName, listValue);
    }

    public boolean checkLoginAndPassword(String login, String password)
    {
        List list = findAndGetAllRow("login;hash",login+";"+ MD5.getMD5(password));

        if (list.size() == 1)
            return true;
        else
            return false;
    }

    public boolean checkLogin(Object obj)
    {
        if (obj instanceof UsersMunMan) {
            return findAndGetAllRow("login",  ((UsersMunMan) obj).getLogin()).size() == 0 ? true : false;
        }
        else
        if (obj instanceof UsersComMan) {
            return findAndGetAllRow("login",  ((UsersComMan) obj).getLogin()).size() == 0 ? true : false;
        }

        return false;
    }

    @Override
    public void addEntity(Object obj) throws Exception {
        if (obj instanceof UsersMunMan) {
            ppuDao.saveUsersMunMan((UsersMunMan) obj);
        }
        else
            if (obj instanceof UsersComMan) {
                ppuDao.saveUsersComMan((UsersComMan) obj);
            }
            else
                throw new Exception("В метод WorkWithUser.addEntity передан неверный параметр");
    }

    @Override
    public void changeEntity(Object obj) throws Exception {
        if (obj instanceof UsersMunMan)
            ppuDao.updateUsersMunMan((UsersMunMan) obj);
        else
            if (obj instanceof UsersComMan)
                ppuDao.updateUsersComMan((UsersComMan) obj);
            else
                throw new Exception("В метод WorkWithUser.addEntity передан неверный параметр");
    }

    @Override
    public void deleteEntity(Object obj) throws Exception {
        if (obj instanceof UsersMunMan)
            ppuDao.deleteUsersMunMan((UsersMunMan) obj);
        else
            if (obj instanceof UsersComMan)
                ppuDao.deleteUsersComMan((UsersComMan) obj);
            else
                throw new Exception("В метод WorkWithUser.addEntity передан неверный параметр");
    }

    @Override
    public Object getEntity(int id) {
        return new Object();
    }

    public UsersMunMan getUserMunManEntity(int id)
    {
        return ppuDao.getUsersMunMan(id);
    }

    public UsersComMan getUserComManEntity(int id)
    {
        return ppuDao.getUsersComMan(id);
    }

	@Override
	public Object getEmptyEntity() {
		return new Object();
	}

	public Object getEmptyMUUser() {
		return new UsersMunMan();
	}

	public Object getEmptyComUser() {
		return new UsersComMan();
	}
}
