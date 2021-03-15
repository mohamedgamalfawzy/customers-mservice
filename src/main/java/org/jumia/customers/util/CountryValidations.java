package org.jumia.customers.util;

import org.jumia.customers.Constants;

import java.util.Objects;

public class CountryValidations {
    public static final IValidator <String> notNullString = Validator.from(Objects::nonNull);
    public static final IValidator<String> MATCHES= Validator.from(r->  RegexHelper.pattern(Constants.COUNTRY_CODE_PATTERN).matcher(r).matches());
    public static final IValidator<String> FIND= Validator.from(r->  RegexHelper.pattern(Constants.COUNTRY_CODE_PATTERN).matcher(r).find());

}
