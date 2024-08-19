package myenglish.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyEnglishQuizAnswerEntity {
	private int questionAnswerId;
	private int questionTitleId;
	private int questionDetailsId;
	private int answerId;
	private String answerCandidateNo1;
	private String answerCandidateNo2;
	private String answerCandidateNo3;
	private String answerCandidateNo4;
}
