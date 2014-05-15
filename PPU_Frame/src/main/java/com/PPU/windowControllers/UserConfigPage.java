package com.PPU.windowControllers;

import com.PPU.DB.security.MD5;
import com.PPU.DB.tables.UsersComMan;
import com.PPU.DB.tables.UsersMunMan;
import com.PPU.DB.workLogic.WorkWithUser;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import java.util.List;

import com.PPU.registrAuthoriz.*;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 02.05.14
 * Time: 20:39
 * To change this template use File | Settings | File Templates.
 */
public class UserConfigPage  extends SelectorComposer<Component> {

    private String oldLogin;
    private String loginStr;
    private String typeOrg;
    private String nameUser;
    private String emailStr;

    @Wire(value = "#login")
    Textbox loginText;

    @Wire(value = "#name")
    Textbox nameText;

    @Wire(value = "#oldPasword")
    Textbox oldPaswordText;

    @Wire(value = "#pasword")
    Textbox paswordText;

    @Wire(value = "#email")
    Textbox emailText;

    public String getTypeOrg() {
        return typeOrg;
    }

    public void setTypeOrg(String typeOrg) {
        this.typeOrg = typeOrg;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getEmailStr() {
        return emailStr;
    }

    public void setEmailStr(String emailStr) {
        this.emailStr = emailStr;
    }

    public String getLoginStr() {
        return loginStr;
    }

    public void setLoginStr(String loginStr) {
        this.loginStr = loginStr;
    }


    public UserConfigPage()
    {
        Session session = Sessions.getCurrent();
        loginStr = (String) session.getAttribute("login");
        oldLogin = loginStr;
        typeOrg = (String) session.getAttribute("type");
        nameUser = (String) session.getAttribute("nameUser");
        emailStr = (String) session.getAttribute("email");
    }

    public boolean checkAll()
    {
        boolean success = true;

        String loginString = loginText.getValue();
        String paswordString = paswordText.getValue();
        String nameString = nameText.getName();
        String emailString = emailText.getValue();
        String oldPaswordString = oldPaswordText.getValue();

        List listUser = new WorkWithUser().findAndGetAllRow("login", oldLogin);

        Object objUser = new Object();

        if (loginString.length() == 0)
        {
            Messagebox.show("Не введен логин!", "Error", Messagebox.OK, Messagebox.ERROR);
            success = false;
        }

        if (paswordString.length() == 0)
        {
            Messagebox.show("Не введен новый пароль!", "Error", Messagebox.OK, Messagebox.ERROR);
            success = false;
        }

        if (nameString.length() == 0)
        {
            Messagebox.show("Не введено имя!", "Error", Messagebox.OK, Messagebox.ERROR);
            success = false;
        }

        if (emailString.length() == 0)
        {
            Messagebox.show("Не введена почта!", "Error", Messagebox.OK, Messagebox.ERROR);
            success = false;
        }

        if (oldPaswordString.length() == 0)
        {
            Messagebox.show("Не введен старый пароль!", "Error", Messagebox.OK, Messagebox.ERROR);
            success = false;
        }

        if (!registrAuthoriz.checkEmail(emailString))
        {
            Messagebox.show("Введена неверная почта!", "Error", Messagebox.OK, Messagebox.ERROR);
            success = false;
        }

        if (listUser.size() != 0)
        {
            objUser = listUser.get(0);

            if (objUser instanceof UsersMunMan)
            {
                if (!MD5.getMD5(oldPaswordString).equals(((UsersMunMan)objUser).getHash()))
                {
                    Messagebox.show("Введен неверный старый пароль!", "Error", Messagebox.OK, Messagebox.ERROR);
                    success = false;
                }
            }
            else
            if (objUser instanceof UsersComMan)
            {
                if (!MD5.getMD5(oldPaswordString).equals(((UsersComMan)objUser).getHash()))
                {
                    Messagebox.show("Введен неверный старый пароль!", "Error", Messagebox.OK, Messagebox.ERROR);
                    success = false;
                }
            }
        }

        return success;
    }

    @Listen("onClick = #saveBtn")
    public void saveBtnOnClick()
    {
        Session session = Sessions.getCurrent();

        if (checkAll())
        {

            List listUser = new WorkWithUser().findAndGetAllRow("login", oldLogin);

            Object objUser = new Object();

            if (listUser.size() != 0)
            {
                objUser = listUser.get(0);

                if (objUser instanceof UsersMunMan)
                {
                    ((UsersMunMan)objUser).setLogin(loginText.getValue());
                    ((UsersMunMan)objUser).setHash(paswordText.getValue());
                    ((UsersMunMan)objUser).setName(nameText.getName());
                    ((UsersMunMan)objUser).setEmail(emailText.getValue());

                    try {
                        new WorkWithUser().changeEntity(objUser);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    session.setAttribute("login", loginText.getValue());
                    session.setAttribute("nameUser", nameText.getName());
                    session.setAttribute("email", emailText.getValue());
                }
                else
                    if (objUser instanceof UsersComMan)
                    {
                        ((UsersComMan)objUser).setLogin(loginText.getValue());
                        ((UsersComMan)objUser).setHash(paswordText.getValue());
                        ((UsersComMan)objUser).setName(nameText.getName());
                        ((UsersComMan)objUser).setEmail(emailText.getValue());

                        try {
                            new WorkWithUser().changeEntity(objUser);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        session.setAttribute("login", loginText.getValue());
                        session.setAttribute("nameUser", nameText.getName());
                        session.setAttribute("email", emailText.getValue());
                    }
            }
        }

    }

}
