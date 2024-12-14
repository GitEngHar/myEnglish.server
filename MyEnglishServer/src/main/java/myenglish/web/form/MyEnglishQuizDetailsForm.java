package myenglish.web.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyEnglishQuizDetailsForm {

	@Size(min=1)
	private int questionDetailsId;

	@Size(min=1)
	private int questionTitleId;

	@NotBlank()
	@Pattern(regexp = "^[a-zA-Z0-9ぁ-んァ-ン一-龥々ー]*$", message = "特殊文字は使用できません（英数字および日本語のみ許可）")
	private String questionWord;

	private boolean booladd;
}