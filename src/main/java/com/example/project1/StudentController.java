package com.example.project1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("student")
@CrossOrigin
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	@Autowired
	private MailService mailService;
	
	private Student st;
	
	@PostMapping
	public ResponseEntity<Student> save(@Valid @RequestBody Student student) throws Exception
	{
		return ResponseEntity.ok(studentService.save(student));
	}
	
	@GetMapping
	public Iterable<Student> load()
	{
		return studentService.load();
	}
	
	@DeleteMapping("/{id}")
	public String delete(@PathVariable Integer id)
	{
		studentService.delete(id);
		return id+" deleted";
	}
	
	@GetMapping("verification/email/{email}/activationCode/{activationCode}")	
	public String emailVerification(@PathVariable String email, @PathVariable String activationCode) throws Exception
	{
		return studentService.emailVerification(email, activationCode);
	}
	
	@PostMapping("update")
	public Student update(@RequestBody Student student)
	{
		return studentService.update(student);
	}
	
	@PostMapping("login")
	public ResponseEntity<String> login(@Valid @RequestBody LoginDTO loginDTO) throws Exception
	{	
		return ResponseEntity.ok(studentService.login(loginDTO));
	}
	
	@PostMapping("change-password")
	public ResponseEntity<String> changPassword(@Valid @RequestBody ChangePasswordDTO changePasswordDTO)
	{
		return ResponseEntity.ok(studentService.changePassword(changePasswordDTO));
	}
	
	@PostMapping("logout")
	public ResponseEntity<String> logOut(@Valid @RequestBody LoginDTO dto)
	{
		return ResponseEntity.ok(studentService.logOUt(dto));
	}
	
}
