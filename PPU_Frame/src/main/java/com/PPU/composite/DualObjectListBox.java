package com.PPU.composite;

import com.PPU.DB.workLogic.ClassInvokeCall;
import com.PPU.DB.workLogic.WorkWithTable;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.IdSpace;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Div;
import org.zkoss.zul.Listitem;

import java.lang.String;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 30.04.14
 * Time: 23:30
 * To change this template use File | Settings | File Templates.
 */
public class DualObjectListBox extends Div implements IdSpace {
    private static final long serialVersionUID = -4653481165297843651L;

    private String imgPath = "pages/composite/img";
    private String worker;
    private Object [] leftList;
    private Object [] rightList;
    private boolean load;
    private int rows;

    @Wire
    ObjectListBox leftListbox;

    @Wire
    ObjectListBox rightListbox;

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getWorker() {
        return worker;
    }

    public void setWorker(String worker) {
        this.worker = worker;
    }

    public boolean isLoad() {
        return load;
    }

    public void setLoad(boolean load) {
        this.load = load;

        loadDataInList();

        Executions.createComponents("/pages/composite/DualObjectListBox.zul", this, null);
        Selectors.wireComponents(this, this, false);
        Selectors.wireEventListeners(this,this);
    }

    public ObjectListBox getLeftListbox() {
        return leftListbox;
    }

    public void setLeftListbox(ObjectListBox leftListbox) {
        this.leftListbox = leftListbox;
    }

    public ObjectListBox getRightListbox() {
        return rightListbox;
    }

    public void setRightListbox(ObjectListBox rightListbox) {
        this.rightListbox = rightListbox;
    }

    public Object[] getLeftList() {
        return leftList;
    }

    public void setLeftList(Object[] leftList) {
        this.leftList = leftList;
    }

    public Object[] getRightList() {
        return rightList;
    }

    public void setRightList(Object[] rightList) {
        this.rightList = rightList;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    private void loadDataInList()
    {
        WorkWithTable workTable = (WorkWithTable) ClassInvokeCall.returnWorkerByName(worker);

        List leftList1 = workTable.findAndGetAllRow("","");

        List rightList1  = new ArrayList();
        rightList1.add(workTable.getEmptyEntity());

        leftList = leftList1.toArray();
        rightList = rightList1.toArray();
    }

    @Listen("onClick = #chooseAllBtn")
    public void onChooseAllBtn()
    {
        int selectIndex = leftListbox.getSelectedIndex();
        Listitem it = leftListbox.getSelectedItem();

        leftListbox.renderItem(it);

        Object o = it.getValue();
        if (selectIndex != -1)
        {
            List<Listitem> listItems = leftListbox.getItems();


        }
    }
}
