package com.PPU.DB.tables;

import com.PPU.DB.tables.TableAnnot.*;
import com.PPU.DB.workLogic.ClassInvokeCall;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 18.05.14
 * Time: 19:59
 * To change this template use File | Settings | File Templates.
 */

//CREATE TABLE "FileMZ"
//        (
//        id integer NOT NULL,
//        file text,
//        id_mz integer,
//        CONSTRAINT "FileMZ_pkey" PRIMARY KEY (id)
//        )

@Entity
@Table(name = "\"FileMZ\"")
public class FileMZ {
    private int id;
    private String file;
    private int idMz;
    private MZ mz;

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

    @Column(name="file")
    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    @Column(name="id_mz")
    public int getIdMz() {
        return idMz;
    }

    public void setIdMz(int idMz) {
        this.idMz = idMz;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_mz", insertable = false, updatable = false)
    public MZ getMz() {
        return mz;
    }

    public void setMz(MZ mz) {
        this.mz = mz;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof PartnersMZ)) return false;

        boolean eq = false;

        Integer par1 = (Integer) ClassInvokeCall.callMethod(this, "getId");
        Integer par2 = (Integer) ClassInvokeCall.callMethod(obj, "getId");

        if (par1.equals(par2)) eq = true;

        return eq;
    }
}
