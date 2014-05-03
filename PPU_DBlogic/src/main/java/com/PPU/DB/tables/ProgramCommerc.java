package com.PPU.DB.tables;

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

//CREATE TABLE programs_commerc
//        (
//        id integer NOT NULL,
//        name text,
//        target text,
//        description text,
//        id_partner_commerc integer,
//        CONSTRAINT programs_commerc_pkey PRIMARY KEY (id)
//        )

@Entity
@Table(name = "programs_commerc")
public class ProgramCommerc {
    private int id;
    private String name;
    private String target;
    private String description;
    private int idPartnerCommerc;
    private Set<MZ> MZ = new LinkedHashSet<MZ>();
    private Set<Project> projects = new LinkedHashSet<Project>();
    private PartnerCommercialMan partnerCommercialMan;

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

    @Column(name="target")
    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    @Column(name="description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name="id_partner_commerc")
    public int getIdPartnerCommerc() {
        return idPartnerCommerc;
    }

    public void setIdPartnerCommerc(int idPartnerCommerc) {
        this.idPartnerCommerc = idPartnerCommerc;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "program")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.DELETE})
    public Set<MZ> getMZ() {
        return MZ;
    }

    public void setMZ(Set<MZ> MZ) {
        this.MZ = MZ;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "program")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.DELETE})
    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_partner_commerc", insertable = false, updatable = false)
    public PartnerCommercialMan getPartnerCommercialMan() {
        return partnerCommercialMan;
    }

    public void setPartnerCommercialMan(PartnerCommercialMan partnerCommercialMan) {
        this.partnerCommercialMan = partnerCommercialMan;
    }




    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ProgramCommerc)) return false;

        boolean eq = false;

        Integer par1 = (Integer) ClassInvokeCall.callMethod(this, "getId");
        Integer par2 = (Integer) ClassInvokeCall.callMethod(obj, "getId");

        if (par1.equals(par2)) eq = true;

        return eq;
    }
}
