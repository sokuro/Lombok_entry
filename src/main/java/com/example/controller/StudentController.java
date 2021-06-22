package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import com.example.request.InQueryRequest;
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
		List<StudentResponse> studentResponseList = new ArrayList<>();
		
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

	@DeleteMapping("delete/{id}")
	public String deleteStudent (@PathVariable long id) {
		return studentService.deleteStudent(id);
	}

	@GetMapping("getByFirstName/{firstName}")
	public List<StudentResponse> getByFirstName (@PathVariable String firstName) {
		List<Student> studentList = studentService.getByFirstName(firstName);

		List<StudentResponse> studentResponseList = new ArrayList<>();

		studentList.forEach(student -> studentResponseList.add(new StudentResponse(student)));

		return studentResponseList;
	}

	@GetMapping("getByFirstNameAndLastName/{firstName}/{lastName}")
	public StudentResponse getByFirstNameAndLastName (@PathVariable String firstName,
													  @PathVariable String lastName) {
		return new StudentResponse(studentService.getByFirstNameAndLastName(firstName, lastName));
	}

	@GetMapping("getByFirstNameOrLastName/{firstName}/{lastName}")
	public List<StudentResponse> getByFirstNameOrLastName (@PathVariable String firstName,
														   @PathVariable String lastName) {
		List<Student> studentList = studentService.getByFirstNameOrLastName(firstName, lastName);

		List<StudentResponse> studentResponseList = new ArrayList<>();

		studentList.forEach(student -> studentResponseList.add(new StudentResponse(student)));

		return studentResponseList;
	}

	@GetMapping("getByFirstNameIn")
	public List<StudentResponse> getByFirstNameIn (
			@RequestBody InQueryRequest inQueryRequest) {
		List<Student> studentList = studentService.getByFirstNameIn(inQueryRequest);

		List<StudentResponse> studentResponseList = new ArrayList<>();

		studentList.forEach(student -> studentResponseList.add(new StudentResponse(student)));

		return studentResponseList;
	}

	@GetMapping("getAllWithPagination")
	public List<StudentResponse> getAllStudentsWithPagination (@RequestParam int pageNo,
															   @RequestParam int pageSize) {

		List<Student> studentList = studentService.
				getAllStudentsWithPagination(pageNo, pageSize);

		List<StudentResponse> studentResponseList = new ArrayList<>();

		studentList.forEach(student -> studentResponseList.add(new StudentResponse(student)));

		return studentResponseList;
	}

}
