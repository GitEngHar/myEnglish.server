package myenglish.session;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Slf4j
@Controller
@RequiredArgsConstructor
public class SessionController {
    /**
     * ユーザー情報のセッションを 更新/取得 するクラス
     * Monoは単一の情報を返す , Fluxは複数の情報を返す
     * */
    private String redirectUrl = "http://localhost:3000"; //リダイレクト先のURL
    private final HttpSession session;

    // セッション動作検証用 foo が keyのvalueを取得する
    @GetMapping("/websession/get/test")
    public void getSession (HttpServletRequest request){
        // redisへのセッション処理
        String returnData = (String) session.getAttribute("userId");
        int userId = Integer.parseInt(returnData);
        System.out.println("session data : " + userId);
        // フロントエンドサーバへのリダイレクト処理
    }


    // セッション動作検証用 foo(key) bar(value) をセッションへ格納する
    @GetMapping("/websession/set/test")
    public String setTestSession (HttpServletRequest request){
        session.setAttribute("foo", "bar");
        System.out.println("set-end");
        // フロントエンドサーバへのリダイレクト処理
        return "redirect:" + redirectUrl;

    }

    // 初回ログイン時にセッションへユーザーIDを登録する
    @GetMapping("/websession/set")
    public String setSession (@RequestParam("userid") String userId){
        // セッションにユーザー情報を格納
        session.setAttribute("userId", userId);
        return "redirect:" + redirectUrl;
    }


}