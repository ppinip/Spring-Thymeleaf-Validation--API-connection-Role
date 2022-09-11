package th.ac.ku.restaurant.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class JwtAccessTokenService { // เก็บ token จาก auth0

    // value จาก config
    @Value("${auth0.audience}")
    private String audience;

    @Value("${auth0.clientId}")
    private String clientId;

    @Value("${auth0.clientSecret}")
    private String clientSecret;

    @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
    private String issuer;

    @Autowired
    private RestTemplate restTemplate; // เรียก GET & POST

    private String token = null; // เก็บ token

    public String requestAccessToken() {

        if (token != null)
            return token;

        // build request body form-urlencoded key->value
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type",
                MediaType.APPLICATION_FORM_URLENCODED.toString());

        MultiValueMap<String, String> requestBody =
                new LinkedMultiValueMap<>();
        requestBody.add("grant_type", "client_credentials");
        requestBody.add("client_id", clientId);
        requestBody.add("client_secret", clientSecret);
        requestBody.add("audience", audience);

        HttpEntity entity = new HttpEntity(requestBody, headers);
        //ใช้ method exchange POST request body ไปยัง auth0 แล้วรับ object token กลับมาเก็บที่ JwtResponse
        ResponseEntity<JwtResponse> response =
                restTemplate.exchange(issuer + "oauth/token",
                                      HttpMethod.POST,
                                      entity, JwtResponse.class);

        JwtResponse jwtResponse = response.getBody(); // get object ออกมา
        token = jwtResponse.getAccessToken(); //เอา token ที่ได้มาเก็บไว้ใน field token

        return token;
    }
}

