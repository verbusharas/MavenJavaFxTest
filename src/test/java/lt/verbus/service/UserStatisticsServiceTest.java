package lt.verbus.service;

import junit.framework.TestCase;
import lt.verbus.dao.UserStatisticsDao;
import lt.verbus.domain.model.Question;
import org.junit.Before;
import org.mockito.Mockito;

import static org.mockito.Mockito.atLeast;

public class UserStatisticsServiceTest extends TestCase {

    private UserStatisticsService userStatisticsService;
    private final UserStatisticsDao userStatisticsDao = Mockito.mock(UserStatisticsDao.class);
    private final QuestionService questionService = Mockito.mock(QuestionService.class);

    @Before
    public void setUp() {
        userStatisticsService = new UserStatisticsService(userStatisticsDao, questionService);
    }

    public void testCompareUserAnswerToAverageAnswer() {
        Mockito.when(userStatisticsDao.getAverageAnswerValueByQuestionIndex(0))
                .thenReturn(2025.0);
        Mockito.when(userStatisticsDao.getAverageAnswerValueByQuestionIndex(1))
                .thenReturn(2050.0);
        Mockito.when(userStatisticsDao.getAverageAnswerValueByQuestionIndex(2))
                .thenReturn(2070.0);

        double ratioWhenEquals = userStatisticsService.compareUserAnswerToAverageAnswer
                (0, 2025);
        double ratioWhenLess = userStatisticsService.compareUserAnswerToAverageAnswer
                (1, 2025);
        double ratioWhenMore = userStatisticsService.compareUserAnswerToAverageAnswer
                (2, 2080);

        assertEquals(0.0, ratioWhenEquals);
        assertEquals(-25.0/110, ratioWhenLess);
        assertEquals(10.0/110, ratioWhenMore);

        Mockito.verify(userStatisticsDao).getAverageAnswerValueByQuestionIndex(0);
        Mockito.verify(userStatisticsDao).getAverageAnswerValueByQuestionIndex(1);
        Mockito.verify(userStatisticsDao).getAverageAnswerValueByQuestionIndex(2);
    }

    public void testCompareUserAnswerToCorrectAnswer() {
        Question mockedQuestion0 = new Question();
        mockedQuestion0.setCorrectAnswer("2025");
        Question mockedQuestion1 = new Question();
        mockedQuestion1.setCorrectAnswer("2030");
        Question mockedQuestion2 = new Question();
        mockedQuestion2.setCorrectAnswer("2035");

        Mockito.when(questionService.getQuestionByIndex(0))
                .thenReturn(mockedQuestion0);

        Mockito.when(questionService.getQuestionByIndex(1))
                .thenReturn(mockedQuestion1);

        Mockito.when(questionService.getQuestionByIndex(2))
                .thenReturn(mockedQuestion2);

        double ratioWhenEquals = userStatisticsService.compareUserAnswerToCorrectAnswer
                (0, 2025);
        double ratioWhenLess = userStatisticsService.compareUserAnswerToCorrectAnswer
                (1, 2025);
        double ratioWhenMore = userStatisticsService.compareUserAnswerToCorrectAnswer
                (2, 2040);

        assertEquals(0.0, ratioWhenEquals);
        assertEquals(-5.0/100, ratioWhenLess);
        assertEquals(5.0/95, ratioWhenMore);

        Mockito.verify(questionService).getQuestionByIndex(0);
        Mockito.verify(questionService).getQuestionByIndex(1);
        Mockito.verify(questionService).getQuestionByIndex(2);
    }

    public void testCompareUserAverageToOverallAverage() {
        Mockito.when(userStatisticsDao.getAverageAnswerValueByUserId(0))
                .thenReturn(2025.0);
        Mockito.when(userStatisticsDao.getAverageAnswerValueByUserId(1))
                .thenReturn(2030.0);
        Mockito.when(userStatisticsDao.getAverageAnswerValueByUserId(2))
                .thenReturn(2035.0);

        Mockito.when(userStatisticsDao.getAverageAnswerValue())
                .thenReturn(2030.0);

        double ratioWhenLess = userStatisticsService.compareUserAverageToOverallAverage(0);
        double ratioWhenEquals = userStatisticsService.compareUserAverageToOverallAverage(1);
        double ratioWhenMore = userStatisticsService.compareUserAverageToOverallAverage(2);

        assertEquals(-5.0/110, ratioWhenLess);
        assertEquals(0.0, ratioWhenEquals);
        assertEquals(5.0/110, ratioWhenMore);

        Mockito.verify(userStatisticsDao).getAverageAnswerValueByUserId(0);
        Mockito.verify(userStatisticsDao).getAverageAnswerValueByUserId(1);
        Mockito.verify(userStatisticsDao).getAverageAnswerValueByUserId(2);

        Mockito.verify(userStatisticsDao, Mockito.atLeast(3)).getAverageAnswerValue();
    }


}