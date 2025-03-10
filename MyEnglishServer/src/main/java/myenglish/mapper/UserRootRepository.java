package myenglish.mapper;
import org.apache.ibatis.annotations.*;

import myenglish.domain.entity.MyEnglishUserEntity;

@Mapper
public interface UserRootRepository {
	/** 新規ユーザーを追加する */
	@Insert("INSERT INTO user_root(name,email,created_date,update_date) VALUES(#{name}, #{email}, NOW(), NOW())")
	int insert(MyEnglishUserEntity MyEnglishUserEntity);
	/** id を 指定してユーザーを検索する **/
	@Select("SELECT * FROM user_root WHERE user_id=#{userId}")
	@ConstructorArgs({
			@Arg(column="user_id",javaType=Integer.class),
			@Arg(column="name",javaType=String.class),
			@Arg(column="email",javaType=String.class)
	})
	MyEnglishUserEntity selectById(@Param("userId") Integer userId);

	/** email を 指定してユーザーを検索する **/
	@Select("SELECT * FROM user_root WHERE email=#{email}")
	@ConstructorArgs({
		@Arg(column="user_id",javaType=Integer.class),
		@Arg(column="name",javaType=String.class),
		@Arg(column="email",javaType=String.class)
	})
	MyEnglishUserEntity selectByEmail(@Param("email") String email);
}
