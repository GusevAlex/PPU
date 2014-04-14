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
 * Time: 23:07
 * To change this template use File | Settings | File Templates.
 */

//CREATE TABLE "type_service_MZ"
//        (
//        id integer NOT NULL,
//        name text,
//        CONSTRAINT "type_service_MZ_pkey" PRIMARY KEY (id)
//        )

@Entity
@Table(name = "\"type_service_MZ\"")
public class TypeServiceMZ {

    private int id;
    private String name;
    private Set<MZ> MZ = new HashSet<MZ>(0);
    private Set<DefaultParametrsServiceMZ> defaultParametrsServiceMZs = new HashSet<DefaultParametrsServiceMZ>(0);

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

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "typeServiceMZ")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.DELETE})
    public Set<MZ> getMZ() {
        return MZ;
    }

    public void setMZ(Set<MZ> MZ) {
        this.MZ = MZ;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "typeServiceMZ")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.DELETE})
    public Set<DefaultParametrsServiceMZ> getDefaultParametrsServiceMZs() {
        return defaultParametrsServiceMZs;
    }

    public void setDefaultParametrsServiceMZs(Set<DefaultParametrsServiceMZ> defaultParametrsServiceMZs) {
        this.defaultParametrsServiceMZs = defaultParametrsServiceMZs;
    }
}
