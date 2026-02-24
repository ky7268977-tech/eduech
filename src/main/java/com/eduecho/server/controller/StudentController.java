package com.eduecho.server.controller;

import com.eduecho.server.dto.CompleteStudentInfoDTO;
import com.eduecho.server.dto.StudentProfileDTO;
import com.eduecho.server.dto.StudentDTO;
import com.eduecho.server.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@AllArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @PostMapping
    public ResponseEntity<String> createStudent(@RequestBody StudentDTO studentDTO) {
        return ResponseEntity.ok(studentService.createNewStudent(studentDTO));
    }

    @GetMapping("/{id}/profile")
    public ResponseEntity<StudentProfileDTO> getStudentProfile(@PathVariable("id") String studentId) {
        return ResponseEntity.ok(studentService.getProfileOfAUser(studentId));
    }

    @PostMapping("/{id}/profile")
    public ResponseEntity<String> SaveProfile(@RequestBody StudentProfileDTO studentProfileDTO, @PathVariable("id") String studentId) {
        return new ResponseEntity<>(studentService.saveProfile(studentProfileDTO, studentId), HttpStatus.CREATED);
    }

    @PutMapping("/{id}/profile")
    public ResponseEntity<String> updateProfile(@RequestBody StudentProfileDTO studentProfileDTO, @PathVariable("id") String studentId) {
        return new ResponseEntity<>(studentService.updateProfile(studentProfileDTO, studentId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CompleteStudentInfoDTO>> getStudentWithProfile(Pageable pageable) {
        return ResponseEntity.ok(studentService.getAllStudentsWithProfile(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompleteStudentInfoDTO> getStudentWithProfile(@PathVariable("id") String id) {
        return ResponseEntity.ok(studentService.getStudentWithProfileById(id));
    }

    @PostMapping("/{id}/skills")
    public ResponseEntity<String> addSkillToStudent(@RequestBody List<String> skills, @PathVariable("id") String studentId) {
        studentService.addSkillToStudent(studentId, skills);
        return ResponseEntity.ok("Skill added successfully");
    }
}
