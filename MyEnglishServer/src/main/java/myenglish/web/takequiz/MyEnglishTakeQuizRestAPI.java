package myenglish.web.takequiz;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import jakarta.servlet.http.HttpSession;
import myenglish.service.quiz.takequiz.TakeQuizServiceImpl;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import myenglish.domain.MyEnglishQuizAnswerEntity;
import myenglish.domain.MyEnglishQuizDetailsEntity;
import myenglish.domain.MyEnglishQuizDetailsWrapperEntity;
import myenglish.domain.MyEnglishQuizTitleEntity;
import myenglish.helper.MyEnglishQuizDetailsWrapperFormHelper;
import myenglish.service.quiz.QuizServiceImpl;
import myenglish.web.form.MyEnglishQuizDetailsWrapperForm;
import myenglish.web.form.MyEnglishQuizTitleForm;

@RequestMapping("takequizrest")
@RequiredArgsConstructor
@RestController
public class MyEnglishTakeQuizRestAPI {
	private final TakeQuizServiceImpl takeQuizService;
	/* クイズを開始 */
	@CrossOrigin
	(origins = "http://localhost:3000")
	@PostMapping("/")
	public List<MyEnglishQuizDetailsWrapperForm> takeQuiz(@RequestBody  @Validated MyEnglishQuizTitleForm questionTitle, BindingResult bindingResult,HttpSession session) {
		if(bindingResult.hasErrors()){
			System.out.println("ERORR");
			return null;
		}else{
			return takeQuizService.takeQuiz(questionTitle,session);
		}
}
}
