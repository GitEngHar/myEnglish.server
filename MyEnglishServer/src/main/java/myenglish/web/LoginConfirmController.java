package myenglish.web;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import myenglish.service.user.UserService;
import myenglish.web.exception.UserNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class LoginConfirmController {
    private final UserService userService;
    /**
     * ユーザーがログイン済みであるかどうかを判定する
     * @return authenticated:true ? false
     * */
    @GetMapping("login/confirm")
    public String loginConfirm(HttpSession session) {
        try {
            // ユーザー情報が取得できない場合は未認証レスポンスを返す
            int userId = userService.getUserId(session);
            userService.getUserByUserId(userId);
            return "{\"authenticated\": true}";
        } catch (UserNotFoundException e) {
            return "{\"authenticated\": false}";
        }
    }
}
