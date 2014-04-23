package com.PPU.DB.tables;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 12.04.14
 * Time: 23:02
 * To change this template use File | Settings | File Templates.
 */

//CREATE TABLE "resources_MZ"
//        (
//        id integer NOT NULL,
//        id_mz integer,
//        id_provider_resources integer,
//        CONSTRAINT resources_pkey PRIMARY KEY (id)
//        )

@Entity
@Table(name = "\"resources_MZ\"")
public class ResourcesMZ {

    private int id;
    private int idMZ;
    private int idProviderResources;
    private MZ MZ;
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

    @Column(name="id_mz")
    public int getIdMZ() {
        return idMZ;
    }

    public void setIdMZ(int idMZ) {
        this.idMZ = idMZ;
    }

    @Column(name="id_provider_resources")
    public int getIdProviderResources() {
        return idProviderResources;
    }

    public void setIdProviderResources(int idProviderResources) {
        this.idProviderResources = idProviderResources;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_mz", insertable = false, updatable = false)
    public MZ getMZ() {
        return MZ;
    }

    public void setMZ(MZ MZ) {
        this.MZ = MZ;
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
