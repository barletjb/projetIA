package fr.eni.masia.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.net.URI;
import java.util.stream.Collectors;

public class GlobalExecptionHandler {

    // Erreur générique
    @ExceptionHandler(Exception.class)
    public ProblemDetail handleGenericException(Exception ex) {
        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        problem.setTitle("Erreur interne");
        problem.setDetail(ex.getMessage());
        problem.setType(URI.create("errors/internal"));
        return problem;
    }

    // Erreurs validation
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidationException(MethodArgumentNotValidException ex) {
        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.NOT_ACCEPTABLE);
        problem.setTitle("Erreur de validation");
        problem.setDetail(ex.getFieldErrors().stream()
                .map(e -> e.getField() + " : " + e.getDefaultMessage())
                .collect(Collectors.joining(" | ")));
        problem.setType(URI.create("errors/validation"));
        return problem;
    }

}
