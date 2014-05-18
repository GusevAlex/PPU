package com.PPU.windowControllers.report;

import com.PPU.DB.tables.MZ;
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
public class MZDataSource implements JRDataSource {

    private List<MZ> mzs;
    private int index = -1;

    public MZDataSource(List<MZ> mzs)
    {
        super();
        this.mzs = mzs;
    }

    @Override
    public Object getFieldValue(JRField jrField) throws JRException {
        String fieldName = jrField.getName();
        MZ mz = mzs.get(index);
        if ("name".equals(fieldName)) {
            return mz.getName();
        } else if ("consumers".equals(fieldName)) {
            return mz.getConsumers();
        } else if ("description".equals(fieldName)) {
            return mz.getDescription();
        } else if ("typeServiceMZ".equals(fieldName)) {
            return mz.getTypeServiceMZ();
        } else if ("leader".equals(fieldName)) {
            return mz.getLeader();
        } else if ("budget".equals(fieldName)) {
            return mz.getBudget();
        } else if ("program".equals(fieldName)) {
            return mz.getProgram();
        } else if ("stat".equals(fieldName)) {
            String levelName = new String();

            switch (mz.getStatus())
            {
                    case 0 : levelName = "Создается";
                        break;
                    case 1 : levelName = "Создан";
                        break;
                    case 2 : levelName = "Планирование";
                        break;
                    case 3 : levelName = "На согласовании";
                        break;
                    case 4 : levelName = "Выполняется";
                        break;
                    case 5 : levelName = "Выполнен";
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
        return ++index < mzs.size();
    }
}
