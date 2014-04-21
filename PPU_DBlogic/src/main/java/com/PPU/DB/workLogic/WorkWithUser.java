package com.PPU.DB.workLogic;

import com.PPU.DB.security.MD5;
import com.PPU.DB.tables.UsersComMan;
import com.PPU.DB.tables.UsersMunMan;

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
    public static String LOGIN_ID = "Login";
    public static String HASH_ID = "Hash";
    public static String NAME_ID = "Name";

    private List<UsersMunMan> usersMunMans;
    private UsersMunMan userMunMan;

    private List<UsersComMan> usersComMans;
    private UsersComMan userComMan;

    public WorkWithUser()
    {
        super();

        userMunMan = new UsersMunMan();
        usersMunMans = ppuDao.findUsersMunMan("", "");
    }

    public WorkWithUser(int idUsers)
    {
        this();

        userMunMan = ppuDao.getUsersMunMan(idUsers);
    }

    public WorkWithUser(String fields, String fieldValue)
    {
        this();

        usersMunMans = ppuDao.findUsersMunMan(fields, fieldValue);
    }

    @Override
    public List getListRows()
    {
        return usersMunMans;
    }

    @Override
    public List findAndGetAllRow(String fields, String fieldValue)
    {
        List list1 = ppuDao.findUsersMunMan(fields, fieldValue);
        List list2 = ppuDao.findUsersComMan(fields, fieldValue);

        return (list1.size()!=0) ? list1 : list2;
    }

    @Override
    public void setRows(Object obj)
    {
        if ((userMunMan != null) && (obj instanceof UsersMunMan))
        {
            this.userMunMan = (UsersMunMan) obj;
        }
        else
            if ((userComMan != null) && (obj instanceof UsersComMan))
            {
                this.userComMan = (UsersComMan) obj;
            }
            else
                throw new IllegalArgumentException("Неверно передан входной параметр");
    }

    @Override
    public void setRowById(int id)
    {
        userMunMan = ppuDao.getUsersMunMan(id);
        userComMan = ppuDao.getUsersComMan(id);
    }

    public UsersMunMan getUsersMunMans()
    {
        return userMunMan;
    }

    public UsersComMan getUserComMan() {
        return userComMan;
    }

    @Override
    public Object getColumnValue(String columnName) throws IllegalAccessException {
        if (userMunMan == null)
        {
            throw new IllegalAccessException("Не было передано параметра в userMunMan");
        }
        else
            return ClassInvokeCall.callMethod(userMunMan, "get"+columnName);
    }

    @Override
    public Object setColumnValueFromList(String columnName, Object ... listValue) throws IllegalAccessException {
        if (userMunMan == null)
        {
            throw new IllegalAccessException("Не было передано параметра в userMunMan");
        }
        else
            return ClassInvokeCall.callMethod(userMunMan, "set"+columnName, listValue);
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
}
