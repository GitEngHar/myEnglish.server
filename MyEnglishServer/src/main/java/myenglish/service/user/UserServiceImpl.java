package myenglish.service.user;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import myenglish.domain.entity.MyEnglishUserEntity;
import myenglish.mapper.UserRootRepository;
import myenglish.web.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRootRepository userRootRepository;
    @Override public int getUserId(HttpSession session){
        return Integer.parseInt(
            Optional.ofNullable(
                (String) session.getAttribute("userId")
            ).orElse("0") // Integerはnullを許容しないため、値が存在しない場合は 0を返す
        );
    }
    @Override
    public int createUser(String name, String email){
        try{
            MyEnglishUserEntity user = getUserByEmail(email);
            return user.userId();
        }catch (UserNotFoundException e){
            // ユーザーが存在しない場合は新しくユーザーを作成する
            // name, emailを指定してentityを生成する
            MyEnglishUserEntity myEnglishUserEntity =  new MyEnglishUserEntity(0,name,email);
            // ユーザーを追加する
            userRootRepository.insert(myEnglishUserEntity);
            MyEnglishUserEntity createdUser = getUserByEmail(email);
            return createdUser.userId();
        }
    }

    @Override
    public MyEnglishUserEntity getUserByUserId(int userId){
        MyEnglishUserEntity user = userRootRepository.selectById(userId);
        if(user==null){
            throw new UserNotFoundException("対象ユーザーが存在しません userId=" + userId);
        }
        return userRootRepository.selectById(userId);
    }

    @Override
    public MyEnglishUserEntity getUserByEmail(String email){
        MyEnglishUserEntity user = userRootRepository.selectByEmail(email);
        if(user==null){
            throw new UserNotFoundException("emailでuser情報を検索しましたが対象ユーザーが存在しません");
        }
        return userRootRepository.selectByEmail(email);
    }

}
