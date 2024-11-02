package myenglish.security;

import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import myenglish.service.user.UserServiceImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import io.jsonwebtoken.Jwts;

import java.io.IOException;
import java.util.Date;
import java.util.logging.Logger;

/*認証成功時にJWT-Tokenを発行する*/
@RestController
public class OAuthSuccessController {
    private final UserServiceImpl userServiceImpl;
    Logger logger = Logger.getLogger(OAuthSuccessController.class.getName());
    String JWT_SECRET = "e976d54cca0ba5f9744a6d4f8c3ee37c05081d204201740c469e6456e5f3eda3d6cd47bac68c9d05e5c2274baa623e8a977764c9aebd60ae4bd7ae4006dd0040f3ac25bf6f4a165b6f20c020529d3bece4ff60f9d444ba6bb59bfe2b47aa7eb63ee396fb005067ffb7f4bd2a1f2de66b966080a364433bda63b40e96ad15a38cf17f8dc36bb2a9d3517ef3ead06e364447755bf89a94eaecb63967669cca5b7bf317a1f075e1d97dc2348b928fd941debfd03adec6ca45fcb35e5039a4f486bdb63dd00ee2b2dccf76786f4925c59653b269308cec7c271f78ca21165e7bda8664353d8fee95dc4886f79d4fda139d9a4fe80afe9edc52356f13803e60533bf4";

    public OAuthSuccessController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }
    /**
     * 認証に成功した場合新規の場合はユーザーを作成する
     * ユーザーIDはsessionで管理
     * */
    @GetMapping("/loginsuccess")
    public void loginSuccess(Authentication authentication, HttpServletResponse response) throws IOException {
        OAuth2User oauth2User = (OAuth2User) authentication.getPrincipal();
        String email = oauth2User.getAttribute("email");
        String name = oauth2User.getAttribute("name");

        // JWTの生成
        String token = Jwts.builder()
                .setSubject(oauth2User.getName())
                .claim("email", email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))  // 有効期限を1日に設定
                .signWith(SignatureAlgorithm.HS256, JWT_SECRET)
                .compact();
        // JWTをCookieに登録する
        Cookie jwtCookie = new Cookie("jwt_token",token); // jwt-tokenをcookieへ格納しておく
        jwtCookie.setHttpOnly(true);     // JavaScriptからのアクセスを禁止
        //jwtCookie.setSecure(true);     // HTTPS通信のみで送信(検証用にオフ)
        jwtCookie.setPath("/");          // Cookieのパスを設定
        jwtCookie.setMaxAge(86400);      // Cookieの有効期限（秒）
        response.addCookie(jwtCookie);
        userServiceImpl.createUser(name,email);
        // フロントエンドサーバのトップページへリダイレクトする
        response.sendRedirect("http://localhost:3000/");
    }
}
