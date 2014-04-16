package com.PPU.funcControl;

import com.PPU.DB.tables.MZ;
import com.PPU.DB.workLogic.ClassInvokeCall;
import com.PPU.DB.workLogic.WorkWithMZ;
import com.PPU.DB.workLogic.WorkWithTable;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zul.ListModelList;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 16.04.14
 * Time: 20:55
 * To change this template use File | Settings | File Templates.
 */
public class reviewListMzController extends SelectorComposer<Component> {

    private WorkWithTable workWithMZ;
    private List<MZ> mzs;
    
    public reviewListMzController()
    {
        workWithMZ = new WorkWithMZ();

        mzs = workWithMZ.getListRows();
        int y =0;
    }
    
    public ListModelList<MZ> getMzs()
    {
        ListModelList n = new ListModelList<MZ>(mzs);
        return new ListModelList<MZ>(mzs);
    }
    
    public void setMzs(List mzs1)
    {
        this.mzs = mzs1;
    }

}
