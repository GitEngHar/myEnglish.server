package myenglish.web.form;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyEnglishQuizTitleForm {
	@Min(0)
	private int questionTitleId;
	@Min(0)
	private int ownerUserId;
	@NotBlank(message="入力必須")
	@Pattern(regexp = "^[a-zA-Z0-9ぁ-んァ-ン一-龥々ー]*$", message = "特殊文字は使用できません（英数字および日本語のみ許可）")
	private String questionTitle;
	private boolean booladd;
}
