package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.domain.dto.BaseStudentDTO;
import com.example.domain.dto.StudentDTO;
import com.example.repository.StudentRepository;
import com.mongodb.rx.client.Success;

import rx.Observable;

@Controller
@RequestMapping("/api/students")
public class StudentResource {
	@Autowired
	private StudentRepository studentRepository;
	
	private static final Logger log = LoggerFactory.getLogger(StudentResource.class);

	@RequestMapping(value = "", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public Observable<Success> createStudent(@RequestBody BaseStudentDTO student) {
		log.debug("Creating a new Student.");
		return studentRepository.createStudent(student);
	}

	@RequestMapping(value = "", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public Observable<StudentDTO> getStudentByName(@RequestParam String name) {
		log.debug("Fetching a new student with the Name: " + name);
		return studentRepository.findByName(name);
	}
}
