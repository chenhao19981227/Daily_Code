package leetcode.Day150;

public class Q1 {
    public int takeAttendance(int[] records) {
        int len=records.length;
        for(int i=0;i<len;i++){
            if(records[i]!=i)
                return i;
        }
        return len;
    }
}
