package myenglish.web.quiz;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import myenglish.domain.MyEnglishQuizTitleEntity;
import myenglish.domain.MyEnglishUserEntity;
import myenglish.helper.MyEnglishQuizTitleFormHelper;
import myenglish.service.quiz.QuizServiceImpl;
import myenglish.web.form.MyEnglishQuizTitleForm;

@RequestMapping(value="/quiz")
@RequiredArgsConstructor
@Controller
public class MyEnglishQuizController {
	private final QuizServiceImpl quizService;
	
	/** クイズのトップ画面 **/
	@GetMapping("")
	public String entryQuiz(Model model) {
		/** MockUserData **/
		MyEnglishUserEntity userProperty = new MyEnglishUserEntity();
		userProperty.setUserId(1);
		
		// 問題のタイトルを取得
		List<MyEnglishQuizTitleEntity> questionTitles = quizService.getQuestionTitle(userProperty);
		boolean isGetTitleNull = questionTitles.stream().anyMatch(title -> title == null);
		if(isGetTitleNull) {
			System.out.println("Nullが入ってきてるよ！");
		}
		// 取得したタイトル群を表示
		model.addAttribute("quiestionProperty",questionTitles);
		return "quiz/index";
	}
	
	/** クイズタイトルをDBへ保存 **/
	@PostMapping("/save")
	public String saveQuizTitle(@Validated MyEnglishQuizTitleForm form,
			BindingResult bindingResult,
			Model model) {
		// TODO:エラーをハンドリングする
		// 入力されたタイトルフォームを変換しDBに登録
		MyEnglishQuizTitleEntity titleEntity = MyEnglishQuizTitleFormHelper.convertToEntity(form);
		quizService.insertQuestionTitle(titleEntity);
		return "redirect:/quiz";
	}
	
	/** クイズタイトル登録画面 **/
	@GetMapping("/form")
	public String quizForm(@ModelAttribute("quizform") MyEnglishQuizTitleForm quizform,Model model) {
		// UserIdをセット
		quizform.setOwnerUserId(1);
		quizform.setBooladd(true);
		return "quiz/form";
	}
	
	// クイズ編集画面
	@GetMapping("/edit/{id}")
	public String quizForm(@PathVariable("id") int id ,Model model) {
		// UserIdを取得
		MyEnglishQuizTitleEntity entity = quizService.getQuestionTitleById(id);
		MyEnglishQuizTitleForm form = MyEnglishQuizTitleFormHelper.convertToForm(entity);
		model.addAttribute("quizform",form);
		return "quiz/form";
	}
	
	// クイズ更新画面
	@PostMapping("/update")
	public String quizForm(@Validated MyEnglishQuizTitleForm form,
			BindingResult bindingResult) {
			// TODO:エラーをハンドリングする
			/** 編集した内容でアップデート **/	
			MyEnglishQuizTitleEntity titleEntity = MyEnglishQuizTitleFormHelper.convertToEntity(form);
			quizService.updateQuestion(titleEntity);
			return "redirect:/quiz";
		}	
	/** クイズ削除 **/
	@GetMapping("/delete/{id}")
	public String quizTitleDelete(@PathVariable("id") int id) {
		MyEnglishQuizTitleEntity entity = quizService.getQuestionTitleById(id);
		quizService.deleteQuestionTitle(entity);
		return "redirect:/quiz";
	}
}
