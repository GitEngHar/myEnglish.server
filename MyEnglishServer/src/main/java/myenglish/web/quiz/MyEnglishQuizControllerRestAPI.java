package myenglish.web.quiz;

import java.util.List;

import jakarta.servlet.http.HttpSession;
import myenglish.service.user.UserServiceImpl;
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
	private final UserServiceImpl userService;
	/** クイズのトップ画面 **/
	@GetMapping("")
	public List<MyEnglishQuizTitleEntity> entryQuiz(HttpSession session) {
		// セッションからユーザーIDを取得する
		int userId = Integer.parseInt((String) session.getAttribute("userId"));
		// userIdからユーザー情報を取得する
		MyEnglishUserEntity userProperty = userService.getUser(null, userId);
		userProperty.setUserId(userId);

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
	public void saveQuizTitle(@RequestBody MyEnglishQuizTitleForm form,HttpSession session) {
		// TODO:エラーをハンドリングする
		// 入力されたタイトルフォームを変換しDBに登録
		MyEnglishQuizTitleEntity titleEntity = MyEnglishQuizTitleFormHelper.convertToEntity(form);
		// セッションからユーザーIDを取得する
		int userId = Integer.parseInt((String) session.getAttribute("userId"));

		titleEntity.setOwnerUserId(userId);
		quizService.insertQuestionTitle(titleEntity);
	}

	
	// クイズ更新画面
	@PostMapping("/update")
	public void quizForm(@RequestBody MyEnglishQuizTitleForm form,HttpSession session) {
		// TODO:エラーをハンドリングする
		/** 編集した内容でアップデート **/
		// セッションからユーザーIDを取得する
		int userId = Integer.parseInt((String) session.getAttribute("userId"));

		MyEnglishQuizTitleEntity titleEntity = MyEnglishQuizTitleFormHelper.convertToEntity(form);
		titleEntity.setOwnerUserId(userId);
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
