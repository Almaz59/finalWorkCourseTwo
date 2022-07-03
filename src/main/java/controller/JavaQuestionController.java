package controller;

import Service.QuestionService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import question.Question;

import java.util.Collection;

@RestController
@RequestMapping("/java")
public class JavaQuestionController {
    private final QuestionService javaQuestionService;


    public JavaQuestionController(@Qualifier("javaService") QuestionService javaQuestionService) {
        this.javaQuestionService = javaQuestionService;
    }

    @GetMapping("add")
    public String add(@RequestParam String question,
                    @RequestParam String answer) {
       return "Вопрос был добавлен: " + javaQuestionService.add(question, answer);
    }

    @GetMapping("remove")
    public String remove(@RequestParam String question,
                    @RequestParam String answer) {
        return "Вопрос был удален: " + javaQuestionService.remove(question, answer);
    }

    @GetMapping
    public Collection<Question> getAll() {
        return javaQuestionService.getAll();
    }
}
