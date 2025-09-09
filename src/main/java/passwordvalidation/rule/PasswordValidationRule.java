package passwordvalidation.rule;

/**
 * Interface for password validation rule.
 * Each rule implementation should validate a specific aspect of the password.
 * @author anuj
 * @version 1.0
 * @since 2025-09-08
 */
public interface PasswordValidationRule {
    void validatePassword(String password) throws Exception;
    String getRuleName();
    Boolean isMandatory();
}
