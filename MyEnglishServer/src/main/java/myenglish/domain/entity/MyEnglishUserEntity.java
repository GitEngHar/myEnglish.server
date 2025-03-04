package myenglish.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
	tableの概念に合わせて作成 (table 1行に相当)
	識別子(カラムとfield ??)が一緒なら一緒
*/

public record MyEnglishUserEntity (Integer userId, String name, String email){}
