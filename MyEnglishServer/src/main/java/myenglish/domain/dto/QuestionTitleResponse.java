package myenglish.domain.dto;

import myenglish.domain.entity.QuestionTitleEntity;

public record QuestionTitleResponse (int questionTitleId, int ownerUserId, String questionTitle){
    public static QuestionTitleResponse fromEntity(QuestionTitleEntity questionTitleEntity){
        return new QuestionTitleResponse(
                questionTitleEntity.questionTitleId(),
                questionTitleEntity.ownerUserId(),
                questionTitleEntity.questionTitle()
        );
    }
}
