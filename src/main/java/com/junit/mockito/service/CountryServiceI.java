package com.junit.mockito.service;

import com.junit.mockito.entity.Country;

import java.util.List;

public interface CountryServiceI {

    //public List<Country> getAllCountries(Integer pageNumber, Integer pageSize);

    public List<Country> getAllCountries();

    public Country getCountryById(Integer id);

    public Country getCountryByName(String countryname);


    public Country addCountry(Country country);


    public Country updateCountry(Country country, Integer id);


    public void deleteCountry(Country country) ;
}
