-- ユーザー情報
CREATE TABLE IF NOT EXISTS user_root (
    user_id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    name varchar(255) UNIQUE NOT NULL,
    email varchar(255) UNIQUE NOT NULL,
    created_date DATETIME NOT NULL,
    update_date DATETIME NOT NULL
)DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- クイズタイトル
CREATE TABLE IF NOT EXISTS question_core (
  question_title_id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  owner_user_id INT NOT NULL,
  question_title varchar(255) NOT NULL,
  created_date DATETIME NOT NULL,
  update_date DATETIME NOT NULL,
  UNIQUE (owner_user_id, question_title),
  FOREIGN KEY (owner_user_id) REFERENCES user_root (user_id) ON DELETE CASCADE
)DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


-- クイズ情報
CREATE TABLE IF NOT EXISTS question_details (
    question_details_id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    question_title_id INT NOT NULL,
    question_word varchar(255) NOT NULL,
    answer_candidate_no_1 varchar(255) NOT NULL,
    answer_candidate_no_2 varchar(255) NOT NULL,
    answer_candidate_no_3 varchar(255) NOT NULL,
    answer_candidate_no_4 varchar(255) NOT NULL,
    answer_number varchar(255) NOT NULL,
    created_date DATETIME NOT NULL,
    update_date DATETIME NOT NULL,
    FOREIGN KEY (question_title_id) REFERENCES question_core(question_title_id) ON DELETE CASCADE
)DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;