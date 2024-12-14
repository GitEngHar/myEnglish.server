package myenglish.service.quiz;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import myenglish.domain.MyEnglishQuizAnswerEntity;
import myenglish.domain.MyEnglishQuizDetailsEntity;
import myenglish.domain.MyEnglishQuizTitleEntity;
import myenglish.mapper.QuestionAnswerPluginRepository;
import myenglish.mapper.QuestionDetailsPluginRepository;
import myenglish.mapper.QuestionTitlePluginRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService{
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
	public void deleteQuestion(MyEnglishQuizDetailsEntity quiz) {
		questionDetailsPluginRepository.delete_question(quiz);
	};


	@Override
	public void updateQuestionDetails(MyEnglishQuizDetailsEntity quiz) {
		questionDetailsPluginRepository.update_question(quiz);
	}

	// 問題を取得する
	@Override
	public List<MyEnglishQuizDetailsEntity> getQuestionDetails(
			MyEnglishQuizTitleEntity title){
		return questionDetailsPluginRepository.selectQuestionByTitleId(title.getQuestionTitleId());
	}
	@Override
	public MyEnglishQuizDetailsEntity getQuestionDetailsById(int id) {
		return questionDetailsPluginRepository.selectQuestionByDetailsId(id) ;
	};
	@Override
	public List<MyEnglishQuizAnswerEntity> getQuestionAnswer(MyEnglishQuizTitleEntity title){
		return questionAnswerPluginRepository.selectQuestioAnswernByTitleId(title.getQuestionTitleId());
	};
	@Override
	public MyEnglishQuizAnswerEntity getQuestionAnswerById(int id) {
		return questionAnswerPluginRepository.selectQuestionAnswerByDetailsId(id);
	};
	@Override
	public void insertQuestionAnswer(MyEnglishQuizAnswerEntity answer) {
		questionAnswerPluginRepository.insert_question_answer(answer);
	};
	@Override
	public void updateQuestionAnswer(MyEnglishQuizAnswerEntity answer) {
		questionAnswerPluginRepository.update_question_answer(answer);
	};
	@Override
	public Optional<MyEnglishQuizDetailsEntity> getQuestionLastestDetails(int id){
		List<MyEnglishQuizDetailsEntity> myEnglishQuizDetailsEntity = questionDetailsPluginRepository.selectQuestionByTitleId(id);
		return myEnglishQuizDetailsEntity.stream().max(Comparator.comparing(MyEnglishQuizDetailsEntity::getCreatedDate));
	}
}
