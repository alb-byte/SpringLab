package by.shymanel.springlab.restcontroller;


import by.shymanel.springlab.dto.UserDto;
import by.shymanel.springlab.model.User;
import by.shymanel.springlab.service.UserService;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/users")
public class UserRestController {
    private static final Logger log = Logger.getLogger(AuthenticationRestController.class);
    private final UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable(name = "id") Long id) throws JSONException {
        try {
            User user = userService.findById(id);

            if (user == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            UserDto result = UserDto.fromUser(user);
            log.info("user " + result.getUsername() + " successful find");
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        catch (Exception e) {
            log.error("Error getUser");
            JSONObject errorJson = new JSONObject();
            errorJson.put("message", "Invalid id");
            return new ResponseEntity(errorJson.toString(), HttpStatus.BAD_REQUEST);
        }
    }

}

