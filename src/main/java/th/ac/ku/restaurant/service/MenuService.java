package th.ac.ku.restaurant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import th.ac.ku.restaurant.dto.MenuDto;
import th.ac.ku.restaurant.security.JwtAccessTokenService;

import java.util.Arrays;
import java.util.List;

@Service
public class MenuService {

    @Autowired
    private RestTemplate restTemplate; // เป็ยตัวส่งไปยัง menu API

    @Autowired
    private JwtAccessTokenService tokenService; // ขอ token

    public List<MenuDto> getMenus() {

        String token = tokenService.requestAccessToken(); // ได้ token มาเก็บไว้ใน token

        HttpHeaders headers = new HttpHeaders(); // สร้าง header ส่งไปให้ server
        headers.add("authorization", "Bearer " + token); // key:authorization -> value:bearer+token
        HttpEntity entity = new HttpEntity(headers);

        String url = "http://localhost:8090/menu";

        ResponseEntity<MenuDto[]> response =
                restTemplate.exchange(url, HttpMethod.GET,
                        entity, MenuDto[].class);

        MenuDto[] menus = response.getBody(); //ได้ menu array
        return Arrays.asList(menus);    // แปลงเป็น list คืนกลับไปยัง controller
    }

    public MenuDto addMenu(MenuDto menu) {

        String token = tokenService.requestAccessToken();

        HttpHeaders headers = new HttpHeaders();
        headers.add("authorization", "Bearer " + token);
        headers.add("Content-Type", MediaType.APPLICATION_JSON.toString());
        HttpEntity entity = new HttpEntity(menu,headers);

        String url = "http://localhost:8090/menu";

        ResponseEntity<MenuDto> response =
                restTemplate.exchange(url, HttpMethod.POST,
                        entity, MenuDto.class);

        return response.getBody();
    }

}
