package myenglish.session;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import myenglish.web.form.MyEnglishQuizTitleForm;
import myenglish.web.form.UserSessionForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import org.springframework.web.server.WebSession;


@Slf4j
@RestController
@AllArgsConstructor
public class SessionController {
    /**
     * ユーザー情報のセッションを 更新/取得 するクラス
     * Monoは単一の情報を返す , Fluxは複数の情報を返す
     * */

    @GetMapping("/websession")
    public Mono<UserSessionForm> getSession (WebSession session){
        // 値が何も存在しない場合に返すデフォルト値
        session.getAttributes().putIfAbsent("userId", 0);
        session.getAttributes().putIfAbsent("name", "anonymous");

        // ユーザー情報をセッションから取得する
        UserSessionForm userSessionForm = new UserSessionForm();
        userSessionForm.setUserId((Integer) session.getAttributes().get("userId"));
        userSessionForm.setName((String) session.getAttributes().get("name"));

        return Mono.just(userSessionForm);
    }

    @GetMapping("/websession/set")
    public void setSession (@RequestBody UserSessionForm userSessionForm, WebSession session){
        int userId = userSessionForm.getUserId();
        String name = userSessionForm.getName();

        // セッションにユーザー情報を格納
        session.getAttributes().put("userId", userId);
        session.getAttributes().put("name", name);
    }


}
