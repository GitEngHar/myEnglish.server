package myenglish.service.quiz.title;

import jakarta.servlet.http.HttpSession;
import myenglish.domain.MyEnglishQuizTitleEntity;
import myenglish.web.form.MyEnglishQuizTitleForm;
import java.util.List;

public interface QuizTitleService {
	/* クイズのタイトル */
	List<MyEnglishQuizTitleEntity> getQuestionTitle(HttpSession session);
	MyEnglishQuizTitleEntity getQuestionTitleById(int questid);
	void insertQuestionTitle(MyEnglishQuizTitleForm form, HttpSession session);
	void updateQuestion(MyEnglishQuizTitleForm form, HttpSession session);
	void deleteQuestionTitle(MyEnglishQuizTitleForm form);

}


