package com.PPU.DB.tables;

import com.PPU.DB.tables.TableAnnot.FieldType;
import com.PPU.DB.tables.TableAnnot.HeaderName;
import com.PPU.DB.workLogic.ClassInvokeCall;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.*;

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
//        id_user integer,
//        CONSTRAINT partner_commercial_man_pkey PRIMARY KEY (id)
//        )

@Entity
@Table(name = "partner_commercial_man")
public class PartnerCommercialMan {

    private int id;
    private String name;
    private String address;
    private String description;
    private Set<ComandProject> comandProject = new LinkedHashSet<ComandProject>();
    private Set<UsersComMan> user;
    private Set<ProgramCommerc> programCommercs = new LinkedHashSet<ProgramCommerc>();
    private Set<NotificationCom> notificationComs = new LinkedHashSet<NotificationCom>();

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

	@FieldType(type = 3, worker="WorkWithCorrectionProject")
	@HeaderName(name = "Команда")
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "partnerProject")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.DELETE})
    public Set<ComandProject> getComandProject() {
        return comandProject;
    }

    public void setComandProject(Set<ComandProject> comandProject) {
        comandProject = comandProject;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "partnerProject")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.DELETE})
    public Set<UsersComMan> getUser() {
        return user;
    }

    public void setUser(Set<UsersComMan> user) {
        this.user = user;
    }

	@FieldType(type = 3, worker="WorkWithProgramCommerc")
	@HeaderName(name = "Программа")
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "partnerCommercialMan")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.DELETE})
    public Set<ProgramCommerc> getProgramCommercs() {
        return programCommercs;
    }

    public void setProgramCommercs(Set<ProgramCommerc> programCommercs) {
        this.programCommercs = programCommercs;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "partners")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.DELETE})
    public Set<NotificationCom> getNotificationComs() {
        return notificationComs;
    }

    public void setNotificationComs(Set<NotificationCom> notificationComs) {
        this.notificationComs = notificationComs;
    }

    public Set<NotificationCom> notificationComs() {
        Set set = new LinkedHashSet();

        int [] masId = new int[notificationComs.size()];

        int i = 0;
        for (NotificationCom not : notificationComs)
            masId[i++] = not.getId();

        Arrays.sort(masId);

        for (int id : masId)
            for (NotificationCom not : notificationComs)
                if (not.getId() == id)
                    set.add(not);

        return set;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof PartnerCommercialMan)) return false;

        boolean eq = false;

        Integer par1 = (Integer) ClassInvokeCall.callMethod(this, "getId");
        Integer par2 = (Integer) ClassInvokeCall.callMethod(obj, "getId");

        if (par1.equals(par2)) eq = true;

        return eq;
    }

}
