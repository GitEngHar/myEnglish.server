package myenglish.service.quiz.title;

import jakarta.servlet.http.HttpSession;
import myenglish.domain.entity.QuestionTitleEntity;
import myenglish.web.form.QuestionTitleForm;
import java.util.List;

public interface QuizTitleService {
	/* クイズのタイトル */
	List<QuestionTitleEntity> getQuestionTitle(HttpSession session);
	QuestionTitleEntity getQuestionTitleById(int questid);
	void insertQuestionTitle(QuestionTitleForm form, HttpSession session);
	void updateQuestionTitle(QuestionTitleForm form, HttpSession session);
	void deleteQuestionTitle(QuestionTitleForm form);

}


