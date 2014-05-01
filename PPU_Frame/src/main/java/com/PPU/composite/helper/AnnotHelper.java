package com.PPU.composite.helper;

import com.PPU.DB.workLogic.ClassInvokeCall;
import com.PPU.DB.workLogic.WorkWithTable;
import com.PPU.Logic.*;
import com.PPU.windowControllers.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by user on 28.04.14.
 */
public class AnnotHelper {

    private List<String> header = new ArrayList<String>(10);
    private List<ListCellContant> listCellContant = new ArrayList<ListCellContant>();
    private List<String> worker = new ArrayList<String>(10);

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

    public List<String> getWorker() {
        return worker;
    }

    public void setWorker(List<String> worker) {
        this.worker = worker;
    }

    public void setParamForList(Object obj)
    {
        Map map = AnotationService.getMapAnnotationFieldTypeByClass(obj);

        List list1 = new ArrayList();
        List list2 = new ArrayList();
        List list3 = new ArrayList();

        for (Object entr : map.entrySet())
        {
            Map.Entry mapEntr = (Map.Entry) entr;
            Object [] values = (Object []) mapEntr.getValue();

            ListCellContant listCellContant1 = new ListCellContant();
            listCellContant1.setColymnType((Integer) values[0]);
            listCellContant1.setMethodList((String) mapEntr.getKey());

            list1.add((String) values[1]);
            list2.add(listCellContant1);
            list3.add((String) values[2]);
        }

        setHeader(list1);
        setListCellContant(list2);
        setWorker(list3);
    }

    public static Object callWorkerMethod(String workerName, String funcName, Object ... obj)
    {
        return ClassInvokeCall.callMethod(ClassInvokeCall.returnWorkerByName(workerName), funcName, obj);
    }

}
