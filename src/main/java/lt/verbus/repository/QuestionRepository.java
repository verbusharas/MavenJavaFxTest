package lt.verbus.repository;

import java.io.File;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import lt.verbus.domain.model.Question;
import lt.verbus.util.XmlQuestionReader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class QuestionRepository implements Repository<Question> {

    private final String questionsFileAddress;

    public QuestionRepository() {
        this.questionsFileAddress = "src/main/resources/questions/original_questions.xml";
    }

    @Override
    public List<Question> findAll() {
        XmlQuestionReader xmlQuestionReader = new XmlQuestionReader(questionsFileAddress);
        return xmlQuestionReader.getAllQuestions();
    }

    @Override
    public Question findById(int id) {
        return findAll().get(id);
    }

    @Override
    public void save(Question question) {
        //TODO: save new question into XML
    }

    @Override
    public void delete(Question question) {
        //TODO: delete question from XML
    }
}
