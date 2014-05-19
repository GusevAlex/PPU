package com.PPU.windowControllers.report;

import com.PPU.DB.tables.MZ;
import com.PPU.DB.tables.Project;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 18.05.14
 * Time: 12:35
 * To change this template use File | Settings | File Templates.
 */
public class ProjectDataSource implements JRDataSource {

    private List<Project> coms;
    private int index = -1;

    public ProjectDataSource(List<Project> coms)
    {
        super();
        this.coms = coms;
    }

    @Override
    public Object getFieldValue(JRField jrField) throws JRException {
        String fieldName = jrField.getName();
        Project com = coms.get(index);
        if ("name".equals(fieldName)) {
            return com.getName();
        } else if ("consumers".equals(fieldName)) {
            return com.getConsumers();
        } else if ("description".equals(fieldName)) {
            return com.getDescription();
        } else if ("leader".equals(fieldName)) {
            return com.getLeader();
        } else if ("budget".equals(fieldName)) {
            return com.getBudget();
        } else if ("program".equals(fieldName)) {
            return com.getProgram();
        } else if ("stat".equals(fieldName)) {
            String levelName = new String();

            switch (com.getStatus())
            {
                    case 0 : levelName = "���������";
                        break;
                    case 1 : levelName = "������";
                        break;
                    case 2 : levelName = "������������";
                        break;
                    case 3 : levelName = "�� ������������";
                        break;
                    case 4 : levelName = "�����������";
                        break;
                    case 5 : levelName = "��������";
                        break;
            }
            return levelName;
        } else if ("num".equals(fieldName)) {
            return index;
        }
        return "";
    }

    @Override
    public boolean next() throws JRException {
        return ++index < coms.size();
    }
}
