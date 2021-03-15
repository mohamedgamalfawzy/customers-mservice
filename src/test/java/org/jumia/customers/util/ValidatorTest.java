package org.jumia.customers.util;

import org.jumia.customers.Constants;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ValidatorTest {
    @Test
    public void testValidationGenerator(){
        IValidator <String> notNullString = Validator.from(Objects::nonNull);
        assertNotNull(notNullString);
        assertFalse(notNullString.validate(null));
        assertTrue(notNullString.validate(""));

        IValidator <Integer> greaterThanZero = Validator.from(s -> s > 0 );
        assertNotNull(greaterThanZero,"greaterThanZero validation creator failed");
        assertFalse(greaterThanZero.validate(-1));
        assertTrue(greaterThanZero.validate(1));
    }

    @Test
    public  void testMatchRegexValidations(){
        IValidator<String> matches= Validator.from(r->  RegexHelper.pattern(Constants.COUNTRY_CODE_PATTERN).matcher(r).matches());

        assertNotNull(matches);
        assertTrue(matches.validate("(+213)"));
        assertFalse(matches.validate("(+"));
        assertFalse(matches.validate(""));
        assertTrue(matches.validate("(+1)"));
        assertTrue(matches.validate("(+2-234)"));
        assertTrue(matches.validate("(+212)"));
        assertTrue(matches.validate("(212)"));
    }
}
