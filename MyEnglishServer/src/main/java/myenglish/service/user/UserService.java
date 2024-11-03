package myenglish.service.user;

import myenglish.domain.MyEnglishUserEntity;

public interface UserService {
    int createUser(String name,String email) throws Exception;
    MyEnglishUserEntity getUser(String email,int userId);
    // TODO: メールアドレス変更時のアカウント移管
    // TODO: アカウントの削除
}
