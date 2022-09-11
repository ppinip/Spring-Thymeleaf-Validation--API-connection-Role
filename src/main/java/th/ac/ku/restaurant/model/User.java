package th.ac.ku.restaurant.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Data                 // เพิ่ม getters และ setters สำหรับทุก fields ที่ไม่เป็น final ทำให้โค้ดสั้นลง
@NoArgsConstructor    // สร้าง constructor ไม่รับ args
@Entity               // เป็น class ที่ save ส่ง database
public class User {

    @Id
    @GeneratedValue
    private UUID id;

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String role;

}