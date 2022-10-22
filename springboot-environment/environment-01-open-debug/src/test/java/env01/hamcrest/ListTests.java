package env01.hamcrest;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;

// tests for the list
@DisplayName("Tests for the List")
@EnabledOnOs(OS.WINDOWS)
public class ListTests {

    private List<Integer> list;

    @BeforeEach
    public void setup() {
        list = Arrays.asList(5, 2, 4);
    }


    @Test
    @DisplayName("List should have an intial size of 3")
    void ensureInitialSize() {
        assertThat(list, hasSize(3));
    }

    @Test
    @DisplayName("Check content of the array")
    void containsNumbersInAnyOrder() {
        assertThat(list, containsInAnyOrder(2, 4, 5));
    }

    @Test
    void everyItemGreaterThan1() {
        assertThat(list, everyItem(greaterThan(1)));
    }
}