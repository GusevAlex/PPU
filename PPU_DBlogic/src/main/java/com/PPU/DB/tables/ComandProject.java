package com.PPU.DB.tables;

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
    private PartnersMZ partnerProject;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_partner_project", insertable = false, updatable = false)
    public PartnersMZ getPartnerProject() {
        return partnerProject;
    }

    public void setPartnerProject(PartnersMZ partnerProject) {
        this.partnerProject = partnerProject;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_project", insertable = false, updatable = false)
    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
