package exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidExamGenerateException extends IllegalArgumentException {
    public InvalidExamGenerateException(int amount, int currentSize) {
        super(String.format("Failed to generate %d questions, the current size %d", amount, currentSize));
    }

}
