package com.project.clinic.requests;

public class ApiResponse<T> {

    private T data;
    private Long records;
    private long executionTimeMs;

    public ApiResponse(long executionTimeMs, T data, Long records) {
        this.executionTimeMs = executionTimeMs;
        this.data = data;
        this.records = records;

    }

    public T getData() {
        return data;
    }

    public Long getRecords() {
        return records;
    }

    public long getExecutionTimeMs() {
        return executionTimeMs;
    }
}