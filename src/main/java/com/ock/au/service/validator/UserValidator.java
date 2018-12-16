package com.ock.au.service.validator;

import com.ock.au.entity.User;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;

@Service
@Getter
public class UserValidator implements Validator {
    private Errors errors;

    @Autowired
    public SpringValidatorAdapter validator;


    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "id.required", "Id required");

        User user = (User) o;

        validator.validate(user, errors);

        if(user.getId() <= 0){
            errors.rejectValue("id", "negative","id can't be negative");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.required");

        this.errors = errors;
    }
}
