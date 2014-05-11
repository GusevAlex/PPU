package com.PPU.DB.workLogic;

import com.PPU.DB.tables.NotificationCom;
import com.PPU.DB.tables.NotificationMU;
import com.PPU.DB.tables.PartnerCommercialMan;
import com.PPU.DB.tables.Providers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 09.05.14
 * Time: 21:26
 * To change this template use File | Settings | File Templates.
 */
public class WorkWithNotificationCom extends WorkWithTable {
    public static String COLUMN_ID = "Id";
    public static String COLUMN_ID_PARAMETR = "Text";
    public static String COLUMN_VALUE = "Receiver";
    public static String COLUMN_ID_PROJECT = "Read";

    public WorkWithNotificationCom()
    {
        super();
    }

    @Override
    public List<NotificationCom> findAndGetAllRow(String fields, String fieldValue)
    {
        return ppuDao.findNotificationCom(fields, fieldValue);
    }

    @Override
    public Object getColumnValue(Object obj, String columnName) throws IllegalAccessException {
        if (obj == null)
        {
            throw new IllegalAccessException("Не было передано параметра в NotificationCom");
        }
        else
            return ClassInvokeCall.callMethod(obj, "get"+columnName);
    }

    @Override
    public Object setColumnValueFromList(Object obj, String columnName, Object ... listValue) throws IllegalAccessException {
        if (obj == null)
        {
            throw new IllegalAccessException("Не было передано параметра в NotificationCom");
        }
        else
            return ClassInvokeCall.callMethod(obj, "set"+columnName, listValue);
    }

    @Override
    public void addEntity(Object obj) throws Exception {
        if (obj instanceof NotificationCom)
            ppuDao.saveNotificationCom((NotificationCom) obj);
        else
            throw new Exception("В метод NotificationCom.addEntity передан неверный параметр");
    }

    @Override
    public void changeEntity(Object obj) throws Exception {
        if (obj instanceof NotificationCom)
            ppuDao.updateNotificationCom((NotificationCom) obj);
        else
            throw new Exception("В метод NotificationCom.addEntity передан неверный параметр");
    }

    @Override
    public void deleteEntity(Object obj) throws Exception {
        if (obj instanceof NotificationCom)
            ppuDao.deleteNotificationCom((NotificationCom) obj);
        else
            throw new Exception("В метод Providers.addEntity передан неверный параметр");
    }

    @Override
    public Object getEntity(int id) {
        return ppuDao.getNotificationCom(id);
    }

    @Override
    public List getListRows() {
        return findAndGetAllRow("", "");
    }

    @Override
    public Object getEmptyEntity() {
        return new NotificationCom();
    }

    public List getLatestNotification(PartnerCommercialMan partner)
    {
        List result = new ArrayList(0);

        List list2 = new ArrayList();

        Set set = partner.notificationComs();
        List list = new ArrayList();

        for (Object o: set)
            list.add(o);

        for (Object o : list)
            if (!((NotificationCom)o).isRead())
                list2.add(o);

        if (list2.size()!=0)
        {
            if (list2.size()>1)
                result.add(list2.get(list2.size()-2));

            result.add(list2.get(list2.size()-1));
        }

        return result;
    }
}
