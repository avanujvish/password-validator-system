package passwordvalidation.rule;

import java.util.concurrent.TimeUnit;

/**
 * Class for password MinLengthRule validation.
 * Rule implementation should validate password having the Minimum length based on Provided length in rules.
 * @author anuj
 * @version 1.0
 * @since 2025-09-09
 */
public class MinLengthRule implements PasswordValidationRule {

    private final int minLength;

    public MinLengthRule(int minLength) {
        this.minLength = minLength;
    }

    @Override
    public void validatePassword(String password) throws Exception {

        //One second delay
        TimeUnit.SECONDS.sleep(1);

        if(password.length()<=minLength)
            throw new IllegalAccessException("Password must be longer then "+minLength+ " characters.");
    }

    @Override
    public String getRuleName() {
        return "MinLengthRule";
    }

    @Override
    public Boolean isMandatory() {
        return false;
    }
}
