package com.PPU.DB.tables;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 12.04.14
 * Time: 23:08
 * To change this template use File | Settings | File Templates.
 */

//CREATE TABLE "values_parametr_for_MZ"
//        (
//        id integer NOT NULL,
//        id_parametr integer,
//        value text,
//        date_rec_value date,
//        id_mz integer,
//        CONSTRAINT "values_parametr_for_MZ_pkey" PRIMARY KEY (id)
//        )

@Entity
@Table(name = "\"values_parametr_for_MZ\"")
public class ValuesParametrForMZ {

    private int id;
    private int idParametr;
    private String value;
    private Date dateRecValue;
    private int idMZ;
    private Parametrs parametr;
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

    @Column(name="date_rec_value")
    public Date getDateRecValue() {
        return dateRecValue;
    }

    public void setDateRecValue(Date dateRecValue) {
        this.dateRecValue = dateRecValue;
    }

    @Column(name="id_mz")
    public int getIdMZ() {
        return idMZ;
    }

    public void setIdMZ(int idMZ) {
        this.idMZ = idMZ;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_parametr", insertable = false, updatable = false)
    public Parametrs getParametr() {
        return parametr;
    }

    public void setParametr(Parametrs parametr) {
        this.parametr = parametr;
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
