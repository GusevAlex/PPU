package com.PPU.windowControllers.report;

import com.PPU.DB.tables.ComandMZ;
import com.PPU.DB.tables.ComandProject;
import com.PPU.DB.tables.PartnerCommercialMan;
import com.PPU.DB.tables.PartnersMZ;
import com.PPU.windowControllers.partners.PartnersCommerc;
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
public class ComandProjectDataSource implements JRDataSource {

    private List<ComandProject> coms;
    private int index = -1;

    public ComandProjectDataSource(List<ComandProject> coms)
    {
        super();
        this.coms = coms;
    }

    @Override
    public Object getFieldValue(JRField jrField) throws JRException {
        String fieldName = jrField.getName();
        PartnerCommercialMan com = ((ComandProject) coms.get(index)).getPartnerProject();
        if ("name".equals(fieldName)) {
            return com.getName();
        } else if ("address".equals(fieldName)) {
            return com.getAddress();
        } else if ("description".equals(fieldName)) {
            return com.getDescription();
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
