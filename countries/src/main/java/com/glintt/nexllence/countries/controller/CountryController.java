package com.glintt.nexllence.countries.controller;

import java.sql.SQLException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.glintt.nexllence.countries.dto.CountryDTO;
import com.glintt.nexllence.countries.service.CountryService;

@RestController
@RequestMapping("/countries")
public class CountryController {

	@Autowired
	CountryService countryService;

	@GetMapping
	public ResponseEntity<?> getListOfCountries(
			@RequestParam(value = "onlyactive", required = false) final boolean onlyactive) {

		List<CountryDTO> countries;

		if (onlyactive == true) {
			countries = countryService.getOnlyActiveCountries();
		} else {
			countries = countryService.getListOfCountries();
		}
		return new ResponseEntity<>(countries, HttpStatus.OK);
	}

	@GetMapping("{codeISOA2}")
	public ResponseEntity<?> getCountryById(@PathVariable(value = "codeISOA2") final String codeISOA2) {

		final CountryDTO countryDto = countryService.getCountryBycodeISOA2(codeISOA2);

		if (countryDto == null) {
			final String message = "The country of codeISOA2: " + codeISOA2 + " do not exist!";
			return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(countryDto, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> createCountry(@RequestBody @Valid final CountryDTO country) {

		final CountryDTO country_ = countryService.createCountry(country);
		if (country_ == null) {
			return new ResponseEntity<>(country_, HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(country_, HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<?> updateCountry(@RequestBody @Valid final CountryDTO countryDto) {

		final CountryDTO country_ = countryService.updateCountry(countryDto);
		if (country_ == null) {
			return new ResponseEntity<>(country_, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(country_, HttpStatus.ACCEPTED);
	}

	@ExceptionHandler({ DataAccessException.class, SQLException.class, Exception.class })
	public ResponseEntity<?> handleExceptionDataAccessAndSqlException() {

		final String bodyOfResponse = "Internal server error.";

		return new ResponseEntity<>(bodyOfResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler({ MethodArgumentNotValidException.class })
	public ResponseEntity<?> handleExceptionMethodArgument(MethodArgumentNotValidException ex) {

		// String bodyOfResponse = CountryValidationErrorConstants.MISSING_PARAMETER;
		List<FieldError> errors = ex.getBindingResult().getFieldErrors();

		return new ResponseEntity<>(errors.get(0).getDefaultMessage(), HttpStatus.BAD_REQUEST);
	}

}
