drop table account;

create table account (
  ano varchar(20) primary key,
  aowner varchar(10) not null,
  abalance number not null
  );
  
  insert into account values( '111-111', 'ȫ�浿', 1000000);
  insert into account values( '222-222', '������', 0);
  
  select * from account;
  rollback; -- ������ Ŀ�Ա��� �ǵ����� = ���� �۾��� ���
  select * from account;
  
  insert into account values( '111-111', 'ȫ�浿', 1000000);
  insert into account values( '222-222', '������', 0);
  select * from account;
  commit;
  select * from account;
  
  --��� ������ Ʈ����� ó��
update account set abalance=abalance-10000 where ano='111-111';
update account set abalance=abalance+10000 where ano='222-223';
ROLLBACK;
  select * from account;
