package com.glintt.nexllence.countries.serviceImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glintt.nexllence.countries.dto.CountryDTO;
import com.glintt.nexllence.countries.entity.Country;
import com.glintt.nexllence.countries.entity.HistoricOfCountries;
import com.glintt.nexllence.countries.enums.TypeOfAction;
import com.glintt.nexllence.countries.repository.CountryRepository;
import com.glintt.nexllence.countries.repository.HistoricRepository;
import com.glintt.nexllence.countries.service.CountryService;

@Service
public class CountryServiceImpl implements CountryService {

	@Autowired
	CountryRepository countryRepository;

	@Autowired
	HistoricRepository historic;

	/**
	 * Gets all countries.
	 * 
	 * @return List<Country>
	 */
	public List<CountryDTO> getListOfCountries() {

		final List<Country> countries = countryRepository.findAll();

		return copyArrayOfCountryEntities(countries);
	}

	/**
	 * Get Only Active Countries.
	 * 
	 * @return List<Country>
	 */
	public List<CountryDTO> getOnlyActiveCountries() {

		LocalDateTime fromDateAndTime = LocalDateTime.of(LocalDate.now(), LocalTime.now());

		List<Country> countries = countryRepository.getOnlyActiveCountry(fromDateAndTime);

		return copyArrayOfCountryEntities(countries);
	}

	/**
	 * Gets country by codeISON3.
	 * 
	 * @param codeISON3
	 * 
	 * @return CountryDTO
	 */
	public CountryDTO getCountryBycodeISOA2(final String codeISOA2) {

		final Country country = countryRepository.findBycodeISOA2(codeISOA2);

		CountryDTO countryDTO = null;

		if (country != null) {
			countryDTO = new CountryDTO(country.getDescription(), country.getCodeISOA2(), country.getCodeISOA3(),
					country.getCodeISON3(), country.getStartDate(), country.getEndDate());
		}
		return countryDTO;
	}

	/**
	 * Create the received country.
	 * 
	 * @param country
	 * 
	 * @return country
	 */
	public CountryDTO createCountry(final CountryDTO countryDto) {

		Country country = null;

		if (countryRepository.findBycodeISOA2(countryDto.getCodeISOA2()) == null) {
			country = countryRepository.save(copyDataFromCountryDtoToCountry(countryDto));
		}

		if (country != null) {
			HistoricOfCountries historics = new HistoricOfCountries();

			historics.setAction(TypeOfAction.CREATE);
			historics.setCountries(country);

			historic.save(historics);
		}

		return country != null ? countryDto : null;
	}

	/**
	 * Update the received country.
	 * 
	 * @param country
	 * 
	 * @return country
	 */
	public CountryDTO updateCountry(final CountryDTO countryDto) {

		final Country country = countryRepository.save(copyDataFromCountryDTO(countryDto));

		if (country != null) {
			HistoricOfCountries historics = new HistoricOfCountries();

			historics.setAction(TypeOfAction.UPDATE);
			historics.setCountries(country);

			historic.save(historics);
		}

		return country != null ? countryDto : null;
	}

	/**
	 * Copy array Of country entities.
	 * 
	 * @param countries
	 * 
	 * @return List<CountryDTO>
	 */
	public List<CountryDTO> copyArrayOfCountryEntities(final List<Country> countries) {

		final List<CountryDTO> countriesDTO = new ArrayList<CountryDTO>();

		for (Country country : countries) {

			CountryDTO countryDTO = new CountryDTO(country.getDescription(), country.getCodeISOA2(),
					country.getCodeISOA3(), country.getCodeISON3(), country.getStartDate(), country.getEndDate());

			countriesDTO.add(countryDTO);
		}

		return countriesDTO;
	}

	/**
	 * Copy datas from CountryDTO to country
	 * 
	 * @param countryDto
	 * @param country
	 * 
	 * @return Country
	 */
	public Country copyDataFromCountryDTO(final CountryDTO countryDto) {

		final Country country = countryRepository.findBycodeISOA2(countryDto.getCodeISOA2());

		country.setDescription(countryDto.getDescription());
		country.setCodeISOA2(countryDto.getCodeISOA2());
		country.setCodeISOA3(countryDto.getCodeISOA3());
		country.setCodeISON3(countryDto.getCodeISON3());
		country.setStartDate(countryDto.getStartDate());
		country.setEndDate(countryDto.getEndDate());

		return country;
	}

	/**
	 * Copy data from CountryDTO to country
	 * 
	 * @param countryDto
	 * @param country
	 * 
	 * @return Country
	 */
	public Country copyDataFromCountryDtoToCountry(final CountryDTO countryDto) {

		final Country country = new Country();

		country.setDescription(countryDto.getDescription());
		country.setCodeISOA2(countryDto.getCodeISOA2());
		country.setCodeISOA3(countryDto.getCodeISOA3());
		country.setCodeISON3(countryDto.getCodeISON3());
		country.setStartDate(countryDto.getStartDate());
		country.setEndDate(countryDto.getEndDate());

		return country;
	}

}
