package com.PPU.windowControllers.notification;

import com.PPU.DB.tables.NotificationCom;
import com.PPU.DB.tables.NotificationMU;
import com.PPU.DB.tables.UsersComMan;
import com.PPU.DB.tables.UsersMunMan;
import com.PPU.DB.workLogic.WorkWithNotificationCom;
import com.PPU.DB.workLogic.WorkWithNotificationMU;
import com.PPU.DB.workLogic.WorkWithUser;
import org.zkoss.zk.ui.Sessions;

import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 10.05.14
 * Time: 12:51
 * To change this template use File | Settings | File Templates.
 */
public class MyNotification {

    private String handName;
    private String descriptionName;

    private Object [][] newNotification;
    private Object [][] allNotification;

    public Object [][] getAllNotification() {
        String typeOrg = (String) Sessions.getCurrent().getAttribute("type");
        Object [][] result = new String[0][];

        if (typeOrg.equals("Mun"))
        {
            UsersMunMan user = (UsersMunMan) new WorkWithUser().findAndGetAllRow("login", (String) Sessions.getCurrent().getAttribute("login")).get(0);
            List list = Arrays.asList(user.getPartnerMZ().getNotificationMU().toArray());
            result = new Object[list.size()][];

            int i = 0;
            for (Object o : list)
            {
                result[i] = new Object [2];
                result[i][0] = ((NotificationMU)o).getText();
                result[i++][1] = ((NotificationMU)o).getDate();
            }
        }
        else
        {
            UsersComMan user = (UsersComMan) new WorkWithUser().findAndGetAllRow("login", (String) Sessions.getCurrent().getAttribute("login")).get(0);
            List list = Arrays.asList(user.getPartnerProject().getNotificationComs().toArray());
            result = new Object[list.size()][];
            if (typeOrg != null)
            {
                int i = 0;

                for (Object o : list)
                {
                    result[i] = new Object [2];
                    result[i][0] = ((NotificationCom)o).getText();
                    result[i++][1] = ((NotificationCom)o).getDate();
                }
            }
        }
        allNotification = result;

        return allNotification;
    }

    public void setAllNotification(Object [][]allNotification) {
        this.allNotification = allNotification;
    }

    public String getDescriptionName() {
        descriptionName = "Здесь представлены все ваши оповещения";
        return descriptionName;
    }

    public void setDescriptionName(String descriptionName) {
        this.descriptionName = descriptionName;
    }

    public String getHandName() {

        handName = "Мои оповещения";
        return handName;
    }

    public void setHandName(String handName) {
        this.handName = handName;
    }

    public Object [][] getNewNotification() {
        String typeOrg = (String) Sessions.getCurrent().getAttribute("type");
        Object [][] result = new Object [0][];

        if (typeOrg.equals("Mun"))
        {
            UsersMunMan user = (UsersMunMan) new WorkWithUser().findAndGetAllRow("login", (String) Sessions.getCurrent().getAttribute("login")).get(0);
            List list = Arrays.asList(user.getPartnerMZ().getNotificationMU().toArray());

            int countNotRead = 0;
            for (Object not : list)
                if (!((NotificationMU)not).isRead())
                    countNotRead++;

            result = new Object[countNotRead][];

            int i = 0;
            for (Object o : list)
                if (!((NotificationMU)o).isRead())
                {
                    result[i] = new Object [2];
                    result[i][0] = ((NotificationMU)o).getText();
                    result[i++][1] = ((NotificationMU)o).getDate();
                }
        }
        else
        {
            UsersComMan user = (UsersComMan) new WorkWithUser().findAndGetAllRow("login", (String) Sessions.getCurrent().getAttribute("login")).get(0);
            List list = Arrays.asList(user.getPartnerProject().getNotificationComs().toArray());

            int countNotRead = 0;
            for (Object not : list)
                if (!((NotificationCom)not).isRead())
                    countNotRead++;

            result = new Object[countNotRead][];

            if (typeOrg != null)
            {
                int i = 0;

                for (Object o : list)
                    if (!((NotificationCom)o).isRead())
                    {
                        result[i] = new Object [2];
                        result[i][0] = ((NotificationCom)o).getText();
                        result[i++][1] = ((NotificationCom)o).getDate();
                    }
            }
        }
        newNotification = result;

        return newNotification;
    }

    public void setNewNotification(Object [][] newNotification) {
        this.newNotification = newNotification;
    }
}
