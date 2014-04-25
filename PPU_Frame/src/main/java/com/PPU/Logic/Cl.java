package com.PPU.Logic;

import com.PPU.DB.tables.TableAnnot.FieldType;
import com.PPU.DB.tables.TableAnnot.HeaderName;

/**
 * Created by Alex on 25.04.2014.
 */
public class Cl {

    String gh;

    @FieldType(type = 2)
    @HeaderName(name = "hgjhgj")
    public String getGh() {
        return gh;
    }

    @FieldType(type = 1)
    public void setGh(String gh) {
        this.gh = gh;
    }
}
