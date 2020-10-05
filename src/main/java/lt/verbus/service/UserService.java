package lt.verbus.service;

import lt.verbus.model.User;

public class UserService {

    private User user;
    private static UserService userService;

    private UserService() {
    }

    public static UserService getInstance(){
        if (userService == null) {
            userService = new UserService();
        }
        return userService;
    }

    public void setUser(User user){
        this.user = user;
    }

    public void addAnswer(int userAnswer){
        user.getAnswers().add(userAnswer);
    }

    public User getUser() {
        return user;
    }
}
