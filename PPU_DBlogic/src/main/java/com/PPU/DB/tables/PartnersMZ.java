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
    private Set<ComandMZ> ComandMZ = new LinkedHashSet<ComandMZ>();
    private Set<UsersMunMan> user;
    private Set<ProgramMZ> programMZs = new LinkedHashSet<ProgramMZ>();
	private Set<MZ> mzs = new LinkedHashSet<MZ>();
    private Set<NotificationMU> notificationMU = new LinkedHashSet<NotificationMU>();
    private TypeMU typesMU;

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
    @HeaderName(name = "Название")
    @Column(name="name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @FieldType(type = 1)
    @HeaderName(name = "Адрес")
    @Column(name="address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @FieldType(type = 1)
    @HeaderName(name = "Описание")
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


//    @FieldType(type = 3, worker="WorkWithCommandMz")
//    @HeaderName(name = "Команда")
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "partnerMZ")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.DELETE})
    public Set<ComandMZ> getComandMZ() {
        return ComandMZ;
    }

    public void setComandMZ(Set<ComandMZ> comandMZ) {
        ComandMZ = comandMZ;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "partnerMZ")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.DELETE})
    public Set<UsersMunMan> getUser() {
        return user;
	}

    public void setUser(Set<UsersMunMan> user) {
        this.user = user;
    }

//	@FieldType(type = 3, worker="WorkWithProgramMZ")
//	@HeaderName(name = "Программы")
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "partnersMZ")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.DELETE})
    public Set<ProgramMZ> getProgramMZs() {
        return programMZs;
    }

    public void setProgramMZs(Set<ProgramMZ> programMZs) {
        this.programMZs = programMZs;
    }

//	@FieldType(type = 3, worker="WorkWithMZ")
//	@HeaderName(name = "Муниципальные задания")
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "leader")
	@Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.DELETE})
	public Set<MZ> getMzs() {
		return mzs;
	}

	public void setMzs(Set<MZ> mzs) {
		this.mzs = mzs;
	}

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "partners")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.DELETE})
    public Set<NotificationMU> getNotificationMU() {
        return notificationMU;
    }

    public void setNotificationMU(Set<NotificationMU> notificationMU) {
        this.notificationMU = notificationMU;
    }

    public Set<NotificationMU> notificationMUSet() {
        Set set = new LinkedHashSet();

        int [] masId = new int[notificationMU.size()];

        int i = 0;
        for (NotificationMU not : notificationMU)
            masId[i++] = not.getId();

        Arrays.sort(masId);

        for (int id : masId)
            for (NotificationMU not : notificationMU)
                if (not.getId() == id)
                    set.add(not);

        return set;
    }

    @FieldType(type = 2, worker = "WorkWithTypeMU")
    @com.PPU.DB.tables.TableAnnot.HeaderName(name = "Тип МУ")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type_mu", insertable = false, updatable = false)
    public TypeMU getTypesMU() {
        return typesMU;
    }

    public void setTypesMU(TypeMU typesMU) {
        this.typesMU = typesMU;
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
