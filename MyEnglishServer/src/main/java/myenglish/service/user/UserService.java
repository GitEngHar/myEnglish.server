package myenglish.service.user;

import jakarta.servlet.http.HttpSession;
import myenglish.domain.entity.MyEnglishUserEntity;

public interface UserService {
    int createUser(String name,String email);
    int getUserId(HttpSession session);
    MyEnglishUserEntity getUser(String email,int userId);
    // TODO: メールアドレス変更時のアカウント移管
    // TODO: アカウントの削除
}
