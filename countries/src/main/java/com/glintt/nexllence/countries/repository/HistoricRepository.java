package com.glintt.nexllence.countries.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.glintt.nexllence.countries.entity.HistoricOfCountries;

public interface HistoricRepository extends JpaRepository<HistoricOfCountries, Long> {

}
