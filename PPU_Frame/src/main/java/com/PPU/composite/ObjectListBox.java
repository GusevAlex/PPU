package com.PPU.composite;

import com.PPU.windowControllers.ListCellContant;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.IdSpace;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zul.Listbox;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 27.04.2014.
 */
public class ObjectListBox extends Listbox implements IdSpace {
    private static final long serialVersionUID = -4653481165297843651L;

    private List<String> header = new ArrayList<String>(10);
    private List<ListCellContant> listCellContant = new ArrayList<ListCellContant>();

    public ObjectListBox()
    {
        Executions.createComponents("/pages/composite/ObjectListBox.zul", this, null);
        Selectors.wireComponents(this, this, false);
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
}
