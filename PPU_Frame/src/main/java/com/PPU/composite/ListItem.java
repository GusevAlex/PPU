package com.PPU.composite;

import org.zkoss.zul.Listitem;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 02.05.14
 * Time: 22:08
 * To change this template use File | Settings | File Templates.
 */
public class ListItem extends Listitem {

    private int idObj;

    public ListItem()
    {
        super();
    }

    public int getIdObj() {
        return idObj;
    }

    public void setIdObj(int idObj) {
        this.idObj = idObj;
    }
}
