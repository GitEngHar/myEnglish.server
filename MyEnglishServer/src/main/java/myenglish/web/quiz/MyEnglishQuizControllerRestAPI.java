package myenglish.web.quiz;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import myenglish.domain.MyEnglishQuizTitleEntity;
import myenglish.domain.MyEnglishUserEntity;
import myenglish.helper.MyEnglishQuizTitleFormHelper;
import myenglish.service.quiz.QuizServiceImpl;
import myenglish.web.form.MyEnglishQuizTitleForm;

@RequestMapping(value="/quizrest")
@RequiredArgsConstructor
@RestController
public class MyEnglishQuizControllerRestAPI {
	private final QuizServiceImpl quizService;
	
	/** クイズのトップ画面 **/
	@GetMapping("")
	public List<MyEnglishQuizTitleEntity> entryQuiz() {
		/** MockUserData **/
		MyEnglishUserEntity userProperty = new MyEnglishUserEntity();
		userProperty.setUserId(1);
		
		// 問題のタイトルを取得
		List<MyEnglishQuizTitleEntity> questionTitles = quizService.getQuestionTitle(userProperty);
		boolean isGetTitleNull = questionTitles.stream().anyMatch(title -> title == null);
		if(isGetTitleNull) {
			return null;
		}
		return questionTitles;
	}
	
	/** クイズタイトルをDBへ保存 **/
	@PostMapping("/save")
	public void saveQuizTitle(@RequestBody MyEnglishQuizTitleForm form) {
		// TODO:エラーをハンドリングする
		// 入力されたタイトルフォームを変換しDBに登録
		MyEnglishQuizTitleEntity titleEntity = MyEnglishQuizTitleFormHelper.convertToEntity(form);
		quizService.insertQuestionTitle(titleEntity);
	}

	
	// クイズ更新画面
	@PostMapping("/update")
	public void quizForm(@RequestBody MyEnglishQuizTitleForm form) {
			// TODO:エラーをハンドリングする
			/** 編集した内容でアップデート **/	
			MyEnglishQuizTitleEntity titleEntity = MyEnglishQuizTitleFormHelper.convertToEntity(form);
			quizService.updateQuestion(titleEntity);
		}	
	
	/** クイズ削除 **/
	@PostMapping("/delete")
	public void quizTitleDelete(@RequestBody MyEnglishQuizTitleForm form) {
		int id = form.getQuestionTitleId();
		MyEnglishQuizTitleEntity entity = quizService.getQuestionTitleById(id);
		quizService.deleteQuestionTitle(entity);
	}
}
