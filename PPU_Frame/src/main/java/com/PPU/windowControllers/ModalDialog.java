package com.PPU.windowControllers;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zul.Window;

/**
 * Created by Alex on 24.04.2014.
 */
public class ModalDialog extends SelectorComposer<Component>
{
    private static String namePage;
    private static String labelButton1;
    private static String labelButton2;

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
}
