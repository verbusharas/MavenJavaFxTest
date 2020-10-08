package lt.verbus.service;

import lt.verbus.domain.model.Question;
import lt.verbus.repository.QuestionRepository;

import java.util.List;
import java.util.Map;

public class QuestionService {

    private final QuestionRepository questionRepository;

    public QuestionService() {
        this.questionRepository = new QuestionRepository();
    }

    public Question getQuestionByNumber(int questionNumber) {
        return questionRepository.findAll().get(questionNumber-1);
    }

    public List<Question> findAll(){
        return questionRepository.findAll();
    }

}
