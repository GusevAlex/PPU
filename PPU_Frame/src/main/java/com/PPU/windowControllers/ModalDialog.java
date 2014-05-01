package com.PPU.windowControllers;

import com.PPU.DB.workLogic.WorkWithCommandMz;
import com.PPU.DB.workLogic.WorkWithPartnerMZ;
import com.PPU.Logic.*;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zul.Window;

import java.util.*;

/**
 * Created by Alex on 24.04.2014.
 */
public class ModalDialog extends SelectorComposer<Component>
{
    private static String namePage;
    private static String labelButton1;
    private static String labelButton2;
    private static List<String> header = new ArrayList<String>(10);
    private static List<ListCellContant> listCellContant = new ArrayList<ListCellContant>();

    private static Object [] obj;

    public  String getNamePage() {
        return namePage;
    }

    public  void setNamePage(String namePage) {
        this.namePage = namePage;
    }

    public void createDialog()
    {

        namePage = "/pages/window/include/list.zul";
        labelButton1 = "dfgfdg";
        labelButton2 = "sdfsdfdfgfdg";

        Window window = (Window) Executions.createComponents(
                "/pages/window/window.zul", null, null);
        window.doModal();
    }

    public String getLabelButton1() {
        return labelButton1;
    }

    public void setLabelButton1(String labelButton1) {
        ModalDialog.labelButton1 = labelButton1;
    }

    public String getLabelButton2() {
        return labelButton2;
    }

    public void setLabelButton2(String labelButton2) {
        ModalDialog.labelButton2 = labelButton2;
    }

    public List<String> getHeader() {
        return header;
    }

    public void setHeader(List<String> header) {
        ModalDialog.header = header;
    }

    public List<ListCellContant> getListCellContant() {
        return listCellContant;
    }

    public void setListCellContant(List<ListCellContant> listCellContant) {
        ModalDialog.listCellContant = listCellContant;
    }

    public Object[] getObj() {
        return obj;
    }

    public void setObj(Object[] obj) {
        ModalDialog.obj = obj;
    }

    public void setParamForList(Object obj)
    {
        Map map = AnotationService.getMapAnnotationFieldTypeByClass(obj);

        List list1 = new ArrayList();
        List list2 = new ArrayList();

        for (Object entr : map.entrySet())
        {
            Map.Entry mapEntr = (Map.Entry) entr;
            Object [] values = (Object []) mapEntr.getValue();

            ListCellContant listCellContant1 = new ListCellContant();
            listCellContant1.setColymnType((Integer) values[0]);
            listCellContant1.setMethodList((String) mapEntr.getKey());

            list1.add((String) values[1]);
            list2.add(listCellContant1);
        }

        setHeader(list1);
        setListCellContant(list2);

        int i = 0;
    }

    public void showList(Object [] obj1)
    {
        if (obj1.length == 0) return;

        setObj(obj1);

        setParamForList(obj1[0]);

//        for (int i=0; i<methodList.size(); i++)
//            try {
//                Object y = WorkWithPartnerMZ.invokeCallMethode(getObj()[0], getMethodList().get(i));
//                int yr = 0;
//            } catch (Exception e) {
//                e.printStackTrace();
//            }

        namePage = "/pages/window/include/list.zul";
        labelButton1 = "dfgfdg";
        labelButton2 = "sdfsdfdfgfdg";

        Window window = (Window) Executions.createComponents(
                "/pages/window/window.zul", null, null);
        window.doModal();

    }

    public static void main(String[] args) {
        ModalDialog modalDialog = new ModalDialog();
        modalDialog.showList(new WorkWithPartnerMZ().findAndGetAllRow("", "").toArray());

    }



}
