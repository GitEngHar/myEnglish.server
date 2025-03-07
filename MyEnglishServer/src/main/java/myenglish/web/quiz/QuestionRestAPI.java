package myenglish.web.quiz;

import java.util.List;

import jakarta.servlet.http.HttpSession;
import myenglish.domain.dto.QuestionTitleResponse;
import myenglish.service.quiz.title.QuizTitleServiceImpl;

import myenglish.web.exception.InvalidRequestException;
import myenglish.web.exception.UserNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import myenglish.web.form.QuestionTitleForm;

@RequestMapping(value="/quizrest")
@RequiredArgsConstructor
@RestController
public class QuestionRestAPI {
	private final QuizTitleServiceImpl quizTitleService;

	/** クイズのトップ画面でのデータ取得
	 *  return : 対象ユーザーの問題タイトル全て
	 * **/
	@GetMapping("")
	public List<QuestionTitleResponse> entryQuiz(HttpSession session) {
			return quizTitleService.getQuestionTitle(session);
	}
	
	/** クイズタイトルをDBへ保存 **/
	@PostMapping("/save")
	public void saveQuizTitle(@RequestBody @Validated QuestionTitleForm form , BindingResult bindingResult, HttpSession session) {
		if(bindingResult.hasErrors()) {
			throw new InvalidRequestException("ユーザー入力された値が不正なのでデータ登録を拒否しました。 : " + bindingResult.getAllErrors().getFirst().getDefaultMessage());
		}else{
			quizTitleService.insertQuestionTitle(form,session);
		}
	}

	
	// クイズ更新画面
	@PostMapping("/update")
	public void quizForm(@RequestBody @Validated QuestionTitleForm form, BindingResult bindingResult , HttpSession session) {
		/** 編集した内容でアップデート **/
		// セッションからユーザーIDを取得する
		if(bindingResult.hasErrors()){
			throw new InvalidRequestException("ユーザー入力された値が不正なのでデータ登録を拒否しました。 : " + bindingResult.getAllErrors().getFirst().getDefaultMessage());
		}else{
			quizTitleService.updateQuestionTitle(form,session);
		}
	}
	
	/** クイズ削除 **/
	@PostMapping("/delete")
	public void quizTitleDelete(@RequestBody QuestionTitleForm form) {
		quizTitleService.deleteQuestionTitle(form);
	}
}
