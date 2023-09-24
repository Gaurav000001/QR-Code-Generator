package com.generate.service;

import java.io.IOException;
import java.util.List;

import com.generate.model.Student;
import com.google.zxing.WriterException;

public interface StudentService {
	
	public Student createStudent(Student student);
	public Student getStudent(String studentId);
	public List<Student> getStudents() throws WriterException, IOException;
}
