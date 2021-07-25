package com.codegym.model;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "cities")
@Component
public class City implements Validator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long city_id;

    @NotEmpty(message = "City name not empty.")
    @Size(min = 3, max = 50)
    private String city_name;

    @NotNull(message = "Area not empty.")
    private float area;

    @NotNull(message = "Population not empty.")
    private int population;

    @NotNull(message = "GDP not empty.")
    private float GDP;

    private String description;


    @ManyToOne
    @JoinColumn(name = "country_id")
    @NotNull(message = "Country not empty.")
    private Country country;

    public City() {
    }

    public City(String city_name, String description, Country country) {
        this.city_name = city_name;
        this.description = description;
        this.country = country;
    }

    public City(Long city_id, String city_name, String description, Country country) {
        this.city_id = city_id;
        this.city_name = city_name;
        this.description = description;
        this.country = country;
    }

    public City(String city_name, float area, int population, float GDP, Country country) {
        this.city_name = city_name;
        this.area = area;
        this.population = population;
        this.GDP = GDP;
        this.country = country;
    }

    public City(String city_name, float area, int population, float GDP, String description, Country country) {
        this.city_name = city_name;
        this.area = area;
        this.population = population;
        this.GDP = GDP;
        this.description = description;
        this.country = country;
    }

    public Long getCity_id() {
        return city_id;
    }

    public void setCity_id(Long city_id) {
        this.city_id = city_id;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Country getCountry() {
        return country;
    }

    public float getArea() {
        return area;
    }

    public void setArea(float area) {
        this.area = area;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public float getGDP() {
        return GDP;
    }

    public void setGDP(float GDP) {
        this.GDP = GDP;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return City.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        City city = (City) o;

//      Validate city_name
        String city_name = city.getCity_name();
        ValidationUtils.rejectIfEmpty(errors,"city_name","city_name.empty");

        if (!(city_name.length() >= 3 && city_name.length() <= 50)){
            errors.rejectValue("city_name","city_name.length");
        }

//        Validate area
        ValidationUtils.rejectIfEmpty(errors,"area","area.empty");

//        Validate country
        ValidationUtils.rejectIfEmpty(errors,"country","country.empty");

//        Validate population
        ValidationUtils.rejectIfEmpty(errors,"population","population.empty");

//        Validate GDP
        ValidationUtils.rejectIfEmpty(errors,"GDP","GDP.empty");

    }
}
