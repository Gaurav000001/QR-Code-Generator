package com.generate.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generate.model.Student;
import com.generate.service.StudentService;
import com.generate.utils.QRCodeGenerator;
import com.google.zxing.WriterException;

@RestController
@RequestMapping("/api/students/")
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@PostMapping
	public ResponseEntity<Student> getStudent(@RequestBody Student student){
		return ResponseEntity.ok(studentService.createStudent(student));
	}
	
	@GetMapping("/{studentId}/")
	public ResponseEntity<Student> getStudent(@PathVariable String studentId){
		return ResponseEntity.ok(studentService.getStudent(studentId));
	}
	
	@GetMapping
	public ResponseEntity<List<Student>> getStudents() throws WriterException, IOException{
		return ResponseEntity.ok(studentService.getStudents());
	}
	
	@GetMapping("/generate/{studentId}/")
	public ResponseEntity<byte[]> getStudentQRCode(@PathVariable String studentId) throws WriterException, IOException{
		
		Student student = studentService.getStudent(studentId);
		
		// Generate the QR code image for the student
        byte[] qrCodeImage = QRCodeGenerator.generate(student);

        // Set response headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        headers.setContentLength(qrCodeImage.length);

        // Return the image as a ResponseEntity
        return ResponseEntity.ok().headers(headers).body(qrCodeImage);
	}
}
