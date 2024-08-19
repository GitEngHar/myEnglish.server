package myenglish.helper;

import myenglish.domain.MyEnglishQuizDetailsEntity;
import myenglish.web.form.MyEnglishQuizDetailsForm;

public class MyEnglishQuizDetailsFormHelper {
//	EntityをFormに変換
	public static MyEnglishQuizDetailsForm convertToForm(MyEnglishQuizDetailsEntity entity) {
		MyEnglishQuizDetailsForm form = new MyEnglishQuizDetailsForm();
		form.setQuestionDetailsId(entity.getQuestionDetailsId());
		form.setQuestionWord(entity.getQuestionWord());
		form.setQuestionTitleId(entity.getQuestionTitleId());
		form.setBooladd(false);
		return form;
	}
//	FormをEntityに変換
	public static MyEnglishQuizDetailsEntity convertToEntity(MyEnglishQuizDetailsForm form) {
		MyEnglishQuizDetailsEntity entity = new MyEnglishQuizDetailsEntity();
		entity.setQuestionDetailsId(form.getQuestionDetailsId());
		entity.setQuestionWord(form.getQuestionWord());
		entity.setQuestionTitleId(form.getQuestionTitleId());
		return entity;
		
	}
}
