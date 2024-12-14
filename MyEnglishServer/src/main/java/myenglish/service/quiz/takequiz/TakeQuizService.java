package myenglish.service.quiz.takequiz;

import jakarta.servlet.http.HttpSession;
import myenglish.domain.MyEnglishQuizAnswerEntity;
import myenglish.domain.MyEnglishQuizDetailsEntity;
import myenglish.domain.MyEnglishQuizTitleEntity;
import myenglish.web.form.MyEnglishQuizDetailsWrapperForm;
import myenglish.web.form.MyEnglishQuizTitleForm;

import java.util.List;
import java.util.Optional;

public interface TakeQuizService {
	List<MyEnglishQuizDetailsWrapperForm> takeQuiz(MyEnglishQuizTitleForm questionTitle, HttpSession session);
}


