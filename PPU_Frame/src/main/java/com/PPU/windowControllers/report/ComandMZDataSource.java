package com.PPU.windowControllers.report;

import com.PPU.DB.tables.ComandMZ;
import com.PPU.DB.tables.MZ;
import com.PPU.DB.tables.PartnersMZ;
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
public class ComandMZDataSource implements JRDataSource {

    private List<ComandMZ> mzs;
    private int index = -1;

    public ComandMZDataSource(List<ComandMZ> mzs)
    {
        super();
        this.mzs = mzs;
    }

    @Override
    public Object getFieldValue(JRField jrField) throws JRException {
        String fieldName = jrField.getName();
        PartnersMZ mz = ((ComandMZ) mzs.get(index)).getPartnerMZ();
        if ("name".equals(fieldName)) {
            return mz.getName();
        } else if ("address".equals(fieldName)) {
            return mz.getAddress();
        } else if ("description".equals(fieldName)) {
            return mz.getDescription();
        } else if ("typesMU".equals(fieldName)) {
            return mz.getTypesMU();
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
