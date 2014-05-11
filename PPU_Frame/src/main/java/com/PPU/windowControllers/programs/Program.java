package com.PPU.windowControllers.programs;

import com.PPU.DB.tables.*;
import com.PPU.DB.workLogic.WorkWithProgramCommerc;
import com.PPU.DB.workLogic.WorkWithProgramMZ;
import com.PPU.DB.workLogic.WorkWithUser;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zul.Messagebox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Alex on 11.05.2014.
 */
public class Program {

    private ProgramMZ programMU = new ProgramMZ();
    private ProgramCommerc programCom = new ProgramCommerc();
    private boolean enableAll = true;
    private String handName;
    private String discrName;
    private boolean mu = true;
    private List listMZ = new ArrayList();
    private List listProject = new ArrayList();

    public Program()
    {
        String progr = Executions.getCurrent().getParameter("progr");

        if (progr != null)
        {
            Object obj = new WorkWithUser().findAndGetAllRow("login", (String) Sessions.getCurrent().getAttribute("login"));

            if (((List)obj).size()!=0)
                if (((List) obj).get(0) instanceof UsersMunMan)
                {
                    programMU = (ProgramMZ) new WorkWithProgramMZ().getEntity(new Integer(progr));
                    handName = "Программа: "+programMU.getName();
                    discrName = "Создано: "+programMU.getPartnersMZ().getName();
                    mu = true;
                    listMZ = Arrays.asList(programMU.getMZ().toArray());

                    PartnersMZ part = ((UsersMunMan) ((List) obj).get(0)).getPartnerMZ();
                    if (programMU.getPartnersMZ().equals(part))
                        enableAll = true;
                    else
                        enableAll = false;
                }
                else
                if (((List) obj).get(0) instanceof UsersComMan)
                {
                    programCom = (ProgramCommerc) new WorkWithProgramCommerc().getEntity(new Integer(progr));
                    handName = "Программа: "+programCom.getName();
                    discrName = "Создано: "+programCom.getPartnerCommercialMan().getName();
                    mu = false;
                    listProject = Arrays.asList(programCom.getProjects().toArray());

                    PartnerCommercialMan part = ((UsersComMan) ((List)obj).get(0)).getPartnerProject();
                    if (programMU.getPartnersMZ().equals(part))
                        enableAll = true;
                    else
                        enableAll = false;
                }
        }
    }

    public ProgramMZ getProgramMU() {
        return programMU;
    }

    public void setProgramMU(ProgramMZ programMU) {
        this.programMU = programMU;
    }

    public ProgramCommerc getProgramCom() {
        return programCom;
    }

    public void setProgramCom(ProgramCommerc programCom) {
        this.programCom = programCom;
    }

    public boolean isEnableAll() {
        return enableAll;
    }

    public void setEnableAll(boolean enableAll) {
        this.enableAll = enableAll;
    }

    public String getHandName() {
        return handName;
    }

    public void setHandName(String handName) {
        this.handName = handName;
    }

    public String getDiscrName() {
        return discrName;
    }

    public void setDiscrName(String discrName) {
        this.discrName = discrName;
    }

    public boolean isMu() {
        return mu;
    }

    public void setMu(boolean mu) {
        this.mu = mu;
    }

    public List getListMZ() {
        return listMZ;
    }

    public void setListMZ(List listMZ) {
        this.listMZ = listMZ;
    }

    public List getListProject() {
        return listProject;
    }

    public void setListProject(List listProject) {
        this.listProject = listProject;
    }

    @Listen("onClick = #saveProgrMU")
    public void onClickSaveProgrMU()
    {
        if (programMU.getId() == 0)
            try {
                new WorkWithProgramMZ().addEntity(programMU);
            } catch (Exception e) {
                e.printStackTrace();
            }
        else
            try {
                new WorkWithProgramMZ().changeEntity(programMU);
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    @Listen("onClick = #saveProgrCommerc")
    public void onClickSaveProgrCommerc()
    {
        if (programCom.getId() == 0)
            try {
                new WorkWithProgramCommerc().addEntity(programCom);
            } catch (Exception e) {
                e.printStackTrace();
            }
        else
            try {
                new WorkWithProgramCommerc().changeEntity(programCom);
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    @Listen("onClick = #addMZ")
    public void onClickAddMZ()
    {
        if (programMU.getId() == 0)
            Messagebox.show("Сначала сохраните программу!", "Ошибка", Messagebox.OK, Messagebox.ERROR);
        else
            Executions.sendRedirect("/pages/pagesMZ/MZ.zul?id=0&progr="+programMU.getId());
    }

    @Listen("onClick = #addProject")
    public void onClickAddProject()
    {
        if (programMU.getId() == 0)
            Messagebox.show("Сначала сохраните программу!", "Ошибка", Messagebox.OK, Messagebox.ERROR);
        else
            Executions.sendRedirect("/pages/pagesProject/project.zul?id=0&progr="+programMU.getId());
    }

    @AfterCompose
    public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
        Selectors.wireComponents(view, this, false);
        Selectors.wireEventListeners(view, this);
    }
}
