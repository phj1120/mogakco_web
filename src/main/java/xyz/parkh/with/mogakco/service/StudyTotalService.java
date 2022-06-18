package xyz.parkh.with.mogakco.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.parkh.with.mogakco.dto.UserStudy;
import xyz.parkh.with.mogakco.entity.StudyTotal;
import xyz.parkh.with.mogakco.entity.User;
import xyz.parkh.with.mogakco.repository.StudyTotalRepository;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class StudyTotalService {

    private final StudyTotalRepository studyTotalRepository;
    private final UserService userService;

    // 일정 사용자 공부 시간 조회 (start ~ end)
    public UserStudy getUserStudyTotalStartToEnd(String userNum, LocalDate start, LocalDate end) {
        List<StudyTotal> findStudyTotalList = studyTotalRepository.findAllByUserUserNumAndDateBetween(userNum, start, end);
        User user = userService.getUser(userNum);
        UserStudy userStudy = new UserStudy(userNum, user.getUserName());
        userStudy.studyTotalListToFullUserStudy(findStudyTotalList, start, end);

        return userStudy;
    }

    // 일정 사용자 공부 시간 조회 (주간)
    public UserStudy getUserStudyTotalWeek(String userNum) {
        LocalDate today = LocalDate.now();
        LocalDate startOfWeek = today.with(DayOfWeek.MONDAY);
        LocalDate endOfWeek = today.with(DayOfWeek.SUNDAY);
        UserStudy userWeekStudyTotal = getUserStudyTotalStartToEnd(userNum, startOfWeek, endOfWeek);
        return userWeekStudyTotal;
    }

    // 일정 사용자 공부 시간 조회 (전체)
    public UserStudy getStudyTotalByUserNum(String userNum) {
        List<StudyTotal> allByUserUserNum = studyTotalRepository.findAllByUserUserNum(userNum);
        User user = userService.getUser(userNum);
        UserStudy userStudy = new UserStudy(userNum, user.getUserName());

        if (allByUserUserNum.get(0) != null) {
            LocalDate start = allByUserUserNum.get(0).getDate();
            LocalDate end = LocalDate.now();
            userStudy.studyTotalListToFullUserStudy(allByUserUserNum, start, end);
        }
        return userStudy;
    }

    // 모든 사용자 공부 시간 조회 (주간)
    public List<UserStudy> getAllUserStudyTotalWeek() {
        List<User> userList = userService.getUserListAll();
        List<UserStudy> allUserStudyTotalWeek = new ArrayList<>();

        for (User user : userList) {
            allUserStudyTotalWeek.add(getUserStudyTotalWeek(user.getUserNum()));
        }
        return allUserStudyTotalWeek;
    }
}
