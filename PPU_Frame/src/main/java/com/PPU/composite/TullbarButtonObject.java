package com.PPU.composite;

import org.zkoss.zul.Toolbarbutton;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 18.05.14
 * Time: 23:08
 * To change this template use File | Settings | File Templates.
 */
public class TullbarButtonObject extends Toolbarbutton {

    private Object obj;

    public TullbarButtonObject()
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
