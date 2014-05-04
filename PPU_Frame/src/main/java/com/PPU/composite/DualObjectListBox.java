package com.PPU.composite;

import com.PPU.DB.tables.PartnersMZ;
import com.PPU.DB.workLogic.ClassInvokeCall;
import com.PPU.DB.workLogic.WorkWithTable;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.IdSpace;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Div;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;

import java.lang.String;
import java.util.ArrayList;
import java.util.Arrays;
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

        if (leftListbox != null)
            leftListbox.setObjs(leftList);
    }

    public Object[] getRightList() {
        return rightList;
    }

    public Object[] getRightListWithoutEmptyFirst() {
        Object [] mas = new Object[rightList.length-1];

        for (int i=0; i<rightList.length-1; i++)
            mas[i] = rightList[i+1];

        return mas;
    }

    public void setRightList(Object[] rightList) {
        this.rightList = rightList;

        if (rightListbox != null)
            rightListbox.setObjs(rightList);
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public void reloadList(Object [] list)
    {
        if (list != null && list.length != 0)
        {
            int id = (Integer) ClassInvokeCall.callMethod(list[0],"getId");

            if (id == 0)
            {
                List list1 = Arrays.asList(list);

                list1.remove(0);
                list = list1.toArray();
            }
            else
            {
                Object obj = new Object();
                try {
                    obj = list[0].getClass().newInstance();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

                Object [] objs = new Object[list.length+1];

                objs[0] = obj;

                for (int i=0; i<list.length; i++)
                    objs[i+1] = list[i];

                list = objs;
            }
        }
        else
        {
            try
            {
            Object [] objs = new Object [1];

            if (leftList!=null && leftList.length!=0)
                objs[0] = leftList[0].getClass().newInstance();
             else if (rightList!=null && rightList.length!=0)
                objs[0] = rightList[0].getClass().newInstance();

                if (leftList == null || leftList.length == 0)
                    leftList = objs;
                else
                    rightList = objs;
            }
            catch (Exception e)
            {
                System.out.println(e);
            }
        }
    }

    public void compareAndDeleteInList()
    {
        reloadList(leftList);
        reloadList(rightList);

        List list = new ArrayList();

        for (int i=0; i<leftList.length; i++)
        {
            boolean isFind = false;

            for (int j=0; j<rightList.length; j++)
                if (leftList[i].equals(rightList[j]))
                {
                    isFind = true;
                }

            if (!isFind)
                list.add(leftList[i]);
        }

        leftList = list.toArray();

        reloadList(leftList);
    }

    private void loadDataInList()
    {
        WorkWithTable workTable = (WorkWithTable) ClassInvokeCall.returnWorkerByName(worker);

        List leftList1 = workTable.findAndGetAllRow("","");
        leftList = leftList1.toArray();

        compareAndDeleteInList();
    }

    @Listen("onClick = #chooseBtn")
    public void onChooseBtn()
    {
        leftList = leftListbox.getObjs();
        rightList = rightListbox.getObjs();
        //сначала добавляем
        for (Listitem selItem : leftListbox.getSelectedItems())
        {
            int selectIndex = selItem.getIndex();

            if (selectIndex != -1)
            {
                Object [] objs2 = new Object[rightList.length+1];

                for (int i=0; i<rightList.length; i++)
                    objs2[i] = rightList[i];

                objs2[rightList.length] = leftList[selectIndex];

                rightList = objs2;

                rightListbox.setObjs(rightList);
            }
        }

        //потом удаляем
        for (Listitem selItem : leftListbox.getSelectedItems())
        {
            int selectIndex = selItem.getIndex();

            if (selectIndex != -1)
            {
                Object [] objs = new Object[leftList.length-1];

                for (int i=0; i<selectIndex; i++)
                {
                    objs[i] = leftList[i];
                }

                for (int i=selectIndex; i<leftList.length-1; i++)
                {
                    objs[i] = leftList[i+1];
                }

                leftList = objs;

                leftListbox.setObjs(leftList);
            }
        }

        leftListbox.refresh();
        rightListbox.refresh();
    }

    @Listen("onClick = #removeBtn")
    public void onRemoveBtn()
    {
        leftList = leftListbox.getObjs();
        rightList = rightListbox.getObjs();
        //сначала добавляем
        for (Listitem selItem : rightListbox.getSelectedItems())
        {
            int selectIndex = selItem.getIndex();

            if (selectIndex != -1)
            {
                Object [] objs2 = new Object[leftList.length+1];


                for (int i=0; i<leftList.length; i++)
                    objs2[i] = leftList[i];

                objs2[leftList.length] = rightList[selectIndex+1];

                leftList = objs2;

                leftListbox.setObjs(leftList);
            }
        }

        //потом удаляем
        for (Listitem selItem : rightListbox.getSelectedItems())
        {
            int selectIndex = selItem.getIndex();

            if (selectIndex != -1)
            {
                Object [] objs = new Object[rightList.length-1];


                for (int i=0; i<selectIndex+1; i++)
                    objs[i] = rightList[i];

                for (int i=selectIndex+1; i<rightList.length-1; i++)
                    objs[i] = rightList[i+1];


                rightList = objs;

                rightListbox.setObjs(rightList);
            }
        }

        rightListbox.refresh();
        leftListbox.refresh();
    }

    public Object [] getSelectedList()
    {
        return rightList;
    }
}
