package com.example.project1;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

	@Autowired
	private StudentRepository studentRepository;
	
	private Student st;
	
	@Autowired
	private MailService mailService;
	
	
	public Student save(Student student) throws Exception
	{
		studentRepository.save(student);
		
		String activationCode = mailService.sendMailToVerifyEmail(student.getEmail());
		student.setActivationCode(activationCode);
		studentRepository.save(student);		
		return student;
	}
	
	
	public Iterable<Student> load()
	{
		return studentRepository.findAll();
	}
	
	
	public String delete(Integer id)
	{
		System.out.println("Here id is : " + id);
		studentRepository.deleteById(id);
		return id+" deleted";
	}
	
	
	public String emailVerification(String email, String activationCode) throws Exception
	{
		String message = "Not Varified";
		Student student = studentRepository.findByEmail(email);
		
		if(activationCode.equals(student.getActivationCode()))
		{
			message = "Your email account verified successfully";
			student.setStatus(1);
			student.setCount(0);
			update(student);
		}
		return message;
	}
	
	
	public Student update(Student student)
	{
		return studentRepository.save(student);
	}


	public Student findByEmail(String email) {
		Student student = studentRepository.findByEmail(email);
		if(student == null) throw new NoSuchElementException();
		return student;
	}
	
	public String login(LoginDTO loginDTO) throws Exception
	{
		Student student = studentRepository.findByEmailAndPassword(loginDTO.getEmail(), loginDTO.getPassword());
		st = findByEmail(loginDTO.getEmail());
		if(st.getLoginStatus() == 1)
			return "User is logged in already";
		if( student != null)
		{
			if(student.getStatus() == 0)
			{
				loginDTO.setStatus("Email is not Activated. Pls Activate your email account");
			}
			else
			{
				st.setCount(0);
				st.setLoginStatus(1);
				loginDTO.setStatus("Login is Success");
			}
		}
		else
		{
			st.setCount(st.getCount()+1);
			if(st.getCount() >= 5 && st.getStatus() == 1)
			{
				st.setStatus(0);
				st.setLoginStatus(0);
				//st.setCount(0);
				loginDTO.setStatus("Your ID has been de-activated, Please activate agian by using link sent to your mail");
				String code = mailService.sendMailToVerifyEmail(loginDTO.getEmail());
				st.setActivationCode(code);
			}
			else if(st.getStatus() == 0)
				loginDTO.setStatus("Email is not Activated. Pls Activate your email account");
			
			else
				loginDTO.setStatus("Login is Failed. Pls try agian & remains "+(5-st.getCount())+" attempts");
			
		}
		update(st);		
		return loginDTO.getStatus();
	}
	
	public String changePassword(ChangePasswordDTO changePasswordDTO)
	{
		Student student = studentRepository.findByEmail(changePasswordDTO.getEmail());
		if(student.getLoginStatus() == 0)
			return "User is not Logged in";
		changePasswordDTO.setStatus("NOT");
		if(student.getPassword().equals(changePasswordDTO.getOldPassword())  )
		{
			student.setPassword(changePasswordDTO.getNewPassword());
			update(student);
			changePasswordDTO.setStatus("Password changed successfully");
		}
		else
		{
			changePasswordDTO.setStatus("Old Password is not correct");
		}
		return changePasswordDTO.getStatus();
	}
	
	public String logOUt(LoginDTO dto)
	{
		Student student = findByEmail(dto.getEmail());
		if(student.getLoginStatus() == 0)
			dto.setStatus("User is not Logged in");
		else
		{
			student.setLoginStatus(0);
			dto.setStatus("Logged Out Successfully");
			update(student);
		}
		return dto.getStatus();
	}
}
