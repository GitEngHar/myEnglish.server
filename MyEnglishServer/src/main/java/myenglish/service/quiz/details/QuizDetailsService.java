package myenglish.service.quiz.details;

import jakarta.servlet.http.HttpSession;
import myenglish.domain.MyEnglishQuizAnswerEntity;
import myenglish.domain.MyEnglishQuizDetailsEntity;
import myenglish.domain.MyEnglishQuizTitleEntity;
import myenglish.web.form.MyEnglishQuizAnswerForm;
import myenglish.web.form.MyEnglishQuizDetailsForm;
import myenglish.web.form.MyEnglishQuizDetailsWrapperForm;
import myenglish.web.form.MyEnglishQuizTitleForm;

import java.util.List;
import java.util.Optional;

public interface QuizDetailsService {

	/* クイズに紐づく問題 */
	List<MyEnglishQuizDetailsEntity> getQuestionDetails(MyEnglishQuizTitleForm quiestionTitle,
														HttpSession session);
	MyEnglishQuizDetailsEntity getQuestionDetailsById(int id);
	void insertQuestion(MyEnglishQuizDetailsEntity quest); 
	void deleteQuestion(MyEnglishQuizDetailsForm quiz);
	void updateQuestionDetails(MyEnglishQuizDetailsEntity quiz);
	void insertQuestionAnswer(MyEnglishQuizDetailsWrapperForm quizDetailsWrapperForm);
	MyEnglishQuizAnswerForm getQuestionAnswerById(MyEnglishQuizDetailsForm quizDetailsForm);
	void updateQuestionAnswer(MyEnglishQuizDetailsWrapperForm quizDetailsWrapperForm);
	Optional<MyEnglishQuizDetailsEntity> getQuestionLastestDetails(int id);
}


