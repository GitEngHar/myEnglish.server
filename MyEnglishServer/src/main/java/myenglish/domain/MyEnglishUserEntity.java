package myenglish.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
	tableの概念に合わせて作成 (table 1行に相当)
	識別子(カラムとfield ??)が一緒なら一緒
*/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyEnglishUserEntity {
	private Integer userId;
	private String name;
	private String email;
	public MyEnglishUserEntity(String name, String email) {
		this.name = name;
		this.email = email;
	}
}
