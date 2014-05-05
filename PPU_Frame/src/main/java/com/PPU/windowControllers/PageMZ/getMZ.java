package com.PPU.windowControllers.PageMZ;

import com.PPU.DB.tables.LimitsMZ;
import com.PPU.DB.tables.MZ;
import com.PPU.DB.tables.Parametrs;
import com.PPU.DB.workLogic.WorkWithMZ;
import com.PPU.DB.workLogic.WorkWithProgramMZ;
import com.PPU.DB.workLogic.WorkWithTypeServiceMZ;
import com.PPU.composite.Contact;
import com.PPU.composite.MenuItem;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Toolbarbutton;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Alex on 05.05.2014.
 */
public class getMZ {

    private int id;

    String mzName;

    String program;

    String name;
    Date startDate;
    Date expirationDate;
    String description;
    float budget;

    Object comandMZ;
    Object[] program1;
    WorkWithProgramMZ workerProgr = new WorkWithProgramMZ();

    String typeServiceMZ;

    List listTypeService;

    Object [] limitsMZ;

    Object [] parametr;

    Object valuesParametrForMZ;
    Object resourcesMZ;
    Object correctionsMZ;

    public getMZ()
    {

    }

    public String getMzName() {
        return mzName;
    }

    public void setMzName(String mzName) {
        this.mzName = mzName;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getBudget() {
        return budget;
    }

    public void setBudget(float budget) {
        this.budget = budget;
    }

    public Object getComandMZ() {
        return comandMZ;
    }

    public void setComandMZ(Object comandMZ) {
        this.comandMZ = comandMZ;
    }

    public Object[] getProgram1() {
        return program1;
    }

    public void setProgram1(Object[] program1) {
        this.program1 = program1;
    }

    public WorkWithProgramMZ getWorkerProgr() {
        return workerProgr;
    }

    public void setWorkerProgr(WorkWithProgramMZ workerProgr) {
        this.workerProgr = workerProgr;
    }

    public String getTypeServiceMZ() {
        return typeServiceMZ;
    }

    public void setTypeServiceMZ(String typeServiceMZ) {
        this.typeServiceMZ = typeServiceMZ;
    }

    public List getListTypeService() {
        return listTypeService;
    }

    public void setListTypeService(List listTypeService) {
        this.listTypeService = listTypeService;
    }

    public Object[] getLimitsMZ() {
        return limitsMZ;
    }

    public void setLimitsMZ(Object[] limitsMZ) {
        this.limitsMZ = limitsMZ;
    }

    public Object[] getParametr() {
        return parametr;
    }

    public void setParametr(Object[] parametr) {
        this.parametr = parametr;
    }

    public Object getValuesParametrForMZ() {
        return valuesParametrForMZ;
    }

    public void setValuesParametrForMZ(Object valuesParametrForMZ) {
        this.valuesParametrForMZ = valuesParametrForMZ;
    }

    public Object getResourcesMZ() {
        return resourcesMZ;
    }

    public void setResourcesMZ(Object resourcesMZ) {
        this.resourcesMZ = resourcesMZ;
    }

    public Object getCorrectionsMZ() {
        return correctionsMZ;
    }

    public void setCorrectionsMZ(Object correctionsMZ) {
        this.correctionsMZ = correctionsMZ;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;

        MZ MZ = ((com.PPU.DB.tables.MZ) new WorkWithMZ().getEntity(id));
        
        mzName = "Муниципальное задание: " + MZ.getName();
        
        program = "Программа" + MZ.getProgram().getName();

        name = MZ.getName();
        startDate = MZ.getStartDate();
        expirationDate = MZ.getExpirationDate();
        description = MZ.getDescription();
        budget = MZ.getBudget();

        comandMZ = (Object) MZ.getComandMZ().toArray();
        program1 = new Object[1];
        program1[0] = (Object) MZ.getProgram();

        typeServiceMZ = (String) MZ.getTypeServiceMZ().getName();

        listTypeService = new WorkWithTypeServiceMZ().getListRows();

        limitsMZ =  MZ.getLimitsMZ().toArray();

        List parametrsList = new ArrayList();

        for (Object l : limitsMZ)
            parametrsList.add(((LimitsMZ)l).getParametr());

        parametr = parametrsList.toArray();

        valuesParametrForMZ = (Object) MZ.getValuesParametrForMZ().toArray();
        resourcesMZ = (Object) MZ.getResourcesMZ().toArray();
        correctionsMZ = (Object) MZ.getCorrectionsMZ().toArray();
    }

    @Command
    public void onClickBtn1(@ContextParam(ContextType.COMPONENT) Component comp)
    {
        int y = 0;
    }
}
