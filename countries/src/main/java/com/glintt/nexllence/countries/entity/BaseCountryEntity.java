 package com.glintt.nexllence.countries.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@MappedSuperclass
public class BaseCountryEntity implements Serializable {

	private static final long serialVersionUID = 3334984184107658172L;

	@CreationTimestamp
	private LocalDateTime created_On;

	@UpdateTimestamp
	private LocalDateTime updated_On;

	/**
	 * Gets the date created_On.
	 * 
	 * @return created_On
	 */
	public LocalDateTime getCreatedOn() {
		return created_On;
	}

	/**
	 * Sets the date created_On.
	 * 
	 * @param created_On
	 */
	public void setCreatedOn(LocalDateTime createdOn) {
		this.created_On = createdOn;
	}

	/**
	 * Gets the date updated_On.
	 * 
	 * @return updated_On.
	 */
	public LocalDateTime getUpdatedOn() {
		return updated_On;
	}

	/**
	 * Sets the date updated_On.
	 * 
	 * @param updated_On
	 */
	public void setUpdatedOn(LocalDateTime updatedOn) {
		this.updated_On = updatedOn;
	}

}
