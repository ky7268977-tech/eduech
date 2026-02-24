package com.eduecho.server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CompleteStudentInfoDTO {
    private String id;
    private String displayName;
    private String profilePicture;
    private String bio;
    private String schoolName;
    private List<SkillDTO> skills;

    public CompleteStudentInfoDTO(String id, String displayName, String profilePicture, String bio, String schoolName) {
        this.id = id;
        this.displayName = displayName;
        this.profilePicture = profilePicture;
        this.bio = bio;
        this.schoolName = schoolName;
    }
}
