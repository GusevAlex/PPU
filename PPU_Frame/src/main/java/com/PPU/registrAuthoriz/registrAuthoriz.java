package com.PPU.registrAuthoriz;

import com.PPU.DB.security.MD5;
import com.PPU.DB.tables.PartnerCommercialMan;
import com.PPU.DB.tables.PartnersMZ;
import com.PPU.DB.tables.UsersComMan;
import com.PPU.DB.workLogic.WorkWithCommandMz;
import com.PPU.DB.workLogic.WorkWithPartnerCommerc;
import com.PPU.DB.workLogic.WorkWithPartnerMZ;
import com.PPU.DB.workLogic.WorkWithUser;
import com.PPU.composite.Contact;
import com.PPU.composite.MenuItem;
import com.PPU.windowControllers.ModalDialog;
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

            Session session = Sessions.getCurrent();
            session.setAttribute("login",name.getValue());

            if (list.get(0) instanceof UsersComMan)
                session.setAttribute("type","Com");
            else
                session.setAttribute("type","Mun");

            Executions.sendRedirect("/index.zul");
        } else {
            mesg.setValue("¬ведены неверные им€ пользовател€ и пароль!");
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
			Clients.showNotification("¬веден неверный email","info", emailField, "end_before", 3000);
    }

	@Listen("onChange = #password")
	public void showMessageByPassword()
	{
		if (passwordField.getValue().length()<5)
			Clients.showNotification("¬веден короткий пароль", passwordField);
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
        ModalDialog modalDialog = new ModalDialog();

        modalDialog.showList(new WorkWithPartnerMZ().findAndGetAllRow("", "").toArray());
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
}
