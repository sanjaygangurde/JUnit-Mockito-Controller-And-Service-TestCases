package com.junit.mockito.controller;

import com.junit.mockito.entity.Country;
import com.junit.mockito.exception.UserNotFoundException;
import com.junit.mockito.service.CountryServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CountryController {


    @Autowired
    private CountryServiceI service;

    @PostMapping("/saveUser")
    public ResponseEntity<Country> addCountry(@RequestBody Country country) {

        if (country == null) {
            throw new UserNotFoundException("User Not Found");
        } else {
            Country save = service.addCountry(country);
            return new ResponseEntity<Country>(save, HttpStatus.CREATED);
        }
    }

    //	@GetMapping("/getAllCountries/pageNumber/{pageNumber}/pageSize/{pageSize}")
//	public ResponseEntity<List<Country>> getAllCountries(
//			@RequestParam(value = "pageNumber", defaultValue = "1", required = true) Integer pageNumber,
//			@RequestParam(value = "pageSize", defaultValue = "1", required = true) Integer pageSize) {
//		if (pageNumber == null  && pageSize==null) {
//			throw new UserNotFoudException("User Not Found");
//
//		}
//
//		else {
//
//			List<Country> allCountries = this.service.getAllCountries();
//
//			return new ResponseEntity<List<Country>>(allCountries, HttpStatus.FOUND);
//		}
//
//	}

    @GetMapping("/getAllCountries")
    public ResponseEntity<List<Country>> getAllCountries()
    {

        List<Country> allCountries = this.service.getAllCountries();

        return new ResponseEntity<List<Country>>(allCountries, HttpStatus.FOUND);
    }

    @GetMapping("/getCountryById/{id}")
    public ResponseEntity<Country> getCountryById(@PathVariable("id") Integer id) {

        if (id == null) {
            throw new UserNotFoundException("User Not found");
        } else {
            Country countryById = this.service.getCountryById(id);
            return new ResponseEntity<Country>(countryById, HttpStatus.FOUND);
        }
    }

    @GetMapping("/getCountryByName")
    public ResponseEntity<Country> getCountryByName(@RequestParam String countryname) {

        if (countryname == null) {
            throw new UserNotFoundException(countryname);

        } else {
            Country countryByName = this.service.getCountryByName(countryname);
            return new ResponseEntity<Country>(countryByName, HttpStatus.FOUND);
        }
    }
}
