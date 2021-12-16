package dogService.controller;

import dogService.model.Dog;
import dogService.service.DogService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //essa classe é um controller rest
@RequestMapping("/dogs")
@RequiredArgsConstructor //lombok - ele vai criar os construtores, sem ele nao deixa criar o autowired
@Setter //lombok - ele vai criar os sets
@Data // anotação que agrupa caracteristicas de getter, setter
public class DogsController { //é a classe que vai receber a requisição http

    @Autowired private final DogService service; //spring é responsavel por instanciar esse objeto na hora certa
    //autowired ele vai instanciar a classe automaticamente sem eu precisar criar o new
/*
    @GetMapping
    public ResponseEntity<List<Dog>> getDogs() {
        List<Dog> dogs;

        try {
            dogs = service.getDogs();
        } catch (DogsServiceException ex) {
            return new ResponseEntity<>(null, null, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (DogsNotFoundException ex) {
            return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(dogs, HttpStatus.OK);
    }
    */

    //ResponseEntity - (ele retorna um objeto e um status) - manda o objeto convertido pra json e o estatus de resposta
    //retorna o corpo do objeto e o status dele
    @GetMapping
    public ResponseEntity<List<Dog>> getDogs(){ //lista de cachorros
        return new ResponseEntity<>(service.getDogs(), HttpStatus.OK); //o OK é o status 200
    }

    //requestbody o corpo vai parar dentro do objeto
    @PostMapping
    public Dog postDogs(@RequestBody Dog dto){ // o que vier no corpo do postman eu vou colocar nesse objeto
        service.add(dto); //vai ir no service e fazer tudo o que precisa pra add
        return dto;
    }

    @GetMapping("/{id}") //entre chaves é uma variavel
    public Dog getById(@PathVariable(required = true) long id){ //required=true ta dizendo que é obrigatorio que tenha um id do tipo long aqui
        return service.getDogById(id);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable(required = true) long id){
        service.delete(id);
        return "Dog deletado: " + id + "!";
    }
}

