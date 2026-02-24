package com.eduecho.server.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MatchHistoryDTO {
    private String winnerId;
    private String loserId;
    private Long subjectId;
    private String score;
}
