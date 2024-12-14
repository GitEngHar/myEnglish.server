package myenglish.web.form;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MyEnglishQuizDetailsWrapperForm {
	@Valid
	private MyEnglishQuizAnswerForm myEnglishQuizAnswerForm;
	@Valid
	private MyEnglishQuizDetailsForm myEnglishQuizDetailsForm;
	
	public MyEnglishQuizDetailsWrapperForm() {
		this.myEnglishQuizAnswerForm = new MyEnglishQuizAnswerForm();
		this.myEnglishQuizDetailsForm = new MyEnglishQuizDetailsForm();
	}
}
