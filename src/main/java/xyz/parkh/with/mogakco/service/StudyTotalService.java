package xyz.parkh.with.mogakco.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.parkh.with.mogakco.entity.StudyTotal;
import xyz.parkh.with.mogakco.repository.StudyTotalRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class StudyTotalService {

    @Autowired
    StudyTotalRepository studyTotalRepository;

    public List<StudyTotal> getStudyTotalByDate(LocalDate date) {
        List<StudyTotal> allByDate = studyTotalRepository.findAllByDate(date);
        return allByDate;
    }

    public List<StudyTotal> getStudyTotalStartToEnd(LocalDate start, LocalDate end) {
        List<StudyTotal> allByDateBetween = studyTotalRepository.findAllByDateBetween(start, end);
        System.out.println(allByDateBetween);
        return allByDateBetween;
    }

    public List<StudyTotal> getStudyTotalByUserNum(String userNum) {
        List<StudyTotal> allByUserUserNum = studyTotalRepository.findAllByUserUserNum(userNum);
        return allByUserUserNum;
    }

}
