package com.codegym.service.country;

import com.codegym.model.Country;
import com.codegym.service.IGeneralService;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ICountryService extends IGeneralService<Country> {
}
