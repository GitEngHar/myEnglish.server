package myenglish.web;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import myenglish.domain.MyEnglishUserEntity;
import myenglish.service.user.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
        int userId = userService.getUserId(session);
        // ユーザー情報を取得する
        MyEnglishUserEntity myEnglishUserEntity = userService.getUser(null, userId);
        // ユーザー情報が取得できない場合は未認証レスポンス
        if(myEnglishUserEntity == null) {
            return "{authenticated:false}";
        }
        return "{authenticated:true}";
    }
}
