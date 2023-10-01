package springSecurity.practice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
@Slf4j
public class LoginController {
    private final LoginServiceImpl loginService;
    @GetMapping("/loginPage")
    public String LoginCheckForm(Model model){
        //int result = loginService.LoginCheck(loginId, loginPassword);
        //model.addAttribute("result",result);
        return "login";
    }

    @GetMapping("/succesLogin")
    @ResponseBody
    public String SuccesForm(Model model){
        //int result = loginService.LoginCheck(loginId, loginPassword);
        //model.addAttribute("result",result);
        return "succesLogin";
    }
//    @PostMapping("/login")
//    public String LoginCheck(String loginId, String password, Model model){
//        int result = loginService.LoginCheck(loginId, password);
//        model.addAttribute("result",result);
//        return "succesLogin";
//    }

    @PostMapping("/join")
    @ResponseBody
    public String join(String joinId, String joinPassword){
        log.info("joinId={},joinPassword={}",joinId,joinPassword);
        loginService.join(joinId, joinPassword);

        return "succesLogin";
    }
}
