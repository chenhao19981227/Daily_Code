package leetcode.Day64;

import java.util.*;

public class Q2 {
    static List<List<String>> result;
    public List<List<String>> solveNQueens(int n) {
        result=new ArrayList<>();
        Set<Integer> columns=new HashSet<>();
        Set<Integer> diagonals1=new HashSet<>();
        Set<Integer> diagonals2=new HashSet<>();
        int[] queens=new int[n];
        Arrays.fill(queens,-1);
        backTrack(queens,0,n,columns,diagonals1,diagonals2);
        return result;
    }

    private static void backTrack(int[] queens, int row, int n, Set<Integer> columns, Set<Integer> diagonals1, Set<Integer> diagonals2) {
        if(row==n){
            result.add(createBoard(queens));
        }else {
            for (int i = 0; i < n; i++) {
                if(columns.contains(i))
                    continue;
                int diagonal1=row-i;
                if(diagonals1.contains(diagonal1))
                    continue;
                int diagonal2=row+i;
                if(diagonals2.contains(diagonal2))
                    continue;
                columns.add(i);
                diagonals1.add(diagonal1);
                diagonals2.add(diagonal2);
                queens[row]=i;
                backTrack(queens,row+1,n,columns,diagonals1,diagonals2);
                queens[row]=-1;
                diagonals2.remove(diagonal2);
                diagonals1.remove(diagonal1);
                columns.remove(i);
            }
        }
    }

    private static List<String> createBoard(int[] queens) {
        List<String> board = new ArrayList<String>();
        for (int i = 0; i < queens.length; i++) {
            char[] row = new char[queens.length];
            Arrays.fill(row, '.');
            row[queens[i]] = 'Q';
            board.add(new String(row));
        }
        return board;
    }
}
