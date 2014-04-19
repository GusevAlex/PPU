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
 * Time: 23:00
 * To change this template use File | Settings | File Templates.
 */

//CREATE TABLE "partners_MZ"
//        (
//        id integer NOT NULL,
//        name text,
//        address text,
//        description text,
//        type_mu integer,
//        id_user integer,
//        CONSTRAINT "partners_MZ_pkey" PRIMARY KEY (id)
//        )

@Entity
@Table(name = "\"partners_MZ\"")
public class PartnersMZ {

    private int id;
    private String name;
    private String address;
    private String description;
    private int typeMU;
    private int idUser;
    private Set<ComandMZ> ComandMZ = new HashSet<ComandMZ>(0);
    private Users user;

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

    @Column(name="type_mu")
    public int getTypeMU() {
        return typeMU;
    }

    public void setTypeMU(int typeMU) {
        this.typeMU = typeMU;
    }

    @Column(name="id_user")
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "partnerMZ")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.DELETE})
    public Set<ComandMZ> getComandMZ() {
        return ComandMZ;
    }

    public void setComandMZ(Set<ComandMZ> comandMZ) {
        ComandMZ = comandMZ;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user", insertable = false, updatable = false)
    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
}
