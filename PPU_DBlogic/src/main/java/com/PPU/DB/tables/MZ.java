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
 * Time: 21:56
 * To change this template use File | Settings | File Templates.
 */

//CREATE TABLE "MZ"
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
//        service_type integer,
//        CONSTRAINT "MZ_pkey" PRIMARY KEY (id)
//        )


@Entity
@Table(name = "\"MZ\"")
public class MZ {

    private int id;
    private int idProgram;
    private String name;
    private int idCustomer;
    private int idLeader;
    private Date startDate;
    private Date expirationDate;
    private String description;
    private float budget;
    private int status;
    private int serviceType;
    private String consumers;
    private Set<ComandMZ> comandMZ = new LinkedHashSet<ComandMZ>();
    private ProgramMZ program;
    private TypeServiceMZ typeServiceMZ;
    private Set<LimitsMZ> limitsMZ = new LinkedHashSet<LimitsMZ>();
    private Set<ValuesParametrForMZ> valuesParametrForMZ = new LinkedHashSet<ValuesParametrForMZ>();
    private Set<ResourcesMZ> resourcesMZ = new LinkedHashSet<ResourcesMZ>();
    private Set<CorrectionsMZ> correctionsMZ = new LinkedHashSet<CorrectionsMZ>();
	private PartnersMZ leader;
    private Set<FileMZ> fileMZs = new LinkedHashSet<FileMZ>();

    @Column(name="id_program")
    public int getIdProgram() {
        return idProgram;
    }

    public void setIdProgram(int idProgram) {
        this.idProgram = idProgram;
    }

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
    @HeaderName(name = "Название")
    @Column(name="name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name="id_customer")
    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    @Column(name="id_leader")
    public int getIdLeader() {
        return idLeader;
    }

    public void setIdLeader(int idLeader) {
        this.idLeader = idLeader;
    }

    @FieldType(type = 1)
    @HeaderName(name = "Дата начала")
    @Column(name="start_date")
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @FieldType(type = 1)
    @HeaderName(name = "Дата окончания")
    @Column(name="expiration_date")
    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    @FieldType(type = 1)
    @HeaderName(name = "Описание")
    @Column(name="description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @FieldType(type = 1)
    @HeaderName(name = "Бюджет")
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

    @Column(name="service_type")
    public int getService_type() {
        return serviceType;
    }

    public void setService_type(int serviceType) {
        this.serviceType = serviceType;
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

    @FieldType(type = 3, worker="WorkWithCommandMz")
	@HeaderName(name = "Команда")
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "MZ")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.DELETE})
    public Set<ComandMZ> getComandMZ() {
        return comandMZ;
    }

    public void setComandMZ(Set<ComandMZ> comandMZ) {
        this.comandMZ = comandMZ;
    }

	@FieldType(type = 2, worker = "WorkWithProgramMZ")
	@com.PPU.DB.tables.TableAnnot.HeaderName(name = "Программа")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_program", insertable = false, updatable = false)
    public ProgramMZ getProgram() {
        return program;
    }

    public void setProgram(ProgramMZ programMZ) {
        this.program = programMZ;
    }

	@FieldType(type = 2, worker = "WorkWithTypeServiceMZ")
	@com.PPU.DB.tables.TableAnnot.HeaderName(name = "Тип услуги")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "service_type", insertable = false, updatable = false)
    public TypeServiceMZ getTypeServiceMZ() {
        return typeServiceMZ;
    }

    public void setTypeServiceMZ(TypeServiceMZ typeServiceMZ) {
        this.typeServiceMZ = typeServiceMZ;
    }

	@FieldType(type = 3, worker="WorkWithLimitsMz")
	@HeaderName(name = "Лимиты МЗ")
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "MZ")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.DELETE})
    public Set<LimitsMZ> getLimitsMZ() {
        return limitsMZ;
    }

    public void setLimitsMZ(Set<LimitsMZ> limitsMZ) {
        this.limitsMZ = limitsMZ;
    }

	@FieldType(type = 3, worker="WorkWithValuesParametrForMZ")
	@HeaderName(name = "Параметры МЗ")
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "MZ")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.DELETE})
    public Set<ValuesParametrForMZ> getValuesParametrForMZ() {
        return valuesParametrForMZ;
    }

    public void setValuesParametrForMZ(Set<ValuesParametrForMZ> valuesParametrForMZ) {
        this.valuesParametrForMZ = valuesParametrForMZ;
    }

//	@FieldType(type = 3, worker="WorkWithResourcesMZ")
//	@HeaderName(name = "Ресурсы")
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "MZ")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.DELETE})
    public Set<ResourcesMZ> getResourcesMZ() {
        return resourcesMZ;
    }

    public void setResourcesMZ(Set<ResourcesMZ> resourcesMZ) {
        this.resourcesMZ = resourcesMZ;
    }

//	@FieldType(type = 3, worker="WorkWithCorrectionProject")
//	@HeaderName(name = "Корректировки МЗ")
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "MZ")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.DELETE})
    public Set<CorrectionsMZ> getCorrectionsMZ() {
        return correctionsMZ;
    }

    public void setCorrectionsMZ(Set<CorrectionsMZ> correctionsMZ) {
        this.correctionsMZ = correctionsMZ;
    }

	@FieldType(type = 2, worker = "WorkWithPartnerMZ")
	@com.PPU.DB.tables.TableAnnot.HeaderName(name = "Руководитель")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_leader", insertable = false, updatable = false)
	public PartnersMZ getLeader() {
		return leader;
	}

	public void setLeader(PartnersMZ leader) {
		this.leader = leader;
	}

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "mz")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.DELETE})
    public Set<FileMZ> getFileMZs() {
        return fileMZs;
    }

    public void setFileMZs(Set<FileMZ> fileMZs) {
        this.fileMZs = fileMZs;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof MZ)) return false;

        boolean eq = false;

        Integer par1 = (Integer) ClassInvokeCall.callMethod(this, "getId");
        Integer par2 = (Integer) ClassInvokeCall.callMethod(obj, "getId");

        if (par1.equals(par2)) eq = true;

        return eq;
    }
}
