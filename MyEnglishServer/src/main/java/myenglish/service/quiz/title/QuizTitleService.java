package myenglish.service.quiz.title;

import jakarta.servlet.http.HttpSession;
import myenglish.domain.entity.MyEnglishQuizTitleEntity;
import myenglish.web.form.MyEnglishQuizTitleForm;
import java.util.List;

public interface QuizTitleService {
	/* クイズのタイトル */
	List<MyEnglishQuizTitleEntity> getQuestionTitle(HttpSession session);
	MyEnglishQuizTitleEntity getQuestionTitleById(int questid);
	void insertQuestionTitle(MyEnglishQuizTitleForm form, HttpSession session);
	void updateQuestionTitle(MyEnglishQuizTitleForm form, HttpSession session);
	void deleteQuestionTitle(MyEnglishQuizTitleForm form);

}


