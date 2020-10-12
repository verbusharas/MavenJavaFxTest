package lt.verbus.service;

import lt.verbus.dao.UserStatisticsDao;
import lt.verbus.domain.entity.Answer;
import lt.verbus.domain.entity.User;
import lt.verbus.domain.model.Question;
import lt.verbus.util.PropertiesReader;

public class UserStatisticsService {

    private final UserStatisticsDao userStatisticsDao;
    private final QuestionService questionService;

    private int MAX_YEAR;
    private int MIN_YEAR;

    public UserStatisticsService() {
        questionService = new QuestionService();
        userStatisticsDao = new UserStatisticsDao();
        PropertiesReader properties = new PropertiesReader();
        MAX_YEAR = properties.getMaxYear();
        MIN_YEAR = properties.getMinyear();

    }

    public UserStatisticsService(UserStatisticsDao userStatisticsDao, QuestionService questionService) {
        this.userStatisticsDao = userStatisticsDao;
        this.questionService = questionService;
    }

    public Double getOverallAvgAnswerValue() {
        return userStatisticsDao.getAvgAnswerValue();
    }

    public Double getAvgSingleAnswerValueByQuestionIndex(int questionIndex) {
        return userStatisticsDao.getAvgSingleAnswerValueByQuestionIndex(questionIndex);
    }

    public Double getUserAvgAnswerValue(int userId) {
        return userStatisticsDao.getUserAverageAnswerValue(userId);
    }

    public double compareUserAnswerToSingleAvg(int questionIndex, int userAnswer) {
        double avgAnswer = getAvgSingleAnswerValueByQuestionIndex(questionIndex);
        double actualDif = userAnswer - avgAnswer;
        double maxDif = getMaxPossibleDifFromAvg();
        return actualDif / maxDif;
    }

    public double compareUserAnswerToCorrectAnswer(int questionIndex, int userAnswer) {
        Question question = (questionService.getQuestionByIndex(questionIndex));
        int correctAnswer = Integer.parseInt(question.getCorrectAnswer());
        double actualDif = userAnswer - correctAnswer;
        double maxDif = getMaxPossibleDifFromCorrect(correctAnswer);
        return actualDif / maxDif;
    }

    public double compareUserAvgToOverallAvg(int userId) {
        double userAvg = getUserAvgAnswerValue(userId);
        return getOverallRatio(userAvg);
    }

    public double compareUserAvgToOverallAvg(User user) {
        double sum = user.getAnswers().stream().mapToInt(Answer::getAnswer).sum();
        double entries = user.getAnswers().size();
        double userAvg = sum / entries;
        return getOverallRatio(userAvg);
    }

    private double getOverallRatio(double userAvg) {
        double overallAvg = getOverallAvgAnswerValue();
        double actualDif = userAvg - overallAvg;
        double maxDif = getMaxPossibleDifFromAvg();
        return actualDif / maxDif;
    }

    private int getMaxPossibleDifFromAvg() {
        return MAX_YEAR - MIN_YEAR;
    }

    private int getMaxPossibleDifFromCorrect(int correctAnswer) {
        return Math.max(MAX_YEAR - correctAnswer, correctAnswer - MIN_YEAR);
    }

}