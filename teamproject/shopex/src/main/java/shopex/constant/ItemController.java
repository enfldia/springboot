package shopex.constant;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ItemController {

    //adim 권한이 있는 경우에만 접근 가능한 매핑
    @GetMapping("/admin/item/new")
    public String itemForm(){
        return "/item/itemForm";
    }
}
