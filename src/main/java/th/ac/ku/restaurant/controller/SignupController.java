package th.ac.ku.restaurant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import th.ac.ku.restaurant.dto.SignupDto;
import th.ac.ku.restaurant.service.SignupService;

import javax.validation.Valid;

@Controller
public class SignupController {

    @Autowired
    private SignupService signupService;

    @GetMapping("/signup")
    public String getSignupPage(SignupDto user) {
        return "signup"; // return หน้าฟอร์ม signup.html
    }

    @PostMapping("/signup")                      // เป็น result ของการ validation เติมหลัง @Valid object ทันที
    public String signupUser(@Valid SignupDto user, BindingResult result,
                             Model model) {

        if (result.hasErrors()) // ถ้ามี error จะreturn หน้า signup ใหม่พร้อมกับส่ง error
            return "signup";

        if (signupService.isUsernameAvailable(user.getUsername())) {
            signupService.createUser(user);
            model.addAttribute("signupSuccess", true);
        } else {
            model.addAttribute("signupError", "Username not available");
        }
        model.addAttribute("signupDto", new SignupDto()); //return หน้า signup ใหม่ต้องส่ง SingupDto ไปด้วย
        return "signup";
    }
}
