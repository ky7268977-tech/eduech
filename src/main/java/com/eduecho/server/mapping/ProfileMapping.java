package com.eduecho.server.mapping;

import com.eduecho.server.dto.StudentProfileDTO;
import com.eduecho.server.model.Student;
import com.eduecho.server.model.StudentProfile;

public class ProfileMapping {
    public static StudentProfile fromStudentProfileDTOToStudentProfile(StudentProfileDTO studentProfileDTO, Student student) {
        StudentProfile studentProfile = new StudentProfile();
        studentProfile.setBio(studentProfileDTO.getBio());
        studentProfile.setSchoolName(studentProfileDTO.getSchoolName());
        studentProfile.setStudent(student);
        return studentProfile;
    }

    public static StudentProfileDTO fromStudentProfileEntityToDTO(StudentProfile studentProfile) {
        StudentProfileDTO profileSemiDTO = new StudentProfileDTO();
        profileSemiDTO.setBio(studentProfile.getBio());
        profileSemiDTO.setSchoolName(studentProfile.getSchoolName());
        return profileSemiDTO;
    }

    public static StudentProfile updateFromStudentProfile(StudentProfileDTO studentProfileDTO, StudentProfile profile) {
        profile.setBio(studentProfileDTO.getBio());
        profile.setSchoolName(studentProfileDTO.getSchoolName());
        return profile;
    }
}
