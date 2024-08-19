package myenglish.helper;

import myenglish.domain.MyEnglishQuizDetailsWrapperEntity;
import myenglish.web.form.MyEnglishQuizDetailsWrapperForm;

public class MyEnglishQuizDetailsWrapperFormHelper {
//	EntityをFormに変換
	public static MyEnglishQuizDetailsWrapperForm convertToForm(MyEnglishQuizDetailsWrapperEntity entity) {
		MyEnglishQuizDetailsWrapperForm form = new MyEnglishQuizDetailsWrapperForm();
		form.setMyEnglishQuizAnswerForm(MyEnglishQuizAnswerFormHelper.convertToForm(entity.getMyEnglishQuizAnswerEntity()));
		form.setMyEnglishQuizDetailsForm(MyEnglishQuizDetailsFormHelper.convertToForm(entity.getMyEnglishQuizDetailsEntity()));
		return form;
	}
//	FormをEntityに変換
	public static MyEnglishQuizDetailsWrapperEntity convertToEntity(MyEnglishQuizDetailsWrapperForm form) {
		MyEnglishQuizDetailsWrapperEntity entity = new MyEnglishQuizDetailsWrapperEntity();
		entity.setMyEnglishQuizAnswerEntity(MyEnglishQuizAnswerFormHelper.convertToEntity(form.getMyEnglishQuizAnswerForm()));
		entity.setMyEnglishQuizDetailsEntity(MyEnglishQuizDetailsFormHelper.convertToEntity(form.getMyEnglishQuizDetailsForm()));
		return entity;
		
	}
}
