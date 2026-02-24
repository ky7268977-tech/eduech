package com.eduecho.server.service;

import com.eduecho.server.dto.CompleteStudentInfoDTO;
import com.eduecho.server.dto.SkillDTO;
import com.eduecho.server.dto.StudentProfileDTO;
import com.eduecho.server.dto.StudentDTO;
import com.eduecho.server.mapping.ProfileMapping;
import com.eduecho.server.mapping.StudentMapping;
import com.eduecho.server.model.Skill;
import com.eduecho.server.model.Student;
import com.eduecho.server.model.StudentProfile;
import com.eduecho.server.repository.SkillRepository;
import com.eduecho.server.repository.StudentProfileRepository;
import com.eduecho.server.repository.StudentRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final StudentProfileRepository studentProfileRepository;
    private final SkillRepository skillRepository;

    public String createNewStudent(StudentDTO studentDTO) {
        Student student = StudentMapping.studentDTOToStudentEntity(studentDTO);
        studentRepository.save(student);
        return "Student created successfully";
    }

    // profile-related apis

    public StudentProfileDTO getProfileOfAUser(String id) {
        StudentProfile profile = studentProfileRepository.findByStudentId(id).
                orElseThrow(() -> new RuntimeException("No profile found."));
        return ProfileMapping.fromStudentProfileEntityToDTO(profile);
    }

    public String saveProfile(StudentProfileDTO studentProfileDTO, String id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));
        if (studentProfileRepository.findByStudentId(student.getId()).isPresent()) {
            throw new RuntimeException("Profile already exists");
        }
        studentProfileRepository.save(ProfileMapping.fromStudentProfileDTOToStudentProfile(studentProfileDTO, student));
        return "Student profile saved successfully";
    }

    public String updateProfile(StudentProfileDTO studentProfileDTO, String id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));
        StudentProfile studentProfile = studentProfileRepository
                .findByStudentId(student.getId())
                .orElseThrow(() -> new RuntimeException("Profile not found"));

        studentProfileRepository.save(ProfileMapping.updateFromStudentProfile(studentProfileDTO, studentProfile));
        return "Profile updated successfully";
    }

    // student-profile-skills-related apis
    @Transactional
    public List<CompleteStudentInfoDTO> getAllStudentsWithProfile(Pageable pageable) {
        List<CompleteStudentInfoDTO> students = studentRepository.getAllStudentsWithProfile(pageable);
        List<Object[]> rows = studentRepository.getAllStudentSkills(pageable);

        Map<String, List<SkillDTO>> skillMap = new HashMap<>();

        for(Object[] row : rows) {
            String studentId = String.valueOf(row[0]);
            SkillDTO skillDTO = new SkillDTO();
            skillDTO.setId((Long) row[2]);
            skillDTO.setSkill(String.valueOf(row[1]));
            skillMap.computeIfAbsent(studentId, k->new ArrayList<>())
                    .add(skillDTO);
        }

        for(CompleteStudentInfoDTO dto : students) {
            dto.setSkills(skillMap.getOrDefault(dto.getId(), new ArrayList<>()));
        }

        return students;
    }

    @Transactional
    public CompleteStudentInfoDTO getStudentWithProfileById(String id) {
        CompleteStudentInfoDTO completeStudentInfoDTO = studentRepository.getStudentWithProfileById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));
        List<SkillDTO> skills = studentRepository.getSkillsByStudentId(id);
        completeStudentInfoDTO.setSkills(skills);
        return completeStudentInfoDTO;
    }

    // Skill-student-related apis
    @Transactional
    public void addSkillToStudent(String studentId, List<String> skills) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new RuntimeException("Student not found"));
        for(String s : skills) {
            Skill skill = skillRepository.findByName(s).
                    orElseGet(() -> {
                        Skill skill1 = new Skill();
                        skill1.setName(s);
                        return skillRepository.save(skill1);
                    });

            student.getSkillSet().add(skill);
            skill.getStudentSet().add(student);
        }
        studentRepository.save(student);
    }
}

