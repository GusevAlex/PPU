package com.PPU.DB.tables;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 12.04.14
 * Time: 22:11
 * To change this template use File | Settings | File Templates.
 */

//CREATE TABLE corrections_project
//        (
//        id integer NOT NULL,
//        id_project integer,
//        correction_date date,
//        id_parametr integer,
//        value_after text,
//        value_before text,
//        CONSTRAINT corrections_project_pkey PRIMARY KEY (id)
//        )

@Entity
@Table(name = "corrections_project")
public class CorrectionsProject {

    private int id;
    private int idProject;
    private Date CorrectionDate;
    private int idParametr;
    private String ValueBefore;
    private String ValueAfter;
    private Project project;
    private Parametrs parametr;

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

    @Column(name="correction_date")
    public Date getCorrectionDate() {
        return CorrectionDate;
    }

    public void setCorrectionDate(Date correctionDate) {
        CorrectionDate = correctionDate;
    }

    @Column(name="id_parametr")
    public int getIdParametr() {
        return idParametr;
    }

    public void setIdParametr(int idParametr) {
        this.idParametr = idParametr;
    }

    @Column(name="value_before")
    public String getValueBefore() {
        return ValueBefore;
    }

    public void setValueBefore(String valueBefore) {
        ValueBefore = valueBefore;
    }

    @Column(name="value_after")
    public String getValueAfter() {
        return ValueAfter;
    }

    public void setValueAfter(String valueAfter) {
        ValueAfter = valueAfter;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_project", insertable = false, updatable = false)
    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_parametr", insertable = false, updatable = false)
    public Parametrs getParametr() {
        return parametr;
    }

    public void setParametr(Parametrs parametr) {
        this.parametr = parametr;
    }
}
