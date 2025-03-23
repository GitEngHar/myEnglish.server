package myenglish.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class OAuth2LoginSecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(withDefaults())
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/login/**").permitAll() // login配下は認証不要
                        .anyRequest().authenticated()
                )
                .csrf(csrf -> csrf.disable()) // CSRFを無効化
                .oauth2Login(withDefaults())  // 認証設定にデフォルトの設定を利用する (applicatin.yml)
                .oauth2Login(customizer -> customizer
                        .defaultSuccessUrl("/loginsuccess",true)); // ログイン成功後にログイン成功リダイレクト先に遷移。trueでいつでも有効にする。

        return http.build();
    }

}