package repository;

import org.springframework.stereotype.Repository;
import question.Question;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Repository("javaRepository")
public class JavaQuestionRepository implements QuestionRepository {
    private final Set<Question> questions;

    public JavaQuestionRepository() {
        questions = new HashSet<>();
    }

    @PostConstruct
    public void init() {
        add(new Question("Раздел памяти где храняться объекты?", "Куча"));
        add(new Question("Какой тип целочисленных значения является самым большим?", "long"));
        add(new Question("Как называется саморасширяемый массив?", "ArrayList"));
        add(new Question("Могут ли в коллекции Set ханиться одинаковые значения?", "Нет"));
        add(new Question("Год запуска Java", "1995"));
    }

    @Override
    public boolean add(Question question) {
        return questions.add(question);
    }

    @Override
    public boolean remove(Question question) {
        return questions.remove(question);
    }

    @Override
    public Collection<Question> getAll() {
        return Collections.unmodifiableCollection(questions);
    }
}
