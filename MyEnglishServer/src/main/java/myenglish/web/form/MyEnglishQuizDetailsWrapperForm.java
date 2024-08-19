package myenglish.web.form;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MyEnglishQuizDetailsWrapperForm {
	private MyEnglishQuizAnswerForm myEnglishQuizAnswerForm;
	private MyEnglishQuizDetailsForm myEnglishQuizDetailsForm;
	
	public MyEnglishQuizDetailsWrapperForm() {
		this.myEnglishQuizAnswerForm = new MyEnglishQuizAnswerForm();
		this.myEnglishQuizDetailsForm = new MyEnglishQuizDetailsForm();
	}
}
