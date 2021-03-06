package com.PPU.DB.tables;

import com.PPU.DB.DAO.PpuDaoInterface;
import com.PPU.DB.tables.TableAnnot.FieldType;
import com.PPU.DB.workLogic.ClassInvokeCall;
import com.PPU.DB.workLogic.WorkWithTable;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 12.04.14
 * Time: 22:04
 * To change this template use File | Settings | File Templates.
 */

//CREATE TABLE "comand_MZ"
//        (
//        id integer NOT NULL,
//        id_mz integer,
//        id_partner_mz integer,
//        CONSTRAINT "comand_MZ_pkey" PRIMARY KEY (id)
//        )

@Entity
@Table(name = "\"comand_MZ\"")
public class ComandMZ {
    private int id;
    private int idMZ;
    private int idPartnerMZ;
    private boolean work;
    private PartnersMZ partnerMZ;
    private MZ MZ;

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


    @Column(name="id_partner_mz")
    public int getIdPartnerMZ() {
        return idPartnerMZ;
    }

    public void setIdPartnerMZ(int idPartnerMZ) {
        this.idPartnerMZ = idPartnerMZ;
    }

    @Column(name="work")
    public boolean isWork() {
        return work;
    }

    public void setWork(boolean work) {
        this.work = work;
    }

	@FieldType(type = 2, worker = "WorkWithPartnerMZ")
	@com.PPU.DB.tables.TableAnnot.HeaderName(name = "�������")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_partner_mz", insertable = false, updatable = false)
    public PartnersMZ getPartnerMZ() {
        return partnerMZ;
    }

    public void setPartnerMZ(PartnersMZ partnersMZ) {
        this.partnerMZ = partnersMZ;
    }

	@FieldType(type = 2, worker = "WorkWithMZ")
	@com.PPU.DB.tables.TableAnnot.HeaderName(name = "������������� �������")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_mz", insertable = false, updatable = false)
    public MZ getMZ() {
//        PpuDaoInterface ppu = WorkWithTable.getPpuDao();
//        MZ = ppu.getMz(this.getIdMZ());

        return MZ;
    }

    public void setMZ(MZ MZ) {
        this.MZ = MZ;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ComandMZ)) return false;

        boolean eq = false;

        Integer par1 = (Integer) ClassInvokeCall.callMethod(this, "getId");
        Integer par2 = (Integer) ClassInvokeCall.callMethod(obj, "getId");

        if (par1.equals(par2)) eq = true;

        return eq;
    }
}
