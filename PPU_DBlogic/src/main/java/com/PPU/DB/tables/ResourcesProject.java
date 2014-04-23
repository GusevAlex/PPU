package com.PPU.DB.tables;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 12.04.14
 * Time: 23:04
 * To change this template use File | Settings | File Templates.
 */

//CREATE TABLE resources_project
//        (
//        id integer NOT NULL,
//        id_project integer,
//        id_provider_resources integer,
//        CONSTRAINT resources_project_pkey PRIMARY KEY (id)
//        )

@Entity
@Table(name = "resources_project")
public class ResourcesProject {

    private int id;
    private int idProject;
    private int idProviderResources;
    private Project project;
    private Providers providers;

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

    @Column(name="id_provider_resources")
    public int getIdProviderResources() {
        return idProviderResources;
    }

    public void setIdProviderResources(int idProviderResources) {
        this.idProviderResources = idProviderResources;
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
    @JoinColumn(name = "id_provider_resources", insertable = false, updatable = false)
    public Providers getProviders() {
        return providers;
    }

    public void setProviders(Providers providers) {
        this.providers = providers;
    }
}
