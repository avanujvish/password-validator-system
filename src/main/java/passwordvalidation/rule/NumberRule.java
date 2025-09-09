package passwordvalidation.rule;

import java.util.concurrent.TimeUnit;

/**
 * Class for password NumberRule validation.
 * Rule implementation should validate password having the Number.
 * @author anuj
 * @version 1.0
 * @since 2025-09-09
 */
public class NumberRule implements PasswordValidationRule {
    @Override
    public void validatePassword(String password) throws Exception {
        TimeUnit.SECONDS.sleep(1);
        if(!password.chars().anyMatch(Character::isDigit)){
            throw new IllegalAccessException("Password must  contain at least one digit");

        }
    }

    @Override
    public String getRuleName() {
        return "NotNullRule";
    }

    @Override
    public Boolean isMandatory() {
        return false;
    }
}
