package com.PPU.windowControllers.PageProject;

import com.PPU.DB.tables.CorrectionsProject;
import com.PPU.DB.tables.Project;
import com.PPU.DB.tables.UsersComMan;
import com.PPU.DB.tables.UsersMunMan;
import com.PPU.DB.workLogic.WorkWithProject;
import com.PPU.DB.workLogic.WorkWithUser;
import com.PPU.XML.ParseCorrection;
import org.zkforge.timeline.data.OccurEvent;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zul.ListModelList;

import java.util.ArrayList;
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
        description = "Здесь отображены оповещения по всем вашим Проектам";
        List<Project> listproject = new ArrayList<Project>();

        Object obj = new WorkWithUser().findAndGetAllRow("login", (String) Sessions.getCurrent().getAttribute("login")).get(0);
        List listAllproject = new WorkWithProject().getListRows();

        int count = 0;

        for (Project project : (List<Project>) listAllproject)
        {
            int role = new WorkWithProject().getUserRole(project, (UsersComMan) obj);

            if (role != 0 && project.getCorrectionsProject().size()!=0)
            {
                count++;
            }
        }
        Object [][][] result = new Object[count][][];


        for (Project project : (List<Project>) listAllproject)
        {
            int role = new WorkWithProject().getUserRole(project, (UsersComMan) obj);

            int j = 0;
            if (role != 0)
            {
                ParseCorrection parseCorrection = new ParseCorrection();
                Object [] o = project.getCorrectionsProject().toArray();

                CorrectionsProject[] cor = new CorrectionsProject[o.length];

                for (int i=0; i<o.length; i++)
                    cor[i] = (CorrectionsProject) o[i];

                ListModelList<OccurEvent> list = parseCorrection.getModelByCorrection(cor);

                result[j] = new Object[cor.length][];

                int y = 0;
                for (OccurEvent event : list)
                {
                    result[j][y] = new Object[4];

                    result[j][y][0] = project.getName();
                    result[j][y][1] = "/pages/pagesProject/Project.zul?id="+project.getId();
                    result[j][y][2] = event.getDescription();
                    result[j][y++][3] = event.getStart();
                }
                j++;
            }
        }

        return result;
    }
}
