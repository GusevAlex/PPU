package com.PPU.DB.tables;

import com.PPU.DB.tables.TableAnnot.FieldType;
import com.PPU.DB.workLogic.ClassInvokeCall;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 12.04.14
 * Time: 22:08
 * To change this template use File | Settings | File Templates.
 */

//CREATE TABLE comand_project
//        (
//        id integer NOT NULL,
//        id_project integer,
//        id_partner_project integer,
//        CONSTRAINT comand_project_pkey PRIMARY KEY (id)
//        )

@Entity
@Table(name = "\"comand_project\"")
public class ComandProject {
    private int id;
    private int idProject;
    private int idPartnerProject;
    private boolean work;
    private PartnerCommercialMan partnerProject;
    private Project project;

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

    @Column(name="id_project")
    public int getIdProject() {
        return idProject;
    }

    public void setIdProject(int idProject) {
        this.idProject = idProject;
    }

    @Column(name="id_partner_project")
    public int getIdPartnerProject() {
        return idPartnerProject;
    }

    public void setIdPartnerProject(int idPartnerProject) {
        this.idPartnerProject = idPartnerProject;
    }

    @Column(name="work")
    public boolean isWork() {
        return work;
    }

    public void setWork(boolean work) {
        this.work = work;
    }

	@FieldType(type = 2, worker = "WorkWithPartnerCommerc")
	@com.PPU.DB.tables.TableAnnot.HeaderName(name = "???????? ?????????? ??????????")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_partner_project", insertable = false, updatable = false)
    public PartnerCommercialMan getPartnerProject() {
        return partnerProject;
    }

    public void setPartnerProject(PartnerCommercialMan partnerProject) {
        this.partnerProject = partnerProject;
    }

	@FieldType(type = 2, worker = "WorkWithProject")
	@com.PPU.DB.tables.TableAnnot.HeaderName(name = "??????")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_project", insertable = false, updatable = false)
    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
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
