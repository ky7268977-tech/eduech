package com.eduecho.server.repository;

import com.eduecho.server.model.MatchHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MatchHistoryRepository extends JpaRepository<MatchHistory, Long> {

    List<MatchHistory> findByWinner_IdOrLoser_Id(String winnerId, String loserId);
}
