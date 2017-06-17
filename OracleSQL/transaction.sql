drop table account;

create table account (
  ano varchar(20) primary key,
  aowner varchar(10) not null,
  abalance number not null
  );
  
  insert into account values( '111-111', '홍길동', 1000000);
  insert into account values( '222-222', '스프링', 0);
  
  select * from account;
  rollback; -- 마지막 커밋까지 되돌린다 = 현재 작업을 취소
  select * from account;
  
  insert into account values( '111-111', '홍길동', 1000000);
  insert into account values( '222-222', '스프링', 0);
  select * from account;
  commit;
  select * from account;
  
  --기능 단위의 트랜잭션 처리
update account set abalance=abalance-10000 where ano='111-111';
update account set abalance=abalance+10000 where ano='222-223';
ROLLBACK;
  select * from account;
