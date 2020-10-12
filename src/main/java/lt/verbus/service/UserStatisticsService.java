package lt.verbus.service;

import lt.verbus.dao.UserStatisticsDao;
import lt.verbus.domain.model.Question;

public class UserStatisticsService {

    private final UserStatisticsDao userStatisticsDao;
    private final QuestionService questionService;

    public UserStatisticsService() {
        questionService = new QuestionService();
        userStatisticsDao = new UserStatisticsDao();
    }

    public UserStatisticsService(UserStatisticsDao userStatisticsDao, QuestionService questionService) {
        this.userStatisticsDao = userStatisticsDao;
        this.questionService = questionService;
    }

    public Double getAverageAnswerValue() {
        return userStatisticsDao.getAverageAnswerValue();
    }

    public Double getAverageAnswerValueByQuestionIndex(int questionIndex) {
        return userStatisticsDao.getAverageAnswerValueByQuestionIndex(questionIndex);
    }

    public Double getAverageAnswerValueByUserId(int userId) {
        return userStatisticsDao.getAverageAnswerValueByUserId(userId);
    }

    public double compareUserAnswerToAverageAnswer(int questionIndex, int userAnswer) {
        double avgAnswer = getAverageAnswerValueByQuestionIndex(questionIndex);
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

    public double compareUserAverageToOverallAverage(int userId) {
        double userAvg = getAverageAnswerValueByUserId(userId);
        double overallAvg = getAverageAnswerValue();
        double actualDif = userAvg - overallAvg;
        double maxDif = getMaxPossibleDifFromAvg();
        return actualDif / maxDif;
    }

    private int getMaxPossibleDifFromAvg() {
        final int MAX = 2130;
        final int MIN = 2020;
        return MAX - MIN;
    }

    private int getMaxPossibleDifFromCorrect(int correctAnswer) {
        final int MAX = 2130;
        final int MIN = 2020;
        return Math.max(MAX - correctAnswer, correctAnswer - MIN);
    }

}