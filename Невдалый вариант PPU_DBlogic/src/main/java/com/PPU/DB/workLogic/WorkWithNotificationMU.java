package com.PPU.DB.workLogic;

import com.PPU.DB.tables.NotificationMU;
import com.PPU.DB.tables.PartnersMZ;
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
public class WorkWithNotificationMU extends WorkWithTable {
    public static String COLUMN_ID = "Id";
    public static String COLUMN_ID_PARAMETR = "Text";
    public static String COLUMN_VALUE = "Receiver";
    public static String COLUMN_ID_PROJECT = "Read";

    public WorkWithNotificationMU()
    {
        super();
    }

    @Override
    public List<NotificationMU> findAndGetAllRow(String fields, String fieldValue)
    {
        return ppuDao.findNotificationMU(fields, fieldValue);
    }

    @Override
    public Object getColumnValue(Object obj, String columnName) throws IllegalAccessException {
        if (obj == null)
        {
            throw new IllegalAccessException("Не было передано параметра в NotificationMU");
        }
        else
            return ClassInvokeCall.callMethod(obj, "get"+columnName);
    }

    @Override
    public Object setColumnValueFromList(Object obj, String columnName, Object ... listValue) throws IllegalAccessException {
        if (obj == null)
        {
            throw new IllegalAccessException("Не было передано параметра в NotificationMU");
        }
        else
            return ClassInvokeCall.callMethod(obj, "set"+columnName, listValue);
    }

    @Override
    public void addEntity(Object obj) throws Exception {
        if (obj instanceof NotificationMU)
            ppuDao.saveNotificationMU((NotificationMU) obj);
        else
            throw new Exception("В метод NotificationMU.addEntity передан неверный параметр");
    }

    @Override
    public void changeEntity(Object obj) throws Exception {
        if (obj instanceof NotificationMU)
            ppuDao.updateNotificationMU((NotificationMU) obj);
        else
            throw new Exception("В метод NotificationMU.addEntity передан неверный параметр");
    }

    @Override
    public void deleteEntity(Object obj) throws Exception {
        if (obj instanceof NotificationMU)
            ppuDao.deleteNotificationMU((NotificationMU) obj);
        else
            throw new Exception("В метод Providers.addEntity передан неверный параметр");
    }

    @Override
    public Object getEntity(int id) {
        return ppuDao.getNotificationMU(id);
    }

    @Override
    public List getListRows() {
        return findAndGetAllRow("", "");
    }

    @Override
    public Object getEmptyEntity() {
        return new NotificationMU();
    }

    public List getLatestNotification(PartnersMZ partner)
    {
        List result = new ArrayList(0);

        List list2 = new ArrayList();

        Set set = partner.notificationMUSet();
        List list = new ArrayList();

        for (Object o: set)
            list.add(o);

        for (Object o : list)
            if (!((NotificationMU)o).isRead())
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
