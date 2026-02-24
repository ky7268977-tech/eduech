package com.eduecho.server.service;

import com.eduecho.server.dto.MatchHistoryDTO;
import com.eduecho.server.dto.MatchHistoryFullDTO;
import com.eduecho.server.mapping.MatchHistoryMapping;
import com.eduecho.server.model.MatchHistory;
import com.eduecho.server.model.Skill;
import com.eduecho.server.model.Student;
import com.eduecho.server.repository.MatchHistoryRepository;
import com.eduecho.server.repository.SkillRepository;
import com.eduecho.server.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MatchHistoryService {
    private final MatchHistoryRepository historyRepository;
    private final StudentRepository studentRepository;
    private final SkillRepository skillRepository;

    public void createMatchHistory(MatchHistoryDTO historyDTO) {
        Student winner = studentRepository.findById(historyDTO.getWinnerId()).orElseThrow();
        Student loser = studentRepository.findById(historyDTO.getLoserId()).orElseThrow();
        Skill subject = skillRepository.findById(historyDTO.getSubjectId()).orElse(null);
        historyRepository.save(MatchHistoryMapping.fromDTOToEntity(winner, loser, subject, historyDTO.getScore()));
    }

    public List<MatchHistoryFullDTO> getAllMatchesById(String studentId) {
        List<MatchHistory> matches = historyRepository.findByWinner_IdOrLoser_Id(studentId,studentId);
        return MatchHistoryMapping.fromEntityToDTO(matches);
    }
}
