package xyz.parkh.with.mogakco.dto;

import lombok.Getter;
import xyz.parkh.with.mogakco.entity.StudyTotal;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Getter
public class UserStudy {
    String userNum;
    String userName;

    Map<String, Integer> studyTimeMap = new LinkedHashMap<>();

    public UserStudy(String userNum, String userName) {
        this.userName = userName;
        this.userNum = userNum;
    }

    public void addStudyTimeMap(String date, Integer studyTime) {
        studyTimeMap.put(date, studyTime);
    }

    // 오늘 공부한 시간 조회
    public Integer todayStudyTime() {
        LocalDate today = LocalDate.now();
        String day = dateFormat(today);
        return studyTimeMap.get(day);
    }

    // 화면에 사용 되는 날짜 형식으로 변환
    public String dateFormat(LocalDate day) {
        return day.getMonthValue() + "/" + day.getDayOfMonth();
    }

    // List<StudyTotal> 를 studyTimeMap 에 추가
    public void studyTotalListToFullUserStudy(List<StudyTotal> studyTotalList, LocalDate start, LocalDate end) {
        long days = ChronoUnit.DAYS.between(start, end) + 1;

        for (int i = 0; i < days; i++) {
            // 공부 시간이 없는 날짜도 포함
            LocalDate day = start.plusDays(i);
            String studyDate = dateFormat(day);
            Integer studyTime = 0;

            Optional<StudyTotal> findStudyTotal = studyTotalList.stream()
                    .filter(studyTotal -> day.equals(studyTotal.getDate()))
                    .findFirst();

            if (!findStudyTotal.isEmpty()) {
                StudyTotal studyTotal = findStudyTotal.get();
                studyTime = studyTotal.getStudyTime();
            }

            if (LocalDate.now().isBefore(day)){
                studyTime = null;
            }

            addStudyTimeMap(studyDate, studyTime);
        }
    }

}
