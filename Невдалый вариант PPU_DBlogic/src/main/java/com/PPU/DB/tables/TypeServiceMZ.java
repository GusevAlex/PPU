package com.PPU.DB.tables;

import com.PPU.DB.tables.TableAnnot.FieldType;
import com.PPU.DB.tables.TableAnnot.HeaderName;
import com.PPU.DB.workLogic.ClassInvokeCall;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.LinkedHashSet;
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
    private Set<MZ> MZ = new LinkedHashSet<MZ>();
    private Set<DefaultParametrsServiceMZ> defaultParametrsServiceMZs = new LinkedHashSet<DefaultParametrsServiceMZ>();

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

    @FieldType(type = 1)
    @com.PPU.DB.tables.TableAnnot.HeaderName(name = "Название")
    @Column(name="name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	@FieldType(type = 3, worker="WorkWithMZ")
	@HeaderName(name = "Муниципальные задания")
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "typeServiceMZ")
    //@Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.DELETE})
    public Set<MZ> getMZ() {
        return MZ;
    }

    public void setMZ(Set<MZ> MZ) {
        this.MZ = MZ;
    }

	@FieldType(type = 3, worker="WorkWithDefaultParametrsServiceMZ")
	@HeaderName(name = "Стандартные параметры для типа МЗ")
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "typeServiceMZ")
    //@Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.DELETE})
    public Set<DefaultParametrsServiceMZ> getDefaultParametrsServiceMZs() {
        return defaultParametrsServiceMZs;
    }

    public void setDefaultParametrsServiceMZs(Set<DefaultParametrsServiceMZ> defaultParametrsServiceMZs) {
        this.defaultParametrsServiceMZs = defaultParametrsServiceMZs;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ComandProject)) return false;

        boolean eq = false;

        Integer par1 = (Integer) ClassInvokeCall.callMethod(this, "getId");
        Integer par2 = (Integer) ClassInvokeCall.callMethod(obj, "getId");

        if (par1.equals(par2)) eq = true;

        return eq;
    }
}
