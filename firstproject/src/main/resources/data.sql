-- article ?? ???
insert into article(title,content) values('가가가가','1111');
insert into article(title,content) values('나나나나','2222');
insert into article(title,content) values('다다다다','3333');

-- article 게시글 더미 데이터
insert into article(title,content) values('당신의 인생 영화는?','댓글 ㄱ');
insert into article(title,content) values('당신의 소울 푸드는?','댓글 ㄱㄱ');
insert into article(title,content) values('당신의 취미는?','댓글 ㄱㄱㄱ');

-- comment 더미 데이터
-- 4번 게시글의 댓글들
insert into comment(article_id,nickname,body) values(4,'Park','굿 윌 헌팅');
insert into comment(article_id,nickname,body) values(4,'Kim','아이 엠 샘');
insert into comment(article_id,nickname,body) values(4,'Choi','쇼생크의 탈출');
-- 5번 게시글의 댓글들
insert into comment(article_id,nickname,body) values(5,'Park','치킨');
insert into comment(article_id,nickname,body) values(5,'Kim','샤브샤브');
insert into comment(article_id,nickname,body) values(5,'Choi','초밥');
-- 6번 게시글의 댓글들
insert into comment(article_id,nickname,body) values(6,'Park','조깅');
insert into comment(article_id,nickname,body) values(6,'Kim','유튜브');
insert into comment(article_id,nickname,body) values(6,'Choi','독서');