package com.eduecho.server.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "match_history")
@Getter
@Setter
public class MatchHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "winner_id", nullable = false)
    private Student winner;

    @ManyToOne
    @JoinColumn(name = "loser_id", nullable = false)
    private Student loser;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Skill subject; // optional: which subject the match/discussion was about

    private String score; // optional: store points, if needed
    private LocalDateTime playedAt = LocalDateTime.now();
}
