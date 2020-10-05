package lt.verbus.service;

import lt.verbus.repository.QuestionRepository;

import java.util.Map;

public class QuestionService {

    private QuestionRepository questionRepository;
    private static QuestionService questionService;


    private QuestionService() {
        this.questionRepository = new QuestionRepository(
                "src/main/resources/questions/original_questions.xml");
    }

    public static QuestionService getInstance(){
        if (questionService == null) {
            questionService = new QuestionService();
        }
        return questionService;
    }

    public String getQuestionByNumber(int questionNumber) {
        // TODO: validate number extents
        return questionRepository.getQuestionByNumber(questionNumber-1);
    }

    public String getTrueAnswerByNumber(int questionNumber) {
        return questionRepository.getTrueAnswerByNumber(questionNumber-1);
    }

    public int getTotalQuestionsCount(){
        return questionRepository.getTotalQuestionsCount();
    }

}
