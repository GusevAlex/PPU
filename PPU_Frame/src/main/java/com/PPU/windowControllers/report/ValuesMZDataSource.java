package com.PPU.windowControllers.report;

import com.PPU.DB.tables.LimitsMZ;
import com.PPU.DB.tables.ValuesParametrForMZ;
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
public class ValuesMZDataSource implements JRDataSource {

    private List<ValuesParametrForMZ> mzs;
    private int index = -1;

    public ValuesMZDataSource(List<ValuesParametrForMZ> mzs)
    {
        super();
        this.mzs = mzs;
    }

    @Override
    public Object getFieldValue(JRField jrField) throws JRException {
        String fieldName = jrField.getName();
        ValuesParametrForMZ mz = mzs.get(index);
        if ("parametr".equals(fieldName)) {
            return mz.getParametr();
        } else if ("value".equals(fieldName)) {
            return mz.getValue();
        } else if ("dateRecValue".equals(fieldName)) {
            return mz.getDateRecValue();
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
