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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
        Set<Question> questions = Set.of(
                new Question("testQ", "testA"),
                new Question("testQ1", "testA1")
        );
        when(repository.getAll()).thenReturn(questions);

        assertEquals(out.getAll().size(), questions.size());
        assertTrue(out.getAll().containsAll(questions));
    }

    @Test
    void test_getRandomQuestion() {
        when(repository.getAll()).thenReturn(List.of(
                new Question("testQ", "testA"),
                new Question("testQ1", "testA1"),
                new Question("testQ2", "testA2")
        ));

        Random randomMock = mock(Random.class);
        when(randomMock.nextInt(anyInt())).thenReturn(0, 2);
        out.setRandom(randomMock);

        assertEquals(new Question("testQ", "testA"), out.getRandomQuestion());
        assertEquals(new Question("testQ2", "testA2"), out.getRandomQuestion());

    }

}