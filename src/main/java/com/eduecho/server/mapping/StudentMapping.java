package com.eduecho.server.mapping;

import com.eduecho.server.dto.StudentDTO;
import com.eduecho.server.model.Student;

public class StudentMapping {
    public static Student studentDTOToStudentEntity(StudentDTO studentDTO) {
        Student student = new Student();
        student.setId(studentDTO.getId());
        student.setDisplayName(studentDTO.getDisplayName());
        student.setProfilePicture(studentDTO.getProfilePicture());
        return student;
    }

    public static StudentDTO studentEntityToStudentDTO(Student student) {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(student.getId());
        studentDTO.setDisplayName(student.getDisplayName());
        studentDTO.setProfilePicture(student.getProfilePicture());
        return studentDTO;
    }
}
