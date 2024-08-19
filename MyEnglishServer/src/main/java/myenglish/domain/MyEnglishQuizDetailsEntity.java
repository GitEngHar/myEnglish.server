package myenglish.domain;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyEnglishQuizDetailsEntity {
	private int questionDetailsId;
	private int questionTitleId;
	private String questionWord;
	private LocalDateTime createdDate;
	private LocalDateTime updateDate;
}
