package com.generate.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generate.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, String>{
	
	public Optional<Student> findByEmail(String email);
	public Optional<Student> findByMobile(String mobile);
}
