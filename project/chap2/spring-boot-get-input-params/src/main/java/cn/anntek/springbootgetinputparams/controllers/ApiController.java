package cn.anntek.springbootgetinputparams.controllers;

import cn.anntek.springbootgetinputparams.models.Book;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@ResponseBody
public class ApiController {

    //采集所有请求头参数
    @GetMapping("/headers")
    public String getHeaderParams(@RequestHeader Map<String, String> headers){
        return "请求头参数:" +headers;
    }

    //采用指定请求头参数（token）
    @GetMapping("/header")
    public String getHeaderParam(@RequestHeader("token")String token){
        return "Token:"+token;
    }

    // 使用HttpServletRequest对象获取请求头
    @GetMapping("/request")
    public String getHeaderParams(HttpServletRequest request){
        return "Token:"+request.getHeader("token");
    }

    @GetMapping("/books")
    public Object getQueryParam(@RequestParam(defaultValue = "") String isbn,
                                @RequestParam(defaultValue = "") String name){
        return new Book(isbn,name);
    }

    @PostMapping("/books/isbn/{isbn}/name/{name}")
    public Object getPathVariables(@PathVariable String isbn, @PathVariable String name){
        return new Book(isbn, name);
    }
    //采集表单参数
    @PostMapping("/books/form")
    public Object getFormParms(Book book){
        return book;
    }
    //采集JSON参数
    @PutMapping("/books/json")
    public Object getJsonParms(@RequestBody Book book){
        return book;

    }
}
