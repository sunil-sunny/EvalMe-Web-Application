package com.group18.asdc.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.group18.asdc.entities.User;
import com.group18.asdc.errorhandling.EnrollingStudentException;
import com.group18.asdc.errorhandling.FileProcessingException;

public interface CourseRolesService {

	public boolean allocateTa(int courseId, User user);

	public boolean enrollStuentsIntoCourse(List<User> studentList, int courseId) throws EnrollingStudentException;

	public List<User> extraxtValidStudentsFromFile(MultipartFile file) throws FileProcessingException;
}
