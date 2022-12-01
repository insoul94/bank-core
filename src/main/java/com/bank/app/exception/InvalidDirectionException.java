package com.bank.app.exception;

import com.bank.app.constant.Direction;

public class InvalidDirectionException extends UserException {

    public InvalidDirectionException() {
        this(String.format("Invalid direction. Allowed: [%s, %s].", Direction.IN, Direction.OUT));
    }

    public InvalidDirectionException(String message) {
        super(message);
    }
}
