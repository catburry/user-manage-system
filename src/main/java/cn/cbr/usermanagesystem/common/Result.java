package cn.cbr.usermanagesystem.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {
    private Integer code;
    private String msg;
    private T data;

    //成功无数据
    public static <T> Result<T> success(){
        Result<T> result=new Result<>();
        result.setCode(200);
        result.setMsg("操作成功");
        return result;
    }
    //成功带数据
    public static <T> Result<T> success(T data){
        Result<T> result =new Result<>();
        result.setCode(200);
        result.setMsg("操作成功");
        result.setData(data);
        return result;
    }
    //成功自定义提示信息带数据
    public static <T> Result<T> success(String msg,T data){
        Result<T> result=new Result<>();
        result.setCode(200);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }
    //失败自定义状态码加自定义提示信息
    public static <T> Result<T> error(Integer code,String msg){
        Result<T> result=new Result<>();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
    //失败传提示信息
    public static <T> Result<T> error(String msg){
        return error(500,msg);
    }
}
