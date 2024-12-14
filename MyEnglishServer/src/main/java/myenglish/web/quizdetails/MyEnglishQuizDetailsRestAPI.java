package myenglish.web.quizdetails;

import java.util.List;
import java.util.Optional;

import com.nimbusds.oauth2.sdk.http.HTTPResponse;
import jakarta.servlet.http.HttpSession;
import myenglish.helper.MyEnglishQuizAnswerFormHelper;
import myenglish.helper.MyEnglishQuizDetailsFormHelper;
import myenglish.service.quiz.details.QuizDetailsServiceImpl;
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
	
	private final QuizDetailsServiceImpl quizDetailsService;
	
	/** クイズ問題画面 **/
	@CrossOrigin
	(origins = "http://localhost:3000")
	@PostMapping("/")
	public List<MyEnglishQuizDetailsEntity> quizdetails(@RequestBody @Validated MyEnglishQuizTitleForm quiestionTitle, BindingResult bindingResult,HttpSession session) {
		if(bindingResult.hasErrors()){
			//TODO: 400エラーと分かる内容を返す
			System.out.println("ERROR!");
			return null;
		}else{
			/** クイズタイトルに該当の問題情報を取得する **/
			List<MyEnglishQuizDetailsEntity> quizDetails =
					quizDetailsService.getQuestionDetails(quiestionTitle,session);
			return quizDetails;
		}
	}
	
	/* クイズ情報を追加する */
	@CrossOrigin
	(origins = "http://localhost:3000")
	@PostMapping("/save")
	public void saveQuizDetails(@RequestBody @Validated MyEnglishQuizDetailsWrapperForm quizDetailsWrapperForm,
								BindingResult bindingResult
			) {
		if(bindingResult.hasErrors()){
			// TODO:400エラー
			System.out.println("ERROR!");
		}else{
			quizDetailsService.insertQuestionAnswer(quizDetailsWrapperForm);
		}
	}
	
	/*クイズ削除 delete */
	@CrossOrigin
	(origins = "http://localhost:3000")
	@PostMapping("/delete")
	public void quizDelete(@RequestBody  MyEnglishQuizDetailsForm quizDetailsForm) {
		quizDetailsService.deleteQuestion(quizDetailsForm);
	}
	
	/* クイズ編集画面 */
	@CrossOrigin
	(origins = "http://localhost:3000")
	@PostMapping("/edit")
	public MyEnglishQuizAnswerForm quizEdit(@RequestBody @Validated MyEnglishQuizDetailsForm quizDetailsForm,BindingResult bindingResult) {
		if(bindingResult.hasErrors()){
			System.out.println("ERROR!");
			return null;
		}else{
			return quizDetailsService.getQuestionAnswerById(quizDetailsForm);
		}

	}
	
	/* クイズ更新画面 */
	@CrossOrigin
	(origins = "http://localhost:3000")
	@PostMapping("/update")
	public void quizForm(@RequestBody @Validated MyEnglishQuizDetailsWrapperForm quizDetailsWrapperForm,BindingResult bindingResult,HttpSession session) {
			// TODO:エラーハンドル
			/* 編集した内容で問題と答えをアップデート */
		if(bindingResult.hasErrors()){
			System.out.println("ERROR!");
		}else{
			quizDetailsService.updateQuestionAnswer(quizDetailsWrapperForm);
		}
	}

}
