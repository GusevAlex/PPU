package com.PPU.windowControllers.PageMZ;

import com.PPU.DB.tables.CorrectionsMZ;
import com.PPU.DB.tables.MZ;
import com.PPU.DB.tables.Project;
import com.PPU.DB.tables.UsersMunMan;
import com.PPU.DB.workLogic.WorkWithMZ;
import com.PPU.DB.workLogic.WorkWithUser;
import com.PPU.XML.ParseCorrection;
import org.zkforge.timeline.data.OccurEvent;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Grid;
import org.zkoss.zul.ListModelList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 19.05.14
 * Time: 17:17
 * To change this template use File | Settings | File Templates.
 */
public class Events {

    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object[][][] getCorList()
    {
        description = "Здесь отображены оповещения по всем вашим Муниципальным заданиям";
        List<MZ> listMz = new ArrayList<MZ>();

        Object obj = new WorkWithUser().findAndGetAllRow("login", (String) Sessions.getCurrent().getAttribute("login")).get(0);
        List listAllMz = new WorkWithMZ().getListRows();

        int count = 0;

        for (MZ mz : (List<MZ>) listAllMz)
        {
            int role = new WorkWithMZ().getUserRole(mz, (UsersMunMan) obj);

            if (role != 0 && mz.getCorrectionsMZ().size()!=0)
            {
                count++;
            }
        }
        Object [][][] result = new Object[count][][];


        for (MZ mz : (List<MZ>) listAllMz)
        {
            int role = new WorkWithMZ().getUserRole(mz, (UsersMunMan) obj);

            int j = 0;
            if (role != 0)
            {
                ParseCorrection parseCorrection = new ParseCorrection();
                Object [] o = mz.getCorrectionsMZ().toArray();

                CorrectionsMZ[] cor = new CorrectionsMZ[o.length];

                for (int i=0; i<o.length; i++)
                    cor[i] = (CorrectionsMZ) o[i];

                ListModelList<OccurEvent> list = parseCorrection.getModelByCorrection(cor);

                result[j] = new Object[cor.length][];

                int y = 0;
                for (OccurEvent event : list)
                {
                    result[j][y] = new Object[4];

                    result[j][y][0] = mz.getName();
                    result[j][y][1] = "/pages/pagesMZ/MZ.zul?id="+mz.getId();
                    result[j][y][2] = event.getDescription();
                    result[j][y++][3] = event.getStart();
                }
                j++;
            }
        }

        return result;
    }
}
