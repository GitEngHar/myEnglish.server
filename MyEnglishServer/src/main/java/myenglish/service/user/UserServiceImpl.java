package myenglish.service.user;

import lombok.AllArgsConstructor;
import myenglish.domain.MyEnglishUserEntity;
import myenglish.mapper.UserRootRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRootRepository userRootRepository;

    @Override
    public int createUser(String name,String email){
        int userId = 0;
        if(getUser(email,userId) == null){
            // ユーザーが存在しない場合は新しく作成する
            MyEnglishUserEntity myEnglishUserEntity =  new MyEnglishUserEntity(name,email);
            userRootRepository.insert(myEnglishUserEntity);
        }
        userId = getUser(email,userId).getUserId();
        return userId;
    }

    @Override
    public MyEnglishUserEntity getUser(String email,int userId){
        MyEnglishUserEntity myEnglishUserEntity;
        if(email == null){
            myEnglishUserEntity = userRootRepository.selectById(userId);
        }else{
            myEnglishUserEntity = userRootRepository.selectByEmail(email);
        }
        return myEnglishUserEntity;
    }

}
