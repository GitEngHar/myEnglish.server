package myenglish.service.quiz;
import java.util.List;
import java.util.Optional;

import myenglish.domain.MyEnglishQuizAnswerEntity;
import myenglish.domain.MyEnglishQuizDetailsEntity;
import myenglish.domain.MyEnglishQuizTitleEntity;
import myenglish.domain.MyEnglishUserEntity;

public interface QuizService {

	/* クイズに紐づく問題 */
	List<MyEnglishQuizDetailsEntity> getQuestionDetails(MyEnglishQuizTitleEntity title);
	MyEnglishQuizDetailsEntity getQuestionDetailsById(int id);
	void insertQuestion(MyEnglishQuizDetailsEntity quest); 
	void deleteQuestion(MyEnglishQuizDetailsEntity quiz);
	void updateQuestionDetails(MyEnglishQuizDetailsEntity quiz);
	
	/*クイズの答え*/
	// Detailsが消されるとAnswerも消えるため不要
	List<MyEnglishQuizAnswerEntity> getQuestionAnswer(MyEnglishQuizTitleEntity title);
	MyEnglishQuizAnswerEntity getQuestionAnswerById(int id);
	void insertQuestionAnswer(MyEnglishQuizAnswerEntity answer);
	void updateQuestionAnswer(MyEnglishQuizAnswerEntity answer);
	
	/*他*/
	Optional<MyEnglishQuizDetailsEntity> getQuestionLastestDetails(int id); //最新の追加された問題を取得する
	
}


