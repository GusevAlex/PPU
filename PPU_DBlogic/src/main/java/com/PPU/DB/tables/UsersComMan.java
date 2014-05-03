package com.PPU.DB.tables;

import com.PPU.DB.security.MD5;
import com.PPU.DB.workLogic.ClassInvokeCall;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 19.04.14
 * Time: 16:17
 * To change this template use File | Settings | File Templates.
 */

//CREATE TABLE "users_ComMan"
//        (
//        login text,
//        hash text,
//        email text,
//        id integer NOT NULL,
//        id_partner_commercial integer,
//        name text,
//        CONSTRAINT users_in_system_pkey PRIMARY KEY (id)
//        )

@Entity
@Table(name = "\"users_ComMan\"")
public class UsersComMan {

    private int id;
    private String login;
    private String hash;
    private String name;
    private String email;
    private int idPartenerCommercial;
    private PartnerCommercialMan partnerProject;

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

    @Column(name="login")
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Column(name="hash")
    public String getHash() {
        return hash;
    }

    public void setHash(String password) {

        this.hash = MD5.getMD5(password);
    }

    @Column(name="name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name="email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name="id_partner_commercial")
    public int getIdPartenerCommercial() {
        return idPartenerCommercial;
    }

    public void setIdPartenerCommercial(int idPartenerCommercial) {
        this.idPartenerCommercial = idPartenerCommercial;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_partner_commercial", insertable = false, updatable = false)
    public PartnerCommercialMan getPartnerProject() {
        return partnerProject;
    }

    public void setPartnerProject(PartnerCommercialMan partnerProject) {
        this.partnerProject = partnerProject;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof UsersComMan)) return false;

        boolean eq = false;

        Integer par1 = (Integer) ClassInvokeCall.callMethod(this, "getId");
        Integer par2 = (Integer) ClassInvokeCall.callMethod(obj, "getId");

        if (par1.equals(par2)) eq = true;

        return eq;
    }
}
