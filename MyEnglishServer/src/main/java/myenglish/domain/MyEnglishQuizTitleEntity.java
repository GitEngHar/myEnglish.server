package myenglish.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyEnglishQuizTitleEntity {
	private int questionTitleId;
	private int ownerUserId;
	private String questionTitle;
}
