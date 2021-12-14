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

-- photographer
insert into photographer values(1,'기흥구','용인시',4.3,true,true,'경기도 용인시 기흥구 동백 3로 11번길 21 더드림오피스텔 211호',1);
insert into photographer values(2,'팔달구','수원시',4.3,true,true,'경기도 용인시 기흥구 동백 3로 11번길 21 더드림오피스텔 211호',2);
insert into photographer values(3,'서초구','서울특별시',4.3,true,false,'경기도 용인시 기흥구 동백 3로 11번길 21 더드림오피스텔 211호',3);
insert into photographer values(4,'동대문구','서울특별시',4.3,true,true,'경기도 용인시 기흥구 동백 3로 11번길 21 더드림오피스텔 211호',4);
insert into photographer values(5,'처인구','용인시',4.3,true,false,'경기도 용인시 기흥구 동백 3로 11번길 21 더드림오피스텔 211호',5);
insert into photographer values(6,'기흥구','용인시',4.3,true,false,'경기도 용인시 기흥구 동백 3로 11번길 21 더드림오피스텔 211호',6);
insert into photographer values(7,'동대문구','서울특별시',4.3,true,true,'경기도 용인시 기흥구 동백 3로 11번길 21 더드림오피스텔 211호',7);

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
