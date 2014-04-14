package com.PPU.DB.tables;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 12.04.14
 * Time: 23:06
 * To change this template use File | Settings | File Templates.
 */

//CREATE TABLE type_budget_service
//        (
//        id integer NOT NULL,
//        name text,
//        description text,
//        CONSTRAINT type_budget_service_pkey PRIMARY KEY (id)
//        )

@Entity
@Table(name = "type_budget_service")
public class TypeBudgetService {

    private int id;
    private String name;
    private String description;

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

    @Column(name="name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name="description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
