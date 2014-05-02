package com.PPU.composite;

import com.PPU.DB.workLogic.ClassInvokeCall;
import com.PPU.composite.helper.*;
import com.PPU.windowControllers.*;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.IdSpace;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Doublespinner;
import org.zkoss.zul.Spinner;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import java.util.*;

/**
 * Created by user on 28.04.14.
 */
public class AddObjectWindow extends Window implements IdSpace {
    private static final long serialVersionUID = -4653481165297843651L;

    @Wire
    Textbox text;

    @Wire
    ObjectListBox listboxV;

    @Wire
    DualObjectListBox dualListBoxObject;

    @Wire
    Spinner spinner;

    @Wire
    Doublespinner doubleSpinnerBox;

    private int countPage;
    private int numPage;

    private AddObject addObject;

    private String header;
    private ListCellContant listCellContant;
    private boolean load;

    private String workerName;

    private Object obj;

    private Object [] objs;

    public AddObjectWindow()
    {
        super();
    }

    public int getCountPage() {
        return countPage;
    }

    public void setCountPage(int countPage) {
        this.countPage = countPage;
    }

    public int getNumPage() {
        return numPage;
    }

    public void setNumPage(int numPage) {
        this.numPage = numPage;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public ListCellContant getListCellContant() {
        return listCellContant;
    }

    public void setListCellContant(ListCellContant listCellContant) {
        this.listCellContant = listCellContant;
    }

    public boolean isLoad() {
        return load;
    }

    public String getWorkerName() {
        return workerName;
    }

    public void setWorkerName(String workerName) {
        this.workerName = workerName;

        if (!workerName.equals(""))
        {
            Object y = AnnotHelper.callWorkerMethod(workerName, "getListRows");
            objs = ((ArrayList)AnnotHelper.callWorkerMethod(workerName, "getListRows")).toArray();
        }
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }



    public void setLoad(boolean load) {
        this.load = load;

        Executions.createComponents("/pages/composite/addObjectWindow.zul", this, null);
        Selectors.wireComponents(this, this, false);
        Selectors.wireEventListeners(this,this);
    }

    public Object[] getObjs() {
        return objs;
    }

    public void setObjs(Object[] objs) {
        this.objs = objs;
    }

    @Override
    public void doModal() {
        if (!workerName.equals(""))
            objs = ((ArrayList)AnnotHelper.callWorkerMethod(workerName, "getListRows")).toArray();

        super.doModal();
    }

    public void doModal(AddObject addObject) {
        this.addObject = addObject;
        if (!workerName.equals(""))
            objs = ((ArrayList)AnnotHelper.callWorkerMethod(workerName, "getListRows")).toArray();
        super.doModal();

        getParamInObj();
    }

    private void closeWindow()
    {
        this.onClose();
    }

    private void setParamInObj()
    {
        Object [] paramList = new Object[1];

        switch (listCellContant.getColymnType())
        {
            case 1:
                paramList[0] = text.getValue();
                break;
            case 2:
                int selectedIndex = listboxV.getSelectedIndex();

                if (selectedIndex != -1)
                    paramList[0] = listboxV.getObjs()[selectedIndex];
                break;
            case 3:
                Set<Object> sets = new LinkedHashSet<Object>(Arrays.asList(dualListBoxObject.rightListbox.getObjs()));

                sets.remove(dualListBoxObject.rightListbox.getObjs()[0]);

                paramList[0] = sets;
                break;
            case 4:
                Float fl = doubleSpinnerBox.getValue().floatValue();
                paramList[0] = fl;

                break;
            case 5:
                int val = spinner.getValue();
                paramList[0] = val;

                break;

        }

        //геттер превращается в сеттер))
        String methodName = "s"+listCellContant.getMethodList().substring(1);


        ClassInvokeCall.callMethod(obj,methodName,paramList);
    }

    private void getParamInObj()
    {
        String methodName = listCellContant.getMethodList();

        switch (listCellContant.getColymnType())
        {
            case 1:
                if (ClassInvokeCall.callMethod(obj,methodName) != null)
                    text.setValue(ClassInvokeCall.callMethod(obj,methodName).toString());
                else
                    text.setValue("");

                break;
            case 3:
                if (dualListBoxObject != null)
                {
                    LinkedHashSet set2 = (LinkedHashSet) ClassInvokeCall.callMethod(obj,methodName);

                    List list1 = new ArrayList();

                    Object emptyEntity = AnnotHelper.callWorkerMethod(workerName, "getEmptyEntity");

                    if ((set2.size()==0) || ((set2.size()!=0) && !set2.toArray()[0].equals(emptyEntity)))
                        list1.add(emptyEntity);

                    List list2 = Arrays.asList(dualListBoxObject.leftListbox.getObjs());
                    List list3 = new ArrayList(0);
                    for (Object o : list2)
                    {
                        boolean save = true;
                        for (Object o2 : set2)
                        {
                            if (o.equals(o2))
                                save = false;
                        }

                        if (save)
                            list3.add(o);
                    }

                    list1.addAll(set2);

                    dualListBoxObject.setRightList(list1.toArray());
                    dualListBoxObject.setLeftList(list3.toArray());

                    dualListBoxObject.leftListbox.refresh();
                    dualListBoxObject.rightListbox.refresh();
                }

                break;
            case 2:
                int val = listboxV.findAndGetEqObject(ClassInvokeCall.callMethod(obj,methodName));

                if (val != -1)
                    listboxV.setSelectedIndex(val);

                break;
            case 4:
                doubleSpinnerBox.setValue(4.7);

                break;
            case 5:
                spinner.setValue(4);

                break;

        }
    }

    @Listen("onClick = #but2")
    public void butn2Click()
    {
        addObject.nextClick(obj);
        int u = 0;
        setParamInObj();
        closeWindow();


    }

    @Listen("onClick = #but1")
    public void butn1Click()
    {
        addObject.previsClick(obj);
        int u = 0;
        setParamInObj();
        closeWindow();
    }

    @Listen("onClick = #but3")
    public void but3Click()
    {
        setParamInObj();
        addObject.saveClick();

        closeWindow();
    }


}
