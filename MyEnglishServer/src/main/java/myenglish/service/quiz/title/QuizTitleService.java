package myenglish.service.quiz.title;

import jakarta.servlet.http.HttpSession;
import myenglish.domain.dto.QuestionTitleResponse;
import myenglish.domain.entity.QuestionTitleEntity;
import myenglish.web.form.QuestionTitleForm;
import java.util.List;

public interface QuizTitleService {
	/* クイズのタイトル */
	List<QuestionTitleResponse> getQuestionTitle(HttpSession session);
	void insertQuestionTitle(QuestionTitleForm form, HttpSession session);
	void updateQuestionTitle(QuestionTitleForm form, HttpSession session);
	void deleteQuestionTitle(QuestionTitleForm form);

}


