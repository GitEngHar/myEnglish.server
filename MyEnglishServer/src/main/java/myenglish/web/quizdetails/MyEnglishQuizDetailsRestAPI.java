package myenglish.web.quizdetails;

import java.util.List;
import java.util.Optional;

import myenglish.helper.MyEnglishQuizAnswerFormHelper;
import myenglish.helper.MyEnglishQuizDetailsFormHelper;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.RequiredArgsConstructor;
import myenglish.domain.MyEnglishQuizAnswerEntity;
import myenglish.domain.MyEnglishQuizDetailsEntity;
import myenglish.domain.MyEnglishQuizDetailsWrapperEntity;
import myenglish.domain.MyEnglishQuizTitleEntity;
import myenglish.helper.MyEnglishQuizDetailsWrapperFormHelper;
import myenglish.helper.MyEnglishQuizTitleFormHelper;
import myenglish.service.quiz.QuizServiceImpl;
import myenglish.web.form.MyEnglishQuizAnswerForm;
import myenglish.web.form.MyEnglishQuizDetailsForm;
import myenglish.web.form.MyEnglishQuizDetailsWrapperForm;
import myenglish.web.form.MyEnglishQuizTitleForm;

@RequestMapping("quizdetailsrest")
@RequiredArgsConstructor
@RestController
public class MyEnglishQuizDetailsRestAPI{
	
	private final QuizServiceImpl quizService;
	
	/** クイズ問題画面 **/
	@CrossOrigin
	(origins = "http://localhost:3000")
	@PostMapping("/")
	public List<MyEnglishQuizDetailsEntity> quizdetails(@RequestBody MyEnglishQuizTitleForm quiestionTitle) {
		
		MyEnglishQuizTitleEntity quiestionTitleEntity = MyEnglishQuizTitleFormHelper.convertToEntity(quiestionTitle);
		
		/** クイズタイトルに該当の問題情報を取得する **/
		List<MyEnglishQuizDetailsEntity> quizDetails = 
				quizService.getQuestionDetails(quiestionTitleEntity);
		
		return quizDetails;
	}
	
	/* クイズ情報を追加する */
	@CrossOrigin
	(origins = "http://localhost:3000")
	@PostMapping("/save")
	public void saveQuizDetails(@RequestBody  MyEnglishQuizDetailsWrapperForm quizDetailsWrapperForm
			) {
		
		// クイズタイトルIDを取得
		int parentQuestId = quizDetailsWrapperForm.getMyEnglishQuizDetailsForm().getQuestionTitleId();
		
		// 入力で受け取った各FormをWrapperへ変換
		MyEnglishQuizDetailsForm questionDetailsForm = quizDetailsWrapperForm.getMyEnglishQuizDetailsForm();
		MyEnglishQuizAnswerForm questionAnswerForm = quizDetailsWrapperForm.getMyEnglishQuizAnswerForm();
		MyEnglishQuizDetailsWrapperForm questionDetailsWrapperForm = new MyEnglishQuizDetailsWrapperForm();
		questionDetailsWrapperForm.setMyEnglishQuizAnswerForm(questionAnswerForm);
		questionDetailsWrapperForm.setMyEnglishQuizDetailsForm(questionDetailsForm);
		MyEnglishQuizDetailsWrapperEntity questionDetailsWrapperEntity = MyEnglishQuizDetailsWrapperFormHelper.convertToEntity(questionDetailsWrapperForm);

		// 問題と答えを追加
		quizService.insertQuestion(questionDetailsWrapperEntity.getMyEnglishQuizDetailsEntity());
		
		// 問題のIDを取得し答えのEntityにセットする
		Optional<MyEnglishQuizDetailsEntity> questionLatestDetailsEntity = quizService.getQuestionLastestDetails(parentQuestId);
		int latestQuestionDetailsId = questionLatestDetailsEntity.get().getQuestionDetailsId();
		questionDetailsWrapperEntity.getMyEnglishQuizAnswerEntity().setQuestionDetailsId(latestQuestionDetailsId);
		questionDetailsWrapperEntity.getMyEnglishQuizAnswerEntity().setQuestionTitleId(parentQuestId);
		quizService.insertQuestionAnswer(questionDetailsWrapperEntity.getMyEnglishQuizAnswerEntity());
	}
	
	/*クイズ削除 delete */
	@CrossOrigin
	(origins = "http://localhost:3000")
	@PostMapping("/delete")
	public void quizDelete(@RequestBody  MyEnglishQuizDetailsForm quizDetailsForm) {
		// TODO: 複数ユーザ利用の場合他ユーザーのデータを削除させないような仕組みが必要そう
		MyEnglishQuizDetailsEntity entity =  quizService.getQuestionDetailsById(quizDetailsForm.getQuestionDetailsId());
		quizService.deleteQuestion(entity);
	}
	
	/* クイズ編集画面 */
	@CrossOrigin
	(origins = "http://localhost:3000")
	@PostMapping("/edit")
	public MyEnglishQuizAnswerForm quizEdit(@RequestBody  MyEnglishQuizDetailsForm quizDetailsForm) {
		MyEnglishQuizAnswerEntity questionAnswerEntity = quizService.getQuestionAnswerById(quizDetailsForm.getQuestionDetailsId());
		return MyEnglishQuizAnswerFormHelper.convertToForm(questionAnswerEntity);
	}
	
	/* クイズ更新画面 */
	@CrossOrigin
	(origins = "http://localhost:3000")
	@PostMapping("/update")
	public void quizForm(@RequestBody MyEnglishQuizDetailsWrapperForm quizDetailsWrapperForm) {
			// TODO:エラーハンドル
			/* 編集した内容で問題と答えをアップデート */
			MyEnglishQuizDetailsWrapperEntity questionDetailsWrapperEntity = MyEnglishQuizDetailsWrapperFormHelper.convertToEntity(quizDetailsWrapperForm);
			quizService.updateQuestionDetails(questionDetailsWrapperEntity.getMyEnglishQuizDetailsEntity());
			// 回答に問題とタイトルのIDを紐づける
			int quiestionDetailsId = quizDetailsWrapperForm.getMyEnglishQuizDetailsForm().getQuestionDetailsId();
			questionDetailsWrapperEntity.getMyEnglishQuizAnswerEntity().setQuestionDetailsId(quiestionDetailsId);
			quizService.updateQuestionAnswer(questionDetailsWrapperEntity.getMyEnglishQuizAnswerEntity());
		}

}
