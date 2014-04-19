package com.PPU.registrAuthoriz;

import com.PPU.DB.workLogic.WorkWithUser;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Label;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;
import org.zkoss.zk.ui.util.Clients;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Alex on 19.04.2014.
 */
public class registrAuthoriz extends SelectorComposer<Component> {

    @Wire
    Window loginWin;

    @Wire
    Label mesg;

    @Wire
    Textbox pwd;

    @Wire
    Textbox name;

    public registrAuthoriz()
    {

    }


    @Init
    public void init()
    {

    }

    @Listen("onClick = #loginOK")
    public void clickLoginOK()
    {
        WorkWithUser workWithUser = new WorkWithUser();
        if (workWithUser.checkLoginAndPassword(name.getValue(), pwd.getValue())) {
            mesg.setValue("");

            Session session = Sessions.getCurrent();
            session.setAttribute("login",name.getValue());

            Clients.evalJavaScript("zk.Widget.$('$loginWin').setMinimized(true)");
        } else {
            mesg.setValue("Введены неверные имя пользователя и пароль!");
            Clients.evalJavaScript("loginFailed()");
        }

    }
}
