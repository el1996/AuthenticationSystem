package AuthApp.Controller;


import AuthApp.Entity.User;
import AuthApp.Service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @RequestMapping(value = "register", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<String> register(@RequestBody User user) {

        if (!authenticationService.isValidEmail(user.getEmail())) {
            return ResponseEntity.badRequest().body("Invalid Email!");
        }
        if (!authenticationService.isValidName(user.getName())) {
            return ResponseEntity.badRequest().body("Invalid Name!");
        }
        if (!authenticationService.isValidPassword(user.getPassword())) {
            return ResponseEntity.badRequest().body("Invalid Password!");
        }
        boolean success = authenticationService.register(user.getEmail(), user.getName(), user.getPassword());

        if (success) {
            return ResponseEntity.ok("User name: " + user.getName() + " was registered");
        } else {
            return ResponseEntity.badRequest().body("The user: " + user.getEmail() + " already exists in the repository!");
        }
    }

    @RequestMapping(value = "login", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<String> login(@RequestBody User user) {

        if (!authenticationService.isValidEmail(user.getEmail())) {
            return ResponseEntity.badRequest().body("Your email address is invalid!");
        }

        String token = authenticationService.login(user.getEmail(), user.getPassword());

        if (token != null) {
            return ResponseEntity.ok("The user: " + user.getEmail() + " logged in\nToken: " + token);
        } else {
            return ResponseEntity.badRequest().body("Invalid credentials, The user: " + user.getEmail() + " can not logged in");
        }
    }
}
