package com.junit.mockito.service.impl;

import com.junit.mockito.entity.Country;
import com.junit.mockito.repository.CountryRepo;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = CountryServiceImplTest.class)
class CountryServiceImplTest {

    @Mock
    private CountryRepo countryRepo;

    @InjectMocks
    private CountryServiceImpl countryService;

    @Test
     void getAllCountriesTest() {

        List<Country> countries = new ArrayList<>();
        countries.add(new Country(101, "India", "Mumbai"));
        countries.add(new Country(102, "China", "Tokyo"));

        when(countryRepo.findAll()).thenReturn(countries);

        List<Country> allCountries = countryService.getAllCountries();
        assertEquals(2, allCountries.size());
        assertEquals(101, allCountries.get(0).getId());
        assertEquals("India", allCountries.get(0).getCountryName());
        assertEquals("Mumbai", allCountries.get(0).getStateName());
    }

    @Test
    void getCountryByIdTest() {

        Country country = new Country(101, "India", "Mumbai");
        int id=101;
        when(countryRepo.findById(id)).thenReturn(Optional.of(country));

        Country countryById = countryService.getCountryById(id);
        assertEquals("India",countryById.getCountryName());
    }

    @Test
    void getCountryByNameTest() {

        List<Country> countries = new ArrayList<>();
        countries.add(new Country(101, "India", "Mumbai"));
        countries.add(new Country(102, "China", "Tokyo"));

        when(countryRepo.findAll()).thenReturn(countries);

        Country country = countryService.getCountryByName("India");

        assertEquals(101,country.getId());


    }

    @Test
    void addCountryTest() {

        Country country = new Country(101, "India", "Mumbai");

        when(countryRepo.save(country)).thenReturn(country);

        Country country1 = countryService.addCountry(country);

        assertNotNull(country1);
    }

    @Test
    void updateCountryTest() {

        Country country = new Country(101, "India", "Mumbai");

        when(countryRepo.save(country)).thenReturn(country);

        Country country1 = countryService.updateCountry(country,101);

        assertNotNull(country1);
    }

    @Test
    void updateCountryNewTest(){

        Country country = new Country(101, "India", "Mumbai");

        when(countryRepo.findById(101)).thenReturn(Optional.of(country));

        when(countryRepo.save(country)).thenReturn(country);

        Country country1 = countryService.updateCountryNew(country, 101);
        assertNotNull(country1);

    }

    @Test
    void deleteCountryTest() {

        Country country = new Country(101, "India", "Mumbai");

        countryService.deleteCountry(country);

        verify(countryRepo,times(1)).delete(country);
    }
}