package myenglish.service.quiz.takequiz;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import myenglish.domain.MyEnglishQuizAnswerEntity;
import myenglish.domain.MyEnglishQuizDetailsEntity;
import myenglish.domain.MyEnglishQuizDetailsWrapperEntity;
import myenglish.domain.MyEnglishQuizTitleEntity;
import myenglish.helper.MyEnglishQuizDetailsWrapperFormHelper;
import myenglish.mapper.QuestionAnswerPluginRepository;
import myenglish.mapper.QuestionDetailsPluginRepository;
import myenglish.mapper.QuestionTitlePluginRepository;
import myenglish.service.quiz.details.QuizDetailsServiceImpl;
import myenglish.web.form.MyEnglishQuizDetailsWrapperForm;
import myenglish.web.form.MyEnglishQuizTitleForm;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@Transactional
@RequiredArgsConstructor
public class TakeQuizServiceImpl implements TakeQuizService {
	private final QuizDetailsServiceImpl quizDetailsServiceImpl;

	@Override
	public List<MyEnglishQuizDetailsWrapperForm> takeQuiz(MyEnglishQuizTitleForm questionTitle, HttpSession session){
		// 対象の問題と答えをWrapperへ格納
		List<MyEnglishQuizAnswerEntity> myEnglishQuizAnswerEntity = quizDetailsServiceImpl.getQuestionAnswer(questionTitle.getQuestionTitleId());
		List<MyEnglishQuizDetailsEntity> myEnglishQuizDetailsEntity = quizDetailsServiceImpl.getQuestionDetails(questionTitle,session);

		// 返信用にWrapperへ格納 (サイズが異なる場合への対応)
		List<MyEnglishQuizDetailsWrapperEntity> MyEnglishQuizDetailsWrapperEntityList = IntStream.range(0,Math.min(myEnglishQuizDetailsEntity.size(), myEnglishQuizAnswerEntity.size()))
				.mapToObj(i -> new MyEnglishQuizDetailsWrapperEntity(myEnglishQuizAnswerEntity.get(i),myEnglishQuizDetailsEntity.get(i))).collect(Collectors.toList());

		List<MyEnglishQuizDetailsWrapperForm> MyEnglishQuizDetailsWrapperFormList = MyEnglishQuizDetailsWrapperEntityList.stream()
				.map(wrapperEntity -> MyEnglishQuizDetailsWrapperFormHelper.convertToForm(wrapperEntity)
				).collect(Collectors.toList());

		return MyEnglishQuizDetailsWrapperFormList;
	}
}

