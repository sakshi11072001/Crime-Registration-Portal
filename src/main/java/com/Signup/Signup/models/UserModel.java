package com.Signup.Signup.models;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users_table")
public class UserModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	private String login;
	String email;
	String password;
	//String role;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
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
	
	
	
	@Override
	public int hashCode() {
		return Objects.hash(email, id, login, password);
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserModel other = (UserModel) obj;
		return Objects.equals(email, other.email) && Objects.equals(id, other.id) && Objects.equals(login, other.login)
				&& Objects.equals(password, other.password);
	}
	@Override
	public String toString() {
		return "UserModel [id=" + id + ", login=" + login + ", email=" + email + "]";
	}
	
	
}

/*CREATE TABLE users_table (
    id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255),
    login VARCHAR(255),
    password VARCHAR(255)
);
*/
