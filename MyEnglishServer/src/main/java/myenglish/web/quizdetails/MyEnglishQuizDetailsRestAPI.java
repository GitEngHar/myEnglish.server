package myenglish.web.quizdetails;

import java.util.List;
import jakarta.servlet.http.HttpSession;

import myenglish.domain.dto.QuestionDetailsResponse;
import myenglish.service.quiz.details.QuizDetailsServiceImpl;
import myenglish.web.form.*;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequestMapping("quizdetailsrest")
@RequiredArgsConstructor
@RestController
public class MyEnglishQuizDetailsRestAPI{
	
	private final QuizDetailsServiceImpl quizDetailsService;
	/** クイズ問題画面 **/
	@CrossOrigin
			(origins = "http://localhost:3000")
	@PostMapping("/")
	public List<QuestionDetailsResponse> quizdetails(
			@RequestBody @Validated MyEnglishQuizTitleForm quiestionTitle,
			BindingResult bindingResult,
			HttpSession session) {
		if(bindingResult.hasErrors()){
			System.out.println("ERROR! quizdetails");
			return null; //TODO: 410エラーと分かる内容を返す
		}
		return quizDetailsService.getAllQuestionDetails(quiestionTitle,session);
	}

	/** クイズ問題と回答を全て取得する **/
	@CrossOrigin
			(origins = "http://localhost:3000")
	@PostMapping("/all")
	public List<QuestionDetailsResponse> quizDetailsAll(@RequestBody  @Validated MyEnglishQuizTitleForm questionTitle, BindingResult bindingResult,HttpSession session) {
		if(bindingResult.hasErrors()) {
			System.out.println("ERORR");
			return null;
		}
		return quizDetailsService.getAllQuestionDetails(questionTitle,session);
	}

	/* クイズ情報を追加する */
	@CrossOrigin
	(origins = "http://localhost:3000")
	@PostMapping("/save")
	public void saveQuizDetails(@RequestBody @Validated QuestionDetailsForm questionDetailsForm,
								BindingResult bindingResult
			) {
		if(bindingResult.hasErrors()){
			// TODO:400エラー
			System.out.println("ERROR! saveQuizDetails");
		}else{
			quizDetailsService.insertQuestion(questionDetailsForm);
		}
	}
	
	/*クイズ削除 delete */
	@CrossOrigin
	(origins = "http://localhost:3000")
	@PostMapping("/delete")
	public void quizDelete(@RequestBody  QuestionDetailsForm questionDetailsForm) {
		quizDetailsService.deleteQuestion(questionDetailsForm);
	}

	/* クイズ更新 */
	@CrossOrigin
	(origins = "http://localhost:3000")
	@PostMapping("/update")
	public void quizForm(
			@RequestBody @Validated QuestionDetailsForm questionDetailsForm,
		 	BindingResult bindingResult,HttpSession session
	) {
			// TODO:エラーハンドル
			/* 編集した内容で問題と答えをアップデート */
		if(bindingResult.hasErrors()){
			System.out.println("ERROR! quizForm");
		}else{
			quizDetailsService.updateQuestionDetails(questionDetailsForm);
		}
	}

}
