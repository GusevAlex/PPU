package com.PPU.composite;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.IdSpace;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Div;
import org.zkoss.zul.Grid;
import org.zkoss.zul.ListModelList;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 03.05.14
 * Time: 16:27
 * To change this template use File | Settings | File Templates.
 */
public class GridReviewProject extends Div implements IdSpace {
    private static final long serialVersionUID = -4653481165297843651L;

    private List model;

    @Wire
    Grid gridProject;

    public GridReviewProject()
    {
        super();

        Executions.createComponents("/pages/composite/GridReviewProject.zul", this, null);
        Selectors.wireComponents(this, this, false);
        Selectors.wireEventListeners(this,this);
    }

    public List getModel() {
        return model;
    }

    public void setModel(List model) {
        this.model = model;

        gridProject.setModel(new ListModelList<Object>(model));


    }
}
