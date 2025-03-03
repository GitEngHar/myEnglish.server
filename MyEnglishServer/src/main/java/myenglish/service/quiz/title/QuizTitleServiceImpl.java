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
		// セッションからUserIdを取得
		int userId = userService.getUserId(session);
		// userEntityを生成する
		MyEnglishUserEntity userProperty = userService.getUser(null, userId);
		if (userProperty==null) {
			// TODO: 直す
			throw new RuntimeException("サーバ側でセッションが継続していません。Cookieを削除して再度ログインしてください");
		}
		userProperty.setUserId(userId);
		// userの問題タイトルを取得する
		List<QuestionTitleEntity> questionTitleEntity = questionTitlePluginRepository.select_by_userid(userProperty.getUserId());
		// userの問題タイトルをresponse形式に変換して返す
		return questionTitleEntity.stream().map(QuestionTitleResponse::fromEntity).toList();
	};

	//タイトルの問題をアップデートする
	@Override
	public void updateQuestionTitle(QuestionTitleForm titleForm, HttpSession session) {
		int userId = userService.getUserId(session);
		QuestionTitleEntity titleEntity = new QuestionTitleEntity(
				titleForm.getQuestionTitleId(),
				userId,
				titleForm.getQuestionTitle()
		);
		questionTitlePluginRepository.update_title(titleEntity);
	}
}
