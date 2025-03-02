package myenglish.service.quiz.details;

import jakarta.servlet.http.HttpSession;
import myenglish.domain.dto.QuestionDetailsResponse;
import myenglish.web.form.*;

import java.util.List;

public interface QuizDetailsService {

	/* クイズに紐づく問題 */
	List<QuestionDetailsResponse> getAllQuestionDetails(
			QuestionTitleForm quiestionTitle,
			HttpSession session
	);

	void insertQuestion(QuestionDetailsForm questionDetailsForm);

	void deleteQuestion(QuestionDetailsForm questionDetailsForm);

	void updateQuestionDetails(QuestionDetailsForm questionDetailsForm);

}


