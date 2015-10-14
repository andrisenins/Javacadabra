package lv.id.andrise.javacadabra.webservice.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by andris on 15.15.10.
 */

@Controller
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    public ResponseEntity<String> registrateUser(@RequestBody User user) {
        if(userService.createNewUser(user)) {
            return new ResponseEntity("Success", HttpStatus.CREATED);
        }
        return new ResponseEntity<String>(HttpStatus.I_AM_A_TEAPOT.toString(), HttpStatus.I_AM_A_TEAPOT);
    }
}
