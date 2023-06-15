package com.nku.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Result<T> {
    private Integer code;
    private String message;
    private T data;

    public Result(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> Result<T> success(){
        return new Result<>(20000, "success", null);
    }

    public static <T> Result<T> success(T data){
        return new Result<>(20000, "success", data);
    }

    public static <T> Result<T> success(String message, T data){
        return new Result<>(20000, message, data);
    }

    public static <T> Result<T> success(String message){
        return new Result<>(20000, message, null);
    }

    public static <T> Result<T> fail(int code, String message){
        return new Result<>(code, message, null);
    }

}
