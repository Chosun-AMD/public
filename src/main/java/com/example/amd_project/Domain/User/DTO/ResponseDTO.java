package com.example.amd_project.Domain.User.DTO;


import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;

import org.springframework.http.HttpStatus;

/**
 * ResponseDTO입니다.
 * @author : 황시준
 * @since : 1.0
 */
public class ResponseDTO<T> {
    private boolean success;
    @JsonIgnore
    private HttpStatus status;
    private T data;
    private List<String> errorMessages;

    public static <T> ResponseDtoBuilder<T> builder() {
        return new ResponseDtoBuilder();
    }

    @JsonGetter
    public int getStatus() {
        return this.status.value();
    }

    public boolean isSuccess() {
        return this.success;
    }

    public T getData() {
        return this.data;
    }

    public List<String> getErrorMessages() {
        return this.errorMessages;
    }

    public ResponseDTO() {
    }

    public ResponseDTO(boolean success, HttpStatus status, T data, List<String> errorMessages) {
        this.success = success;
        this.status = status;
        this.data = data;
        this.errorMessages = errorMessages;
    }

    public static class ResponseDtoBuilder<T> {
        private boolean success;
        private HttpStatus status;
        private T data;
        private List<String> errorMessages;

        ResponseDtoBuilder() {
        }

        public ResponseDtoBuilder<T> success(boolean success) {
            this.success = success;
            return this;
        }

        public ResponseDtoBuilder<T> status(HttpStatus status) {
            this.status = status;
            return this;
        }

        public ResponseDtoBuilder<T> data(T data) {
            this.data = data;
            return this;
        }

        public ResponseDtoBuilder<T> errorMessages(List<String> errorMessages) {
            this.errorMessages = errorMessages;
            return this;
        }

        public ResponseDTO<T> build() {
            return new ResponseDTO(this.success, this.status, this.data, this.errorMessages);
        }

        public String toString() {
            return "ResponseDto.ResponseDtoBuilder(success=" + this.success + ", status=" + this.status + ", data=" + this.data + ", errorMessages=" + this.errorMessages + ")";
        }
    }
}