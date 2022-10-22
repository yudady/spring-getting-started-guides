package env01.hamcrest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.Matchers.emptyString;
import static org.hamcrest.Matchers.is;

@DisplayName("Tests for String")
@EnabledOnOs(OS.WINDOWS)
public class StringTests {
    @Test
    void ensureThatAnEmptryStringIsEmpty() {
        String input = "";
        assertThat(input, is(emptyString()));
    }

    @Test
    void ensureThatAStringIsEitherNullOrEmpty() {
        String input = "";
        assertThat(input, is(emptyOrNullString()));
    }
}
