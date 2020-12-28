package com.glintt.nexllence.countries.service;

import java.util.List;

import com.glintt.nexllence.countries.dto.CountryDTO;

public interface CountryService {

	/**
	 * Gets all countries.
	 * 
	 * @return List<Country>
	 */
	List<CountryDTO> getListOfCountries();

	/**
	 * Get Only Active Countries.
	 * 
	 * @return List<Country>
	 */
	List<CountryDTO> getOnlyActiveCountries();

	/**
	 * Gets country by codeISON3.
	 * 
	 * @param codeISON3
	 * 
	 * @return CountryDTO
	 */
	CountryDTO getCountryBycodeISOA2(final String codeISOA2);

	/**
	 * Create the received country.
	 * 
	 * @param country
	 * 
	 * @return country
	 */
	CountryDTO createCountry(final CountryDTO countryDto);

	/**
	 * Update the received country.
	 * 
	 * @param country
	 * 
	 * @return country
	 */
	CountryDTO updateCountry(final CountryDTO countryDto);
}