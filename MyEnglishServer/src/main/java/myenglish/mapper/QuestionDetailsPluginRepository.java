package myenglish.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import myenglish.domain.MyEnglishQuizDetailsEntity;

@Mapper
public interface QuestionDetailsPluginRepository {
	
	/** タイトルIDから問題を取得する **/
	@Select("SELECT * FROM question_details_plugin WHERE question_title_id = #{questionTitleId}")
	@Results(
			{
				@Result(property = "questionDetailsId", column = "question_details_id"),
		        @Result(property = "questionTitleId", column = "question_title_id"),
				@Result(property = "questionWord", column = "question_word"),
				@Result(property = "createdDate", column = "created_date"),
				@Result(property = "updateDate", column = "update_date")
			}
			)
	List<MyEnglishQuizDetailsEntity> selectQuestionByTitleId(int questionTitleId);
	
	/** 問題IDから問題を取得する **/
	@Select("SELECT * FROM question_details_plugin WHERE question_details_id = #{questionDetailsId}")
	@Results(
			{
				@Result(property = "questionDetailsId", column = "question_details_id"),
		        @Result(property = "questionTitleId", column = "question_title_id"),
				@Result(property = "questionWord", column = "question_word"),
				@Result(property = "createdDate", column = "created_date"),
				@Result(property = "updateDate", column = "update_date")
			}
			)
	MyEnglishQuizDetailsEntity selectQuestionByDetailsId(int questionDetailsId);
	
	/** 問題を追加する **/
	@Insert("INSERT INTO question_details_plugin(question_title_id,question_word,created_date,update_date)"
			+ " VALUES(#{questionTitleId},#{questionWord},NOW(),NOW())")
	void insert_question(MyEnglishQuizDetailsEntity MyEnglishQuizDetailsEntity);
	
	/** 問題を更新する **/
	@Update("UPDATE question_details_plugin SET question_word=#{questionWord} , update_date=NOW()"
			+ "WHERE question_title_id=#{questionTitleId} AND question_details_id=#{questionDetailsId}")
	void update_question(MyEnglishQuizDetailsEntity MyEnglishQuizDetailsEntity);
	
	/** 問題を削除する **/
	@Delete("DELETE FROM question_details_plugin "
			+ "WHERE question_title_id=#{questionTitleId} AND question_details_id=#{questionDetailsId}")
	void delete_question(MyEnglishQuizDetailsEntity MyEnglishQuizDetailsEntity);
	
}
