package com.PPU.DB.tables;

import com.PPU.DB.workLogic.ClassInvokeCall;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.TreeSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 12.04.14
 * Time: 22:01
 * To change this template use File | Settings | File Templates.
 */

//CREATE TABLE programs
//        (
//        id integer NOT NULL,
//        name text,
//        target text,
//        description text,
//        CONSTRAINT programs_pkey PRIMARY KEY (id)
//        )

@Entity
@Table(name = "programs")
public class Program {
    private int id;
    private String name;
    private String target;
    private String description;
    private Set<MZ> MZ = new TreeSet<MZ>();
    private Set<Project> projects = new TreeSet<Project>();

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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "program")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.DELETE})
    public Set<MZ> getMZ() {
        return MZ;
    }

    public void setMZ(Set<MZ> MZ) {
        this.MZ = MZ;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "program")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.DELETE})
    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Program)) return false;

        boolean eq = false;

        Integer par1 = (Integer) ClassInvokeCall.callMethod(this, "getId");
        Integer par2 = (Integer) ClassInvokeCall.callMethod(obj, "getId");

        if (par1.equals(par2)) eq = true;

        return eq;
    }
}
