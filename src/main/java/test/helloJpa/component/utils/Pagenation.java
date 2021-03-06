package test.helloJpa.component.utils;

import lombok.Data;

@Data
public class Pagenation {

    // 페이지당 게시물 수
    public static final int PAGE_SCALE = 10;
    // 화면당 페이지 수
    public static final int BLOCK_SCALE = 10;

    private int curPage;    // 현재 페이지
    private int prevPage;   // 이전 페이지
    private int nextPage;   // 다음 페이지
    private int totPage;    // 전체 페이지 갯수
    private int totBlock;   // 전체 페이지 블록 갯수
    private int curBlock;   // 현재 페이지 블록
    private int prevBlock;  // 이전 페이지 블록
    private int nextBlock;  // 다음 페이지 블록

    // WHERE rn BETWEEN #{start} AND #{end}
    private int pageBegin;  // #{start}
    private int pageEnd;    // #{end}
    // [이전] blockBegin -> 41 42 43 44 45 46 47 48 49 50 [다음]
    private int blockBegin; // 현재 페이지 블록의 시작번호
    // [이전] 41 42 43 44 45 46 47 48 49 50 <- blockEnd [다음]
    private int blockEnd;   // 현재 페이지 블록의 끝번호

    // 생성자
    // PageMaker(게시물 갯수, 현재 페이지 번호)
    public Pagenation(int count, int curPage){
        curBlock = 1;                   // 현재 페이지 블록 번호
        this.curPage = curPage;         // 현재 페이지 설정
        setTotPage(count);              // 전체 페이지 갯수 계산
        setPageRange();
        setTotBlock(count);             // 전체 페이지 블록 갯수 계산
        setBlockRange();                // 페이지 블록의 시작, 끝 번호 계산
    }

    public void setBlockRange(){
        // *현재 페이지가 몇번째 페이지 블록에 속하는지 계산
        curBlock = (int)Math.ceil((curPage-1) / BLOCK_SCALE)+1;
        // *현재 페이지 블록의 시작, 끝 번호 계산
        blockBegin = (curBlock-1)*BLOCK_SCALE+1;
        // 페이지 블록의 끝번호
        blockEnd = blockBegin+BLOCK_SCALE-1;
        // *마지막 블록이 범위를 초과하지 않도록 계산
        if(blockEnd > totPage) blockEnd = totPage;
        // *이전을 눌렀을 때 이동할 페이지 번호
        prevPage = curPage-1;
        // *다음을 눌렀을 때 이동할 페이지 번호
        nextPage = curPage+1;
        // 마지막 페이지가 범위를 초과하지 않도록 처리
        if(nextPage >= totPage) nextPage = totPage;
    }

    public void setPageRange(){
        // WHERE rn BETWEEN #{start} AND #{end}
        // 시작번호 = (현재페이지-1)*페이지당 게시물수 +1
        pageBegin = (curPage-1)*PAGE_SCALE+1;
        // 끝번호 = 시작번호+페이지당 게시물수 -1
        pageEnd = pageBegin+PAGE_SCALE-1;
    }

}
