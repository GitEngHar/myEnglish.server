package myenglish.web.form;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyEnglishQuizTitleForm {	
	private int questionTitleId;
	private int ownerUserId;
	@NotBlank(message="入力必須")
	private String questionTitle;
	private boolean booladd;
}
