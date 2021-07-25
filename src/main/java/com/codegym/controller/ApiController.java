package com.codegym.controller;

import com.codegym.model.City;
import com.codegym.model.Country;
import com.codegym.service.city.ICityService;
import com.codegym.service.country.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class ApiController {
    @Autowired
    private ICityService cityService;

    @Autowired
    private ICountryService countryService;

    @GetMapping("/")
    public ModelAndView listCity(){
        return new ModelAndView("/api/list");
    }

    @GetMapping("/allCountries")
    public ResponseEntity<Iterable<Country>> allCountries(){
        return new ResponseEntity<>(countryService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/allCities")
    public ResponseEntity<Iterable<City>> allCities(){
        return new ResponseEntity<>(cityService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/createNewCity")
    public ResponseEntity<City> createNewCity(@Valid @RequestBody City city, BindingResult bindingResult){
        new City().validate(city, bindingResult);
        if (bindingResult.hasFieldErrors()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Country country = countryService.findById(city.getCountry().getCountry_id()).get();
        city.setCountry(country);
        return new ResponseEntity<>(cityService.save(city), HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteCity/{id}")
    public ResponseEntity<City> deleteCity(@PathVariable Long id){
        cityService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/edit/{id}")
    public ResponseEntity<City> showEditCity(@PathVariable Long id){
        return new ResponseEntity<>(cityService.findById(id).get(),HttpStatus.CREATED);
    }
}
