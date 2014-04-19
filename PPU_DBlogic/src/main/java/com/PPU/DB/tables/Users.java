package com.PPU.DB.tables;

import com.PPU.DB.security.MD5;
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

//CREATE TABLE users
//        (
//        id integer NOT NULL,
//        login text,
//        hash text,
//        name text,
//        id_partner_mz integer,
//        id_partner_commerc integer,
//        CONSTRAINT users_pkey PRIMARY KEY (id)
//        )

@Entity
@Table(name = "users")
public class Users {

    private int id;
    private String login;
    private String hash;
    private String name;
    private Set<PartnersMZ> partnersMZ;
    private Set<PartnerCommercialMan> partnerProject;

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

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.DELETE})
    public Set<PartnersMZ> getPartnersMZ() {
        return partnersMZ;
    }

    public void setPartnersMZ(Set<PartnersMZ> partnersMZ) {
        this.partnersMZ = partnersMZ;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.DELETE})
    public Set<PartnerCommercialMan> getPartnerProject() {
        return partnerProject;
    }

    public void setPartnerProject(Set<PartnerCommercialMan> partnerProject) {
        this.partnerProject = partnerProject;
    }
}
