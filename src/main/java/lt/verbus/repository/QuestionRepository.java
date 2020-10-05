package lt.verbus.repository;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class QuestionRepository {

    private String questionsFileAddress;
    private NodeList xmlQuestionNodes;

    public QuestionRepository(String questionsFileAddress) {
        this.questionsFileAddress = questionsFileAddress;
    }

    public String getQuestionByNumber(int questionNumber) {
        return getQuestionInfoByTag(questionNumber, "text");
    }

    public String getTrueAnswerByNumber(int questionNumber) {
        return getQuestionInfoByTag(questionNumber, "answer");
    }

    private String getQuestionInfoByTag(int questionNumber, String infoTag){
        String questionInfo = null;

        if (xmlQuestionNodes == null) {
            loadAllNodesFromXmlByTag("question");
        }

        Node xmlQuestionNode = xmlQuestionNodes.item(questionNumber);
        if (xmlQuestionNode.getNodeType() == Node.ELEMENT_NODE) {
            Element xmlQuestionElement = (Element) xmlQuestionNode;
            questionInfo = xmlQuestionElement
                    .getElementsByTagName(infoTag)
                    .item(0)
                    .getTextContent()
                    .trim()
                    .replaceAll("[ ]{2,}", "\n");
        }

        return questionInfo;
    }

    private void loadAllNodesFromXmlByTag(String tagName) {
        try {
            File file = new File(questionsFileAddress);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();
            xmlQuestionNodes = doc.getElementsByTagName(tagName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getTotalQuestionsCount() {
        loadAllNodesFromXmlByTag("question");
        return xmlQuestionNodes == null ? 0 : xmlQuestionNodes.getLength();
    }

}
