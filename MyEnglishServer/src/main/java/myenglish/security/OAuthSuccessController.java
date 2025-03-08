package myenglish.security;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import myenglish.service.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import io.jsonwebtoken.Jwts;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.security.Key;
import java.util.Date;
import java.util.logging.Logger;

/*認証成功時にJWT-Tokenを発行する*/
@RestController
public class OAuthSuccessController {
    private final UserServiceImpl userServiceImpl;
    @Value("${jwt.secret-key}")
    String jwtSecret;
    public OAuthSuccessController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }
    /**
     * 認証に成功した場合新規の場合はユーザーを作成しjwt-tokenを発行する
     * ユーザーIDはsessionに保持する
     * */
    @GetMapping("/loginsuccess")
    public void loginSuccess(Authentication authentication, HttpServletResponse response) throws IOException {
        OAuth2User oauth2User = (OAuth2User) authentication.getPrincipal();
        String email = oauth2User.getAttribute("email");
        String name = oauth2User.getAttribute("name");

        // 署名の値に生のパスワード文字列が渡される悲劇が相次いだので変更した
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
        // 鍵を暗号化に適用できる型に変換する
        Key key = Keys.hmacShaKeyFor(keyBytes);
        // JWTの生成 null値はプロパティから削除される
        String token = Jwts.builder()
                .subject(oauth2User.getName()) // user識別子
                .issuedAt(new Date()) // token発行時間
                .expiration(new Date(System.currentTimeMillis() + 86400000))  // 有効期限を1日に設定
                .signWith(key) // 署名の適用
                .compact();

        // JWTをCookieに登録する
        Cookie jwtCookie = new Cookie("jwt_token",token); // jwt-tokenをcookieへ格納しておく
        jwtCookie.setHttpOnly(true);     // JavaScriptからのアクセスを禁止
        //jwtCookie.setSecure(true);     // HTTPS通信のみで送信(検証用にオフ)
        jwtCookie.setPath("/");          // Cookieのパスを設定
        jwtCookie.setMaxAge(86400);      // Cookieの有効期限（秒）
        response.addCookie(jwtCookie);

        // ユーザーの作成
        int userId = userServiceImpl.createUser(name,email);

        // セッションにユーザー情報を登録しフロントエンドサーバへリダイレクトさせる
        String baseUrl = "/websession/set";
        URI uri = UriComponentsBuilder
                .fromUriString(baseUrl)
                .queryParam("userid", "{userid}")
                .build(userId);
        response.sendRedirect(uri.toString());
    }
}
