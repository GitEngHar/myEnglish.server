package myenglish.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import myenglish.domain.MyEnglishQuizTitleEntity;

@Mapper
public interface QuestionTitlePluginRepository {
		/** userIdからクエストタイトルを取得 **/ 
		@Select("SELECT * FROM question_core WHERE owner_user_id=#{ownerUserId}")
		@Results(
				{
					@Result(property = "questionTitleId", column = "question_title_id"),
			        @Result(property = "ownerUserId", column = "owner_user_id"),
					@Result(property = "questionTitle", column = "question_title")
				}
				)
		
		/** titleIdからクエストタイトルを取得 **/ 
		List<MyEnglishQuizTitleEntity> select_by_userid(int ownerUserId);
		@Results(
				{
					@Result(property = "questionTitleId", column = "question_title_id"),
			        @Result(property = "ownerUserId", column = "owner_user_id"),
					@Result(property = "questionTitle", column = "question_title")
				}
				)
		@Select("SELECT * FROM question_core WHERE question_title_id=#{questionTitleId}")
	    MyEnglishQuizTitleEntity select_by_id(int questionTitleId);
		/** タイトルの追加 **/
		@Insert("INSERT INTO question_core(owner_user_id,question_title,created_date,update_date) "
				+ "VALUES(#{ownerUserId},#{questionTitle},CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)")
		void insert_title(MyEnglishQuizTitleEntity MyEnglishQuizTitleEntity);
		/** タイトルの削除 **/
		@Delete("DELETE FROM question_core WHERE question_title_id=#{questionTitleId}")
		void delete_title(int questionTitleId);
		/** タイトルの更新 **/
		@Update("UPDATE question_core SET question_title=#{questionTitle} "
				+ "WHERE question_title_id=#{questionTitleId}")
		void update_title(MyEnglishQuizTitleEntity MyEnglishQuizTitleEntity);

		/** タイトルを古い問題を元に更新 **/
		@Update("UPDATE question_core SET question_title=#{MyEnglishQuizTitleEntity.questionTitle} "
				+ "WHERE question_title=#{oldQuestionTitle} AND owner_user_id=#{MyEnglishQuizTitleEntity.ownerUserId}")
		void update_title_by_old_question(MyEnglishQuizTitleEntity MyEnglishQuizTitleEntity,String oldQuestionTitle);

}
