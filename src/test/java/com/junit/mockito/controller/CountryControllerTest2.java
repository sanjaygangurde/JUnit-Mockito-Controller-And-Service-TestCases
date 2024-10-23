package com.junit.mockito.controller;

import com.junit.mockito.entity.Country;
import com.junit.mockito.service.CountryServiceI;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = CountryControllerTest2.class)
@ComponentScan(basePackages = "com.junit.mockito.controller")
@AutoConfigureMockMvc
@ContextConfiguration
class CountryControllerTest2 {

    @Mock
    private CountryServiceI serviceI;

    @InjectMocks
    private CountryController controller;

    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    public void setUp() {

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

    }


    @Test
    void addCountryTest() throws Exception {

        Country country = new Country(101, "India", "Mumbai");

        when(serviceI.addCountry(country)).thenReturn(country);

        ObjectMapper mapper = new ObjectMapper();

        String value = mapper.writeValueAsString(country);

        mockMvc.perform(post("/saveUser").content(value).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()).andDo(print());
    }

    @Test
    void getAllCountriesTest() throws Exception {

        List<Country> countries = new ArrayList<>();
        countries.add(new Country(101, "India", "Mumbai"));
        countries.add(new Country(102, "Dubai", "Nashik"));

        when(serviceI.getAllCountries()).thenReturn(countries);

        mockMvc.perform(get("/getAllCountries")).andExpect(status().isFound());
    }

    @Test
    void getCountryByIdTest() throws Exception {

        Country country = new Country(101, "India", "Mumbai");

        when(serviceI.getCountryById(101)).thenReturn(country);

        mockMvc.perform(get("/getCountryById/{id}", 101))
                .andExpect(status().isFound())
                .andExpect(MockMvcResultMatchers.jsonPath(".id").value(101))
                .andExpect(MockMvcResultMatchers.jsonPath(".countryName").value("India"))
                .andExpect(MockMvcResultMatchers.jsonPath(".stateName").value("Mumbai"))
                .andDo(print());

    }

    @Test
    void getCountryByNameTest() throws Exception {

        Country country = new Country(101, "India", "Mumbai");

        String countryname="India";

        when(serviceI.getCountryByName(countryname)).thenReturn(country);

        ResultActions resultActions = mockMvc.perform(get("/getCountryByName").param("countryname", "India"))
                .andExpect(status().isFound());

        int status = resultActions.andReturn().getResponse().getStatus();
        System.out.println(status);
        assertNotNull(resultActions);

//                 .andExpect(MockMvcResultMatchers.jsonPath(".id").value(101))
//                 .andExpect(MockMvcResultMatchers.jsonPath(".countryName").value("India"))
//                 .andExpect(MockMvcResultMatchers.jsonPath(".stateName").value("Mumbai"))
//                 .andDo(print());
    }
}