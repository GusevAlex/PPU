package com.PPU.DB.tables;

import com.PPU.DB.security.MD5;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 19.04.14
 * Time: 16:17
 * To change this template use File | Settings | File Templates.
 */

//CREATE TABLE "users_MunMan"
//        (
//        id integer NOT NULL,
//        login text,
//        hash text,
//        name text,
//        id_partner_mz integer,
//        CONSTRAINT users_pkey PRIMARY KEY (id)
//        )

@Entity
@Table(name = "\"users_MunMan\"")
public class UsersMunMan {

    private int id;
    private String login;
    private String hash;
    private String name;
    private int idPartnerMZ;
    private PartnersMZ partnerMZ;

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

    @Column(name="id_partner_mz")
    public int getIdPartnerMZ() {
        return idPartnerMZ;
    }

    public void setIdPartnerMZ(int idPartnerMZ) {
        this.idPartnerMZ = idPartnerMZ;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_partner_mz", insertable = false, updatable = false)
    public PartnersMZ getPartnerMZ() {
        return partnerMZ;
    }

    public void setPartnerMZ(PartnersMZ partnerMZ) {
        this.partnerMZ = partnerMZ;
    }
}
