package dogService.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
@Slf4j //anotação lambok pra geracao de campo de log(mensagens de erro)
public class DogsServiceErrorAdvice { //é a classe que vai tratar as exceções

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<String> handleRunTimeException(RuntimeException e){
        return error(INTERNAL_SERVER_ERROR, e); //status e exceção
    }

    @ExceptionHandler({DogsNotFoundException.class})
    public ResponseEntity<String> handleNotFoundException(DogsNotFoundException e){
        return error(NOT_FOUND, e);
    }

    @ExceptionHandler({DogsServiceException.class})
    public ResponseEntity<String> handleDogsServiceException(DogsServiceException e){
        return error(INTERNAL_SERVER_ERROR, e);
    }

    private ResponseEntity<String> error(HttpStatus status, Exception e){
        log.error("Exception: ", e);
        return ResponseEntity.status(status).body(e.getMessage());
    }
}

/*
Essa é uma outra forma de fazer mais simples,
utilizando as classes DogsNotFoundException e DogsServiceException:

@ControllerAdvice
public class DogsServiceErrorAdvice {

    @ResponseStatus(HttpStatus.NOT_FOUND)

    @ExceptionHandler({DogsNotFoundException.class})
    public void handle(DogsNotFoundException e) {}

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({DogsServiceException.class, SQLException.class, NullPointerException.class})
    public void handle() {}

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({DogsServiceValidationException.class})
    public void handle(DogsServiceValidationException e) {}
}
 */
