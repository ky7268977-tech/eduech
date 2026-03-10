package com.eduecho.server.controller;

import com.eduecho.server.dto.MatchHistoryDTO;
import com.eduecho.server.dto.MatchHistoryFullDTO;
import com.eduecho.server.service.MatchHistoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/matches")
@AllArgsConstructor
public class MatchHistoryController {

    private final MatchHistoryService matchHistoryService;

    @PostMapping
    public ResponseEntity<String> createMatchHistory(@RequestBody MatchHistoryDTO matchHistoryDTO) {
        matchHistoryService.createMatchHistory(matchHistoryDTO);
        return new ResponseEntity<>("Match History Created Successfully", HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<MatchHistoryFullDTO>> getMatchHistoryById(@PathVariable("id") String studentId) {
        return ResponseEntity.ok(matchHistoryService.getAllMatchesById(studentId));
    }
}
