package com.PPU.DB.tables;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 12.04.14
 * Time: 22:08
 * To change this template use File | Settings | File Templates.
 */

//CREATE TABLE "corrections_MZ"
//        (
//        id integer NOT NULL,
//        id_mz integer,
//        correction_date date,
//        id_parametr integer,
//        value_after text,
//        value_before text,
//        CONSTRAINT "corrections_MZ_pkey" PRIMARY KEY (id)
//        )

@Entity
@Table(name = "\"corrections_MZ\"")
public class CorrectionsMZ {

    private int id;
    private int idMZ;
    private Date CorrectionDate;
    private int idParametr;
    private String ValueBefore;
    private String ValueAfter;
    private MZ MZ;
    private Parametrs parametr;

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

    @Column(name="id_mz")
    public int getIdMZ() {
        return idMZ;
    }

    public void setIdMZ(int idMZ) {
        this.idMZ = idMZ;
    }

    @Column(name="correction_date")
    public Date getCorrectionDate() {
        return CorrectionDate;
    }

    public void setCorrectionDate(Date correctionDate) {
        CorrectionDate = correctionDate;
    }

    @Column(name="id_parametr")
    public int getIdParametr() {
        return idParametr;
    }

    public void setIdParametr(int idParametr) {
        this.idParametr = idParametr;
    }

    @Column(name="value_before")
    public String getValueBefore() {
        return ValueBefore;
    }

    public void setValueBefore(String valueBefore) {
        ValueBefore = valueBefore;
    }

    @Column(name="value_after")
    public String getValueAfter() {
        return ValueAfter;
    }

    public void setValueAfter(String valueAfter) {
        ValueAfter = valueAfter;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_mz", insertable = false, updatable = false)
    public MZ getMZ() {
        return MZ;
    }

    public void setMZ(MZ MZ) {
        this.MZ = MZ;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_parametr", insertable = false, updatable = false)
    public Parametrs getParametr() {
        return parametr;
    }

    public void setParametr(Parametrs parametr) {
        this.parametr = parametr;
    }
}
