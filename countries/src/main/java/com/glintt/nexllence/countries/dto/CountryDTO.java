package com.glintt.nexllence.countries.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.glintt.nexllence.countries.common.CountryValidationErrorConstants;

public class CountryDTO {

	@NotNull(message = CountryValidationErrorConstants.MISSING_DESCRIPTION)
	private String description;

	@NotNull(message = CountryValidationErrorConstants.MISSING_CODEISOA2)
	private String codeISOA2;

	@NotNull(message = CountryValidationErrorConstants.MISSING_CODEISOA3)
	private String codeISOA3;

	@NotNull(message = CountryValidationErrorConstants.MISSING_CODEISON3)
	private Integer codeISON3;

	@NotNull(message = CountryValidationErrorConstants.MISSING_STARTDATE)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	private LocalDateTime startDate;

	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	private LocalDateTime endDate;

	public CountryDTO(String description, String codeISOA2, String codeISOA3, Integer codeISON3,
			LocalDateTime startDate, LocalDateTime endDate) {
		this.description = description;
		this.codeISOA2 = codeISOA2;
		this.codeISOA3 = codeISOA3;
		this.codeISON3 = codeISON3;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public CountryDTO() {

	}

	/**
	 * Gets the description.
	 * 
	 * @return description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 * 
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the codeISOA2.
	 * 
	 * @return codeISOA2
	 */
	public String getCodeISOA2() {
		return codeISOA2;
	}

	/**
	 * Sets the codeISOA2
	 * 
	 * @param codeISOA2
	 */
	public void setCodeISOA2(String codeISOA2) {
		this.codeISOA2 = codeISOA2;
	}

	/**
	 * Gets the codeISOA3.
	 * 
	 * @return codeISOA3
	 */
	public String getCodeISOA3() {
		return codeISOA3;
	}

	/**
	 * Sets the codeISOA3.
	 * 
	 * @param codeISOA3
	 */
	public void setCodeISOA3(String codeISOA3) {
		this.codeISOA3 = codeISOA3;
	}

	/**
	 * Gets the codeISON3.
	 * 
	 * @return codeISON3
	 */
	public Integer getCodeISON3() {
		return codeISON3;
	}

	/**
	 * Sets the codeISON3.
	 * 
	 * @param codeISON3
	 */
	public void setCodeISON3(Integer codeISON3) {
		this.codeISON3 = codeISON3;
	}

	/**
	 * Gets the startDate.
	 * 
	 * @return startDate
	 */
	public LocalDateTime getStartDate() {
		return startDate;
	}

	/**
	 * Sets the startDate.
	 * 
	 * @param startDate
	 */
	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	/**
	 * Gets the endDate.
	 * 
	 * @return endDate
	 */
	public LocalDateTime getEndDate() {
		return endDate;
	}

	/**
	 * Sets the endDate.
	 * 
	 * @param endDate
	 */
	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

}
