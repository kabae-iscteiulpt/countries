package com.glintt.nexllence.countries;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import com.glintt.nexllence.countries.dto.CountryDTO;
import com.glintt.nexllence.countries.entity.Country;
import com.glintt.nexllence.countries.repository.CountryRepository;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(Lifecycle.PER_CLASS)
public class CountriesApplicationTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private CountryRepository repository;

	private Country country1 = new Country();
	private Country country2 = new Country();
	private LocalDateTime dateTime = LocalDateTime.of(2020, 11, 25, 19, 06);
	private LocalDateTime dateTimef = LocalDateTime.of(2020, 11, 25, 19, 06);

	@BeforeAll
	public void setUp() {

		country1.setCodeISOA2("CV");
		country1.setCodeISOA3("CPV");
		country1.setCodeISON3(132);
		country1.setDescription("Cabo Verde");
		country1.setStartDate(dateTime);

		repository.save(country1);

		country2.setCodeISOA2("AO");
		country2.setCodeISOA3("AGO");
		country2.setCodeISON3(024);
		country2.setDescription("Angola");
		country2.setStartDate(dateTime);
		country2.setEndDate(dateTimef);

		repository.save(country2);

	}

	@Test
	public void addNewCountry() throws Exception {

		CountryDTO countryDTO = new CountryDTO();

		countryDTO.setCodeISOA2("PT");
		countryDTO.setCodeISOA3("PRT");
		countryDTO.setCodeISON3(620);
		countryDTO.setDescription("Portugal");
		countryDTO.setStartDate(dateTime);
		countryDTO.setEndDate(dateTime);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<CountryDTO> requestEntity = new HttpEntity<>(countryDTO, headers);

		ResponseEntity<CountryDTO> response = restTemplate.postForEntity("/countries", requestEntity, CountryDTO.class);

		assertNotNull(response);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		compareCountryFields(countryDTO, response.getBody());
	}

	@Test
	public void updateCountry() throws Exception {

		CountryDTO countryDTO = new CountryDTO();

		countryDTO.setCodeISOA2("CV");
		countryDTO.setCodeISOA3("CPV");
		countryDTO.setCodeISON3(132);
		countryDTO.setDescription("Cabo Verde");
		countryDTO.setStartDate(dateTime);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<CountryDTO> requestEntity = new HttpEntity<>(countryDTO, headers);

		ResponseEntity<CountryDTO> response = restTemplate.exchange("/countries", HttpMethod.PUT, requestEntity,
				CountryDTO.class);
		assertNotNull(response);
		assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
		compareCountryFields(countryDTO, response.getBody());
	}

	@Test
	public void getListOfCountries() throws Exception {

		ResponseEntity<List<CountryDTO>> response = restTemplate.exchange("/countries", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<CountryDTO>>() {
				});

		CountryDTO countryDto1 = new CountryDTO();
		countryDto1.setCodeISOA2(country1.getCodeISOA2());
		countryDto1.setCodeISOA3(country1.getCodeISOA3());
		countryDto1.setCodeISON3(country1.getCodeISON3());
		countryDto1.setDescription(country1.getDescription());
		countryDto1.setStartDate(country1.getStartDate());
		countryDto1.setStartDate(country1.getStartDate());

		CountryDTO countryDto2 = new CountryDTO();
		countryDto2.setCodeISOA2(country2.getCodeISOA2());
		countryDto2.setCodeISOA3(country2.getCodeISOA3());
		countryDto2.setCodeISON3(country2.getCodeISON3());
		countryDto2.setDescription(country2.getDescription());
		countryDto2.setStartDate(country2.getStartDate());
		countryDto2.setEndDate(country2.getEndDate());

		List<CountryDTO> countryDtoList = new ArrayList<CountryDTO>();
		countryDtoList.add(countryDto1);
		countryDtoList.add(countryDto2);

		assertNotNull(response);
		assertEquals(HttpStatus.OK, response.getStatusCode());

		for (CountryDTO countryExpected : countryDtoList) {
			CountryDTO countryActual = response.getBody().stream()
					.filter(c -> countryExpected.getCodeISOA2().equals(c.getCodeISOA2())).findAny().orElse(null);
			assertNotNull(countryActual);
			compareCountryFields(countryExpected, countryActual);
		}
	}

	@Test
	public void getCountryByISOA2() throws Exception {

		CountryDTO countryDTO = new CountryDTO();

		countryDTO.setCodeISOA2("CV");
		countryDTO.setCodeISOA3("CPV");
		countryDTO.setCodeISON3(132);
		countryDTO.setDescription("Cabo Verde");
		countryDTO.setStartDate(dateTime);

		ResponseEntity<CountryDTO> response = restTemplate.exchange("/countries/{isoA2}", HttpMethod.GET, null,
				CountryDTO.class, "CV");

		assertNotNull(response);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		compareCountryFields(countryDTO, response.getBody());
	}

	@Test
	public void getOnlyActiveCountries() throws Exception {

		CountryDTO countryDto1 = new CountryDTO();
		countryDto1.setCodeISOA2(country1.getCodeISOA2());
		countryDto1.setCodeISOA3(country1.getCodeISOA3());
		countryDto1.setCodeISON3(country1.getCodeISON3());
		countryDto1.setDescription(country1.getDescription());
		countryDto1.setStartDate(country1.getStartDate());
		countryDto1.setStartDate(country1.getStartDate());

		List<CountryDTO> countryDtoList = new ArrayList<CountryDTO>();
		countryDtoList.add(countryDto1);

		ResponseEntity<List<CountryDTO>> response = restTemplate.exchange("/countries?onlyactive=true", HttpMethod.GET,
				null, new ParameterizedTypeReference<List<CountryDTO>>() {
				});

		assertNotNull(response);
		assertEquals(HttpStatus.OK, response.getStatusCode());

		for (CountryDTO countryExpected : countryDtoList) {
			CountryDTO countryActual = response.getBody().stream()
					.filter(c -> countryExpected.getCodeISOA2().equals(c.getCodeISOA2())).findAny().orElse(null);
			assertNotNull(countryActual);
			compareCountryFields(countryExpected, countryActual);
		}
	}

	private void compareCountryFields(CountryDTO countryExpected, CountryDTO countryActual) {

		assertEquals(countryExpected.getDescription(), countryActual.getDescription());
		assertEquals(countryExpected.getCodeISOA2(), countryActual.getCodeISOA2());
		assertEquals(countryExpected.getCodeISOA3(), countryActual.getCodeISOA3());
		assertEquals(countryExpected.getCodeISON3(), countryActual.getCodeISON3());
		assertEquals(countryExpected.getEndDate(), countryActual.getEndDate());
		assertEquals(countryExpected.getStartDate(), countryActual.getStartDate());
	}

}
