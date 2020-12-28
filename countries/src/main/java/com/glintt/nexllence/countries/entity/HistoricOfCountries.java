package com.glintt.nexllence.countries.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.glintt.nexllence.countries.enums.TypeOfAction;

@Entity
@Table(name = "Historic")
public class HistoricOfCountries extends BaseCountryEntity {

	private static final long serialVersionUID = 2158726889126290265L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@ManyToOne(targetEntity = Country.class, fetch = FetchType.LAZY, optional = false)
	private Country country;

	@Column(name = "type_action")
	private TypeOfAction type_action;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TypeOfAction getAction() {
		return type_action;
	}

	public void setAction(TypeOfAction type_action) {
		this.type_action = type_action;
	}

	public Country getCountries() {
		return country;
	}

	public void setCountries(Country country) {
		this.country = country;
	}

}
