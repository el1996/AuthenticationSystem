package AuthApp.Controller;

import AuthApp.Entity.RequestEmailWrapper;
import AuthApp.Entity.User;
import AuthApp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    AuthenticationController authenticationController;

    @RequestMapping(value = "updateUserName", method = RequestMethod.PATCH, consumes = "application/json")
    public ResponseEntity<String> updateUserName(@RequestBody User user,
                                                 @RequestHeader String token) {

        String response = userService.updateUserName(user.getEmail(), user.getName(), token);
        if (response == null) {
            return ResponseEntity.ok("User name was updated to: " + user.getName());
        } else {
            return ResponseEntity.badRequest().body(response);
        }
    }

    @RequestMapping(value = "updateUserEmail", method = RequestMethod.PATCH, consumes = "application/json")
    public ResponseEntity<String> updateUserEmail(@RequestBody RequestEmailWrapper requestEmailWrapper,
                                                  @RequestHeader String token) {

        String response = userService.updateUserEmail(requestEmailWrapper.getEmail(), requestEmailWrapper.getNewEmail(), token);
        if (response == null) {
            return ResponseEntity.ok("User email was updated to: " + requestEmailWrapper.getNewEmail());
        } else {
            return ResponseEntity.badRequest().body(response);
        }
    }

    @RequestMapping(value = "updateUserPassword", method = RequestMethod.PATCH, consumes = "application/json")
    public ResponseEntity<String> updateUserPassword(@RequestBody User user,
                                                     @RequestHeader String token) {

        String response = userService.updateUserPassword(user.getEmail(), user.getPassword(), token);
        if (response == null) {
            return ResponseEntity.ok("User password was updated to: " + user.getPassword());
        } else {
            return ResponseEntity.badRequest().body(response);
        }
    }

    @RequestMapping(value = "deleteUser", method = RequestMethod.DELETE, consumes = "application/json")
    public ResponseEntity<String> deleteUser(@RequestParam String email,
                                             @RequestHeader String token) {

        String response = userService.deleteUser(email, token);
        if (response == null) {
            return ResponseEntity.ok("The user " + email + " was deleted");
        } else {
            return ResponseEntity.badRequest().body(response);
        }
    }
}
