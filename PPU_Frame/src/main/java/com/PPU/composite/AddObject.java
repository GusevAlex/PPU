package com.PPU.composite;

import com.PPU.DB.workLogic.ClassInvokeCall;
import com.PPU.DB.workLogic.WorkWithTable;
import com.PPU.Logic.*;
import com.PPU.composite.helper.*;
import com.PPU.windowControllers.*;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.IdSpace;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.MouseEvent;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zul.Window;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by user on 28.04.14.
 */
public class AddObject implements EventListener {

    private WorkWithTable worker;
    private Object obj;

    private List<String> header = new ArrayList<String>(10);
    private List<ListCellContant> listCellContant = new ArrayList<ListCellContant>();
    private List<String> workerName = new ArrayList<String>(10);

    private int countPage;
    private int numPage;

    private ObjectListBox objectListBox;

    public WorkWithTable getWorker() {
        return worker;
    }

    public void setWorker(WorkWithTable worker) {
        this.worker = worker;
    }

    private void initObject()
    {
        if (obj == null)
            obj = worker.getEmptyEntity();

        setParamForList(obj);

        countPage = header.size();
    }

    public List<String> getHeader() {
        return header;
    }

    public void setHeader(List<String> header) {
        this.header = header;
    }

    public List<ListCellContant> getListCellContant() {
        return listCellContant;
    }

    public void setListCellContant(List<ListCellContant> listCellContant) {
        this.listCellContant = listCellContant;
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

    public List<String> getWorkerName() {
        return workerName;
    }

    public void setWorkerName(List<String> workerName) {
        this.workerName = workerName;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public ObjectListBox getObjectListBox() {
        return objectListBox;
    }

    public void setObjectListBox(ObjectListBox objectListBox) {
        this.objectListBox = objectListBox;
    }

    private void setParamForList(Object obj)
    {
        AnnotHelper annotHelper = new AnnotHelper();

        annotHelper.setParamForList(obj);

        setHeader(annotHelper.getHeader());
        setListCellContant(annotHelper.getListCellContant());
        setWorkerName(annotHelper.getWorker());
    }

    public AddObjectWindow createWindow()
    {
        AddObjectWindow add = (AddObjectWindow) Executions.createComponents(
                "/pages/window/addWindow.zul", null, null);

        //AddObjectWindow add = new AddObjectWindow();
        add.setCountPage(countPage);
        add.setNumPage(numPage);

        add.setHeader(header.get(numPage));
        add.setListCellContant(listCellContant.get(numPage));
        add.setWorkerName(workerName.get(numPage));

        add.setId("addWindow"+numPage);

        add.setObj(obj);

        add.setLoad(true);

        add.addEventListener("onClose", this);

        return add;
    }

    public void showWindow()
    {
        initObject();

        createWindow().doModal(this);
    }

    private void nextObject()
    {
        numPage++;

        if (numPage<countPage)
        {
            createWindow().doModal(this);
        }
    }

    private void previsionObject()
    {
        numPage--;

        if (numPage<countPage)
        {
            createWindow().doModal(this);
        }
    }

    public void saveObject()
    {
        try {
            worker.addEntity(obj);
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    @Override
    public void onEvent(Event evt) throws Exception{

    }

    public void nextClick(Object value)
    {
        nextObject();
    }

    public void previsClick(Object value)
    {
        previsionObject();
    }

    public void saveClick()
    {
        saveObject();

        if (objectListBox != null)
        {
            Object [] listVal = objectListBox.getObjs();
            Object [] resVal = new Object[listVal.length+1];


            for (int i=0; i<listVal.length; i++)
                resVal[i] = listVal[i];

            resVal[listVal.length] = obj;

            objectListBox.setObjs(resVal);

            objectListBox.refresh();
        }
    }
}
