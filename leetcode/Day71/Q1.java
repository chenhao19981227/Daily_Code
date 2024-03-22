package leetcode.Day71;

public class Q1 {
    public String tictactoe(String[] board) {
        int m=board.length;
        boolean isBlank=false;
        int rowX,rowO,colX,colO;
        for (int i = 0; i < m; i++) {
            rowX=0;
            rowO=0;
            colX=0;
            colO=0;
            for (int j = 0; j < m; j++) {
                if(!isBlank)
                    if(board[i].charAt(j)==' ') isBlank=true;
                if(board[i].charAt(j)=='X')rowX++;
                else if(board[i].charAt(j)=='O')rowO++;
                if(board[j].charAt(i)=='X') colX++;
                else if(board[j].charAt(i)=='O') colO++;
            }
            if(colX==m||rowX==m)return "X";
            if(colO==m||rowO==m)return "O";
        }
        int leftTopX=0,leftTopO=0,leftBottomX=0,leftBottomO=0;
        for (int i = 0; i < m; i++) {
            if(board[i].charAt(i)=='X')leftTopX++;
            else if(board[i].charAt(i)=='O')leftTopO++;
            if(board[m-i-1].charAt(i)=='X')leftBottomX++;
            else if(board[m-i-1].charAt(i)=='O')leftBottomO++;
        }
        if(leftTopO==m||leftBottomO==m)return "O";
        if(leftTopX==m||leftBottomX==m)return "X";
        return isBlank?"Pending":"Draw";
    }
}
