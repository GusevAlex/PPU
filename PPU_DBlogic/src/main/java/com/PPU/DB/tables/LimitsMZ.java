package com.PPU.DB.tables;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 12.04.14
 * Time: 22:53
 * To change this template use File | Settings | File Templates.
 */

//CREATE TABLE "limits_MZ"
//        (
//        id integer NOT NULL,
//        id_parametr integer,
//        value text,
//        id_mz integer,
//        CONSTRAINT limits_pkey PRIMARY KEY (id)
//        )

@Entity
@Table(name = "\"limits_MZ\"")
public class LimitsMZ {

    private int id;
    private int idParametr;
    private String value;
    private int idMZ;
    private MZ MZ;

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

    @Column(name="id_parametr")
    public int getIdParametr() {
        return idParametr;
    }

    public void setIdParametr(int idParametr) {
        this.idParametr = idParametr;
    }

    @Column(name="value")
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Column(name="id_mz")
    public int getIdMZ() {
        return idMZ;
    }

    public void setIdMZ(int idMZ) {
        this.idMZ = idMZ;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_mz", insertable = false, updatable = false)
    public MZ getMZ() {
        return MZ;
    }

    public void setMZ(MZ MZ) {
        this.MZ = MZ;
    }
}
