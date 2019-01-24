package com.springjpa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "archive")
public class ProjectRecord implements Serializable {

	private static final long serialVersionUID = -3009157732242241606L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "eventdate")
	private String eventdate;

	@Column(name = "feeder1")
	private String feeder1;

	@Column(name = "feeder2")
	private String feeder2;

	@Column(name = "temp")
	private String temperature;

	@Column(name = "prediction")
	private Integer prediction;

	protected ProjectRecord() {
	}

	public ProjectRecord(String eventdate, String feeder1,String feeder2,String temperature,Integer prediction) {
		this.eventdate = eventdate;
		this.feeder1 = feeder1;
		this.feeder2 = feeder2;
		this.temperature = temperature;
		this.prediction = prediction;
	}

	@Override
	public String toString() {
		return String.format("Record[eventdate='%s', feeder1='%s', feeder2='%s', temperature='%s',prediction=%d]", eventdate,feeder1, feeder2, temperature, prediction);
	}
}
