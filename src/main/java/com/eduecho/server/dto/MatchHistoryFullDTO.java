package com.eduecho.server.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MatchHistoryFullDTO {
    private String loserId;
    private String loserName;
    private String winnerId;
    private String winnerName;
    private Long subjectId;
    private String subjectName;
    private String score;
    private LocalDateTime playedAt;
}
