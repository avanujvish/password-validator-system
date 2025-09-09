package passwordvalidation.validator;

import passwordvalidation.rule.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Main Password validator component that orchestrates
 * multiple validation rules.
 * @author anuj
 * @version 1.0
 * @since 2025-09-09
 */
public class PasswordValidator {

    //It can be revised based on the rule run in parallel
    public static final int N_THREADS = 3;
    public static final int MIN_PASS_LENGTH = 8;
    private final List<PasswordValidationRule> rules;
    private final ExecutorService executorService;

    public PasswordValidator() {
        this.rules = new ArrayList<>();
        this.executorService = Executors.newFixedThreadPool(N_THREADS);
        initializeRules();
    }


    private void initializeRules() {
        rules.add(new NotNullRule());
        rules.add(new MinLengthRule(MIN_PASS_LENGTH));
        rules.add(new UpperCaseRule());
        rules.add(new LowerCaseRule());
        rules.add(new NumberRule());
    }



    public Boolean validatePassword(String password) {
        Boolean result = false;
        List<PasswordValidationRule> mandatoryRules =
                rules.stream().filter(PasswordValidationRule::isMandatory)
                        .collect(Collectors.toList());

        List<PasswordValidationRule> otherRules =
                rules.stream().filter(r -> !r.isMandatory())
                        .collect(Collectors.toList());


        // parallel streaming

        List<CompletableFuture<Boolean>> mandatoryFuture =
                mandatoryRules.stream().map(rule ->
                        CompletableFuture.supplyAsync(() -> {
                            try {
                                rule.validatePassword(password);
                                return true;
                            } catch (Exception e) {
                                return false;
                            }
                        }, executorService)).collect(Collectors.toList());

        CompletableFuture<Boolean> allMandatory = CompletableFuture.allOf(
                mandatoryFuture.toArray(new CompletableFuture[0])
        ).thenApply(m -> mandatoryFuture.stream().
                allMatch(CompletableFuture::join));

        try {
             if(!allMandatory.get())
                 return false;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        List<CompletableFuture<Boolean>> otherFuture =
                otherRules.stream().map(rule ->
                        CompletableFuture.supplyAsync(() -> {
                            try {
                                rule.validatePassword(password);
                                return true;
                            } catch (Exception e) {
                                return false;
                            }
                        }, executorService)).collect(Collectors.toList());

        CompletableFuture<Integer> otherCount = CompletableFuture.allOf(
                otherFuture.toArray(new CompletableFuture[0])
        ).thenApply(m -> (int) otherFuture.stream().
                mapToInt(f -> f.join() ? 1 : 0).sum());

        try {
            CompletableFuture<Boolean> future= allMandatory.thenCombine(otherCount,(m,o)->m && o>=1);
            result= future.get();
            shutdown();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }


    public void shutdown(){
        executorService.shutdown();
    }

}
