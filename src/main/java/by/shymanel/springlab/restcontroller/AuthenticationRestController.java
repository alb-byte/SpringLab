package by.shymanel.springlab.restcontroller;


import by.shymanel.springlab.dto.AuthenticationRequestDto;
import by.shymanel.springlab.dto.UserDto;
import by.shymanel.springlab.model.User;
import by.shymanel.springlab.security.jwt.JwtTokenProvider;
import by.shymanel.springlab.service.UserService;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/auth")
public class AuthenticationRestController {
    private static final Logger log = Logger.getLogger(AuthenticationRestController.class);
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    @Autowired
    public AuthenticationRestController(AuthenticationManager authenticationManager,
                                        JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthenticationRequestDto requestDto) throws JSONException {
        try {
            String username = requestDto.getUsername();
            log.info("User " + username + " login");

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));
            User user = userService.findByUsername(username);

            String token = jwtTokenProvider.createToken(username, user.getRoles());

            Map<Object, Object> response = new HashMap<>();
            response.put("username", username);
            response.put("token", token);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            log.error("Error login");
            JSONObject errorJson = new JSONObject();
            errorJson.put("message", "Invalid username or password");
            return new ResponseEntity(errorJson.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/registration")
    public ResponseEntity registration(@RequestBody UserDto dto) throws JSONException {
        try {
            User u = userService.register(dto.toUser());
            log.info("User " + dto.getUsername() + " is registered");
            return ResponseEntity.ok(UserDto.fromUser(u));
        } catch (Exception e) {
            log.error("Error registration");
            JSONObject errorJson = new JSONObject();
            errorJson.put("message", "Invalid username or password");
            return new ResponseEntity(errorJson.toString(), HttpStatus.BAD_REQUEST);
        }

    }
}
