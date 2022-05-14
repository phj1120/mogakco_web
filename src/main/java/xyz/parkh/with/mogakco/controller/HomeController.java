package xyz.parkh.with.mogakco.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import xyz.parkh.with.mogakco.entity.StudyTotal;
import xyz.parkh.with.mogakco.entity.User;
import xyz.parkh.with.mogakco.service.StudyTotalService;
import xyz.parkh.with.mogakco.service.UserService;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping()
public class HomeController {

    @Autowired
    UserService userService;

    @Autowired
    StudyTotalService studyTotalService;

    @GetMapping
    public String index(Model model) {
        List<User> userListAll = userService.getUserListAll();
        model.addAttribute("userList", userListAll);
        return "index";
    }

    @GetMapping("/{userNum}")
    public String userNum(@PathVariable("userNum") String userNum, Model model) {
        // TODO Service 에 비즈니스 로직이 있는게 좋은 듯.
        // 그러면 호출을 여러번 하는데 괜찮나?

        List<StudyTotal> allList = studyTotalService.getStudyTotalByUserNum(userNum);
        String userName = allList.get(0).getUser().getUserName();

        LocalDate today = LocalDate.now();

        String todayStudyTime;
        StudyTotal lastStudyTotal = allList.get(allList.size() - 1);
        if (today.equals(lastStudyTotal.getDate())) {
            todayStudyTime = lastStudyTotal.getStudyTime();
        } else {
            todayStudyTime = "None";
        }

        LocalDate startOfWeek = today.with(DayOfWeek.MONDAY);
        LocalDate endOfWeek = today.with(DayOfWeek.SUNDAY);
        List<StudyTotal> studyTotalWeekList = studyTotalService.getStudyTotalStartToEnd(startOfWeek, endOfWeek);

        List<StudyTotal> weekList = new ArrayList<>();
        for (StudyTotal studyTotal : studyTotalWeekList) {
            if (userNum.equals(studyTotal.getUser().getUserNum())) {
                weekList.add(studyTotal);
            }
        }

        model.addAttribute("userName", userName);
        model.addAttribute("todayStudyTime", todayStudyTime);
        model.addAttribute("weekList", weekList);
        model.addAttribute("allList", allList);
        return "userNum";
    }

    @GetMapping("/{userNum}/today")
    public String today(@PathVariable("userNum") String userNum, Model model) {
        List<StudyTotal> studyTotalByUserNum = studyTotalService.getStudyTotalByUserNum(userNum);
        model.addAttribute("studyTotalList", studyTotalByUserNum);
        return "userNum/today";
    }



}
