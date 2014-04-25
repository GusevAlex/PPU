package com.PPU.windowControllers;

import com.PPU.Logic.AnotationService;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zul.Window;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Alex on 24.04.2014.
 */
public class ModalDialog extends SelectorComposer<Component>
{
    private static String namePage;
    private static String labelButton1;
    private static String labelButton2;
    private static String [] header;
    private static Integer [] colymnType;
    private static List<String> methodList;

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

    public String[] getHeader() {
        return header;
    }

    public void setHeader(String[] header) {
        ModalDialog.header = header;
    }

    public Integer[] getColymnType() {
        return colymnType;
    }

    public void setColymnType(Integer[] colymnType) {
        ModalDialog.colymnType = colymnType;
    }

    public List<String> getMethodList() {
        return methodList;
    }

    public void setMethodList(List<String> methodList) {
        ModalDialog.methodList = methodList;
    }

    public void setParamForList(Object obj)
    {
        Map map = AnotationService.getMapAnnotationFieldTypeByClass(obj);

        methodList = Collections.emptyList();
        for (Object set : map.keySet())
        {
            methodList.add((String) set);
        }
    }

}
