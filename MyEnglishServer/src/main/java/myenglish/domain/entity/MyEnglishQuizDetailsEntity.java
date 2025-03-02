package myenglish.domain.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// TODO:削除する
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
