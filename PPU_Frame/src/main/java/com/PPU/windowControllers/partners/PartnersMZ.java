package com.PPU.windowControllers.partners;

import com.PPU.DB.workLogic.WorkWithPartnerMZ;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 10.05.14
 * Time: 22:30
 * To change this template use File | Settings | File Templates.
 */
public class PartnersMZ {

    private List<com.PPU.DB.tables.PartnersMZ> partnersMZ = new ArrayList<com.PPU.DB.tables.PartnersMZ>();

    public PartnersMZ()
    {
        partnersMZ = new WorkWithPartnerMZ().getListRows();
    }

    public List<com.PPU.DB.tables.PartnersMZ> getPartnersMZ() {
        return partnersMZ;
    }

    public void setPartnersMZ(List<com.PPU.DB.tables.PartnersMZ> partnersMZ) {
        this.partnersMZ = partnersMZ;
    }
}
