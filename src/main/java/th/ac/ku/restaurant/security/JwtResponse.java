package th.ac.ku.restaurant.security;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class JwtResponse { // class สำหรับรับ object ที่เป็น token (json)

    /*
    JsonProperty จะทำการ map attribute ของ json กับ instance variable ของ class นี้
    จะทำการ map ชื่อ field ของ json ที่ไม่ตรงกับ spring boot
     */

    @JsonProperty("access_token") // ชื่อ field json
    private String accessToken;   // ชื่อ field spring boot

    @JsonProperty("expires_in")
    private int expiresIn;

    @JsonProperty("token_type")
    private String tokenType;
}
