package myenglish.web.takequiz;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import myenglish.domain.MyEnglishQuizAnswerEntity;
import myenglish.domain.MyEnglishQuizDetailsEntity;
import myenglish.domain.MyEnglishQuizTitleEntity;
import myenglish.service.quiz.QuizServiceImpl;

@RequestMapping("takequiz")
@RequiredArgsConstructor
@Controller
public class MyEnglishTakeQuizController {
	private final QuizServiceImpl quizservice;
	/* クイズを開始 */
	@GetMapping(value="/{questionTitleId}")
	public String takeQuiz(@PathVariable("questionTitleId") int questionTitleId, 
			Model model) {
		// 対象の問題と答えをWrapperへ格納
		MyEnglishQuizTitleEntity myEnglishQuestionTitleEntity = new MyEnglishQuizTitleEntity();
		myEnglishQuestionTitleEntity.setQuestionTitleId(questionTitleId);
		List<MyEnglishQuizAnswerEntity> myEnglishQuizAnswerEntity = quizservice.getQuestionAnswer(myEnglishQuestionTitleEntity);
		List<MyEnglishQuizDetailsEntity> myEnglishQuizDetailsEntity = quizservice.getQuestionDetails(myEnglishQuestionTitleEntity);
		// 問題と答えをhtmlへ渡す (結果の照合や表示はフロントでやる)
		model.addAttribute("myEnglishQuizAnswerEntity",myEnglishQuizAnswerEntity);
		model.addAttribute("myEnglishQuizDetailsEntity",myEnglishQuizDetailsEntity);
		model.addAttribute("questionTitleId",questionTitleId);
		return "takequiz/index.html";
	}
}
