package myenglish.helper;

import myenglish.domain.MyEnglishQuizAnswerEntity;
import myenglish.web.form.MyEnglishQuizAnswerForm;

public class MyEnglishQuizAnswerFormHelper {
//	EntityをFormに変換
	public static MyEnglishQuizAnswerForm convertToForm(MyEnglishQuizAnswerEntity entity) {
		MyEnglishQuizAnswerForm form = new MyEnglishQuizAnswerForm();
		form.setQuestionAnswerId(entity.getQuestionAnswerId());
		form.setQuestionTitleId(entity.getQuestionTitleId());
		form.setQuestionDetailsId(entity.getQuestionDetailsId());
		form.setAnswerCandidateNo1(entity.getAnswerCandidateNo1());
		form.setAnswerCandidateNo2(entity.getAnswerCandidateNo2());
		form.setAnswerCandidateNo3(entity.getAnswerCandidateNo3());
		form.setAnswerCandidateNo4(entity.getAnswerCandidateNo4());
		form.setAnswerId(entity.getAnswerId());
		return form;
	}
//	FormをEntityに変換
	public static MyEnglishQuizAnswerEntity convertToEntity(MyEnglishQuizAnswerForm form) {
		MyEnglishQuizAnswerEntity entity = new MyEnglishQuizAnswerEntity();
		entity.setQuestionAnswerId(form.getQuestionAnswerId());
		entity.setQuestionTitleId(form.getQuestionTitleId());
		entity.setQuestionDetailsId(form.getQuestionDetailsId());
		entity.setAnswerCandidateNo1(form.getAnswerCandidateNo1());
		entity.setAnswerCandidateNo2(form.getAnswerCandidateNo2());
		entity.setAnswerCandidateNo3(form.getAnswerCandidateNo3());
		entity.setAnswerCandidateNo4(form.getAnswerCandidateNo4());
		entity.setAnswerId(form.getAnswerId());
		return entity;
		
	}
}
