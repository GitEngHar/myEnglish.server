package myenglish.domain.entity;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * @param answerCandidateNo1 TODO: 配列にする
 */
public record QuestionDetailsEntity(int questionDetailsId, int questionTitleId, String questionWord,
                                    String answerCandidateNo1, String answerCandidateNo2, String answerCandidateNo3, String answerCandidateNo4,
                                    int answerNumber,
                                    LocalDateTime createdDate, LocalDateTime updateDate) {
    public QuestionDetailsEntity {
        List<String> answerCandidates = Arrays.asList(
                answerCandidateNo1,
                answerCandidateNo2,
                answerCandidateNo3,
                answerCandidateNo4
        );
        validateAnswerCandidate(answerCandidates);
    }

    private void validateAnswerCandidate(List<String> answerCandidates) {
        // 回答のユニークな値の数を取得
        int uniqueCandidateNums = (int) answerCandidates.stream().distinct().count();
        // 回答全ての数を取得
        int candidateNums = answerCandidates.size();
        // 回答全てがユニークであることを判定する
        boolean isUniqueCandidate = candidateNums == uniqueCandidateNums;
        if (!isUniqueCandidate) {
            // TODO: Exceptionをカスタマイズする
            throw new RuntimeException("重複した回答が設定されています。ユニークな値を設定してください。");
        }
    }

}
