package com.example.springbootcrud.response;

import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.stream.Collectors;

public class ApiResponse<T> {

    private final boolean success;
    private final String[] messages; // Change from String to String[]
    private final T data;

    public ApiResponse(boolean success, String[] messages, T data) {
        this.success = success;
        this.messages = messages;
        this.data = data;
    }

    public ApiResponse(boolean success, List<String> messages, T data) {
        this(success, messages.toArray(new String[0]), data);
    }


    public ApiResponse(boolean success, String messages, T data) {
        this(success, new String[]{messages}, data);
    }

    public ApiResponse(boolean success, BindingResult result, T data) {
        this(success,
                result.getFieldErrors()
                        .stream()
                        .map(error -> error.getField() + ": " + error.getDefaultMessage())
                        .collect(Collectors.toList()),
                data);
    }

    public boolean isSuccess() {
        return success;
    }

    public String[] getMessages() { // Change the return type
        return messages;
    }

    public T getData() {
        return data;
    }
}


