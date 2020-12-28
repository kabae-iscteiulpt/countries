package com.glintt.nexllence.countries.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "country")
public class Country extends BaseCountryEntity {

	private static final long serialVersionUID = 3751640986762005135L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "description", nullable = false)
	private String description;

	@Column(name = "codeISOA2", nullable = false)
	private String codeISOA2;

	@Column(name = "codeISOA3", nullable = false)
	private String codeISOA3;

	@Column(name = "codeISON3", nullable = false)
	private Integer codeISON3;

	@Column(name = "start_Date", nullable = false)
	private LocalDateTime start_Date;

	@Column(name = "end_Date", nullable = true)
	private LocalDateTime end_Date;

	/**
	 * Gets the id.
	 * 
	 * @return id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 * 
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
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
	 * Gets the start_Date.
	 * 
	 * @return start_Date
	 */
	public LocalDateTime getStartDate() {
		return start_Date;
	}

	/**
	 * Sets the start_Date.
	 * 
	 * @param start_Date
	 */
	public void setStartDate(LocalDateTime startDate) {
		this.start_Date = startDate;
	}

	/**
	 * Gets the end_Date.
	 * 
	 * @return end_Date
	 */
	public LocalDateTime getEndDate() {
		return end_Date;
	}

	/**
	 * Sets the end_Date.
	 * 
	 * @param end_Date
	 */
	public void setEndDate(LocalDateTime endDate) {
		this.end_Date = endDate;
	}

}
