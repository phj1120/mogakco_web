package xyz.parkh.with.mogakco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.parkh.with.mogakco.entity.StudyTotal;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface StudyTotalRepository extends JpaRepository<StudyTotal, String> {
    List<StudyTotal> findAllByUserUserNumAndDateBetween(String userNum, LocalDate start, LocalDate end);

    List<StudyTotal> findAllByUserUserNum(String userNum);
}
