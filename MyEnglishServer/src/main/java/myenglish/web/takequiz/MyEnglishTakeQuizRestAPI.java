package myenglish.web.takequiz;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
	private final QuizServiceImpl quizservice;
	/* クイズを開始 */
	@CrossOrigin
	(origins = "http://localhost:3000")
	@PostMapping("/")
	public List<MyEnglishQuizDetailsWrapperForm> takeQuiz(@RequestBody  MyEnglishQuizTitleForm questionTitle) {
		// 対象の問題と答えをWrapperへ格納
		MyEnglishQuizTitleEntity myEnglishQuestionTitleEntity = new MyEnglishQuizTitleEntity();
		myEnglishQuestionTitleEntity.setQuestionTitleId(questionTitle.getQuestionTitleId());
		List<MyEnglishQuizAnswerEntity> myEnglishQuizAnswerEntity = quizservice.getQuestionAnswer(myEnglishQuestionTitleEntity);
		List<MyEnglishQuizDetailsEntity> myEnglishQuizDetailsEntity = quizservice.getQuestionDetails(myEnglishQuestionTitleEntity);
		
		// 返信用にWrapperへ格納 (サイズが異なる場合への対応)
		List<MyEnglishQuizDetailsWrapperEntity> MyEnglishQuizDetailsWrapperEntityList = IntStream.range(0,Math.min(myEnglishQuizDetailsEntity.size(), myEnglishQuizAnswerEntity.size()))
				.mapToObj(i -> new MyEnglishQuizDetailsWrapperEntity(myEnglishQuizAnswerEntity.get(i),myEnglishQuizDetailsEntity.get(i))).collect(Collectors.toList());		
		
		List<MyEnglishQuizDetailsWrapperForm> MyEnglishQuizDetailsWrapperFormList = MyEnglishQuizDetailsWrapperEntityList.stream()
			.map(wrapperEntity -> MyEnglishQuizDetailsWrapperFormHelper.convertToForm(wrapperEntity)
			).collect(Collectors.toList());
		
		return MyEnglishQuizDetailsWrapperFormList;	
		}
}
