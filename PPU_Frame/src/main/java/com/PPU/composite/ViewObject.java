package com.PPU.composite;

import com.PPU.DB.workLogic.ClassInvokeCall;
import com.PPU.DB.workLogic.WorkWithTable;
import com.PPU.Logic.*;
import com.PPU.composite.helper.*;
import com.PPU.windowControllers.*;
import org.hibernate.collection.PersistentSet;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.IdSpace;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.MouseEvent;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Window;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by user on 28.04.14.
 */
public class ViewObject extends Window implements IdSpace{
    private static final long serialVersionUID = -4653481165297843651L;

    private Object objs2;
    private boolean load;

    @Wire
    ObjectListBox listView;

    public ViewObject()
    {
        super();
    }

    public Object getObjs2() {
        return objs2;
    }

    public void setObjs2(Object objs2) {
        if (objs2 instanceof PersistentSet)
            this.objs2 = ((PersistentSet) objs2).toArray();
        else
            this.objs2 = objs2;
    }

    public boolean isLoad() {
        return load;
    }

    public void setLoad(boolean load) {
        this.load = load;

        Executions.createComponents("/pages/composite/viewObject.zul", this, null);
        Selectors.wireComponents(this, this, false);
        Selectors.wireEventListeners(this,this);
    }
}
