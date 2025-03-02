package myenglish.service.quiz.details;

import jakarta.servlet.http.HttpSession;
import myenglish.domain.dto.QuestionDetailsResponse;
import myenglish.domain.entity.MyEnglishQuestionDetailsEntity;
import myenglish.domain.entity.MyEnglishQuizAnswerEntity;
import myenglish.domain.entity.MyEnglishQuizDetailsEntity;
import myenglish.web.form.*;

import java.util.List;
import java.util.Optional;

public interface QuizDetailsService {

	/* クイズに紐づく問題 */
	List<QuestionDetailsResponse> getAllQuestionDetails(
			MyEnglishQuizTitleForm quiestionTitle,
			HttpSession session
	);

	void insertQuestion(QuestionDetailsForm questionDetailsForm);

	void deleteQuestion(QuestionDetailsForm questionDetailsForm);

	void updateQuestionDetails(QuestionDetailsForm questionDetailsForm);

}


