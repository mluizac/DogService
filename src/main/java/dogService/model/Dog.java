package dogService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dog {

    @NonNull
    private long id;
    private String nome;
    private int idade;
}

