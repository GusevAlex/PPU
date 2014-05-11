package com.PPU.windowControllers.partners;

import com.PPU.DB.workLogic.WorkWithPartnerCommerc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 10.05.14
 * Time: 22:30
 * To change this template use File | Settings | File Templates.
 */
public class PartnersCommerc {

    private List<com.PPU.DB.tables.PartnersMZ> partnersCommerc = new ArrayList<com.PPU.DB.tables.PartnersMZ>();

    public PartnersCommerc()
    {
        partnersCommerc = new WorkWithPartnerCommerc().getListRows();
    }

    public List<com.PPU.DB.tables.PartnersMZ> getPartnersCommerc() {
        return partnersCommerc;
    }

    public void setPartnersCommerc(List<com.PPU.DB.tables.PartnersMZ> partnersCommerc) {
        this.partnersCommerc = partnersCommerc;
    }
}
