package myenglish.domain.dto;


import myenglish.domain.entity.QuestionDetailsEntity;

import java.time.LocalDateTime;

public record QuestionDetailsResponse(int questionDetailsId, int questionTitleId, String questionWord,
                                      String answerCandidateNo1, String answerCandidateNo2, String answerCandidateNo3, String answerCandidateNo4, int answerNumber,
                                      LocalDateTime createdDate, LocalDateTime updateDate) {
    public static QuestionDetailsResponse fromEntity(QuestionDetailsEntity questionDetailsEntity) {
        return new QuestionDetailsResponse(
                questionDetailsEntity.questionDetailsId(),
                questionDetailsEntity.questionTitleId(),
                questionDetailsEntity.questionWord(),
                questionDetailsEntity.answerCandidateNo1(),
                questionDetailsEntity.answerCandidateNo2(),
                questionDetailsEntity.answerCandidateNo3(),
                questionDetailsEntity.answerCandidateNo4(),
                questionDetailsEntity.answerNumber(),
                questionDetailsEntity.createdDate(),
                questionDetailsEntity.updateDate()
        );
    }
}
