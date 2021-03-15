package org.jumia.customers.util;

import com.sun.istack.NotNull;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class RegexHelper {
    private Pattern pattern;
    private RegexHelper(Pattern pattern){
        this.pattern=pattern;
    }
    @NotNull
    public static RegexHelper pattern(String regex){
        Pattern pattern=Pattern.compile(regex);
        return new RegexHelper(pattern);
    }
    public Matcher matcher(String target){
        if (pattern==null){
            throw new IllegalArgumentException("No Pattern found. Please identify the pattern before using matcher method");
        }
        boolean ch=this.pattern.matcher(target).find();
        return this.pattern.matcher(target);
    }
    public <T> Validator validations(Predicate<T> predicate){
        return Validator.from(predicate);
    }
}
