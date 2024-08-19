package myenglish.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import myenglish.domain.MyEnglishQuizAnswerEntity;

@Mapper
public interface QuestionAnswerPluginRepository {
	
	/** タイトルIDから問題を取得する **/
	@Select("SELECT * FROM question_details_answer_plugin WHERE question_title_id = #{questionTitleId}")
	@Results(
			{
				@Result(property = "questionAnswerId", column = "question_answer_id"),
				@Result(property = "questionTitleId", column = "question_title_id"),
				@Result(property = "questionDetailsId", column = "question_details_id"),
				@Result(property = "answerId", column = "answer_id"),
				@Result(property = "answerCandidateNo1", column = "answer_candidate_no_1"),
				@Result(property = "answerCandidateNo2", column = "answer_candidate_no_2"),
				@Result(property = "answerCandidateNo3", column = "answer_candidate_no_3"),
				@Result(property = "answerCandidateNo4", column = "answer_candidate_no_4"),
			}
			)
	List<MyEnglishQuizAnswerEntity> selectQuestioAnswernByTitleId(int questionTitleId);
	
	/** 問題IDから問題を取得する **/
	@Select("SELECT * FROM question_details_answer_plugin WHERE question_details_id = #{questionDetailsId}")
	@Results(
			{
				@Result(property = "questionAnswerId", column = "question_answer_id"),
				@Result(property = "questionTitleId", column = "question_title_id"),
				@Result(property = "questionDetailsId", column = "question_details_id"),
				@Result(property = "answerId", column = "answer_id"),
				@Result(property = "answerCandidateNo1", column = "answer_candidate_no_1"),
				@Result(property = "answerCandidateNo2", column = "answer_candidate_no_2"),
				@Result(property = "answerCandidateNo3", column = "answer_candidate_no_3"),
				@Result(property = "answerCandidateNo4", column = "answer_candidate_no_4"),
			}
			)
	MyEnglishQuizAnswerEntity selectQuestionAnswerByDetailsId(int questionDetailsId);
	
	/** 答えを追加する **/
	@Insert("INSERT INTO question_details_answer_plugin(question_title_id,question_details_id,answer_id,answer_candidate_no_1,"
			+ "answer_candidate_no_2,answer_candidate_no_3,answer_candidate_no_4,created_date,update_date)"
			+ " VALUES(#{questionTitleId},#{questionDetailsId},#{answerId},#{answerCandidateNo1},#{answerCandidateNo2},#{answerCandidateNo3},#{answerCandidateNo4},NOW(),NOW())")
	void insert_question_answer(MyEnglishQuizAnswerEntity MyEnglishQuizDetailsEntity);
	
	/** 答えを更新する **/
	@Update("UPDATE question_details_answer_plugin "
			+ "SET answer_id=#{answerId},answer_candidate_no_1=#{answerCandidateNo1},answer_candidate_no_2=#{answerCandidateNo2},answer_candidate_no_3=#{answerCandidateNo3},answer_candidate_no_4=#{answerCandidateNo4} , update_date=NOW()"
			+ "WHERE question_details_id=#{questionDetailsId} AND question_answer_id=#{questionAnswerId}")
	void update_question_answer(MyEnglishQuizAnswerEntity MyEnglishQuizDetailsEntity);
	
	/** 問題を削除する **/
	@Delete("DELETE FROM question_details_answer_plugin"
			+ "WHERE question_details_id=#{questionDetailsId} AND AND question_answer_id=#{questionAnswerId}")
	void delete_question_answer(MyEnglishQuizAnswerEntity MyEnglishQuizDetailsEntity);
	
}
