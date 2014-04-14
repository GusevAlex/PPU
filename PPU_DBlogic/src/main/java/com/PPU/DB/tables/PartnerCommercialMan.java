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
 * Time: 22:57
 * To change this template use File | Settings | File Templates.
 */

//CREATE TABLE partner_commercial_man
//        (
//        id integer NOT NULL,
//        name text,
//        address text,
//        description text,
//        CONSTRAINT partner_commercial_man_pkey PRIMARY KEY (id)
//        )

@Entity
@Table(name = "partner_commercial_man")
public class PartnerCommercialMan {

    private int id;
    private String name;
    private String address;
    private String description;
    private Set<ComandProject> comandProject = new HashSet<ComandProject>(0);

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

    @Column(name="address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name="description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "partnerProject")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.DELETE})
    public Set<ComandProject> getComandProject() {
        return comandProject;
    }

    public void setComandProject(Set<ComandProject> comandProject) {
        comandProject = comandProject;
    }
}
