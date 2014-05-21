package com.PPU.DB.tables;

import com.PPU.DB.tables.TableAnnot.*;
import com.PPU.DB.workLogic.ClassInvokeCall;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 09.05.14
 * Time: 21:08
 * To change this template use File | Settings | File Templates.
 */

//CREATE TABLE "notification_MU"
//        (
//        id integer NOT NULL,
//        text text,
//        receiver integer,
//        read boolean,
//        CONSTRAINT "notification_MU_pkey" PRIMARY KEY (id)
//        )

@Entity
@Table(name = "\"notification_MU\"")
public class NotificationMU {
    private int id;
    private String text;
    private int receiver;
    private boolean read;
    private Date date;
    private PartnersMZ partners;

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

    @Column(name="read")
    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    @Column(name="receiver")
    public int getReceiver() {
        return receiver;
    }

    public void setReceiver(int receiver) {
        this.receiver = receiver;
    }

    @Column(name="text")
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "receiver", insertable = false, updatable = false)
    public PartnersMZ getPartners() {
        return partners;
    }

    public void setPartners(PartnersMZ partners) {
        this.partners = partners;
    }

    @Column(name="date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
