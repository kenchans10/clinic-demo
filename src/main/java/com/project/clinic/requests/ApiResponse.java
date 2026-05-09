package com.project.clinic.requests;

public class ApiResponse<T> {

    private T data;
    private long executionTimeMs;

    public ApiResponse(long executionTimeMs, T data) {
        this.executionTimeMs = executionTimeMs;
        this.data = data;

    }

    public T getData() {
        return data;
    }

    public long getExecutionTimeMs() {
        return executionTimeMs;
    }
}