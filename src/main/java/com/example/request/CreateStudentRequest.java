package com.example.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class CreateStudentRequest {
	
	@JsonProperty("first_name")
	@NotBlank(message = "First Name is required")
	private String firstName;

	@JsonProperty("last_name")
	private String lastName;

	private String email;

}
