package com.glintt.nexllence.countries.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.glintt.nexllence.countries.entity.Country;

public interface CountryRepository extends JpaRepository<Country, Long> {

	@Query(value = "select * from country c where (:currentDate between c.start_date and  c.end_date)"
			+ " OR (:currentDate >= c.start_date and c.end_date is null);", nativeQuery = true)
	List<Country> getOnlyActiveCountry(@Param("currentDate") LocalDateTime currentDate);

	@Query(value = "select * from country c where c.codeISOA2= :codeISOA2", nativeQuery = true)
	Country findBycodeISOA2(@Param("codeISOA2") String codeISOA2);

}
