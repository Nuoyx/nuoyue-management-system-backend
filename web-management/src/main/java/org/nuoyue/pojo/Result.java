package org.nuoyue.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {

    private Integer code; // 1 success, 0 error
    private String msg; // success, error message
    private Object data; // data to return

    public static Result success(){
        Result result = new Result();
        result.setMsg("success");
        result.setCode(1);
        return result;
    }

    public static Result success(Object object){
        Result result = new Result();
        result.setMsg("success");
        result.setCode(1);
        result.setData(object);
        return result;
    }

    public static Result error(String msg){
        Result result = new Result();
        result.setMsg(msg);
        result.setCode(0);
        return result;
    }


}
