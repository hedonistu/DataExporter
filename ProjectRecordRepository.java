package com.springjpa.repo;

import java.util.List;

import com.springjpa.model.ProjectRecord;
import org.springframework.data.repository.CrudRepository;

import com.springjpa.model.ProjectRecord;

public interface ProjectRecordRepository extends CrudRepository<ProjectRecord, Long>{
	List<ProjectRecord> findByLastName(String lastName);
}
