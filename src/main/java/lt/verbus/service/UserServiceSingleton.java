package lt.verbus.service;

import lt.verbus.model.User;

public class UserServiceSingleton {

    private User user;
    private static UserServiceSingleton userServiceSingleton;

    private UserServiceSingleton() {
    }

    public static UserServiceSingleton getInstance(){
        if (userServiceSingleton == null) {
            userServiceSingleton = new UserServiceSingleton();
        }
        return userServiceSingleton;
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
