package myenglish.web.form;

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
	private String answerCandidateNo1;
	private String answerCandidateNo2;
	private String answerCandidateNo3;
	private String answerCandidateNo4;
}
