package Service;

import question.Question;

import java.util.Collection;

public interface QuestionService {
    boolean add(String question, String answer);

    boolean add(Question question);

    boolean remove(String question, String answer);

    Collection<Question> getAll();

    Question getRandomQuestion();

    int getSize();

}
