insert into user_tb(username, password, email)
values ('ssar', '1234', 'ssar@nate.com');
insert into user_tb(username, password, email)
values ('cos', '1234', 'cos@nate.com');

insert into board_tb(title, content, user_id)
values ('제목1', '내용1', 1);
insert into board_tb(title, content, user_id)
values ('제목2', '내용2', 1);
insert into board_tb(title, content, user_id)
values ('제목3', '내용3', 1);
insert into board_tb(title, content, user_id)
values ('제목4', '내용4', 1);
insert into board_tb(title, content, user_id)
values ('제목5', '내용5', 2);

insert into reply_tb(comment, user_id, board_id)
values ('댓글1', 1, 5);
insert into reply_tb(comment, user_id, board_id)
values ('댓글2', 2, 5);
insert into reply_tb(comment, user_id, board_id)
values ('댓글3', 1, 4);
insert into reply_tb(comment, user_id, board_id)
values ('댓글4', 1, 4);
insert into reply_tb(comment, user_id, board_id)
values ('댓글5', 2, 4);