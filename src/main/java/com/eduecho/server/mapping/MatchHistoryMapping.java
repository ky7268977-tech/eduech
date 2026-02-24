package com.eduecho.server.mapping;

import com.eduecho.server.dto.MatchHistoryFullDTO;
import com.eduecho.server.model.MatchHistory;
import com.eduecho.server.model.Skill;
import com.eduecho.server.model.Student;

import java.util.ArrayList;
import java.util.List;


public class MatchHistoryMapping {
    public static MatchHistory fromDTOToEntity(Student winner, Student loser, Skill subject, String score) {
        MatchHistory matchHistory = new MatchHistory();
        matchHistory.setWinner(winner);
        matchHistory.setLoser(loser);
        matchHistory.setSubject(subject);
        matchHistory.setScore(score);

        return matchHistory;
    }

    public static List<MatchHistoryFullDTO> fromEntityToDTO(List<MatchHistory> matches) {
        return matches.stream()
                .map(match -> {
                    MatchHistoryFullDTO m = new MatchHistoryFullDTO();
                    m.setLoserId(match.getLoser().getId());
                    m.setLoserName(match.getLoser().getDisplayName());
                    m.setWinnerId(match.getWinner().getId());
                    m.setWinnerName(match.getWinner().getDisplayName());
                    m.setSubjectId(match.getSubject().getId());
                    m.setSubjectName(match.getSubject().getName());
                    m.setScore(match.getScore());
                    m.setPlayedAt(match.getPlayedAt());
                    return m;
                }).toList();
    }
}
