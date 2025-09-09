package passwordvalidation.rule;

import java.util.concurrent.TimeUnit;


/**
 * Class for password LowerCaseRule validation.
 * Rule implementation should validate password having the lowercase.
 * @author anuj
 * @version 1.0
 * @since 2025-09-08
 */
public class LowerCaseRule implements PasswordValidationRule {
    @Override
    public void validatePassword(String password) throws Exception {
        TimeUnit.SECONDS.sleep(1);
        if(!password.chars().anyMatch(Character::isLowerCase)){
            throw new IllegalAccessException("Password must  contain at least one lower letter.");

        }
    }

    @Override
    public String getRuleName() {
        return "LowerCaseRule";
    }

    @Override
    public Boolean isMandatory() {
        return true;
    }
}
