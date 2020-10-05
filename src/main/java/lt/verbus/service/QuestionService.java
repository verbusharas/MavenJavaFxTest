package lt.verbus.service;

import lt.verbus.repository.QuestionRepository;

import java.util.Map;

public class QuestionService {

    private final QuestionRepository questionRepository;

    public QuestionService() {
        this.questionRepository = new QuestionRepository(
                "src/main/resources/questions/original_questions.xml");
    }


    public String getQuestionByNumber(int questionNumber) {
        return questionRepository.getQuestionByNumber(questionNumber-1);
    }

    public String getTrueAnswerByNumber(int questionNumber) {
        return questionRepository.getTrueAnswerByNumber(questionNumber-1);
    }

    public int getTotalQuestionsCount(){
        return questionRepository.getTotalQuestionsCount();
    }

}
