package myenglish.service.quiz.details;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import myenglish.domain.dto.QuestionDetailsResponse;
import myenglish.domain.entity.*;
import myenglish.mapper.QuestionDetailsRepository;
import myenglish.service.user.UserService;
import myenglish.web.form.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class QuizDetailsServiceImpl implements QuizDetailsService {
	private final QuestionDetailsRepository questionDetailsRepository;
	private final UserService userService;

	@Override
	public List<QuestionDetailsResponse> getAllQuestionDetails(
			MyEnglishQuizTitleForm questionTitle,
			HttpSession session
	){
		// ユーザーIDを取得
		int userId = userService.getUserId(session);
		// 問題タイトルのentityをインスタンス生成
		MyEnglishQuizTitleEntity questionTitleEntity = new MyEnglishQuizTitleEntity(
				questionTitle.getQuestionTitleId(),
				userId,
				questionTitle.getQuestionTitle()
		);
		List<MyEnglishQuestionDetailsEntity> questionDetailsEntities = questionDetailsRepository.selectQuestionByTitleId(questionTitleEntity.questionTitleId());
		// DTOに変換して返す
		return questionDetailsEntities.stream()
				.map(QuestionDetailsResponse::fromEntity)
				.toList();
	}

	//問題を追加する
	@Override
	public void insertQuestion(
			QuestionDetailsForm questionDetailsForm) {
		MyEnglishQuestionDetailsEntity questionDetailsEntity = new MyEnglishQuestionDetailsEntity(
				questionDetailsForm.getQuestionDetailsId(),
				questionDetailsForm.getQuestionTitleId(),
				questionDetailsForm.getQuestionWord(),
				questionDetailsForm.getAnswerCandidateNo1(),
				questionDetailsForm.getAnswerCandidateNo2(),
				questionDetailsForm.getAnswerCandidateNo3(),
				questionDetailsForm.getAnswerCandidateNo4(),
				questionDetailsForm.getAnswerNumber(),
				null,
				null
		);
		questionDetailsRepository.insert_question(questionDetailsEntity);
	};


	//タイトル内の問題を1つ削除する
	@Override
	public void deleteQuestion(QuestionDetailsForm questionDetailsForm) {
		MyEnglishQuestionDetailsEntity questionDetailsEntity = new MyEnglishQuestionDetailsEntity(
				questionDetailsForm.getQuestionDetailsId(),
				questionDetailsForm.getQuestionTitleId(),
				questionDetailsForm.getQuestionWord(),
				questionDetailsForm.getAnswerCandidateNo1(),
				questionDetailsForm.getAnswerCandidateNo2(),
				questionDetailsForm.getAnswerCandidateNo3(),
				questionDetailsForm.getAnswerCandidateNo4(),
				questionDetailsForm.getAnswerNumber(),
				null,
				null
		);
		questionDetailsRepository.delete_question(questionDetailsEntity);
	};


	@Override
	public void updateQuestionDetails(QuestionDetailsForm questionDetailsForm) {
		MyEnglishQuestionDetailsEntity questionDetailsEntity = new MyEnglishQuestionDetailsEntity(
				questionDetailsForm.getQuestionDetailsId(),
				questionDetailsForm.getQuestionTitleId(),
				questionDetailsForm.getQuestionWord(),
				questionDetailsForm.getAnswerCandidateNo1(),
				questionDetailsForm.getAnswerCandidateNo2(),
				questionDetailsForm.getAnswerCandidateNo3(),
				questionDetailsForm.getAnswerCandidateNo4(),
				questionDetailsForm.getAnswerNumber(),
				null,
				null
		);
		questionDetailsRepository.update_question(questionDetailsEntity);
	}



}
