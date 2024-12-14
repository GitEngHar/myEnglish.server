package myenglish.service.quiz.title;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import myenglish.domain.MyEnglishQuizTitleEntity;
import myenglish.domain.MyEnglishUserEntity;
import myenglish.helper.MyEnglishQuizTitleFormHelper;
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
			MyEnglishQuizTitleForm title,
			HttpSession session)
	{
		MyEnglishQuizTitleEntity titleEntity = MyEnglishQuizTitleFormHelper.convertToEntity(title);
		int userId = Integer.parseInt((String) session.getAttribute("userId"));
		titleEntity.setOwnerUserId(userId);
		questionTitlePluginRepository.insert_title(titleEntity);
	};

	//タイトルごと問題を削除する
	@Override
	public void deleteQuestionTitle(
			MyEnglishQuizTitleForm form) {
		int id = form.getQuestionTitleId();
		MyEnglishQuizTitleEntity entity = getQuestionTitleById(id);
		questionTitlePluginRepository.delete_title(entity.getQuestionTitleId());
	};

	//対象ユーザーのタイトルを取得し、Mapで返す
	@Override
	public List<MyEnglishQuizTitleEntity> getQuestionTitle(
			HttpSession session){
		int userId = Integer.parseInt((String) session.getAttribute("userId"));
		MyEnglishUserEntity userProperty = userService.getUser(null, userId);
		userProperty.setUserId(userId);
		return questionTitlePluginRepository.select_by_userid(userProperty.getUserId());
	};

	//タイトルの問題をアップデートする
	@Override
	public void updateQuestion(MyEnglishQuizTitleForm form,HttpSession session) {
		int userId = Integer.parseInt((String) session.getAttribute("userId"));
		MyEnglishQuizTitleEntity titleEntity = MyEnglishQuizTitleFormHelper.convertToEntity(form);
		titleEntity.setOwnerUserId(userId);
		questionTitlePluginRepository.update_title(titleEntity);
	}

	//タイトルを取得する
	@Override
	public MyEnglishQuizTitleEntity getQuestionTitleById(
			int questid){
		return questionTitlePluginRepository.select_by_id(questid);
	}


}
