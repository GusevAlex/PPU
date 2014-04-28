package com.PPU.composite;

import com.PPU.Logic.AnotationService;
import com.PPU.composite.helper.AnnotHelper;
import com.PPU.windowControllers.ListCellContant;
import org.aspectj.lang.annotation.Before;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.IdSpace;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listhead;
import org.zkoss.zul.Listheader;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Alex on 27.04.2014.
 */
public class ObjectListBox extends Listbox implements IdSpace {
    private static final long serialVersionUID = -4653481165297843651L;

    private List<String> header = new ArrayList<String>(10);
    private List<ListCellContant> listCellContant = new ArrayList<ListCellContant>();
    private Object [] objs;
    private static String gh = "dfdfg";
    private boolean load;

     public ObjectListBox()
    {

    }

    public List<String> getHeader() {
        return header;
    }

    public void setHeader(List<String> header) {
        this.header = header;
    }

    public List<ListCellContant> getListCellContant() {
        return listCellContant;
    }

    public void setListCellContant(List<ListCellContant> listCellContant) {
        this.listCellContant = listCellContant;
    }

    public Object[] getObjs() {
        return objs;
    }

    public void setObjs(Object[] objs) {
        this.objs = objs;

        if (objs.length!=0)
            setParamForList(objs[0]);
    }

    public String getGh() {
        return gh;
    }

    public void setGh(String gh) {
        this.gh = gh;
    }

    public boolean getLoad() {
        return load;
    }

    public void setParamForList(Object obj)
    {
		AnnotHelper annotHelper = new AnnotHelper();

		annotHelper.setParamForList(obj);

		setHeader(annotHelper.getHeader());
		setListCellContant(annotHelper.getListCellContant());
    }

    public void setLoad(boolean load) {
        this.load = load;
        Executions.createComponents("/pages/composite/ObjectListBox.zul", this, null);
        Selectors.wireComponents(this, this, false);
    }
}
