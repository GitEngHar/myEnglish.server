package myenglish.service.quiz.details;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import myenglish.domain.MyEnglishQuizAnswerEntity;
import myenglish.domain.MyEnglishQuizDetailsEntity;
import myenglish.domain.MyEnglishQuizDetailsWrapperEntity;
import myenglish.domain.MyEnglishQuizTitleEntity;
import myenglish.helper.MyEnglishQuizAnswerFormHelper;
import myenglish.helper.MyEnglishQuizDetailsWrapperFormHelper;
import myenglish.helper.MyEnglishQuizTitleFormHelper;
import myenglish.mapper.QuestionAnswerPluginRepository;
import myenglish.mapper.QuestionDetailsPluginRepository;
import myenglish.mapper.QuestionTitlePluginRepository;
import myenglish.web.form.MyEnglishQuizAnswerForm;
import myenglish.web.form.MyEnglishQuizDetailsForm;
import myenglish.web.form.MyEnglishQuizDetailsWrapperForm;
import myenglish.web.form.MyEnglishQuizTitleForm;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class QuizDetailsServiceImpl implements QuizDetailsService {
	private final QuestionDetailsPluginRepository questionDetailsPluginRepository;
	private final QuestionTitlePluginRepository questionTitlePluginRepository;
	private final QuestionAnswerPluginRepository questionAnswerPluginRepository;

	//問題を追加する
	@Override
	public void insertQuestion(
			MyEnglishQuizDetailsEntity quiz) {
		questionDetailsPluginRepository.insert_question(quiz);
	};

	//タイトル内の問題を1つ削除する
	@Override
	public void deleteQuestion(MyEnglishQuizDetailsForm quiz) {
		MyEnglishQuizDetailsEntity entity =  getQuestionDetailsById(quiz.getQuestionDetailsId());
		questionDetailsPluginRepository.delete_question(entity);
	};


	@Override
	public void updateQuestionDetails(MyEnglishQuizDetailsEntity quiz) {
		questionDetailsPluginRepository.update_question(quiz);
	}

	// 問題を取得する
	@Override
	public List<MyEnglishQuizDetailsEntity> getQuestionDetails(
			MyEnglishQuizTitleForm quiestionTitle,
			HttpSession session
			){
		int userId = Integer.parseInt((String) session.getAttribute("userId"));
		MyEnglishQuizTitleEntity quiestionTitleEntity = MyEnglishQuizTitleFormHelper.convertToEntity(quiestionTitle);
		quiestionTitleEntity.setOwnerUserId(userId);
		return questionDetailsPluginRepository.selectQuestionByTitleId(quiestionTitleEntity.getQuestionTitleId());
	}
	@Override
	public MyEnglishQuizDetailsEntity getQuestionDetailsById(int id) {
		return questionDetailsPluginRepository.selectQuestionByDetailsId(id) ;
	};

	@Override
	public MyEnglishQuizAnswerForm getQuestionAnswerById(MyEnglishQuizDetailsForm quizDetailsForm) {
		MyEnglishQuizAnswerEntity questionAnswerEntity = questionAnswerPluginRepository.selectQuestionAnswerByDetailsId(quizDetailsForm.getQuestionDetailsId());
		return MyEnglishQuizAnswerFormHelper.convertToForm(questionAnswerEntity);
	}

	@Override
	@Transactional
	public void insertQuestionAnswer(MyEnglishQuizDetailsWrapperForm quizDetailsWrapperForm) {
		// クイズタイトルIDを取得
		int parentQuestId = quizDetailsWrapperForm.getMyEnglishQuizDetailsForm().getQuestionTitleId();
		// 入力で受け取った各FormをWrapperへ変換
		MyEnglishQuizDetailsForm questionDetailsForm = quizDetailsWrapperForm.getMyEnglishQuizDetailsForm();
		MyEnglishQuizAnswerForm questionAnswerForm = quizDetailsWrapperForm.getMyEnglishQuizAnswerForm();
		MyEnglishQuizDetailsWrapperForm questionDetailsWrapperForm = new MyEnglishQuizDetailsWrapperForm();
		questionDetailsWrapperForm.setMyEnglishQuizAnswerForm(questionAnswerForm);
		questionDetailsWrapperForm.setMyEnglishQuizDetailsForm(questionDetailsForm);
		MyEnglishQuizDetailsWrapperEntity questionDetailsWrapperEntity = MyEnglishQuizDetailsWrapperFormHelper.convertToEntity(questionDetailsWrapperForm);

		// 問題を追加
		insertQuestion(questionDetailsWrapperEntity.getMyEnglishQuizDetailsEntity());

		// 答えと選択肢を追加
		Optional<MyEnglishQuizDetailsEntity> questionLatestDetailsEntity = getQuestionLastestDetails(parentQuestId);
		int latestQuestionDetailsId = questionLatestDetailsEntity.get().getQuestionDetailsId();
		questionDetailsWrapperEntity.getMyEnglishQuizAnswerEntity().setQuestionDetailsId(latestQuestionDetailsId);
		questionDetailsWrapperEntity.getMyEnglishQuizAnswerEntity().setQuestionTitleId(parentQuestId);
		questionAnswerPluginRepository.insert_question_answer(questionDetailsWrapperEntity.getMyEnglishQuizAnswerEntity());


	}

	@Override
	@Transactional
	public void updateQuestionAnswer(MyEnglishQuizDetailsWrapperForm quizDetailsWrapperForm) {
		// クイズ問題をEntityに変換
		MyEnglishQuizDetailsWrapperEntity questionDetailsWrapperEntity = MyEnglishQuizDetailsWrapperFormHelper.convertToEntity(quizDetailsWrapperForm);
		updateQuestionDetails(questionDetailsWrapperEntity.getMyEnglishQuizDetailsEntity());

		// 回答へ問題詳細IDを代入する (何の問題の答えかわかるようにする)
		int quiestionDetailsId = quizDetailsWrapperForm.getMyEnglishQuizDetailsForm().getQuestionDetailsId();
		questionDetailsWrapperEntity.getMyEnglishQuizAnswerEntity().setQuestionDetailsId(quiestionDetailsId);

		// 回答情報を更新する
		MyEnglishQuizAnswerEntity answer = questionDetailsWrapperEntity.getMyEnglishQuizAnswerEntity();
		questionAnswerPluginRepository.update_question_answer(answer);

	}

	@Override
	public Optional<MyEnglishQuizDetailsEntity> getQuestionLastestDetails(int id){
		List<MyEnglishQuizDetailsEntity> myEnglishQuizDetailsEntity = questionDetailsPluginRepository.selectQuestionByTitleId(id);
		return myEnglishQuizDetailsEntity.stream().max(Comparator.comparing(MyEnglishQuizDetailsEntity::getCreatedDate));
	}

	@Override
	public List<MyEnglishQuizAnswerEntity>  getQuestionAnswer(int id){
		return questionAnswerPluginRepository.selectQuestioAnswernByTitleId(id);
	}

}
