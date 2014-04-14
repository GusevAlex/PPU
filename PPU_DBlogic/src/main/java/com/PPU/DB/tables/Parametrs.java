package com.PPU.DB.tables;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 12.04.14
 * Time: 22:55
 * To change this template use File | Settings | File Templates.
 */

//CREATE TABLE parametrs
//        (
//        id integer NOT NULL,
//        name text,
//        type character(1),
//        CONSTRAINT parametrs_pkey PRIMARY KEY (id)
//        )

@Entity
@Table(name = "parametrs")
public class Parametrs {

    private int id;
    private String name;
    private char type;
    private Set<ValuesParametrForMZ> valuesParametrForMZ = new HashSet<ValuesParametrForMZ>(0);
    private Set<ValuesParametrForProject> valuesParametrForProjects = new HashSet<ValuesParametrForProject>(0);
    private Set<CorrectionsMZ> correctionsMZ = new HashSet<CorrectionsMZ>(0);

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

    @Column(name="type")
    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "parametr")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.DELETE})
    public Set<ValuesParametrForMZ> getValuesParametrForMZ() {
        return valuesParametrForMZ;
    }

    public void setValuesParametrForMZ(Set<ValuesParametrForMZ> valuesParametrForMZ) {
        this.valuesParametrForMZ = valuesParametrForMZ;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "parametr")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.DELETE})
    public Set<ValuesParametrForProject> getValuesParametrForProjects() {
        return valuesParametrForProjects;
    }

    public void setValuesParametrForProjects(Set<ValuesParametrForProject> valuesParametrForProjects) {
        this.valuesParametrForProjects = valuesParametrForProjects;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "parametr")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.DELETE})
    public Set<CorrectionsMZ> getCorrectionsMZ() {
        return correctionsMZ;
    }

    public void setCorrectionsMZ(Set<CorrectionsMZ> correctionsMZ) {
        this.correctionsMZ = correctionsMZ;
    }
}
