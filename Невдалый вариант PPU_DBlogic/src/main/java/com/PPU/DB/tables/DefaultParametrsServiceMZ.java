package com.PPU.DB.tables;

import com.PPU.DB.tables.TableAnnot.FieldType;
import com.PPU.DB.workLogic.ClassInvokeCall;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 12.04.14
 * Time: 22:12
 * To change this template use File | Settings | File Templates.
 */

//CREATE TABLE "default_parametrs_service_MZ"
//        (
//        id integer NOT NULL,
//        id_service_mz integer,
//        id_parametr integer,
//        CONSTRAINT "default_parametrs_service_MZ_pkey" PRIMARY KEY (id)
//        )

@Entity
@Table(name = "\"default_parametrs_service_MZ\"")
public class DefaultParametrsServiceMZ {

    private int id;
    private int idServiceMZ;
    private int idParametr;
    private TypeServiceMZ typeServiceMZ;

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

    @Column(name="id_service_mz")
    public int getIdServiceMZ() {
        return idServiceMZ;
    }

    public void setIdServiceMZ(int idServiceMZ) {
        this.idServiceMZ = idServiceMZ;
    }

    @Column(name="id_parametr")
    public int getIdParametr() {
        return idParametr;
    }

    public void setIdParametr(int idParametr) {
        this.idParametr = idParametr;
    }

	@FieldType(type = 2, worker = "WorkWithDefaultParametrsServiceMZ")
	@com.PPU.DB.tables.TableAnnot.HeaderName(name = "“ËÔ Ã”")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_service_mz", insertable = false, updatable = false)
    public TypeServiceMZ getTypeServiceMZ() {
        return typeServiceMZ;
    }

    public void setTypeServiceMZ(TypeServiceMZ typeServiceMZ) {
        this.typeServiceMZ = typeServiceMZ;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof DefaultParametrsServiceMZ)) return false;

        boolean eq = false;

        Integer par1 = (Integer) ClassInvokeCall.callMethod(this, "getId");
        Integer par2 = (Integer) ClassInvokeCall.callMethod(obj, "getId");

        if (par1.equals(par2)) eq = true;

        return eq;
    }
}
