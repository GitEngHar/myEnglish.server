package myenglish.mapper;

import myenglish.domain.entity.QuestionDetailsEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface QuestionDetailsRepository {
    @Select("SELECT * FROM question_details WHERE question_title_id = #{questionTitleId}")
    @ConstructorArgs({
            @Arg(column = "question_details_id", javaType = int.class),
            @Arg(column = "question_title_id", javaType = int.class),
            @Arg(column = "question_word", javaType = String.class),
            @Arg(column = "answer_candidate_no_1", javaType = String.class),
            @Arg(column = "answer_candidate_no_2", javaType = String.class),
            @Arg(column = "answer_candidate_no_3", javaType = String.class),
            @Arg(column = "answer_candidate_no_4", javaType = String.class),
            @Arg(column = "answer_number", javaType = int.class),
            @Arg(column = "created_date", javaType = java.time.LocalDateTime.class),
            @Arg(column = "update_date", javaType = java.time.LocalDateTime.class)
    })
    List<QuestionDetailsEntity> selectQuestionByTitleId(int questionTitleId);

    @Insert("INSERT INTO question_details(question_details_id,question_title_id,question_word,answer_candidate_no_1,answer_candidate_no_2,answer_candidate_no_3,answer_candidate_no_4,answer_number,created_date,update_date)"
            + " VALUES(#{questionDetailsId},#{questionTitleId},#{questionWord},#{answerCandidateNo1},#{answerCandidateNo2},#{answerCandidateNo3},#{answerCandidateNo4},#{answerNumber},NOW(),NOW())")
    void insert_question(QuestionDetailsEntity questionDetailsEntity);

    /** 問題を更新する **/
    @Update("UPDATE question_details SET question_word=#{questionWord},answer_candidate_no_1=#{answerCandidateNo1},answer_candidate_no_2=#{answerCandidateNo2},answer_candidate_no_3=#{answerCandidateNo3},answer_candidate_no_4=#{answerCandidateNo4},answer_number=#{answerNumber}, update_date=NOW()"
            + "WHERE question_title_id=#{questionTitleId} AND question_details_id=#{questionDetailsId}")
    void update_question(QuestionDetailsEntity questionDetailsEntity);

    /** 問題を削除する **/
    @Delete("DELETE FROM question_details "
            + "WHERE question_title_id=#{questionTitleId} AND question_details_id=#{questionDetailsId}")
    void delete_question(QuestionDetailsEntity questionDetailsEntity);
}
