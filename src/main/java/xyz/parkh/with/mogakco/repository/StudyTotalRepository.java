package xyz.parkh.with.mogakco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.parkh.with.mogakco.entity.StudyTotal;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface StudyTotalRepository extends JpaRepository<StudyTotal, String> {
    List<StudyTotal> findAllByDateBetween(LocalDate start, LocalDate end);
    List<StudyTotal> findAllByDate(LocalDate date);
    List<StudyTotal> findAllByUserUserNum(String userNum);
}
