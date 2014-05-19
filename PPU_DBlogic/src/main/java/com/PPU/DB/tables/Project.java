package com.PPU.DB.tables;

import com.PPU.DB.tables.TableAnnot.FieldType;
import com.PPU.DB.tables.TableAnnot.HeaderName;
import com.PPU.DB.workLogic.ClassInvokeCall;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 12.04.14
 * Time: 22:03
 * To change this template use File | Settings | File Templates.
 */

//CREATE TABLE projects
//        (
//        id integer NOT NULL,
//        id_program integer,
//        name text,
//        id_customer integer,
//        id_leader integer,
//        start_date date,
//        expiration_date date,
//        description text,
//        budget real,
//        status integer,
//        CONSTRAINT projects_pkey PRIMARY KEY (id)
//        )

@Entity
@Table(name = "projects")
public class Project {

    private int id;
    private int id_program;
    private String name;
    private int id_customer;
    private int id_leader;
    private Date startDate;
    private Date expirationDate;
    private String description;
    private float budget;
    private int status;
    private String consumers;
    private Set<ComandProject> comandProject = new LinkedHashSet<ComandProject>();
    private ProgramCommerc program;
    private Set<LimitsProject> limitsProject = new LinkedHashSet<LimitsProject>();
    private Set<ValuesParametrForProject> valuesParametrForProject = new LinkedHashSet<ValuesParametrForProject>();
    private Set<ResourcesProject> resourcesProject = new LinkedHashSet<ResourcesProject>();
    private Set<CorrectionsProject> correctionsProject = new LinkedHashSet<CorrectionsProject>();
    private PartnerCommercialMan leader;
    private Set<FileProject> fileProjects = new LinkedHashSet<FileProject>();

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

    @Column(name="id_program")
    public int getId_program() {
        return id_program;
    }

    public void setId_program(int id_program) {
        this.id_program = id_program;
    }

    @Column(name="name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name="id_customer")
    public int getId_customer() {
        return id_customer;
    }

    public void setId_customer(int id_customer) {
        this.id_customer = id_customer;
    }

    @Column(name="id_leader")
    public int getId_leader() {
        return id_leader;
    }

    public void setId_leader(int id_leader) {
        this.id_leader = id_leader;
    }

    @Column(name="start_date")
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date start_date) {
        this.startDate = start_date;
    }

    @Column(name="expiration_date")
    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expiration_date) {
        this.expirationDate = expiration_date;
    }

    @Column(name="description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name="budget")
    public float getBudget() {
        return budget;
    }

    public void setBudget(float budget) {
        this.budget = budget;
    }

    @Column(name="status")
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @FieldType(type = 1)
    @HeaderName(name = "Потребители МУ")
    @Column(name="consumers")
    public String getConsumers() {
        return consumers;
    }

    public void setConsumers(String consumers) {
        this.consumers = consumers;
    }

	@FieldType(type = 3, worker="WorkWithComandProject")
	@HeaderName(name = "Команда проекта")
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "project")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.DELETE})
    public Set<ComandProject> getComandProject() {
        return comandProject;
    }

    public void setComandProject(Set<ComandProject> comandProject) {
        this.comandProject = comandProject;
    }

	@FieldType(type = 2, worker = "WorkWithProgramCommerc")
	@com.PPU.DB.tables.TableAnnot.HeaderName(name = "Программа")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_program", insertable = false, updatable = false)
    public ProgramCommerc getProgram() {
        return program;
    }

    public void setProgram(ProgramCommerc program) {
        this.program = program;
    }

	@FieldType(type = 3, worker="WorkWithLimitsProject")
	@HeaderName(name = "Лимиты")
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "project")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.DELETE})
    public Set<LimitsProject> getLimitsProject() {
        return limitsProject;
    }

    public void setLimitsProject(Set<LimitsProject> limitsProject) {
        this.limitsProject = limitsProject;
    }

	@FieldType(type = 3, worker="WorkWithValuesParametrForProject")
	@HeaderName(name = "Параметры")
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "project")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.DELETE})
    public Set<ValuesParametrForProject> getValuesParametrForProject() {
        return valuesParametrForProject;
    }

    public void setValuesParametrForProject(Set<ValuesParametrForProject> valuesParametrForProject) {
        this.valuesParametrForProject = valuesParametrForProject;
    }

	@FieldType(type = 3, worker="WorkWithResourcesProject")
	@HeaderName(name = "Ресурсы")
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "project")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.DELETE})
    public Set<ResourcesProject> getResourcesProject() {
        return resourcesProject;
    }

    public void setResourcesProject(Set<ResourcesProject> resourcesProject) {
        this.resourcesProject = resourcesProject;
    }

	@FieldType(type = 3, worker="WorkWithCorrectionProject")
	@HeaderName(name = "Корректировки")
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "project")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.DELETE})
    public Set<CorrectionsProject> getCorrectionsProject() {
        return correctionsProject;
    }

    public void setCorrectionsProject(Set<CorrectionsProject> correctionsProject) {
        this.correctionsProject = correctionsProject;
    }

    @FieldType(type = 2, worker = "WorkWithPartnerCommerc")
    @com.PPU.DB.tables.TableAnnot.HeaderName(name = "Руководитель")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_leader", insertable = false, updatable = false)
    public PartnerCommercialMan getLeader() {
        return leader;
    }

    public void setLeader(PartnerCommercialMan leader) {
        this.leader = leader;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "project")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.DELETE})
    public Set<FileProject> getFileProjects() {
        return fileProjects;
    }

    public void setFileProjects(Set<FileProject> fileProjects) {
        this.fileProjects = fileProjects;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Project)) return false;

        boolean eq = false;

        Integer par1 = (Integer) ClassInvokeCall.callMethod(this, "getId");
        Integer par2 = (Integer) ClassInvokeCall.callMethod(obj, "getId");

        if (par1.equals(par2)) eq = true;

        return eq;
    }
}
