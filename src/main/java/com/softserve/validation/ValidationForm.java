package com.softserve.validation;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;

@FieldMatch.List({@FieldMatch(first = "password", second = "matchingPassword", message = "The password fields must match")
})
public class ValidationForm {

	@ValidEmail
	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String email;

	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String password;
	
	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String matchingPassword;

	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String firstName;

	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String lastName;

	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String age;

	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String phone;

	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private Date regDate;

	public ValidationForm() {

	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMatchingPassword() { return matchingPassword; }

	public void setMatchingPassword(String matchingPassword) {
		this.matchingPassword = matchingPassword;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getRegDate() { return regDate; }

	public void setRegDate(Date regDate) { this.regDate = regDate; }

}
