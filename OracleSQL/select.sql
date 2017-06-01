/*
select
*/
select *
from board
where bwriter='ȫ�浿';

--bwriter�� ȫ�浿�� �Խù��� �����Ͷ�
select bno, btitle, bwriter
from board
where bwriter='ȫ�浿';

--bno�� 2���� 6���̿� �Խù��� �������ÿ�
select *
from board
where bno>1 and bno<7;

select *
from board
where bno between 1 and 7;

--bwriter�� 'ȫ'�� �����ϴ� �Խù��� bno, btitle, bwriter�� �������ÿ�
select bno, btitle, bwriter
from board
where bwriter like '%ȫ%';

--�Խù��� ���� �߿� '�ڹ�'�� ���ԵǾ� �ִ� �Խù��� bno, btitle, bwriter�� �������ÿ�
select bno, btitle, bwriter
from board
where btitle like '%����%';

--�Խù��� ����, ���� �߿� '�ڹ�'�� ���ԵǾ� �ִ� �Խù��� bno, btitle, bwriter�� �������ÿ�
select bno, btitle, bwriter
from board
where btitle like '%�ڹ�%' or bcontent like '%�ڹ�%';

--�Խù��� '���ڹ�', '��浿', '�����' �� ���ԵǾ� �ִ� �Խù��� ��ȣ, ����, �۾��̸� �������ÿ�
select bno, btitle, bwriter
from board
where bwriter='���ڹ�' or bwriter='��浿' or bwriter='�����';

select bno, btitle, bwriter
from board
where bwriter in('���ڹ�','��浿','�����');

--÷�� ������ ���� �Խù��� �������ÿ�
select *
from board
where BORIGINALFILENAME is null;

--÷�� ������ �ִ� �Խù��� �������ÿ�
select *
from board
where BORIGINALFILENAME is not null;

--�Խù��� �ۼ��� �����  �̸��� �������ÿ�(�ߺ� ����)
select distinct bwriter from board;

--�Խñ��� �� ��¥�� 2016���� �Խñ��� �������ÿ�
select * from board
where BDATE between '2016.01.01' and '2016.12.31'; -- �Ǵ� BDATE >= '2016.01.01' and BDATE <= '2016.12.31';

--bno -> ��ȣ, btitle -> ����, bwriter -> �۾��̷� �÷��̸��� �����ؼ� �������ÿ�
select bno as ��ȣ, btitle as ����, bwriter �۾���
from board;
/*
����
*/
-- �Խù��� ��ȣ�� �������� �������� �������ÿ�
select *
from board
order by bno asc;

-- 2017�⵵�� �ۼ��� �Խù��� ��ȣ�� �������� ������������ �������ÿ�
select * from board
where BDATE between '2017.01.01' and '2017.12.31'
order by bno desc;

-- �۾��̸� �������� 1�� ����(����) �ϰ� �� ��¥�� �������� 2�� ����(����) �Ͻÿ�
select * from board
order by bwriter asc, bdate desc;
/*
����¡ ó��
*/
select rownum, bno, btitle, bwriter, bdate, bhitcount
from board;

--���� ��, ���ȣ �ű��
select rownum, bno, btitle, bwriter, bdate, bhitcount 
from (select bno, btitle, bwriter, bdate, bhitcount from board order by bno desc);

-- Ư�� ���ȣ ���ϸ� �������� (Top N�� ��������)
select rownum, bno, btitle, bwriter, bdate, bhitcount 
from (select bno, btitle, bwriter, bdate, bhitcount from board order by bno desc)
where rownum <= 10;

--���� ���ȣ�� �� ���ȣ ������ �Խù� ��������
select * from
(
  select rownum as r, bno, btitle, bwriter, bdate, bhitcount 
  from (select bno, btitle, bwriter, bdate, bhitcount from board order by bno desc)
   where rownum<=3*10
  --where rownum <= (pageNo*rowsPerPage)
)
where r>=((3-1)*10)+1;
--where r>=((pageNo-1)*rowsPerPage)+1; ���� ������ �Խù��� �����Ͷ�
/*
�� �� ���ϱ�
*/
--��ü ��� ���ϱ�
select count(*) from board;
select count(BORIGINALFILENAME) from board;

--Ư�� ���ǿ� �´� ��� ���ϱ�
select count(*) from board where bwriter <> 'ȫ�浿';

select * from
(
select rownum as r, mid, mname, mtel, memail, mage, maddress from 
(select mid, mname, mtel, memail, mage, maddress from member order by mid desc)
 where rownum<=5*10
)
where r>=((5-1)*10)+1;
