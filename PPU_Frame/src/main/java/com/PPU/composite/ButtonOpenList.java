package com.PPU.composite;

import org.zkoss.zk.ui.IdSpace;
import org.zkoss.zul.Button;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 02.05.14
 * Time: 10:02
 * To change this template use File | Settings | File Templates.
 */
public class ButtonOpenList extends Button implements IdSpace {

    private Object obj;
    private String workerName = new String();

    public ButtonOpenList()
    {
        super();
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public String getWorkerName() {
        return workerName;
    }

    public void setWorkerName(String workerName) {
        this.workerName = workerName;
    }
}
