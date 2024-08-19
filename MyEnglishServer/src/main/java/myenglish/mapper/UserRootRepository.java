package myenglish.mapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import myenglish.domain.MyEnglishUserEntity;

@Mapper
public interface UserRootRepository {
	/** id を 指定してユーザーを検索する **/
	@Select("SELECT * FROM user_root WHERE user_id=#{userId}")
	MyEnglishUserEntity selectById(@Param("user_id") Integer userId);
}
