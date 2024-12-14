package myenglish.web.quiz;

import java.util.List;

import jakarta.servlet.http.HttpSession;
import myenglish.service.quiz.title.QuizTitleServiceImpl;
import myenglish.service.user.UserServiceImpl;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import myenglish.domain.MyEnglishQuizTitleEntity;
import myenglish.service.quiz.QuizServiceImpl;
import myenglish.web.form.MyEnglishQuizTitleForm;

@RequestMapping(value="/quizrest")
@RequiredArgsConstructor
@RestController
public class MyEnglishQuizControllerRestAPI {
	private final QuizTitleServiceImpl quizTitleService;

	/** クイズのトップ画面でのデータ取得
	 *  return : 対象ユーザーの問題タイトル全て
	 * **/
	@GetMapping("")
	public List<MyEnglishQuizTitleEntity> entryQuiz(HttpSession session) {

		// 問題のタイトルを取得
		List<MyEnglishQuizTitleEntity> questionTitles = quizTitleService.getQuestionTitle(session);

		// タイトルが一つもない場合は null を返す
		boolean isGetTitleNull = questionTitles.stream().anyMatch(title -> title == null);
		if(isGetTitleNull) {
			return null;
		}

		return questionTitles;
	}
	
	/** クイズタイトルをDBへ保存 **/
	@PostMapping("/save")
	public void saveQuizTitle(@RequestBody @Validated MyEnglishQuizTitleForm form , BindingResult bindingResult, HttpSession session) {
		if(bindingResult.hasErrors()) {
			// TODO: 401で返す
			System.out.println("!!!ERROR!!!");
		}else{
			quizTitleService.insertQuestionTitle(form,session);
		}
	}

	
	// クイズ更新画面
	@PostMapping("/update")
	public void quizForm(@RequestBody @Validated MyEnglishQuizTitleForm form, BindingResult bindingResult , HttpSession session) {
		// TODO:エラーをハンドリングする
		/** 編集した内容でアップデート **/
		// セッションからユーザーIDを取得する
		if(bindingResult.hasErrors()){
			// TODO: 401で返す
			System.out.println("!!!ERROR!!!");
		}else{
			quizTitleService.updateQuestion(form,session);
		}
	}
	
	/** クイズ削除 **/
	@PostMapping("/delete")
	public void quizTitleDelete(@RequestBody MyEnglishQuizTitleForm form) {
		quizTitleService.deleteQuestionTitle(form);
	}
}
