-- 테스트데이터 생성
-- user
insert into user values(1,'lgh95m@naver.com','naver-12345',true,false,'이기환','gh','010-885-6376','naver','12345','user');
insert into user values(2,'lgh95m@kakao.com','kakao-12345',true,false,'이기환','gh','010-885-6376','kakao','12345','user');

-- category
insert into category values(1,'웨딩');
insert into category values(2,'스냅');
insert into category values(3,'화보');

-- photographer
insert into photographer values(1,'기흥구','용인시',4.3,true,1);

-- estimate
insert into estimate values(1,'기흥구','용인시','찍어주세요.',now(),DATE_ADD(NOW(), INTERVAL 100 SECOND),now(),1,1);
insert into estimate values(2,'기흥구','용인시','찍어주세요.',now(),DATE_ADD(NOW(), INTERVAL 100 SECOND),now(),2,2);

-- p_category
insert into p_category values(1,'웨딩',1,1);

-- work
insert into work values(1,'찍어줄게요',now(),'pico/src/images/image.jpg','웨딩사진',1,1);

-- photo
insert into photo values(1,'1024*1024','pico/src/images/image.jpg','웨딩사진',1);

-- review
insert into review values(1,'최고에용',now(),4.2,1,2);

-- apply
insert into apply values(1,NOW(),'1',1,1);

