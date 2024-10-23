package com.junit.mockito.repository;

import com.junit.mockito.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepo  extends JpaRepository<Country, Integer> {

}
