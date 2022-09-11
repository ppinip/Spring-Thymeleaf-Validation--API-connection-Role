package th.ac.ku.restaurant.dto;

import lombok.Data;

// มาตรวจสอบ validate ที่ SignupDto แทนใน model
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

// สร้าง class ส่ง object ไปมาเชื่อมต่อระหว่าง server กับ cilent
@Data
public class SignupDto {

    @NotBlank
    @Size(min=4, message = "Username must have at least 4 characters")
    private String username;

    @NotBlank
    @Size(min=8, max=128, message = "Password must have at least 8 characters")
    private String password;

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    @Pattern(regexp = "^(ROLE_ADMIN|ROLE_USER)$",
             message = "Role is in an incorrect format.")
    private String role;

    @Email
    @NotBlank
    private String email;

}

