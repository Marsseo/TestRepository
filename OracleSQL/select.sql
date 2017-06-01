/*
select
*/
select *
from board
where bwriter='홍길동';

--bwriter가 홍길동인 게시물을 가져와라
select bno, btitle, bwriter
from board
where bwriter='홍길동';

--bno가 2에서 6사이에 게시물을 가져오시오
select *
from board
where bno>1 and bno<7;

select *
from board
where bno between 1 and 7;

--bwriter가 '홍'을 포함하는 게시물의 bno, btitle, bwriter를 가져오시오
select bno, btitle, bwriter
from board
where bwriter like '%홍%';

--게시물의 제목 중에 '자바'가 포함되어 있는 게시물의 bno, btitle, bwriter를 가져오시오
select bno, btitle, bwriter
from board
where btitle like '%제목%';

--게시물의 제목, 내용 중에 '자바'가 포함되어 있는 게시물의 bno, btitle, bwriter를 가져오시오
select bno, btitle, bwriter
from board
where btitle like '%자바%' or bcontent like '%자바%';

--게시물의 '감자바', '김길동', '라즈베리' 가 포함되어 있는 게시물의 번호, 제목, 글쓴이를 가져오시오
select bno, btitle, bwriter
from board
where bwriter='감자바' or bwriter='김길동' or bwriter='라즈베리';

select bno, btitle, bwriter
from board
where bwriter in('감자바','김길동','라즈베리');

--첨부 파일이 없는 게시물을 가져오시오
select *
from board
where BORIGINALFILENAME is null;

--첨부 파일이 있는 게시물을 가져오시오
select *
from board
where BORIGINALFILENAME is not null;

--게시물을 작성한 사람의  이름을 가져오시오(중복 없이)
select distinct bwriter from board;

--게시글을 쓴 날짜가 2016년인 게시글을 가져오시오
select * from board
where BDATE between '2016.01.01' and '2016.12.31'; -- 또는 BDATE >= '2016.01.01' and BDATE <= '2016.12.31';

--bno -> 번호, btitle -> 제목, bwriter -> 글쓴이로 컬럼이름을 번경해서 가져오시오
select bno as 번호, btitle as 제목, bwriter 글쓴이
from board;
/*
정렬
*/
-- 게시물의 번호를 기준으로 오름차순 가져오시오
select *
from board
order by bno asc;

-- 2017년도에 작성한 게시물의 번호를 기준으로 내림차순으로 가져오시오
select * from board
where BDATE between '2017.01.01' and '2017.12.31'
order by bno desc;

-- 글쓴이를 기준으로 1차 정렬(오름) 하고 쓴 날짜를 기준으로 2차 정렬(내림) 하시오
select * from board
order by bwriter asc, bdate desc;
/*
페이징 처리
*/
select rownum, bno, btitle, bwriter, bdate, bhitcount
from board;

--정렬 후, 행번호 매기기
select rownum, bno, btitle, bwriter, bdate, bhitcount 
from (select bno, btitle, bwriter, bdate, bhitcount from board order by bno desc);

-- 특정 행번호 이하만 가져오기 (Top N을 가져오기)
select rownum, bno, btitle, bwriter, bdate, bhitcount 
from (select bno, btitle, bwriter, bdate, bhitcount from board order by bno desc)
where rownum <= 10;

--시작 행번호와 끝 행번호 사이의 게시물 가져오기
select * from
(
  select rownum as r, bno, btitle, bwriter, bdate, bhitcount 
  from (select bno, btitle, bwriter, bdate, bhitcount from board order by bno desc)
   where rownum<=3*10
  --where rownum <= (pageNo*rowsPerPage)
)
where r>=((3-1)*10)+1;
--where r>=((pageNo-1)*rowsPerPage)+1; 숫자 사이의 게시물을 가져와라
/*
행 수 구하기
*/
--전체 행수 구하기
select count(*) from board;
select count(BORIGINALFILENAME) from board;

--특정 조건에 맞는 행수 구하기
select count(*) from board where bwriter <> '홍길동';

select * from
(
select rownum as r, mid, mname, mtel, memail, mage, maddress from 
(select mid, mname, mtel, memail, mage, maddress from member order by mid desc)
 where rownum<=5*10
)
where r>=((5-1)*10)+1;
