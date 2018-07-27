package cn.ep.spring.boot.ch04.s05;

import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CustomController {

    @RequestMapping("/custom")
    public String custom(@Valid Customer customer, BindingResult result) {
        if (result.hasErrors()) {
            List<ObjectError> list = result.getAllErrors();
            for (ObjectError error : list) {
                System.out.println(error.getCode() + "---" + error.getArguments() + "---" + error.getDefaultMessage());
            }
            return "/404.jsp";
        }
        return "/";
    }

}
