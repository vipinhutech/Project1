package com.example.project1;

import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Integer> {
	
	public Student findByEmail(String mail);
	public Student findByEmailAndPassword(String email, String password);
}
