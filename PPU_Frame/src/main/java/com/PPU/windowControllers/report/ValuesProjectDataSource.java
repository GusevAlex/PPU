package com.PPU.windowControllers.report;

import com.PPU.DB.tables.ValuesParametrForMZ;
import com.PPU.DB.tables.ValuesParametrForProject;
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
public class ValuesProjectDataSource implements JRDataSource {

    private List<ValuesParametrForProject> coms;
    private int index = -1;

    public ValuesProjectDataSource(List<ValuesParametrForProject> coms)
    {
        super();
        this.coms = coms;
    }

    @Override
    public Object getFieldValue(JRField jrField) throws JRException {
        String fieldName = jrField.getName();
        ValuesParametrForProject com = coms.get(index);
        if ("parametr".equals(fieldName)) {
            return com.getParametr();
        } else if ("value".equals(fieldName)) {
            return com.getValue();
        } else if ("dateRecValue".equals(fieldName)) {
            return com.getDateRecValue();
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
