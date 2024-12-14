package myenglish.web.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyEnglishQuizAnswerForm {

	@Size(min=1)
	private int questionAnswerId;

	@Size(min=1)
	private int questionTitleId;

	@Size(min=1)
	private int questionDetailsId;

	@Size(min=1,max=4)
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
