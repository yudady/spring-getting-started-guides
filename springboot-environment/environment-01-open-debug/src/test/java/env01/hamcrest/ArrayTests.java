package env01.hamcrest;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.arrayContaining;
import static org.hamcrest.Matchers.arrayWithSize;

@DisplayName("Tests for the array")
@EnabledOnOs(OS.WINDOWS)
public class ArrayTests {
    private Integer[] ints;

    @BeforeEach
    public void setup() {
        ints = new Integer[]{7, 5, 12, 16};

    }

    @Test
    void arrayHasSizeOf4() {
        assertThat(ints, arrayWithSize(4));
    }

    @Test
    void arrayContainsNumbersInGivenOrder() {
        assertThat(ints, arrayContaining(7, 5, 12, 16));
    }
}