package com.PPU.composite;

import com.PPU.DB.workLogic.WorkWithPartnerMZ;
import com.PPU.Logic.*;
import com.PPU.composite.helper.*;
import com.PPU.windowControllers.*;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.IdSpace;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Window;

import java.io.BufferedWriter;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Alex on 27.04.2014.
 */
public class ViewWindow extends Window implements IdSpace {
    private static final long serialVersionUID = -4653481165297843651L;

    private List<String> header = new ArrayList<String>(10);
    private List<ListCellContant> listCellContant = new ArrayList<ListCellContant>();

    private Object [] obj;

    private boolean load;

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

    public void setParamForList(Object obj)
    {
		AnnotHelper annotHelper = new AnnotHelper();

		annotHelper.setParamForList(obj);

		setHeader(annotHelper.getHeader());
		setListCellContant(annotHelper.getListCellContant());
    }

    public Object[] getObj() {
        return obj;
    }

    public void setObj(Object[] obj) {
        this.obj = obj;

        if (obj.length == 0) return;

        setParamForList(obj[0]);
    }

    public boolean isLoad() {
        return load;
    }

    public void setLoad(boolean load) {
        this.load = load;
        Executions.createComponents("/pages/composite/viewWindow.zul", this, null);
        Selectors.wireComponents(this, this, false);
		Selectors.wireEventListeners(this, this);
    }

    public static void showWindow()
    {
        ViewWindow window = (ViewWindow) Executions.createComponents(
                "/pages/window/window.zul", null, null);

        WorkWithPartnerMZ work = new WorkWithPartnerMZ();
        Object [] objs = work.getListRows().toArray();
        window.setObj(objs);
        window.setLoad(true);
        window.doModal();
    }
}
