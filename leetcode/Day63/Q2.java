package leetcode.Day63;

public class Q2 {
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        boolean[][] mark=new boolean[image.length][image[0].length];
        backTrack(image,mark,sr,sc,image[sr][sc],newColor);
        return image;
    }
    private static void backTrack(int[][] image,boolean[][] mark,int r,int c,int color,int newColor){
        if(r<0||c<0||r>=image.length||c>=image[0].length||mark[r][c]) return;
        if(image[r][c]==color){
            mark[r][c]=true;
            image[r][c]=newColor;
            backTrack(image,mark,r+1,c,color,newColor);
            backTrack(image,mark,r-1,c,color,newColor);
            backTrack(image,mark,r,c-1,color,newColor);
            backTrack(image,mark,r,c+1,color,newColor);
        }
    }
}
