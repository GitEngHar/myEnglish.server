package myenglish.mapper;

import java.util.List;

import org.apache.ibatis.annotations.*;

import myenglish.domain.entity.QuestionTitleEntity;

@Mapper
public interface QuestionTitlePluginRepository {
		/** userIdからクエストタイトルを取得 **/
		@Select("SELECT * FROM question_core WHERE owner_user_id=#{ownerUserId}")
		@ConstructorArgs(
				{
						@Arg(column = "question_title_id", javaType = int.class),
						@Arg(column = "owner_user_id", javaType = int.class),
						@Arg(column = "question_title", javaType = String.class),
				}
		)
		/** titleIdからクエストタイトルを取得 **/ 
		List<QuestionTitleEntity> select_by_userid(int ownerUserId);

		@ConstructorArgs(
			{
				@Arg(column = "question_title_id", javaType = int.class),
				@Arg(column = "owner_user_id", javaType = int.class),
				@Arg(column = "question_title", javaType = String.class),
			}
		)
		@Select("SELECT * FROM question_core WHERE question_title_id=#{questionTitleId}")
		QuestionTitleEntity select_by_id(int questionTitleId);
		/** タイトルの追加 **/
		@Insert("INSERT INTO question_core(owner_user_id,question_title,created_date,update_date) "
				+ "VALUES(#{ownerUserId},#{questionTitle},CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)")
		void insert_title(QuestionTitleEntity QuestionTitleEntity);
		/** タイトルの削除 **/
		@Delete("DELETE FROM question_core WHERE question_title_id=#{questionTitleId}")
		void delete_title(int questionTitleId);
		/** タイトルの更新 **/
		@Update("UPDATE question_core SET question_title=#{questionTitle} "
				+ "WHERE question_title_id=#{questionTitleId}")
		void update_title(QuestionTitleEntity QuestionTitleEntity);

		/** タイトルを古い問題を元に更新 **/
		@Update("UPDATE question_core SET question_title=#{MyEnglishQuizTitleEntity.questionTitle} "
				+ "WHERE question_title=#{oldQuestionTitle} AND owner_user_id=#{MyEnglishQuizTitleEntity.ownerUserId}")
		void update_title_by_old_question(QuestionTitleEntity QuestionTitleEntity, String oldQuestionTitle);

}
