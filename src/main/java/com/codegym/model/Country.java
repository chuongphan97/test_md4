package com.codegym.model;

import antlr.collections.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;

@Table(name = "countries")
@Entity
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long country_id;

    private String country_name;

    public Country() {
    }

    public Country(String country_name) {
        this.country_name = country_name;
    }

    public Country(Long country_id, String country_name) {
        this.country_id = country_id;
        this.country_name = country_name;
    }

    public Long getCountry_id() {
        return country_id;
    }

    public void setCountry_id(Long country_id) {
        this.country_id = country_id;
    }

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

}
