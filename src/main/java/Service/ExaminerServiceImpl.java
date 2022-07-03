package Service;

import exeption.InvalidExamGenerateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import question.Question;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class ExaminerServiceImpl implements ExaminerService{
    private static final Logger log = LoggerFactory.getLogger(ExaminerServiceImpl.class);

    private final QuestionService questionService;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        int size = questionService.getSize();
        if (amount <= 0 || size < amount) {
            log.error("Amount must be positive and no more than" + size);
            throw new InvalidExamGenerateException(amount, size);
        }

        Set<Question> questions = new HashSet<>();
        while (questions.size() < amount) {
            questions.add(questionService.getRandomQuestion());
        }

        log.info("The following random questions for amount {} have been found: {}", amount, questions);
        return questions;
    }
}
