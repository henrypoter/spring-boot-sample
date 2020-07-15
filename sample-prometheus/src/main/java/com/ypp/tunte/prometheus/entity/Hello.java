package com.ypp.tunte.prometheus.entity;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * 功能说明
 *
 * @author yanpp
 * @createDateTime 2020/7/7
 **/
@Entity
@Table(name = "tb_hello")
public class Hello {
    @Id
    @GeneratedValue(strategy=IDENTITY)
    private Integer id;
    @Column(name ="name", length = 50)
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
