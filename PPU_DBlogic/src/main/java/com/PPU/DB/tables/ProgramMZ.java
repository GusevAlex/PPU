package com.PPU.DB.tables;

import com.PPU.DB.tables.TableAnnot.FieldType;
import com.PPU.DB.tables.TableAnnot.HeaderName;
import com.PPU.DB.workLogic.ClassInvokeCall;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 12.04.14
 * Time: 22:01
 * To change this template use File | Settings | File Templates.
 */

//CREATE TABLE "programs_MZ"
//        (
//        id integer NOT NULL,
//        name text,
//        target text,
//        description text,
//        id_partner_mz integer,
//        CONSTRAINT programs_pkey PRIMARY KEY (id)
//        )

@Entity
@Table(name = "\"programs_MZ\"")
public class ProgramMZ {
    private int id;
    private String name;
    private String target;
    private String description;
    private int idPartnerMz;
    private Set<MZ> MZ = new LinkedHashSet<MZ>();
    private PartnersMZ partnersMZ;

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

    @Column(name="name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @FieldType(type = 1)
    @com.PPU.DB.tables.TableAnnot.HeaderName(name = "Цель")
    @Column(name="target")
    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    @FieldType(type = 1)
    @com.PPU.DB.tables.TableAnnot.HeaderName(name = "Описание")
    @Column(name="description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name="id_partner_mz")
    public int getIdPartnerMz() {
        return idPartnerMz;
    }

    public void setIdPartnerMz(int idPartnerMz) {
        this.idPartnerMz = idPartnerMz;
    }

	@FieldType(type = 3, worker="WorkWithMZ")
	@HeaderName(name = "Муниципальные задания")
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "program")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.DELETE})
    public Set<MZ> getMZ() {
        return MZ;
    }

    public void setMZ(Set<MZ> MZ) {
        this.MZ = MZ;
    }

	@FieldType(type = 2, worker = "WorkWithPartnerMZ")
	@com.PPU.DB.tables.TableAnnot.HeaderName(name = "Руководитель")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_partner_mz", insertable = false, updatable = false)
    public PartnersMZ getPartnersMZ() {
        return partnersMZ;
    }

    public void setPartnersMZ(PartnersMZ partnersMZ) {
        this.partnersMZ = partnersMZ;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ProgramMZ)) return false;

        boolean eq = false;

        Integer par1 = (Integer) ClassInvokeCall.callMethod(this, "getId");
        Integer par2 = (Integer) ClassInvokeCall.callMethod(obj, "getId");

        if (par1.equals(par2)) eq = true;

        return eq;
    }
}
