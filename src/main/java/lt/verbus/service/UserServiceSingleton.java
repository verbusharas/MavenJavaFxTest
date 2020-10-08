package lt.verbus.service;

import lt.verbus.domain.entity.Answer;
import lt.verbus.domain.entity.User;
import lt.verbus.repository.UserRepository;

public class UserServiceSingleton {

    private User user;
    private static UserServiceSingleton userServiceSingleton;
    private UserRepository userRepository;

    private UserServiceSingleton() {
        userRepository = new UserRepository();
    }

    public static UserServiceSingleton getInstance() {
        if (userServiceSingleton == null) {
            userServiceSingleton = new UserServiceSingleton();
        }
        return userServiceSingleton;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void addAnswer(Answer userAnswer) {
        userAnswer.setUser(user);
        user.getAnswers().add(userAnswer);
    }

    public User getUser() {
        return user;
    }

    public void save(User user) {
        userRepository.save(user);
    }
}
