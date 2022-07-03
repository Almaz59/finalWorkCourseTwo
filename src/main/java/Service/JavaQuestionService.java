package Service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import question.Question;
import repository.QuestionRepository;

import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Service
public class JavaQuestionService implements QuestionService{

    private final QuestionRepository javaQuestionRepository;
    private Random random = new Random();

    public JavaQuestionService(@Qualifier("javaRepository") QuestionRepository javaQuestionRepository) {
        this.javaQuestionRepository = javaQuestionRepository;
    }

    @Override
    public boolean add(String question, String answer) {
        return add(new Question(question, answer));
    }

    @Override
    public boolean add(Question question) {
        return javaQuestionRepository.add(question);
    }

    @Override
    public boolean remove(String question, String answer) {
        return javaQuestionRepository.remove(new Question(question, answer));
    }

    @Override
    public Collection<Question> getAll() {
        return Set.copyOf(javaQuestionRepository.getAll());
    }

    @Override
    public Question getRandomQuestion() {
        List<Question> questions = List.copyOf(javaQuestionRepository.getAll());
        return questions.get(random.nextInt(questions.size()));
    }

    @Override
    public int getSize() {
        return javaQuestionRepository.getAll().size();
    }

    public void setRandom(Random random) {
        this.random = random;
    }
}
