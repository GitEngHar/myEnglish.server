package myenglish.web.form;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDetailsForm {
    @Min(0)
    private int questionDetailsId;

    @Min(0)
    private int questionTitleId;

    @NotBlank()
    @Pattern(regexp = "^[a-zA-Z0-9ぁ-んァ-ン一-龥々ー]*$", message = "特殊文字は使用できません（英数字および日本語のみ許可）")
    private String questionWord;

    @NotBlank()
    @Pattern(regexp = "^[a-zA-Z0-9ぁ-んァ-ン一-龥々ー]*$", message = "特殊文字は使用できません（英数字および日本語のみ許可）")
    private String answerCandidateNo1;

    @NotBlank()
    @Pattern(regexp = "^[a-zA-Z0-9ぁ-んァ-ン一-龥々ー]*$", message = "特殊文字は使用できません（英数字および日本語のみ許可）")
    private String answerCandidateNo2;

    @NotBlank()
    @Pattern(regexp = "^[a-zA-Z0-9ぁ-んァ-ン一-龥々ー]*$", message = "特殊文字は使用できません（英数字および日本語のみ許可）")
    private String answerCandidateNo3;

    @NotBlank()
    @Pattern(regexp = "^[a-zA-Z0-9ぁ-んァ-ン一-龥々ー]*$", message = "特殊文字は使用できません（英数字および日本語のみ許可）")
    private String answerCandidateNo4;

    @Min(1)
    @Max(4)
    private int answerNumber;
}
