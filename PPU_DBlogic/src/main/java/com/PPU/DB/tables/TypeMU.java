package com.PPU.DB.tables;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 12.04.14
 * Time: 23:05
 * To change this template use File | Settings | File Templates.
 */

//CREATE TABLE "type_MU"
//        (
//        id integer NOT NULL,
//        name text,
//        CONSTRAINT "type_MZ_pkey" PRIMARY KEY (id)
//        )

@Entity
@Table(name = "\"type_MU\"")
public class TypeMU {

    private int id;
    private String name;

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
}
