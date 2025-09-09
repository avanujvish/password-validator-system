package passwordvalidation.rule;

import java.util.concurrent.TimeUnit;

/**
 * Class for password NotNullRule validation.
 * Rule implementation should validate not null of the password.
 * @author anuj
 * @version 1.0
 * @since 2025-09-09
 */
public class NotNullRule implements PasswordValidationRule {
    @Override
    public void validatePassword(String password) throws Exception {
        //One second delay
        TimeUnit.SECONDS.sleep(1);
        if(password==null)
            throw new IllegalAccessException("Password cannot be null");
    }

    @Override
    public String getRuleName() {
        return "NotNullRule";
    }

    @Override
    public Boolean isMandatory() {
        return true;
    }
}
