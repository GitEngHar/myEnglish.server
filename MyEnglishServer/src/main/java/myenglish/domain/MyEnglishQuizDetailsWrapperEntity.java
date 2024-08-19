package myenglish.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MyEnglishQuizDetailsWrapperEntity {
	private MyEnglishQuizAnswerEntity myEnglishQuizAnswerEntity;
	private MyEnglishQuizDetailsEntity myEnglishQuizDetailsEntity;
	
	/* フィールド初期化用コンストラクタ */
	public MyEnglishQuizDetailsWrapperEntity(){
		this.myEnglishQuizAnswerEntity = new MyEnglishQuizAnswerEntity();
		this.myEnglishQuizDetailsEntity = new MyEnglishQuizDetailsEntity() ;
	}
}
