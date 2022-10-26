package data05.redis04.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.beans.BeanProperty;
import java.io.Serializable;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class User implements Serializable{

    @NotNull
    @NotBlank
    @JsonProperty("name")
    public String name;

    @NotNull
    @Min(0)
    @JsonProperty("age")
    public Integer age;


    @Override
    public String toString() {
        return "User{" +
            "name='" + name + '\'' +
            ", age=" + age +
            '}';
    }
}
