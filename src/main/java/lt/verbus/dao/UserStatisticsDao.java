package lt.verbus.dao;

import lt.verbus.domain.entity.Answer;

public class UserStatisticsDao extends UserDao {

    public Double getAverageAnswerValue() {
        CriteriaCustomizer<Double> criteriaCustomizer =
                (criteria) -> {
                    criteria.select(builder.avg(sourceRoot.get("answer")));
                    return criteria;
                };
        return super.createCustomQuery(criteriaCustomizer, Double.class, Answer.class).getSingleResult();
    }

    public Double getAverageAnswerValueByQuestionIndex(int questionIndex) {
        CriteriaCustomizer<Double> criteriaCustomizer =
                (criteria) -> {
                    criteria.select(builder.avg(sourceRoot.get("answer"))).where(
                            builder.equal(sourceRoot.get("questionNumber"), questionIndex)
                    );
                    return criteria;
                };
        return super.createCustomQuery(criteriaCustomizer, Double.class, Answer.class).getSingleResult();
    }

    public Double getAverageAnswerValueByUserId(int userId) {
        CriteriaCustomizer<Double> criteriaCustomizer =
                (criteria) -> {
                    criteria.select(builder.avg(sourceRoot.get("answer"))).where(
                            builder.equal(sourceRoot.get("user"), userId)
                    );
                    return criteria;
                };
        return super.createCustomQuery(criteriaCustomizer, Double.class, Answer.class).getSingleResult();
    }

}
