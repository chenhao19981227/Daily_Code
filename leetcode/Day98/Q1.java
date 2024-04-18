package leetcode.Day98;

import java.util.*;
import java.util.stream.Collectors;

public class Q1 {
    private final char[] directions= new char[]{'R', 'D', 'L', 'U'};
    private int[] position = new int[2];
    private int direction = 0;
    private int left=0,right=0,up=0,down=0;
    public List<String> printKMoves(int K) {
        if (K < 1) return List.of("R");
        HashMap<List<Integer>, Boolean> map = new HashMap<>();
        while (--K>=0){
            List<Integer> pos=List.of(position[0],position[1]);
            if(map.containsKey(pos)){
                direction=map.get(pos)?(direction+1)%4:(direction+3)%4;
                map.put(pos,!map.get(pos));
            }else {
                direction=(direction+1)%4;
                map.put(pos,false);
            }
            move();
            left=Math.min(left,position[0]);
            right=Math.max(right,position[0]);
            down=Math.min(down,position[1]);
            up=Math.max(up,position[1]);
        }
        char[][] res=new char[up-down+1][right-left+1];
        for (char[] row : res)
            Arrays.fill(row, '_');
        for (Map.Entry<List<Integer>, Boolean> entry : map.entrySet()) {
            List<Integer> pos = entry.getKey();
            Boolean isWhite = entry.getValue();
            if(!isWhite)
                res[up- pos.get(1)][pos.get(0) - left] = 'X';
        }
        res[up- position[1]][position[0] - left] = directions[direction];
        return Arrays.stream(res).map(String::valueOf).collect(Collectors.toList());
    }

    private void move() {
        switch (direction) {
            case 0:
                ++position[0];
                break;
            case 1:
                --position[1];
                break;
            case 2:
                --position[0];
                break;
            default:
                ++position[1];
        }
    }
}
