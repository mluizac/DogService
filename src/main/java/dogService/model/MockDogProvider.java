package dogService.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component // essa é uma classe que eu vou utilizar durante a execução, o spring vai chamar ela
public class MockDogProvider {

    private List<Dog> mockDogStore;

    public MockDogProvider(){
        mockDogStore = new ArrayList<>();
        mockDogStore.add(new Dog(1, "Benji", 10));
        mockDogStore.add(new Dog(2, "Baxter", 9));
        mockDogStore.add(new Dog(3, "Brinkley", 8));
        mockDogStore.add(new Dog(4, "Daisy", 10));
        mockDogStore.add(new Dog(5, "Cujo", 12));
    }

    public List<Dog> getDogs(){
        return mockDogStore;
    }

    public Dog findDogById(long id){
        if (mockDogStore.stream().filter(dog -> dog.getId() == id).findFirst().isPresent()){
            return mockDogStore.stream().filter(dog -> dog.getId() == id).findFirst().get();
        }
        return null;
    }

    public void add (Dog dto){
        mockDogStore.add(dto);
    }

    public void delete (long id){
        if (mockDogStore.stream().anyMatch(dog -> dog.getId() == id)){
            Dog removerDog = mockDogStore.stream().filter(dog -> dog.getId() == id).findFirst().get();
            mockDogStore.remove(removerDog);
        }
    }
}
