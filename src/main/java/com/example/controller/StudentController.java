package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import com.example.request.UpdateStudentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.entity.Student;
import com.example.request.CreateStudentRequest;
import com.example.response.StudentResponse;
import com.example.service.StudentService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/student/")
public class StudentController {

	@Autowired
	StudentService studentService;
	
	@GetMapping("getAll")
	public List<StudentResponse> getAllStudents () {
		List<Student> studentList = studentService.getAllStudents();
		List<StudentResponse> studentResponseList = new ArrayList<StudentResponse>();
		
		studentList.stream().forEach(student -> {
			studentResponseList.add(new StudentResponse(student));
		});
		
		return studentResponseList;
	}
	
	@PostMapping("create")
	public StudentResponse createStudent (@Valid @RequestBody CreateStudentRequest createStudentRequest) {
		Student student = studentService.createStudent(createStudentRequest);
		
		return new StudentResponse(student);
	}

	@PutMapping("update")
	public StudentResponse updateStudent (@Valid @RequestBody UpdateStudentRequest updateStudentRequest) {
		Student student = studentService.updateStudent(updateStudentRequest);

		return new StudentResponse(student);
	}

	@DeleteMapping("delete")
	public String deleteStudent (@RequestParam long id) {
		return studentService.deleteStudent(id);
	}
}
