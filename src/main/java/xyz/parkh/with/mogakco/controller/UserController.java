package xyz.parkh.with.mogakco.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import xyz.parkh.with.mogakco.dto.UserStudy;
import xyz.parkh.with.mogakco.service.StudyTotalService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final StudyTotalService studyTotalService;

    @GetMapping("/")
    public String index(Model model) {
        List<UserStudy> allUserWeeklyStudyTimeList = studyTotalService.getAllUserStudyTotalWeek();
        List<String> userNameList = allUserWeeklyStudyTimeList.stream().map(UserStudy::getUserName).collect(Collectors.toList());

        model.addAttribute("userNameList", userNameList);
        model.addAttribute("allUserWeeklyStudyTimeList", allUserWeeklyStudyTimeList);
        return "index";
    }

    @GetMapping("/users/{userNum}")
    public String userNum(@PathVariable("userNum") String userNum, Model model) {
        UserStudy userStudyAll = studyTotalService.getStudyTotalByUserNum(userNum);
        UserStudy userStudyWeek = studyTotalService.getUserStudyTotalWeek(userNum);

        Map<String, Integer> studyTimeMapAll = userStudyAll.getStudyTimeMap();
        Map<String, Integer> studyTimeMapWeek = userStudyWeek.getStudyTimeMap();

        String userName = userStudyAll.getUserName();
        Integer wantStudyTotal = 300;
        Integer todayStudyTotal = userStudyWeek.todayStudyTime();

        model.addAttribute("userName", userName);
        model.addAttribute("todayStudyTotal", todayStudyTotal);
        model.addAttribute("wantStudyTotal", wantStudyTotal);
        model.addAttribute("studyTimeMapAll", studyTimeMapAll);
        model.addAttribute("studyTimeMapWeek", studyTimeMapWeek);
        return "userNum";
    }
}
