package myenglish.mapper;
import org.apache.ibatis.annotations.*;

import myenglish.domain.MyEnglishUserEntity;

@Mapper
public interface UserRootRepository {
	/** 新規ユーザーを追加する */
	@Insert("INSERT INTO user_root(name,email,created_date,update_date) VALUES(#{name}, #{email}, NOW(), NOW())")
	int insert(MyEnglishUserEntity MyEnglishUserEntity);
	/** id を 指定してユーザーを検索する **/
	@Select("SELECT * FROM user_root WHERE user_id=#{userId}")
	MyEnglishUserEntity selectById(@Param("userId") Integer userId);

	/** email を 指定してユーザーを検索する **/
	@Select("SELECT * FROM user_root WHERE email=#{email}")
	@Results(
			{
					@Result(property = "userId", column = "user_id")
			}
	)
	MyEnglishUserEntity selectByEmail(@Param("email") String email);
}
