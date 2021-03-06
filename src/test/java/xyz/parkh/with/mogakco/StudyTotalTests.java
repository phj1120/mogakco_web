package xyz.parkh.with.mogakco;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import xyz.parkh.with.mogakco.dto.UserStudy;
import xyz.parkh.with.mogakco.entity.StudyTotal;
import xyz.parkh.with.mogakco.service.StudyTotalService;

import java.util.List;

@SpringBootTest
public class StudyTotalTests {

    @Autowired
    StudyTotalService studyTotalService;

//    @Test
//    public void getStudyTotalListTest() {
//        // TODO 정확한 테스트를 위해서는 초기 데이터 집어 넣고 조회가 되는지 확인하는 코드가 필요할 듯
//        LocalDate start = LocalDate.parse("20220320", DateTimeFormatter.BASIC_ISO_DATE);
//        LocalDate end = LocalDate.parse("2022-03-22");
//
//        List<StudyTotal> studyTotals = studyTotalService.getUserStudyTotalStartToEnd("1", start, end);
//
//        Assertions.assertThat(studyTotals.isEmpty()).isFalse();
//    }

    @Test
    public void getStudyTotalListByUserNumTest() {
        // TODO 일단 조회용 하드코딩...
        UserStudy studyTotalByUserNum = studyTotalService.getStudyTotalByUserNum("1");
        Assertions.assertThat(studyTotalByUserNum.getUserName()).isEqualTo("박현준");
    }

    @Test
    public void what() {
        // TODO 일단 조회용 하드코딩...
        UserStudy studyTotalByUserNum = studyTotalService.getUserStudyTotalWeek("1");
        System.out.println("studyTotalByUserNum = " + studyTotalByUserNum);
    }


}
