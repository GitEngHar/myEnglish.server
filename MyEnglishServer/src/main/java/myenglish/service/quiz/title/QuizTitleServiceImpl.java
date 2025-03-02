package myenglish.service.quiz.title;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import myenglish.domain.entity.MyEnglishQuizTitleEntity;
import myenglish.domain.entity.MyEnglishUserEntity;
import myenglish.mapper.QuestionTitlePluginRepository;
import myenglish.service.user.UserServiceImpl;
import myenglish.web.form.MyEnglishQuizTitleForm;
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
			MyEnglishQuizTitleForm titleForm,
			HttpSession session)
	{
//		MyEnglishQuizTitleEntity titleEntity = MyEnglishQuizTitleFormHelper.convertToEntity(title);
		int userId = userService.getUserId(session);
		MyEnglishQuizTitleEntity titleEntity = new MyEnglishQuizTitleEntity(
				titleForm.getQuestionTitleId(),
				userId,
				titleForm.getQuestionTitle()
			);
		questionTitlePluginRepository.insert_title(titleEntity);
	};

	//タイトルごと問題を削除する
	@Override
	public void deleteQuestionTitle(
			MyEnglishQuizTitleForm titleForm) {
		MyEnglishQuizTitleEntity titleEntity = new MyEnglishQuizTitleEntity(
				titleForm.getQuestionTitleId(),
				0,
				titleForm.getQuestionTitle()
		);
		questionTitlePluginRepository.delete_title(titleEntity.questionTitleId());
	};

	//対象ユーザーのタイトルを取得し、Mapで返す
	@Override
	public List<MyEnglishQuizTitleEntity> getQuestionTitle(
			HttpSession session){
		int userId = userService.getUserId(session);
		MyEnglishUserEntity userProperty = userService.getUser(null, userId);
		userProperty.setUserId(userId);
		return questionTitlePluginRepository.select_by_userid(userProperty.getUserId());
	};

	//タイトルの問題をアップデートする
	@Override
	public void updateQuestion(MyEnglishQuizTitleForm titleForm, HttpSession session) {
		int userId = userService.getUserId(session);
		int questionId = titleForm.getQuestionTitleId();
		String oldQuestionTitle = ""; //新規データ追加時に検索する為の旧データ
		MyEnglishQuizTitleEntity titleEntity;

		 // フロントエンドでID同期されていない場合の処理
		if(titleForm.getQuestionTitle().contains(",")){
			String[] splitQuestion = titleForm.getQuestionTitle().split(",");
			String newQuestionTitle = splitQuestion[0];
			titleEntity = new MyEnglishQuizTitleEntity(
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
		titleEntity = new MyEnglishQuizTitleEntity(
				titleForm.getQuestionTitleId(),
				userId,
				titleForm.getQuestionTitle()
		);
		questionTitlePluginRepository.update_title(titleEntity);
	}

	//タイトルを取得する
	@Override
	public MyEnglishQuizTitleEntity getQuestionTitleById(
			int questid){
		return questionTitlePluginRepository.select_by_id(questid);
	}


}
