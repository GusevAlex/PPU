package com.PPU.funcControl;

import com.PPU.DB.tables.ComandMZ;
import com.PPU.DB.tables.MZ;
import com.PPU.DB.tables.NotificationMU;
import com.PPU.DB.tables.PartnersMZ;
import com.PPU.DB.workLogic.WorkWithCommandMz;
import com.PPU.DB.workLogic.WorkWithNotificationMU;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 09.05.14
 * Time: 22:03
 * To change this template use File | Settings | File Templates.
 */
public class NotificationService {

    public static String addNotifHtmlForAdd(PartnersMZ partnersMZ, String adressPage, MZ mz, int level)
    {
        String ret = "";

        if (partnersMZ!=null && !adressPage.equals(""))
        {
            String levelName = "";

            switch (level)
            {
                case 1 : levelName = "��������";
                    break;
                case 2 : levelName = "������������";
                    break;
                case 3 : levelName = "�� ������������";
                    break;
                case 4 : levelName = "����������";
                    break;
                case 5 : levelName = "";
                    break;
            }
            String port = ( Executions.getCurrent().getServerPort() == 80 ) ? "" : (":" + Executions.getCurrent().getServerPort());
            String url = Executions.getCurrent().getScheme() + "://" + Executions.getCurrent().getServerName() + port + Executions.getCurrent().getContextPath();

            ret+="<div>��� �� "+levelName+" ��������� ������������� ������� <a href=\""+url
                    +adressPage+"?id="+mz.getId()+"\">"+mz.getName()+"</a></div>";

            NotificationMU notificationMU = new NotificationMU();
            notificationMU.setPartners(partnersMZ);
            notificationMU.setRead(false);
            notificationMU.setText(ret);

            try {
                new WorkWithNotificationMU().addEntity((Object)notificationMU);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return ret;
    }

    public static String revertNotifHtmlForAdd(PartnersMZ partnersMZ, String adressPage, MZ mz, int level, String reason)
    {
        String ret = "";

        if (partnersMZ!=null && !adressPage.equals(""))
        {
            String levelName = "";

            switch (level)
            {
                case 1 : levelName = "��������";
                    break;
                case 2 : levelName = "������������";
                    break;
                case 3 : levelName = "�� ������������";
                    break;
                case 4 : levelName = "����������";
                    break;
                case 5 : levelName = "";
                    break;
            }
            String port = ( Executions.getCurrent().getServerPort() == 80 ) ? "" : (":" + Executions.getCurrent().getServerPort());
            String url = Executions.getCurrent().getScheme() + "://" + Executions.getCurrent().getServerName() + port + Executions.getCurrent().getContextPath();

            ret+="<div>��� �� "+levelName+" ���������� ������������� ������� <a href=\""+url
                    +adressPage+"?id="+mz.getId()+"\">"+mz.getName()+"</a>"+".\n ���������� �� �������: "+reason+"</div>";

            NotificationMU notificationMU = new NotificationMU();
            notificationMU.setPartners(partnersMZ);
            notificationMU.setRead(false);
            notificationMU.setText(ret);

            try {
                new WorkWithNotificationMU().addEntity((Object)notificationMU);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return ret;
    }

    public static String addNotifHtmlForRevertComandWork(PartnersMZ partnersMZ, String adressPage, MZ mz, int level, String reason)
    {
        String ret = "";

        if (partnersMZ!=null && !adressPage.equals(""))
        {
            String port = ( Executions.getCurrent().getServerPort() == 80 ) ? "" : (":" + Executions.getCurrent().getServerPort());
            String url = Executions.getCurrent().getScheme() + "://" + Executions.getCurrent().getServerName() + port + Executions.getCurrent().getContextPath();

            ret+="<div>������������ �������������� ������� <a href=\""+url
                    +adressPage+"?id="+mz.getId()+"\">"+mz.getName()+"</a>, "+ partnersMZ.getName()+" ���� ��������� ������"+"</div>";

            NotificationMU notificationMU = new NotificationMU();
            notificationMU.setPartners(partnersMZ);
            notificationMU.setRead(false);
            notificationMU.setText(ret);

            for (ComandMZ com : mz.getComandMZ())
                if (com.equals(partnersMZ))
                {
                    com.setWork(false);
                    try {
                        new WorkWithCommandMz().changeEntity(com);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            try {
                new WorkWithNotificationMU().addEntity((Object)notificationMU);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return ret;
    }

    public static String revertNotifHtmlForRevertComandWork(PartnersMZ partnersMZ, String adressPage, MZ mz, int level, String reason)
    {
        String ret = "";

        if (partnersMZ!=null && !adressPage.equals(""))
        {
            String levelName = "";

            switch (level)
            {
                case 1 : levelName = "��������";
                    break;
                case 2 : levelName = "������������";
                    break;
                case 3 : levelName = "�� ������������";
                    break;
                case 4 : levelName = "����������";
                    break;
                case 5 : levelName = "";
                    break;
            }
            String port = ( Executions.getCurrent().getServerPort() == 80 ) ? "" : (":" + Executions.getCurrent().getServerPort());
            String url = Executions.getCurrent().getScheme() + "://" + Executions.getCurrent().getServerName() + port + Executions.getCurrent().getContextPath();

            ret+="<div>��� �� "+levelName+" ��������� ������������� ������� <a href=\""+url
                    +adressPage+"?id="+mz.getId()+"\">"+mz.getName()+"</a>"+".\n ���������� �� �������: "+reason+"</div>";

            NotificationMU notificationMU = new NotificationMU();
            notificationMU.setPartners(partnersMZ);
            notificationMU.setRead(false);
            notificationMU.setText(ret);

            for (ComandMZ com : mz.getComandMZ())
                if (com.equals(partnersMZ))
                {
                    com.setWork(true);
                    try {
                        new WorkWithCommandMz().changeEntity(com);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            try {
                new WorkWithNotificationMU().addEntity((Object)notificationMU);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return ret;
    }
}
