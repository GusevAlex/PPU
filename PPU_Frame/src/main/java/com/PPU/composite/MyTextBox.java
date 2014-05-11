package com.PPU.composite;

import org.zkoss.zk.ui.IdSpace;
import org.zkoss.zul.Textbox;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 09.05.14
 * Time: 10:09
 * To change this template use File | Settings | File Templates.
 */
public class MyTextBox extends Textbox implements IdSpace {

    private Object obj;

    public MyTextBox()
    {
        super();
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}
