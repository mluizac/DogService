package dogService.service;

import dogService.model.Dog;
import dogService.model.MockDogProvider;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Setter
public class DogService {

    @Autowired
    private final MockDogProvider mockDogProvider;

    public void add (Dog dto){
        mockDogProvider.add(dto);
    }

    public void delete (long id){
        mockDogProvider.delete(id);
    }

    public List<Dog> getDogs(){
        return mockDogProvider.getDogs();
    }

    /*public List<Dog> getDogs() {
    throw new DogsNotFoundException("No Dog Found Here..");
    }
    */

    public Dog getDogById(long id){
        return mockDogProvider.findDogById(id);
    }
}
