package passwordvalidation.rule;

import java.util.concurrent.TimeUnit;

/**
 * Class for password UpperCaseRule validation.
 * Rule implementation should validate password having the uppercase.
 * @author anuj
 * @version 1.0
 * @since 2025-09-09
 */
public class UpperCaseRule implements PasswordValidationRule {
    @Override
    public void validatePassword(String password) throws Exception {
        TimeUnit.SECONDS.sleep(1);
        if(!password.chars().anyMatch(Character::isUpperCase)){
            throw new IllegalAccessException("Password must  contain at least one upercase letter.");

        }
    }

    @Override
    public String getRuleName() {
        return "UpperCaseRule";
    }

    @Override
    public Boolean isMandatory() {
        return false;
    }
}
