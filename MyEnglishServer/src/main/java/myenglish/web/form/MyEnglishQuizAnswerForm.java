package myenglish.web.form;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyEnglishQuizAnswerForm {

	@Min(0)
	private int questionAnswerId;

	@Min(0)
	private int questionTitleId;

	@Min(0)
	private int questionDetailsId;

	@Min(1)
	@Max(4)
	private int answerId;

	@NotBlank()
	@Pattern(regexp = "^[a-zA-Z0-9ぁ-んァ-ン一-龥々ー]*$", message = "特殊文字は使用できません（英数字および日本語のみ許可）")
	private String answerCandidateNo1;

	@NotBlank()
	@Pattern(regexp = "^[a-zA-Z0-9ぁ-んァ-ン一-龥々ー]*$", message = "特殊文字は使用できません（英数字および日本語のみ許可）")
	private String answerCandidateNo2;

	@NotBlank()
	@Pattern(regexp = "^[a-zA-Z0-9ぁ-んァ-ン一-龥々ー]*$", message = "特殊文字は使用できません（英数字および日本語のみ許可）")
	private String answerCandidateNo3;

	@NotBlank()
	@Pattern(regexp = "^[a-zA-Z0-9ぁ-んァ-ン一-龥々ー]*$", message = "特殊文字は使用できません（英数字および日本語のみ許可）")
	private String answerCandidateNo4;
}
