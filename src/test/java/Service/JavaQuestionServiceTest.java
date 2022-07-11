package Service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import question.Question;
import repository.QuestionRepository;

import java.util.List;
import java.util.Random;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JavaQuestionServiceTest {

    @Mock
    private QuestionRepository repository;

    @InjectMocks
    private JavaQuestionService out;

    @Test
    public void test_add() {
        Question question = new Question("testQ", "testA");
        when(repository.add(question)).thenReturn(true, false);

        assertTrue(out.add(question));
        assertFalse(out.add(question));
    }

    @Test
    void test_remove() {
        String questionText = "testQ";
        String answerText = "testA";
        Question question = new Question(questionText, answerText);
        when(repository.remove(question)).thenReturn(true, false);

        assertTrue(out.remove(questionText, answerText));
        assertFalse(out.remove(questionText, answerText));

    }

    @Test
    void test_getAll() {
        Set<Question> questionSet = Set.of(
                new Question("testQ", "testA"),
                new Question("testQ1", "testA1"));
        when(repository.getAll()).thenReturn(questionSet);
        assertTrue(out.getAll().containsAll(questionSet));
    }

    @Test
    void test_getRandomQuestion() {
        Set<Question> questionSet = Set.of(
                new Question("testQ", "testA"),
                new Question("testQ1", "testA1"));
        when(repository.getAll()).thenReturn(questionSet);
        Random random = mock(Random.class, withSettings().withoutAnnotations());
        when(random.nextInt(anyInt())).thenReturn(0, 1);
        out.setRandom(random);

        assertEquals(new Question("testQ", "testA"), out.getRandomQuestion());
        assertEquals(new Question("testQ1", "testA1"), out.getRandomQuestion());
    }

}