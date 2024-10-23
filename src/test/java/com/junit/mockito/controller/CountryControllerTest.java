package com.junit.mockito.controller;

import com.junit.mockito.entity.Country;
import com.junit.mockito.service.CountryServiceI;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = CountryControllerTest.class)
class CountryControllerTest {

    @Mock
    private CountryServiceI countryServiceI;

    @InjectMocks
    private CountryController countryController;

    @Test
    void addCountryTest() {

        Country country = new Country(101, "India", "Mumbai");

        when(countryServiceI.addCountry(country)).thenReturn(country);

        ResponseEntity<Country> country1 = countryController.addCountry(country);

        String countryName = country1.getBody().getCountryName();
        HttpStatusCode statusCode = country1.getStatusCode();
        System.out.println(statusCode);

        assertNotNull(statusCode);
        assertEquals("India", countryName);

    }

    @Test
    void getAllCountriesTest() {

        List<Country> countries = new ArrayList<>();
        countries.add(new Country(101, "India", "Mumbai"));
        countries.add(new Country(102, "Dubai", "Nashik"));

        when(countryServiceI.getAllCountries()).thenReturn(countries);

        ResponseEntity<List<Country>> allCountries = countryController.getAllCountries();

        assertEquals(2, allCountries.getBody().size());
        assertEquals("India", allCountries.getBody().get(0).getCountryName());
        assertNotNull(allCountries.getStatusCode());

    }

    @Test
    void getCountryByIdTest() {

        Country country = new Country(101, "India", "Mumbai");

        when(countryServiceI.getCountryById(101)).thenReturn(country);

        ResponseEntity<Country> countryById = countryController.getCountryById(101);

        Integer id = countryById.getBody().getId();

        assertEquals("India", countryById.getBody().getCountryName());
        assertNotNull(countryById.getStatusCode());
    }

    @Test
    void getCountryByNameTest() {

        Country country = new Country(101, "India", "Mumbai");

        when(countryServiceI.getCountryByName("India")).thenReturn(country);

        ResponseEntity<Country> countryByName = countryController.getCountryByName("India");


        assertEquals(101, countryByName.getBody().getId());
        assertEquals("India", countryByName.getBody().getCountryName());
        assertEquals("Mumbai", countryByName.getBody().getStateName());
        assertNotNull(countryByName.getStatusCode());

    }
}