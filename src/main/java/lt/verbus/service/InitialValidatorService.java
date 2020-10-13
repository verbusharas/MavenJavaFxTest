package lt.verbus.service;

import lt.verbus.domain.entity.User;
import lt.verbus.exception.EmptyRepositoryException;
import lt.verbus.exception.NumberOfQuestionsMismatchException;

import java.util.List;

public class InitialValidatorService {

    private QuestionService questionService;
    private UserService userService;

    private List<User> users;

    public InitialValidatorService() {
        questionService = new QuestionService();
        userService = new UserService();
        users = userService.findAll();
    }


    public boolean validateUserRepository() throws EmptyRepositoryException {
        if (users.size() < 1) {
            throw new EmptyRepositoryException();
        }
        return true;
    }

    public boolean validateQuestionRepository() throws NumberOfQuestionsMismatchException {
        if (users.get(0).getAnswers().size() != questionService.findAll().size()) {
            throw new NumberOfQuestionsMismatchException();
        }
        return true;
    }
}
