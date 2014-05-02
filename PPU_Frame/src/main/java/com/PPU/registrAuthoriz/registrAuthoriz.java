package com.PPU.registrAuthoriz;

import com.PPU.DB.security.MD5;
import com.PPU.DB.tables.PartnerCommercialMan;
import com.PPU.DB.tables.PartnersMZ;
import com.PPU.DB.tables.UsersComMan;
import com.PPU.DB.tables.UsersMunMan;
import com.PPU.DB.workLogic.WorkWithCommandMz;
import com.PPU.DB.workLogic.WorkWithPartnerCommerc;
import com.PPU.DB.workLogic.WorkWithPartnerMZ;
import com.PPU.DB.workLogic.WorkWithUser;
import com.PPU.composite.*;
import com.PPU.windowControllers.*;
import org.zkoss.bind.annotation.*;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.*;
import org.zkoss.zk.ui.util.Clients;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    @Wire
    Textbox login;

    @Wire("#password")
	Textbox passwordField;

	@Wire("#email")
	Textbox emailField;

	@Wire
	Listbox listboxMun;

	@Wire
	Listbox listboxCommerc;

	@Wire
	Groupbox groupboxMZ;

	@Wire
	Groupbox groupboxCommerc;

    @Wire
    ObjectListBox listboxMun1;

    @Wire
    ObjectListBox listboxCom1;

    private String css = "../../css/common.css.dsp";
    private String nameUser;
    private String surnameUser;
    private String loginUser;
    private String passwordUser;
    private String email;
    private PartnersMZ partnersMZ;
    private PartnerCommercialMan partnerCommercialMan;

    public registrAuthoriz()
    {

    }


    @Init
    public void init()
    {

    }

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);

		if (listboxMun != null)
		{
			WorkWithPartnerMZ workWithPartnerMZ = new WorkWithPartnerMZ();
			listboxMun.setModel(new ListModelList<Object>(workWithPartnerMZ.getListRows()));
		}

		if (listboxCommerc != null)
		{
			WorkWithPartnerCommerc workWithPartnerCommerc = new WorkWithPartnerCommerc();
			listboxCommerc.setModel(new ListModelList<Object>(workWithPartnerCommerc.getListRows()));
		}
	}

	@Listen("onClick = #loginOK")
    public void clickLoginOK()
    {
        WorkWithUser workWithUser = new WorkWithUser();
        if (workWithUser.checkLoginAndPassword(name.getValue(), pwd.getValue())) {
            mesg.setValue("");

            List list = workWithUser.findAndGetAllRow("login", name.getValue());
            String nameUser = new String();

            if (list.get(0) instanceof UsersComMan)
                nameUser = ((UsersComMan) list.get(0)).getName();
            else
                nameUser = ((UsersMunMan) list.get(0)).getName();

            Session session = Sessions.getCurrent();
            session.setAttribute("login",name.getValue());
            session.setAttribute("nameUser",nameUser);

            if (list.get(0) instanceof UsersComMan)
                session.setAttribute("type","Com");
            else
                session.setAttribute("type","Mun");

            Executions.sendRedirect("/index.zul");
        } else {
            mesg.setValue("Введены неверные имя пользователя и пароль!");
            Clients.evalJavaScript("loginFailed()");
        }



    }

    public static boolean checkEmail(String email)
    {
        Pattern pattern = Pattern.compile("^[-a-z0-9!#$%&'*+/=?^_`{|}~]+(?:\\.[-a-z0-9!#$%&'*+/=?^_`{|}~]+)*@(?:[a-z0-9]([-a-z0-9]{0,61}[a-z0-9])?\\.)*(?:aero|arpa|asia|biz|cat|com|coop|edu|gov|info|int|jobs|mil|mobi|museum|name|net|org|pro|tel|travel|[a-z][a-z])$");
        Matcher matcher = pattern.matcher(email);

        return matcher.find();
    }

	@Listen("onChange = #email")
    public void showMessageByEmail()
    {
		if (!checkEmail(emailField.getValue()))
			Clients.showNotification("Введен неверный email","info", emailField, "end_before", 3000);
    }

	@Listen("onChange = #password")
	public void showMessageByPassword()
	{
		if (passwordField.getValue().length()<5)
			Clients.showNotification("Введен короткий пароль", passwordField);
	}

    public static void main(String[] args) {
        checkEmail("");
    }

    public String getCss() {
        return css;
    }

    public void setCss(String css) {
        this.css = css;
    }

    public PartnersMZ getPartnersMZ() {
        return partnersMZ;
    }

    public void setPartnersMZ(PartnersMZ partnersMZ) {
        this.partnersMZ = partnersMZ;
    }

    public PartnerCommercialMan getPartnerCommercialMan() {

        return partnerCommercialMan;
    }

    public void setPartnerCommercialMan(PartnerCommercialMan partnerCommercialMan) {
        this.partnerCommercialMan = partnerCommercialMan;
    }

	@Listen("onClick = #groupboxMZ")
	public void openCaptionMZ()
	{
		if (groupboxMZ.isOpen())
		{
			groupboxCommerc.setOpen(false);
		}
	}

	@Listen("onClick = #groupboxCommerc")
	public void openCaptionCommerc()
	{
		if (groupboxCommerc.isOpen())
		{
			groupboxMZ.setOpen(false);
		}
	}

    @Listen("onClick = #addMunMan")
    public void onClickAddMunMan()
    {
        AddObject add = new AddObject();

        add.setWorker(new WorkWithPartnerMZ());

        add.setObjectListBox(listboxMun1);

        add.showWindow();
    }

    public boolean checkRegister()
    {
        boolean success = true;

        String userName = name.getValue();
        String password = passwordField.getValue();
        String email = emailField.getValue();
        String loginVal = login.getValue();
        int selectedIndex = -1;

        if (groupboxMZ.isOpen())
        {
            selectedIndex = listboxMun1.getSelectedIndex();
        }
        else
            if (groupboxCommerc.isOpen())
                selectedIndex = listboxCom1.getSelectedIndex();

        if (userName.equals(""))
        {
            Messagebox.show("Не введены Фамилия и имя!", "Error", Messagebox.OK, Messagebox.ERROR);
            success = false;
        }

        if (password.length()<=4)
        {
            Messagebox.show("Введен короткий пароль!", "Error", Messagebox.OK, Messagebox.ERROR);
            success = false;
        }

        if (!checkEmail(email))
        {
            Messagebox.show("Введен неверный email!", "Error", Messagebox.OK, Messagebox.ERROR);
            success = false;
        }

        if (!groupboxMZ.isOpen() && !groupboxCommerc.isOpen())
        {
            Messagebox.show("Не выбран вид управления!", "Error", Messagebox.OK, Messagebox.ERROR);
            success = false;
        }
        else
        {
            if (selectedIndex == -1)
            {
                Messagebox.show("Не выбрана организация управления!", "Error", Messagebox.OK, Messagebox.ERROR);
                success = false;
            }
            Object objUser = new Object();

            if (groupboxMZ.isOpen())
            {
                objUser = new UsersMunMan();
                ((UsersMunMan)objUser).setLogin(loginVal);
            }

            if (groupboxCommerc.isOpen())
            {
                objUser = new UsersComMan();
                ((UsersComMan)objUser).setLogin(loginVal);
            }

            if (!(new WorkWithUser().checkLogin(objUser)))
            {
                Messagebox.show("Введен не уникальный логин!", "Error", Messagebox.OK, Messagebox.ERROR);
                success = false;
            }
        }
     return success;
    }



    @Listen("onClick = #registation")
    public void onClickRegistation()
    {
        if (checkRegister())
        {
            String userName = name.getValue();
            String password = passwordField.getValue();
            String email = emailField.getValue();
            String loginVal = login.getValue();

            Object objUser = new Object();

            if (groupboxMZ.isOpen())
            {
                objUser = new UsersMunMan();
                ((UsersMunMan)objUser).setLogin(loginVal);
                ((UsersMunMan)objUser).setHash(password);
                ((UsersMunMan)objUser).setName(userName);
                ((UsersMunMan)objUser).setEmail(email);

                int selectedIndex = listboxMun1.getSelectedIndex();

                ((UsersMunMan)objUser).setPartnerMZ( (PartnersMZ) listboxMun1.getObjs()[selectedIndex]);
            }

            if (groupboxCommerc.isOpen())
            {
                objUser = new UsersComMan();
                ((UsersComMan)objUser).setLogin(loginVal);
                ((UsersComMan)objUser).setHash(password);
                ((UsersComMan)objUser).setName(userName);
                ((UsersComMan)objUser).setEmail(email);

                int selectedIndex = listboxCom1.getSelectedIndex();

                ((UsersComMan)objUser).setPartnerProject((PartnerCommercialMan) listboxCom1.getObjs()[selectedIndex]);
            }

            try {
                new WorkWithUser().addEntity(objUser);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
