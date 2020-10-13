package lt.verbus.service;

import lt.verbus.domain.entity.Answer;
import lt.verbus.domain.entity.User;

public class FakeInitial {

    private final UserService userService;

    public FakeInitial() {
        userService = new UserService();
    }

    public void fakeCreateAndSave(){
        User user = new User();
        user.setName("Šarūnas Verbus");
        user.setCity("Vilnius");
        user.setCountry("Lietuva");

        int numberOfAnswers = 4;
        for(int i = 0; i < numberOfAnswers; i++) {
            Answer answer = new Answer(2030);
            answer.setQuestionIndex(i);
            answer.setUser(user);
            user.getAnswers().add(answer);
        }
        userService.save(user);
    }
}
