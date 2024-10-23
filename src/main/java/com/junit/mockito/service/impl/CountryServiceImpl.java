package com.junit.mockito.service.impl;

import com.junit.mockito.entity.Country;
import com.junit.mockito.repository.CountryRepo;
import com.junit.mockito.service.CountryServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryServiceI {
    @Autowired
    private CountryRepo countryRepo;

    public List<Country> getAllCountries() {

        return countryRepo.findAll();

    }


//	 public List<Country> getAllCountries(Integer pageNumber, Integer pageSize) {
//
//
//		PageRequest page = PageRequest.of(pageNumber,pageSize);
//
//
//		Page<Country> findAll = this.countryRepo.findAll(page);
//
//		List<Country> content = findAll.getContent();
//
//		return content;
//
//
//	}

    public Country getCountryById(Integer id) {

        Country country = this.countryRepo.findById(id).get();


        return country;
    }

    public Country getCountryByName(String countryName) {

        List<Country> countries = this.countryRepo.findAll();

        Country country = null;

        for (Country con : countries) {
            if (con.getCountryName().equalsIgnoreCase(countryName))
                country = con;

        }
        return country;
    }

    public Country addCountry(Country country) {
        Country save = countryRepo.save(country);
        return save;
    }

    public Country updateCountry(Country country, Integer id) {

        country.setId(id);
        Country save = countryRepo.save(country);
        return save;
    }

    public void deleteCountry(Country country) {

        countryRepo.delete(country);
    }

    public Country updateCountryNew(Country country, Integer id) {

        Country country1 = countryRepo.findById(id).get();
        country.setCountryName(country1.getCountryName());
        country.setStateName(country1.getStateName());

        Country save = countryRepo.save(country1);
        return save;
    }

}
