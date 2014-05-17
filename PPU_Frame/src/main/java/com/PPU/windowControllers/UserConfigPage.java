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
import org.zkoss.zul.Checkbox;
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

    private String oldLogin = new String();
    private String loginStr = new String();
    private String typeOrg = new String();
    private String nameUser = new String();
    private String emailStr = new String();
    private String hashPassword = new String();
    private boolean sendMail;
    private String oldPassword = new String();
    private String newPassword = new String();

    @Wire(value = "#login")
    Textbox loginText;

    @Wire(value = "#name")
    Textbox nameText;

    @Wire(value = "#oldPasword")
    Textbox oldPaswordText;

    @Wire(value = "#paswordText")
    Textbox paswordText;

    @Wire(value = "#email")
    Textbox emailText;

    @Wire
    Checkbox sendNotif;

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

    public boolean isSendMail() {
        return sendMail;
    }

    public void setSendMail(boolean sendMail) {
        this.sendMail = sendMail;
    }

    public String getHashPassword() {
        return hashPassword;
    }

    public void setHashPassword(String hashPassword) {
        this.hashPassword = hashPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public UserConfigPage()
    {
        Session session = Sessions.getCurrent();
        loginStr = (String) session.getAttribute("login");
        oldLogin = loginStr;
        typeOrg = (String) session.getAttribute("type");

        Object userObj = new WorkWithUser().findAndGetAllRow("login", oldLogin).get(0);

        if (userObj instanceof UsersMunMan)
        {
            sendMail = ((UsersMunMan) userObj).isSendMail();
            hashPassword = ((UsersMunMan) userObj).getHash();
            emailStr = ((UsersMunMan) userObj).getEmail();
            nameUser = ((UsersMunMan) userObj).getName();
        }
        else
            if (userObj instanceof UsersComMan)
            {
                sendMail = ((UsersComMan) userObj).isSendMail();
                hashPassword = ((UsersComMan) userObj).getHash();
                emailStr = ((UsersComMan) userObj).getEmail();
                nameUser = ((UsersComMan) userObj).getName();
            }

        if (emailStr == null)
            emailStr = new String();
    }

    public boolean checkAll()
    {
        boolean success = true;

        String loginString = loginText.getValue();
        String paswordString = paswordText.getValue();
        String nameString = nameText.getValue();
        String emailString = emailText.getValue();
        String oldPaswordString = oldPaswordText.getValue();

        List listUser = new WorkWithUser().findAndGetAllRow("login", oldLogin);

        Object objUser = new Object();

        if (loginString.length() == 0)
        {
            Messagebox.show("Не введен логин!", "Error", Messagebox.OK, Messagebox.ERROR);
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

        if (!registrAuthoriz.checkEmail(emailString))
        {
            Messagebox.show("Введена неверная почта!", "Error", Messagebox.OK, Messagebox.ERROR);
            success = false;
        }

        if (listUser.size() != 0)
        {
            objUser = listUser.get(0);

            if (oldPaswordString.length() != 0)
            {
                if (paswordString.length() == 0)
                {
                    Messagebox.show("Не введен новый пароль!", "Error", Messagebox.OK, Messagebox.ERROR);
                    success = false;
                }

                if (objUser instanceof UsersMunMan)
                {
                    if (!checkMailUser(oldPaswordString))
                    {
                        Messagebox.show("Введен неверный старый пароль!", "Error", Messagebox.OK, Messagebox.ERROR);
                        success = false;
                    }
                }
                else
                if (objUser instanceof UsersComMan)
                {
                    if (!checkMailUser(oldPaswordString))
                    {
                        Messagebox.show("Введен неверный старый пароль!", "Error", Messagebox.OK, Messagebox.ERROR);
                        success = false;

                    }
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

                    if (paswordText.getValue().length()>0)
                        ((UsersMunMan)objUser).setHashByPassword(paswordText.getValue());

                    ((UsersMunMan)objUser).setName(nameText.getValue());
                    ((UsersMunMan)objUser).setEmail(emailText.getValue());
                    ((UsersMunMan)objUser).setSendMail(sendNotif.isChecked());

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

                        if (paswordText.getValue().length()>0)
                            ((UsersComMan)objUser).setHashByPassword(paswordText.getValue());

                        ((UsersComMan)objUser).setName(nameText.getValue());
                        ((UsersComMan)objUser).setEmail(emailText.getValue());
                        ((UsersComMan)objUser).setSendMail(sendNotif.isChecked());

                        try {
                            new WorkWithUser().changeEntity(objUser);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        session.setAttribute("login", loginText.getValue());
                        session.setAttribute("nameUser", nameText.getName());
                        session.setAttribute("email", emailText.getValue());
                    }

                Messagebox.show("Изменения сохранены", "Info", Messagebox.OK, Messagebox.ERROR);
            }
        }

    }

    public boolean checkMailUser(String password)
    {
        return new WorkWithUser().checkLoginAndPassword(oldLogin, password);
    }

}
