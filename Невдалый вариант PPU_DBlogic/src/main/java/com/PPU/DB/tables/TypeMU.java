package com.PPU.DB.tables;

import com.PPU.DB.workLogic.ClassInvokeCall;
import org.hibernate.annotations.GenericGenerator;

import com.PPU.DB.tables.TableAnnot.FieldType;
import com.PPU.DB.tables.TableAnnot.HeaderName;
import com.PPU.DB.workLogic.ClassInvokeCall;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 12.04.14
 * Time: 23:05
 * To change this template use File | Settings | File Templates.
 */

//CREATE TABLE "type_MU"
//        (
//        id integer NOT NULL,
//        name text,
//        CONSTRAINT "type_MZ_pkey" PRIMARY KEY (id)
//        )

@Entity
@Table(name = "\"type_MU\"")
public class TypeMU {

    private int id;
    private String name;
    private Set<PartnersMZ> partners;

    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    @Column(name="id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @FieldType(type = 1)
    @HeaderName(name = "Название")
    @Column(name="name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "typesMU")
    //@Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.DELETE})
    public Set<PartnersMZ> getPartners() {
        return partners;
    }

    public void setPartners(Set<PartnersMZ> partners) {
        this.partners = partners;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof TypeMU)) return false;

        boolean eq = false;

        Integer par1 = (Integer) ClassInvokeCall.callMethod(this, "getId");
        Integer par2 = (Integer) ClassInvokeCall.callMethod(obj, "getId");

        if (par1.equals(par2)) eq = true;

        return eq;
    }
}
