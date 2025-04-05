package myenglish.web.quizdetails;

import java.util.List;
import jakarta.servlet.http.HttpSession;

import myenglish.domain.dto.QuestionDetailsResponse;
import myenglish.service.quiz.details.QuizDetailsServiceImpl;
import myenglish.web.exception.InvalidRequestException;
import myenglish.web.form.*;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequestMapping("/web/quizdetailsrest")
@RequiredArgsConstructor
@RestController
public class QuestionDetailsRestAPI {
	
	private final QuizDetailsServiceImpl quizDetailsService;
	/** クイズ問題画面 **/
	@PostMapping("/")
	public List<QuestionDetailsResponse> quizdetails(
			@RequestBody @Validated QuestionTitleForm quiestionTitle,
			BindingResult bindingResult,
			HttpSession session) {
		if(bindingResult.hasErrors()){
			throw new InvalidRequestException("ユーザー入力された値が不正なのでデータ登録を拒否しました。 : " + bindingResult.getAllErrors().getFirst().getDefaultMessage());
		}
		return quizDetailsService.getAllQuestionDetails(quiestionTitle,session);
	}

	/** クイズ問題と回答を全て取得する **/
	@PostMapping("/all")
	public List<QuestionDetailsResponse> quizDetailsAll(@RequestBody  @Validated QuestionTitleForm questionTitle, BindingResult bindingResult, HttpSession session) {
		if(bindingResult.hasErrors()) {
			throw new InvalidRequestException("ユーザー入力された値が不正なのでデータ登録を拒否しました。 : " + bindingResult.getAllErrors().getFirst().getDefaultMessage());
		}
		return quizDetailsService.getAllQuestionDetails(questionTitle,session);
	}

	/* クイズ情報を追加する */
	@PostMapping("/save")
	public void saveQuizDetails(@RequestBody @Validated QuestionDetailsForm questionDetailsForm,
								BindingResult bindingResult
			) {
		if(bindingResult.hasErrors()){
			throw new InvalidRequestException("ユーザー入力された値が不正なのでデータ登録を拒否しました。 : " + bindingResult.getAllErrors().getFirst().getDefaultMessage());
		}
		quizDetailsService.insertQuestion(questionDetailsForm);
	}
	
	/*クイズ削除 delete */
	@PostMapping("/delete")
	public void quizDelete(@RequestBody  QuestionDetailsForm questionDetailsForm) {
		quizDetailsService.deleteQuestion(questionDetailsForm);
	}

	/* クイズ更新 */
	@PostMapping("/update")
	public void quizForm(
			@RequestBody @Validated QuestionDetailsForm questionDetailsForm,
		 	BindingResult bindingResult,HttpSession session
	) {
		/* 編集した内容で問題と答えをアップデート */
		if(bindingResult.hasErrors()){
			throw new InvalidRequestException("ユーザー入力された値が不正なのでデータ登録を拒否しました。 : " + bindingResult.getAllErrors().getFirst().getDefaultMessage());
		}
		quizDetailsService.updateQuestionDetails(questionDetailsForm);
	}

}
