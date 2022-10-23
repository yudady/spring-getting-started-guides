package data04.redis03;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

@EnabledOnOs(OS.WINDOWS)
@TestMethodOrder(MethodOrderer.MethodName.class)
class JacksonTest {

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void test01_JavaObject2JSON() throws Exception {
        Car car = new Car("yellow", 1);
        String carAsString = objectMapper.writeValueAsString(car);
        System.out.println("[LOG] Car = " + carAsString);
    }

    @Test
    void test02_JSON2JavaObject() throws Exception {
        String json = """
            { "name" : "Black", "age" : 1 }
            """;
        Car car = objectMapper.readValue(json, Car.class);
        System.out.println("[LOG] Car = " + car);
    }

    @Test
    void test03_JSON2JsonNode() throws Exception {
        String json = """
            { "name" : "Black", "age" : "FIAT" }
            """;
        JsonNode jsonNode = objectMapper.readTree(json);
        String name = jsonNode.get("name").asText();
        System.out.println("[LOG] Output: name -> Black = " + name);


    }

    @Test
    void test04_JSON2List() throws Exception {
        // Creating a Java List From a JSON Array String
        String jsonCarArray = """
            [
                { "name" : "Black", "age" : 2 },
                { "name" : "Red", "age" : 3 }
            ]
            """;

        List<Car> cars1 = objectMapper.readValue(jsonCarArray, new TypeReference<List<Car>>() {
        });
        System.out.println("cars1 = " + cars1);
        List<Car> cars2 = objectMapper.readValue(jsonCarArray, List.class);
        System.out.println("cars2 = " + cars2);
    }

    @Test
    void test05_ObjectMapper() {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS, false);
    }

    public static class Car {


        public String name;
        public Integer age;

        public Date date; // TODO

        @JsonProperty("local_Date_Time")
        public LocalDateTime localDateTime; // TODO

        public Car() {
        }

        public Car(String name, Integer age) {
            this.name = name;
            this.age = age;
        }

    }
}
