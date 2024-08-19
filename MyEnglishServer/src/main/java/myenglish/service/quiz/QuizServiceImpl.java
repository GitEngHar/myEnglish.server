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
import myenglish.domain.MyEnglishUserEntity;
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
//	タイトルを追加する
	@Override
	public void insertQuestionTitle(MyEnglishQuizTitleEntity title) {
		questionTitlePluginRepository.insert_title(title);	
		};
//	問題を追加する
	@Override
	public void insertQuestion(
			MyEnglishQuizDetailsEntity quiz) {
		questionDetailsPluginRepository.insert_question(quiz);
	};
//	タイトルごと問題を削除する
	@Override
	public void deleteQuestionTitle(
			MyEnglishQuizTitleEntity title) {
		questionTitlePluginRepository.delete_title(title.getQuestionTitleId());
	};
//  タイトル内の問題を1つ削除する
	@Override
	public void deleteQuestion(MyEnglishQuizDetailsEntity quiz) {
		questionDetailsPluginRepository.delete_question(quiz);
	};
//	自分のタイトルを取得し、Mapで返す
	@Override
	public List<MyEnglishQuizTitleEntity> getQuestionTitle(
			MyEnglishUserEntity userProperty){
		return questionTitlePluginRepository.select_by_userid(userProperty.getUserId());
	};
	@Override
	public void updateQuestion(MyEnglishQuizTitleEntity title) {
		questionTitlePluginRepository.update_title(title);
	}
	@Override
	public void updateQuestionDetails(MyEnglishQuizDetailsEntity quiz) {
		questionDetailsPluginRepository.update_question(quiz);
	}
//	タイトルを取得する
	@Override
	public MyEnglishQuizTitleEntity getQuestionTitleById(
			int questid){
		return questionTitlePluginRepository.select_by_id(questid);
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
