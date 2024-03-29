package com.generate.service;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generate.model.Student;
import com.generate.repository.StudentRepository;
import com.generate.utils.QRCodeGenerator;
import com.google.zxing.WriterException;


@Service
public class StudentServiceImpl implements StudentService{
	
	@Autowired
	private StudentRepository studentRepository;

	@Override
	public Student createStudent(Student student) {
		// check if the student exists with same email and mobile separately
		
		student.setStudentId(UUID.randomUUID().toString());
		return studentRepository.save(student);
	}

	@Override
	public Student getStudent(String studentId) {
		// TODO Auto-generated method stub
		return studentRepository.findById(studentId).orElseThrow(() -> new RuntimeException("student not found"));
	}

	@Override
	public List<Student> getStudents() throws WriterException, IOException {
		// TODO Auto-generated method stub
		List<Student> students = studentRepository.findAll();
		
		for(var student: students) {
			QRCodeGenerator.generate(student);
		}
		
		return students;
	}
	
	
	
}
