package myenglish.helper;

import myenglish.domain.MyEnglishQuizTitleEntity;
import myenglish.web.form.MyEnglishQuizTitleForm;

public class MyEnglishQuizTitleFormHelper{
	public static MyEnglishQuizTitleForm convertToForm(MyEnglishQuizTitleEntity entity) {
		MyEnglishQuizTitleForm form = new MyEnglishQuizTitleForm();
		form.setQuestionTitle(entity.getQuestionTitle());
		form.setOwnerUserId(entity.getOwnerUserId());
		form.setQuestionTitleId(entity.getQuestionTitleId());
		form.setBooladd(false);
		return form;
	}
	public static MyEnglishQuizTitleEntity convertToEntity(MyEnglishQuizTitleForm form) {
		MyEnglishQuizTitleEntity entity = new MyEnglishQuizTitleEntity();
		entity.setQuestionTitle(form.getQuestionTitle());
		entity.setOwnerUserId(form.getOwnerUserId());
		entity.setQuestionTitleId(form.getQuestionTitleId());
		return entity;
		
	}
}
