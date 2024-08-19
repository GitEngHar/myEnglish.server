DROP TABLE IF EXISTS user_root CASCADE;
DROP TABLE IF EXISTS question_core CASCADE;
DROP TABLE IF EXISTS question_details_plugin CASCADE;
DROP TABLE IF EXISTS question_details_answer_plugin CASCADE;

-- ユーザー情報
CREATE TABLE IF NOT EXISTS user_root (
    user_id serial NOT NULL PRIMARY KEY,
    name varchar(255) UNIQUE NOT NULL,
    created_date timestamp without time zone NOT NULL,
    update_date timestamp without time zone NOT NULL
);

-- クイズタイトル
CREATE TABLE IF NOT EXISTS question_core (
  question_title_id serial PRIMARY KEY NOT NULL,
  owner_user_id INT NOT NULL,
  question_title varchar(255) NOT NULL,
  created_date timestamp without time zone NOT NULL,
  update_date timestamp without time zone NOT NULL,
  CONSTRAINT owner_user_id_foreign_key FOREIGN KEY (owner_user_id) REFERENCES user_root(user_id) ON DELETE CASCADE
);


-- クイズの問題
CREATE TABLE IF NOT EXISTS question_details_plugin (
    question_details_id serial PRIMARY KEY NOT NULL,
    question_title_id INT NOT NULL,
    question_word varchar(255) NOT NULL,
    created_date timestamp without time zone NOT NULL,
    update_date timestamp without time zone NOT NULL,
    CONSTRAINT parent_quest_id_foreign_key FOREIGN KEY (question_title_id) REFERENCES question_core(question_title_id) ON DELETE CASCADE
);


-- クイズの回答候補
CREATE TABLE IF NOT EXISTS question_details_answer_plugin (
	/* TODO:answertとdetailsとtitleの組み合わせをuniqueにする */
    question_answer_id serial PRIMARY KEY NOT NULL, 
    question_title_id INT NOT NULL,
    question_details_id INT NOT NULL, 
    answer_id INT NOT NULL,
    answer_candidate_no_1 varchar(255) NOT NULL, 
    answer_candidate_no_2 varchar(255) NOT NULL, 
    answer_candidate_no_3 varchar(255) NOT NULL, 
    answer_candidate_no_4 varchar(255) NOT NULL, 
    created_date timestamp without time zone NOT NULL, 
    update_date timestamp without time zone NOT NULL, 
    CONSTRAINT parent_answer_question_details_id_foreign_key FOREIGN KEY (question_details_id) REFERENCES question_details_plugin(question_details_id) ON DELETE CASCADE,
	CONSTRAINT parent_answer_question_title_id_foreign_key FOREIGN KEY (question_title_id) REFERENCES question_core(question_title_id) ON DELETE CASCADE
);