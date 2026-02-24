package com.eduecho.server.repository;

import com.eduecho.server.dto.CompleteStudentInfoDTO;
import com.eduecho.server.dto.SkillDTO;
import com.eduecho.server.model.Student;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
    @Query("""
       SELECT new com.eduecho.server.dto.CompleteStudentInfoDTO(
            s.id,
            s.displayName,
            s.profilePicture,
            p.bio,
            p.schoolName
       )
       FROM Student s
       LEFT JOIN StudentProfile p ON p.student.id = s.id
       """)
    List<CompleteStudentInfoDTO> getAllStudentsWithProfile(Pageable pageable);

    @Query("""
        SELECT s.id, sk.name, sk.id
        FROM Student s
        JOIN s.skillSet sk
    """)
    List<Object[]> getAllStudentSkills(Pageable pageable);

    @Query("""
        SELECT new com.eduecho.server.dto.CompleteStudentInfoDTO(
            s.id,
            s.displayName,
            s.profilePicture,
            p.bio,
            p.schoolName
        )
        FROM Student s
        LEFT JOIN StudentProfile p ON p.student.id = s.id
        WHERE s.id = :id
      """)
    Optional<CompleteStudentInfoDTO> getStudentWithProfileById(String id);

    @Query("""
        SELECT sk.name, sk.id
        FROM Student s
        JOIN s.skillSet sk
        WHERE s.id = :id
    """)
    List<SkillDTO> getSkillsByStudentId(String id);

}
