package com.example.service;

import java.util.List;

import com.example.entity.Address;
import com.example.repository.AddressRepository;
import com.example.request.InQueryRequest;
import com.example.request.UpdateStudentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.entity.Student;
import com.example.repository.StudentRepository;
import com.example.request.CreateStudentRequest;

@Service
public class StudentService {

	@Autowired
	StudentRepository studentRepository;

	@Autowired
	AddressRepository addressRepository;
	
	public List<Student> getAllStudents () {
		return studentRepository.findAll();
	}
	
	public Student createStudent (CreateStudentRequest createStudentRequest) {
		Student student = new Student(createStudentRequest);

		Address address = new Address();
		address.setStreet(createStudentRequest.getStreet());
		address.setCity(createStudentRequest.getCity());

		address = addressRepository.save(address);

		student.setAddress(address);
		student = studentRepository.save(student);
		return student;
	}

	public Student updateStudent(UpdateStudentRequest updateStudentRequest) {
		Student student = studentRepository.findById(updateStudentRequest.getId()).get();

		if (updateStudentRequest.getFirstName() != null &&
				!updateStudentRequest.getFirstName().isEmpty()) {
			student.setFirstName(updateStudentRequest.getFirstName());
		}

		student = studentRepository.save(student);
		return student;
	}

	public String deleteStudent(long id) {
		studentRepository.deleteById(id);
		return "Student has been deleted successfully";
	}

	public List<Student> getByFirstName(String firstName) {
		return studentRepository.findByFirstName(firstName);
	}

	public Student getByFirstNameAndLastName(String firstName, String lastName) {
		return studentRepository.findByLastNameAndFirstName(lastName, firstName);
	}

	public List<Student> getByFirstNameOrLastName(String firstName, String lastName) {
		return studentRepository.findByFirstNameOrLastName(firstName, lastName);
	}

	public List<Student> getByFirstNameIn (InQueryRequest inQueryRequest) {
		return studentRepository.findByFirstNameIn(inQueryRequest.getFirstNames());
	}

	public List<Student> getAllStudentsWithPagination (int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);

		return studentRepository.findAll(pageable).getContent();
	}

	public List<Student> getAllStudentsWithSorting() {
		Sort sort = Sort.by(Sort.Direction.ASC, "firstName", "lastName", "email");

		return studentRepository.findAll(sort);
	}

	public List<Student> like(String firstName) {
		return studentRepository.findByFirstNameContains(firstName);
	}

	public List<Student> startsWith(String firstName) {
		return studentRepository.findByFirstNameStartsWith(firstName);
	}

	public Student getByFirstNameAndLastNameByJPQL(String firstName, String lastName) {
		return studentRepository.getByLastNameAndFirstNameByJPQL(lastName, firstName);
	}

	public Integer updateStudentWithJpql(Long id, String firstName) {
		return studentRepository.updateFirstName(id, firstName);
	}

	public Integer deleteStudent (String firstName) {
		return studentRepository.deleteByFirstName(firstName);
	}

	public List<Student> getByCity(String city) {
		return studentRepository.getByAddressCity(city);
	}
}
