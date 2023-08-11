package io.elementor.infra;

import java.util.ArrayList;
import java.util.List;

public class Assertions {
    public static void softAssert(Runnable... assertions){
        List<AssertionError> errors = new ArrayList<>();
        for (Runnable assertion :
                assertions) {
            try{
                assertion.run();
            }catch(AssertionError e){
                errors.add(e);
            }
        }
        validate(errors);

    }

    private static void validate(List<AssertionError> errors){
        for (AssertionError error :
                errors) {
            error.printStackTrace();
        }
        if(errors.size()>0){
            throw errors.get(0);
        }
    }
}
