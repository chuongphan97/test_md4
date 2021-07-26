package com.codegym.controller;

import com.codegym.model.City;
import com.codegym.model.Country;
import com.codegym.service.city.ICityService;
import com.codegym.service.country.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class HomeController {
    @Autowired
    private ICityService cityService;

    @Autowired
    private ICountryService countryService;

    @GetMapping("/")
    public ModelAndView listCity(){
        ModelAndView modelAndView = new ModelAndView("/list");
        modelAndView.addObject("cities", cityService.findAll());
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("city",new City());
        modelAndView.addObject("countries", countryService.findAll());
        return modelAndView;
    }

    @PostMapping("/create-city")
    public String create(@Valid @ModelAttribute City city, BindingResult bindingResult, Model model){
        new City().validate(city,bindingResult);
        if (bindingResult.hasFieldErrors()){
            return "/create";
        } else {
            cityService.save(city);
            model.addAttribute("cities", cityService.findAll());
            return "redirect:/";
        }
    }

    @GetMapping("/delete/{id}")
    private ModelAndView showDeleteForm(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("/delete");
        modelAndView.addObject("city", cityService.findById(id).get());
        return modelAndView;
    }

    @GetMapping("/{id}")
    private String delete(@PathVariable Long id){
        cityService.remove(id);
        return "redirect:/";
    }


    @GetMapping("/edit/{id}")
    private ModelAndView showEditForm(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("/edit");
        modelAndView.addObject("countries", countryService.findAll());
        modelAndView.addObject("city",cityService.findById(id).get());
        return modelAndView;
    }

    @PostMapping("/edit/{id}")
    private String edit(@Valid @ModelAttribute City city, BindingResult bindingResult,@PathVariable Long id, Model model){
        new City().validate(city,bindingResult);
        if (bindingResult.hasFieldErrors()){
            return "/edit";
        } else {
            city.setCity_id(id);
            cityService.save(city);
            return "redirect:/";
        }
    }
}
