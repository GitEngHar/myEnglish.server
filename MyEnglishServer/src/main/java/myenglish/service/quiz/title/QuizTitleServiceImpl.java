package myenglish.service.quiz.title;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import myenglish.domain.dto.QuestionTitleResponse;
import myenglish.domain.entity.QuestionTitleEntity;
import myenglish.domain.entity.MyEnglishUserEntity;
import myenglish.mapper.QuestionTitlePluginRepository;
import myenglish.service.user.UserServiceImpl;
import myenglish.web.form.QuestionTitleForm;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class QuizTitleServiceImpl implements QuizTitleService {
	private final QuestionTitlePluginRepository questionTitlePluginRepository;
	private final UserServiceImpl userService;
	//タイトルを追加する
	@Override
	public void insertQuestionTitle(
			QuestionTitleForm titleForm,
			HttpSession session)
	{
//		MyEnglishQuizTitleEntity titleEntity = MyEnglishQuizTitleFormHelper.convertToEntity(title);
		int userId = userService.getUserId(session);
		QuestionTitleEntity titleEntity = new QuestionTitleEntity(
				titleForm.getQuestionTitleId(),
				userId,
				titleForm.getQuestionTitle()
			);
		questionTitlePluginRepository.insert_title(titleEntity);
	};

	//タイトルごと問題を削除する
	@Override
	public void deleteQuestionTitle(
			QuestionTitleForm titleForm) {
		QuestionTitleEntity titleEntity = new QuestionTitleEntity(
				titleForm.getQuestionTitleId(),
				0,
				titleForm.getQuestionTitle()
		);
		questionTitlePluginRepository.delete_title(titleEntity.questionTitleId());
	};

	//対象ユーザーのタイトルを取得し、Mapで返す
	@Override
	public List<QuestionTitleResponse> getQuestionTitle(
			HttpSession session){
		int userId = userService.getUserId(session);
		MyEnglishUserEntity userProperty = userService.getUser(null, userId);
		userProperty.setUserId(userId);
		List<QuestionTitleEntity> questionTitleEntity = questionTitlePluginRepository.select_by_userid(userProperty.getUserId());
		return questionTitleEntity.stream().map(QuestionTitleResponse::fromEntity).toList();
	};

	//タイトルの問題をアップデートする
	@Override
	public void updateQuestionTitle(QuestionTitleForm titleForm, HttpSession session) {
		int userId = userService.getUserId(session);
		int questionId = titleForm.getQuestionTitleId();
		String oldQuestionTitle = ""; //新規データ追加時に検索する為の旧データ
		QuestionTitleEntity titleEntity;

		 // フロントエンドでID同期されていない場合の処理
		if(titleForm.getQuestionTitle().contains(",")){
			String[] splitQuestion = titleForm.getQuestionTitle().split(",");
			String newQuestionTitle = splitQuestion[0];
			titleEntity = new QuestionTitleEntity(
					titleForm.getQuestionTitleId(),
					userId,
					newQuestionTitle
			);
			if(questionId == 0){
				// 旧タイトルデータを取得
				oldQuestionTitle = splitQuestion[1];
				questionTitlePluginRepository.update_title_by_old_question(titleEntity,oldQuestionTitle);
				return;
			}
			questionTitlePluginRepository.update_title(titleEntity);
			return;
		}
		// questionId が 0の場合 新規追加時の編集のため Titleをperseして旧titleでクエリする
		titleEntity = new QuestionTitleEntity(
				titleForm.getQuestionTitleId(),
				userId,
				titleForm.getQuestionTitle()
		);
		questionTitlePluginRepository.update_title(titleEntity);
	}
}
