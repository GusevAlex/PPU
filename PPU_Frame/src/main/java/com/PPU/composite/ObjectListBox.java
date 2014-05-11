package com.PPU.composite;
import com.PPU.DB.tables.LimitsMZ;
import com.PPU.DB.tables.MZ;
import com.PPU.DB.tables.Parametrs;
import com.PPU.DB.workLogic.WorkWithProgramMZ;
import com.PPU.DB.workLogic.WorkWithTypeServiceMZ;
import com.PPU.composite.helper.*;
import com.PPU.windowControllers.*;
import com.sun.istack.internal.NotNull;
import org.hibernate.collection.PersistentSet;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.IdSpace;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zul.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Alex on 27.04.2014.
 */
public class ObjectListBox extends Listbox implements IdSpace {
    private static final long serialVersionUID = -4653481165297843651L;

    private List<String> header = new ArrayList<String>(10);
    private List<ListCellContant> listCellContant = new ArrayList<ListCellContant>();
    private Object [] objs;
    private Object selectedObjs;

    private static String gh = "dfdfg";
    private boolean load;

    private boolean loadHandler;

    private boolean loadAllSecondFildInRows = true;
    private boolean loadList = true;

    public ObjectListBox()
    {
        super();
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

    public void setObjs(@NotNull Object[] objs) {
        this.objs = objs;

        if (objs.length!=0)
            setParamForList(objs[0]);
        else
            loadList = false;
    }

    public Object getSelectedObjs() {
        return selectedObjs;
    }

    public void setSelectedObjs(Object selectedObjs) {
        this.selectedObjs = selectedObjs;
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
        removeChild();

        if (objs.length != 0)
            loadList = true;

        Executions.createComponents("/pages/composite/ObjectListBox.zul", this, null);
        Selectors.wireComponents(this, this, false);

        if (selectedObjs != null)
        for (int i=0; i<objs.length; i++)
        {
            if (objs[i].equals(selectedObjs))
            {
                setSelectedIndex(i);
            }
        }
    }

    public boolean isLoadAllSecondFildInRows() {
        return loadAllSecondFildInRows;
    }

    public void setLoadAllSecondFildInRows(boolean loadAllSecondFildInRows) {
        this.loadAllSecondFildInRows = loadAllSecondFildInRows;
    }

    public void removeChild()
    {
        this.loadHandler = true;

        List<Component> listChild = this.getChildren();
        for (int i=listChild.size()-1 ; i>=0; i--)
        {

            if (!(listChild.get(i) instanceof Paging))
            {
                if ((listChild.get(i) instanceof Listhead))
                {
                    this.loadHandler = false;
                }

                if ((listChild.get(i) instanceof Listitem))
                {
                    this.removeChild(listChild.get(i));
                }
            }

        }

    }

    public void refresh()
    {
        setLoad(true);
    }

    public boolean getLoadHandler() {
        return loadHandler;
    }

    public void setLoadHandler(boolean loadHandler) {
        this.loadHandler = loadHandler;
    }

    public boolean isLoadList() {
        return loadList;
    }

    public void setLoadList(boolean loadList) {
        this.loadList = loadList;
    }

    public int findAndGetEqObject(Object obj)
    {
        int result = -1;

        for (int i=0; i<objs.length; i++)
        {
            if (objs[i].equals(obj))
            {
                result = i;

                return result;
            }
        }

        return result;
    }

    public static void showViewObject(Object obj)
    {
        ViewObject view  = (ViewObject) Executions.createComponents(
                "/pages/window/viewWindowObjectDialog.zul", null, null);

        view.setId("viewWindowObject");
        view.setObjs2(obj);
        view.setLoad(true);

        view.doModal();
    }

    public static void showViewObject(PersistentSet obj)
    {
        ViewObject view  = (ViewObject) Executions.createComponents(
                "/pages/window/viewWindowObjectDialog.zul", null, null);

        //ViewObject view = new ViewObject();
        view.setId("viewWindowObject");
        view.setObjs2(obj);
        view.setLoad(true);

        view.doModal();
    }

    public static void showViewObject(Set obj)
    {
        ViewObject view  = (ViewObject) Executions.createComponents(
                "/pages/window/viewWindowObjectDialog.zul", null, null);

        //ViewObject view = new ViewObject();
        view.setId("viewWindowObject");
        view.setObjs2(obj);
        view.setLoad(true);

        view.doModal();

    }
}