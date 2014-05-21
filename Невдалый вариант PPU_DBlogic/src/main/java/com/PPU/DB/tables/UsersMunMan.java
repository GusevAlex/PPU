package com.PPU.DB.tables;

import com.PPU.DB.DAO.PpuDaoInterface;
import com.PPU.DB.security.MD5;
import com.PPU.DB.tables.TableAnnot.FieldType;
import com.PPU.DB.workLogic.ClassInvokeCall;
import com.PPU.DB.workLogic.WorkWithTable;
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
    private String email;
    private int idPartnerMZ;
    private boolean sendMail;
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

    @FieldType(type = 1)
    @com.PPU.DB.tables.TableAnnot.HeaderName(name = "Логин")
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

    public void setHash(String hash) {

        this.hash = hash;
    }

    public void setHashByPassword(String password) {

        this.hash = MD5.getMD5(password);
    }

    @FieldType(type = 1)
    @com.PPU.DB.tables.TableAnnot.HeaderName(name = "Имя")
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

    @FieldType(type = 1)
    @com.PPU.DB.tables.TableAnnot.HeaderName(name = "Почта")
    @Column(name="email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name="sendMail")
    public boolean isSendMail() {
        return sendMail;
    }

    public void setSendMail(boolean sendMail) {
        this.sendMail = sendMail;
    }

	@FieldType(type = 2, worker = "WorkWithPartnerMZ")
	@com.PPU.DB.tables.TableAnnot.HeaderName(name = "Участник коммерчесого управления")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_partner_mz", insertable = false, updatable = false)
    public PartnersMZ getPartnerMZ() {
        PpuDaoInterface ppu = WorkWithTable.getPpuDao();
        partnerMZ = ppu.getPartnersMz(this.getIdPartnerMZ());

        return partnerMZ;
    }

    public void setPartnerMZ(PartnersMZ partnerMZ) {
        this.partnerMZ = partnerMZ;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof UsersMunMan)) return false;

        boolean eq = false;

        Integer par1 = (Integer) ClassInvokeCall.callMethod(this, "getId");
        Integer par2 = (Integer) ClassInvokeCall.callMethod(obj, "getId");

        if (par1.equals(par2)) eq = true;

        return eq;
    }
}
