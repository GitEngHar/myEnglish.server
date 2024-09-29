DELETE FROM question_details_answer_plugin;
DELETE FROM question_details_plugin;
DELETE FROM question_core;
DELETE FROM user_root;

INSERT IGNORE INTO user_root (user_id,name,created_date,update_date)
VALUES
(1,'testuser',NOW(), NOW());

INSERT IGNORE INTO question_core (question_title_id,owner_user_id,question_title,created_date,update_date)
VALUES
(1,1,'testTitle' , NOW(), NOW());

INSERT IGNORE INTO question_details_plugin (question_details_id,question_title_id,question_word,created_date,update_date)
VALUES
(1,1,'道の隅に黄色い花が咲いている',NOW(), NOW());

INSERT IGNORE INTO question_details_answer_plugin (question_answer_id,question_title_id,question_details_id,answer_id,answer_candidate_no_1,answer_candidate_no_2,answer_candidate_no_3,answer_candidate_no_4,created_date,update_date)
VALUES
(1,1,1,1,'Yellow flowers in the corner of the road.','hogehoge','hogehoge','hogehoge',NOW(), NOW());