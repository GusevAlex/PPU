package com.PPU.funcControl;

import com.PPU.DB.tables.*;
import com.PPU.DB.workLogic.WorkWithComandProject;
import com.PPU.DB.workLogic.WorkWithCommandMz;
import com.PPU.DB.workLogic.WorkWithNotificationCom;
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
                case 1 : levelName = "Создание";
                    break;
                case 2 : levelName = "Планирование";
                    break;
                case 3 : levelName = "На согласование";
                    break;
                case 4 : levelName = "Выполнение";
                    break;
                case 5 : levelName = "";
                    break;
            }
            String port = ( Executions.getCurrent().getServerPort() == 80 ) ? "" : (":" + Executions.getCurrent().getServerPort());
            String url = Executions.getCurrent().getScheme() + "://" + Executions.getCurrent().getServerName() + port + Executions.getCurrent().getContextPath();

            ret+="<div>Вам на "+levelName+" поступило Муниципальное задание <a href=\""+url
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
                case 1 : levelName = "Создание";
                    break;
                case 2 : levelName = "Планирование";
                    break;
                case 3 : levelName = "На согласование";
                    break;
                case 4 : levelName = "Выполнение";
                    break;
                case 5 : levelName = "";
                    break;
            }
            String port = ( Executions.getCurrent().getServerPort() == 80 ) ? "" : (":" + Executions.getCurrent().getServerPort());
            String url = Executions.getCurrent().getScheme() + "://" + Executions.getCurrent().getServerName() + port + Executions.getCurrent().getContextPath();

            ret+="<div>Вам на "+levelName+" возвращено Муниципальное задание <a href=\""+url
                    +adressPage+"?id="+mz.getId()+"\">"+mz.getName()+"</a>"+".\n Возвращено по причине: "+reason+"</div>";

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

            ret+="<div>Исполнителем муниципального задания <a href=\""+url
                    +adressPage+"?id="+mz.getId()+"\">"+mz.getName()+"</a>, "+ partnersMZ.getName()+" была завершена работа"+"</div>";

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
                case 1 : levelName = "Создание";
                    break;
                case 2 : levelName = "Планирование";
                    break;
                case 3 : levelName = "На согласование";
                    break;
                case 4 : levelName = "Выполнение";
                    break;
                case 5 : levelName = "";
                    break;
            }
            String port = ( Executions.getCurrent().getServerPort() == 80 ) ? "" : (":" + Executions.getCurrent().getServerPort());
            String url = Executions.getCurrent().getScheme() + "://" + Executions.getCurrent().getServerName() + port + Executions.getCurrent().getContextPath();

            ret+="<div>Вам на "+levelName+" вернулось Муниципальное задание <a href=\""+url
                    +adressPage+"?id="+mz.getId()+"\">"+mz.getName()+"</a>"+".\n Возвращено по причине: "+reason+"</div>";

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

    //todo коммерческое управление

    public static String addNotifHtmlForAddCom(PartnerCommercialMan partnerCommercialMan, String adressPage, Project project, int level)
    {
        String ret = "";

        if (partnerCommercialMan!=null && !adressPage.equals(""))
        {
            String levelName = "";

            switch (level)
            {
                case 1 : levelName = "Создание";
                    break;
                case 2 : levelName = "Планирование";
                    break;
                case 3 : levelName = "На согласование";
                    break;
                case 4 : levelName = "Выполнение";
                    break;
                case 5 : levelName = "";
                    break;
            }
            String port = ( Executions.getCurrent().getServerPort() == 80 ) ? "" : (":" + Executions.getCurrent().getServerPort());
            String url = Executions.getCurrent().getScheme() + "://" + Executions.getCurrent().getServerName() + port + Executions.getCurrent().getContextPath();

            ret+="<div>Вам на "+levelName+" поступил Проект <a href=\""+url
                    +adressPage+"?id="+project.getId()+"\">"+project.getName()+"</a></div>";

            NotificationCom notificationCom = new NotificationCom();
            notificationCom.setPartners(partnerCommercialMan);
            notificationCom.setRead(false);
            notificationCom.setText(ret);

            try {
                new WorkWithNotificationCom().addEntity((Object)notificationCom);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return ret;
    }

    public static String revertNotifHtmlForAddCom(PartnerCommercialMan partnerCommercialMan, String adressPage, Project project, int level, String reason)
    {
        String ret = "";

        if (partnerCommercialMan!=null && !adressPage.equals(""))
        {
            String levelName = "";

            switch (level)
            {
                case 1 : levelName = "Создание";
                    break;
                case 2 : levelName = "Планирование";
                    break;
                case 3 : levelName = "На согласование";
                    break;
                case 4 : levelName = "Выполнение";
                    break;
                case 5 : levelName = "";
                    break;
            }
            String port = ( Executions.getCurrent().getServerPort() == 80 ) ? "" : (":" + Executions.getCurrent().getServerPort());
            String url = Executions.getCurrent().getScheme() + "://" + Executions.getCurrent().getServerName() + port + Executions.getCurrent().getContextPath();

            ret+="<div>Вам на "+levelName+" возвращен Проект <a href=\""+url
                    +adressPage+"?id="+project.getId()+"\">"+project.getName()+"</a>"+".\n Возвращено по причине: "+reason+"</div>";

            NotificationCom notificationCom = new NotificationCom();
            notificationCom.setPartners(partnerCommercialMan);
            notificationCom.setRead(false);
            notificationCom.setText(ret);

            try {
                new WorkWithNotificationCom().addEntity((Object)notificationCom);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return ret;
    }

    public static String addNotifHtmlForRevertComandWorkCom(PartnerCommercialMan partnerCommercialMan, String adressPage, Project project, int level, String reason)
    {
        String ret = "";

        if (partnerCommercialMan!=null && !adressPage.equals(""))
        {
            String port = ( Executions.getCurrent().getServerPort() == 80 ) ? "" : (":" + Executions.getCurrent().getServerPort());
            String url = Executions.getCurrent().getScheme() + "://" + Executions.getCurrent().getServerName() + port + Executions.getCurrent().getContextPath();

            ret+="<div>Исполнителем Проекта <a href=\""+url
                    +adressPage+"?id="+project.getId()+"\">"+project.getName()+"</a>, "+ partnerCommercialMan.getName()+" была завершена работа"+"</div>";

            NotificationCom notificationCom = new NotificationCom();
            notificationCom.setPartners(partnerCommercialMan);
            notificationCom.setRead(false);
            notificationCom.setText(ret);

            for (ComandProject com : project.getComandProject())
                if (com.equals(partnerCommercialMan))
                {
                    com.setWork(false);
                    try {
                        new WorkWithComandProject().changeEntity(com);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            try {
                new WorkWithNotificationCom().addEntity((Object)notificationCom);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return ret;
    }

    public static String revertNotifHtmlForRevertComandWorkCom(PartnerCommercialMan partnerCommercialMan, String adressPage, Project project, int level, String reason)
    {
        String ret = "";

        if (partnerCommercialMan!=null && !adressPage.equals(""))
        {
            String levelName = "";

            switch (level)
            {
                case 1 : levelName = "Создание";
                    break;
                case 2 : levelName = "Планирование";
                    break;
                case 3 : levelName = "На согласование";
                    break;
                case 4 : levelName = "Выполнение";
                    break;
                case 5 : levelName = "";
                    break;
            }
            String port = ( Executions.getCurrent().getServerPort() == 80 ) ? "" : (":" + Executions.getCurrent().getServerPort());
            String url = Executions.getCurrent().getScheme() + "://" + Executions.getCurrent().getServerName() + port + Executions.getCurrent().getContextPath();

            ret+="<div>Вам на "+levelName+" вернулся Проект <a href=\""+url
                    +adressPage+"?id="+project.getId()+"\">"+project.getName()+"</a>"+".\n Возвращено по причине: "+reason+"</div>";

            NotificationCom notificationCom = new NotificationCom();
            notificationCom.setPartners(partnerCommercialMan);
            notificationCom.setRead(false);
            notificationCom.setText(ret);

            for (ComandProject com : project.getComandProject())
                if (com.equals(partnerCommercialMan))
                {
                    com.setWork(true);
                    try {
                        new WorkWithComandProject().changeEntity(com);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            try {
                new WorkWithNotificationCom().addEntity((Object)notificationCom);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return ret;
    }
}
