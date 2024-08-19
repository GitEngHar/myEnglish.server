package myenglish.web.form;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyEnglishQuizDetailsForm {
	private int questionDetailsId;
	private int questionTitleId;
	@NotBlank(message="入力必須")
	private String questionWord;
	private boolean booladd;
}