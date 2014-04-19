package com.PPU.DB.workLogic;

import com.PPU.DB.security.MD5;
import com.PPU.DB.tables.Users;
import com.PPU.DB.tables.Users;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

    private List<Users> users;
    private Users user;

    public WorkWithUser()
    {
        super();

        user = new Users();
        users = ppuDao.findUsers("", "");
    }

    public WorkWithUser(int idUsers)
    {
        this();

        user = ppuDao.getUsers(idUsers);
    }

    public WorkWithUser(String fields, String fieldValue)
    {
        this();

        users = ppuDao.findUsers(fields, fieldValue);
    }

    @Override
    public List getListRows()
    {
        return users;
    }

    @Override
    public List<Users> findAndGetAllRow(String fields, String fieldValue)
    {
        return ppuDao.findUsers(fields, fieldValue);
    }

    @Override
    public void setRows(Object obj)
    {
        if ((user != null) && (user instanceof Users))
        {
            this.user = (Users) obj;
        }
        else
            throw new IllegalArgumentException("Неверно передан входной параметр");
    }

    @Override
    public void setRowById(int id)
    {
        user = ppuDao.getUsers(id);
    }

    public Users getUsers()
    {
        return user;
    }

    @Override
    public Object getColumnValue(String columnName) throws IllegalAccessException {
        if (user == null)
        {
            throw new IllegalAccessException("Не было передано параметра в user");
        }
        else
            return ClassInvokeCall.callMethod(user, "get"+columnName);
    }

    @Override
    public Object setColumnValueFromList(String columnName, Object ... listValue) throws IllegalAccessException {
        if (user == null)
        {
            throw new IllegalAccessException("Не было передано параметра в user");
        }
        else
            return ClassInvokeCall.callMethod(user, "set"+columnName, listValue);
    }

    public boolean checkLoginAndPassword(String login, String password)
    {
        List list = findAndGetAllRow("login;hash",login+";"+ MD5.getMD5(password));

        if (list.size() == 1)
            return true;
        else
            return false;
    }

    @Override
    public void addEntity(Object obj) throws Exception {
        if (obj instanceof Users)
            ppuDao.saveUsers((Users) obj);
        else
            throw new Exception("В метод WorkWithUser.addEntity передан неверный параметр");
    }

    @Override
    public void changeEntity(Object obj) throws Exception {
        if (obj instanceof Users)
            ppuDao.updateUsers((Users) obj);
        else
            throw new Exception("В метод WorkWithUser.addEntity передан неверный параметр");
    }

    @Override
    public void deleteEntity(Object obj) throws Exception {
        if (obj instanceof Users)
            ppuDao.deleteUsers((Users) obj);
        else
            throw new Exception("В метод WorkWithUser.addEntity передан неверный параметр");
    }

    @Override
    public Object getEntity(int id) {
        return ppuDao.getUsers(id);
    }
}
