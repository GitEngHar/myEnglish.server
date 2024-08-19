INSERT INTO user_root (name,created_date,update_date) 
VALUES
('testuser',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO question_core (owner_user_id,question_title,created_date,update_date) 
VALUES
(1,'testTitle' , CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO question_details_plugin (question_title_id,question_word,created_date,update_date) 
VALUES
(1,'道の隅に黄色い花が咲いている',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO question_details_answer_plugin (question_title_id,question_details_id,answer_id,answer_candidate_no_1,answer_candidate_no_2,answer_candidate_no_3,answer_candidate_no_4,created_date,update_date) 
VALUES
(1,1,1,'Yellow flowers in the corner of the road.','hogehoge','hogehoge','hogehoge',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);