package myenglish.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

/*認証成功時にJWT-Tokenを発行する*/
@RestController
public class OAuthSuccessController {
    Logger logger = Logger.getLogger(OAuthSuccessController.class.getName());
    @GetMapping("/loginsuccess")
    public void loginSuccess(Authentication authentication) {
        OAuth2User oauth2User = (OAuth2User) authentication.getPrincipal();
        String email = oauth2User.getAttribute("email");

    }
}
