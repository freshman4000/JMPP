package com.freshman4000.validators;

import com.freshman4000.utility.CustomException;

public interface UDValidator<T> {
    boolean validate(T item);
}
