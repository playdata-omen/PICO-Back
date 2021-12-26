-- 테스트데이터 생성
-- user
insert into user values(1,'lgh95m@naver.com','naver-12345',true,false,'이기환','gh','010-885-6376','naver','12345','ROLE_USER');
insert into user values(2,'lgh95m@kakao.com','kakao-12345',true,false,'이기환','gh','010-885-6376','kakao','12345','ROLE_USER');
insert into user values(3,'lgh95m@kakao.com','kakao-12345',true,false,'조하운','gh','010-885-6376','kakao','12345','ROLE_USER');
insert into user values(4,'lgh95m@kakao.com','kakao-12345',true,false,'김지명','gh','010-885-6376','kakao','12345','ROLE_USER');
insert into user values(5,'lgh95m@kakao.com','kakao-12345',true,false,'임우송','gh','010-885-6376','kakao','12345','ROLE_USER');
insert into user values(6,'lgh95m@kakao.com','kakao-12345',true,false,'차재훈','gh','010-885-6376','kakao','12345','ROLE_USER');
insert into user values(7,'lgh95m@kakao.com','kakao-12345',true,false,'이기환2','gh','010-885-6376','kakao','12345','ROLE_USER');

-- category
insert into category values(1,'웨딩');
insert into category values(2,'스냅');
insert into category values(3,'화보');
insert into category values(4,'행사');
insert into category values(5,'제품');
insert into category values(6,'기타');

-- photographer
insert into photographer values(1,'기흥구','용인시',4.3,true,true,'기흥구','용인시',1);
insert into photographer values(2,'팔달구','수원시',4.3,true,true,'기흥구','용인시',2);
insert into photographer values(3,'서초구','서울특별시',4.3,true,false,'기흥구','용인시',3);
insert into photographer values(4,'동대문구','서울특별시',4.3,true,true,'기흥구','용인시',4);
insert into photographer values(5,'처인구','용인시',4.3,true,false,'기흥구','용인시',5);
insert into photographer values(6,'기흥구','용인시',4.3,true,false,'기흥구','용인시',6);
insert into photographer values(7,'동대문구','서울특별시',4.3,true,true,'기흥구','용인시',7);

-- estimate
insert into estimate values(1,'기흥구','용인시','찍어주세요.',now(),DATE_ADD(NOW(), INTERVAL 100 SECOND),now(),'1',1,1);
insert into estimate values(2,'기흥구','용인시','찍어주세요.',now(),DATE_ADD(NOW(), INTERVAL 100 SECOND),now(),'1',2,2);

-- p_category
insert into p_category values(1,'웨딩',1,1);
insert into p_category values(2,'웨딩',1,2);
insert into p_category values(3,'웨딩',1,3);
insert into p_category values(4,'웨딩',1,4);
insert into p_category values(5,'웨딩',1,5);
insert into p_category values(6,'웨딩',1,6);
insert into p_category values(7,'스냅',2,1);
insert into p_category values(8,'웨딩',1,7);
-- work
insert into work values(1,'찍어줄게요',now(),'pico/src/images/image.jpg','웨딩사진',1,1);

-- photo
insert into photo values(1,'1024*1024','pico/src/images/image.jpg','웨딩사진',1);

-- review
insert into review values(1,'최고에용',now(),4.2,1,2);

-- apply
insert into apply values(1,NOW(),true,'1',1,1);
insert into apply values(2,NOW(),true,'1',1,2);
insert into apply values(3,NOW(),false,'1',1,3);


-- user
select * from user;
INSERT into user values(null, "test3@test.com", true, true, "테스트3", "테스트3", "010-1234-5678", "kakao", "12345test3", "ROLE_USER", "12345test3@kakao.social");
INSERT into user values(null, "test4@test.com", true, true, "테스트4", "테스트4", "010-1234-5678", "kakao", "12345test4", "ROLE_USER", "12345test4@kakao.social");
INSERT into user values(null, "test5@test.com", true, true, "테스트5", "테스트5", "010-1234-5678", "kakao", "12345test5", "ROLE_USER", "12345test5@kakao.social");
INSERT into user values(null, "test6@test.com", true, true, "테스트6", "테스트6", "010-1234-5678", "kakao", "12345test6", "ROLE_USER", "12345test6@kakao.social");
INSERT into user values(null, "test7@test.com", true, true, "테스트7", "테스트7", "010-1234-5678", "kakao", "12345test7", "ROLE_USER", "12345test7@kakao.social");
INSERT into user values(null, "test8@test.com", true, true, "테스트8", "테스트8", "010-1234-5678", "kakao", "12345test8", "ROLE_USER", "12345test8@kakao.social");
INSERT into user values(null, "test9@test.com", true, true, "테스트9", "테스트9", "010-1234-5678", "kakao", "12345test9", "ROLE_USER", "12345test9@kakao.social");
INSERT into user values(null, "test10@test.com", true, true, "테스트10", "테스트10", "010-1234-5678", "kakao", "12345test10", "ROLE_USER", "12345test10@kakao.social");
INSERT into user values(null, "test11@test.com", true, true, "테스트11", "테스트11", "010-1234-5678", "kakao", "12345test11", "ROLE_USER", "12345test11@kakao.social");
INSERT into user values(null, "test12@test.com", true, true, "테스트12", "테스트12", "010-1234-5678", "kakao", "12345test12", "ROLE_USER", "12345test12@kakao.social");
INSERT into user values(null, "test13@test.com", true, true, "테스트13", "테스트13", "010-1234-5678", "kakao", "12345test13", "ROLE_USER", "12345test13@kakao.social");


-- photographer
select * from photographer;
insert into photographer values(null, "전체", "서울특별시", null, false, false, null, null, 8);
insert into photographer values(null, "전체", "서울특별시", null, false, false, null, null, 9);
insert into photographer values(null, "전체", "서울특별시", null, false, false, null, null, 10);
insert into photographer values(null, "전체", "서울특별시", null, false, false, null, null, 11);
insert into photographer values(null, "전체", "서울특별시", null, false, false, null, null, 12);
insert into photographer values(null, "전체", "서울특별시", null, false, false, null, null, 13);
insert into photographer values(null, "전체", "서울특별시", null, false, false, null, null, 14);
insert into photographer values(null, "전체", "서울특별시", null, false, false, null, null, 15);
insert into photographer values(null, "전체", "서울특별시", null, false, false, null, null, 16);
insert into photographer values(null, "전체", "서울특별시", null, false, false, null, null, 17);
insert into photographer values(null, "전체", "서울특별시", null, false, false, null, null, 18);
insert into photographer values(null, "전체", "서울특별시", null, false, false, null, null, 19);

-- p_category
select * from p_category;
INSERT into p_category values(null, "스냅", 2, 6);
INSERT into p_category values(null, "스냅", 2, 7);
INSERT into p_category values(null, "스냅", 2, 8);
INSERT into p_category values(null, "스냅", 2, 9);
INSERT into p_category values(null, "스냅", 2, 10);
INSERT into p_category values(null, "스냅", 2, 11);
INSERT into p_category values(null, "스냅", 2, 12);
INSERT into p_category values(null, "스냅", 2, 13);
INSERT into p_category values(null, "스냅", 2, 14);
INSERT into p_category values(null, "스냅", 2, 15);
INSERT into p_category values(null, "스냅", 2, 16);
INSERT into p_category values(null, "스냅", 2, 17);
INSERT into p_category values(null, "스냅", 2, 18);