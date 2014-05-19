package com.PPU.windowControllers.report;

import com.PPU.DB.tables.LimitsMZ;
import com.PPU.DB.tables.LimitsProject;
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
public class LimitsProjectDataSource implements JRDataSource {

    private List<LimitsProject> coms;
    private int index = -1;

    public LimitsProjectDataSource(List<LimitsProject> coms)
    {
        super();
        this.coms = coms;
    }

    @Override
    public Object getFieldValue(JRField jrField) throws JRException {
        String fieldName = jrField.getName();
        LimitsProject com = coms.get(index);
        if ("parametr".equals(fieldName)) {
            return com.getParametr();
        } else if ("value".equals(fieldName)) {
            return com.getValue();
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
