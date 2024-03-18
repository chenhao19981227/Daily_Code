package leetcode.Day67;

public class Q2 {
    public int findString(String[] words, String s) {
        int left=0,right=words.length-1;
        while (left<=right){
            int mid=(right-left)/2+left;
            while (mid>left&&words[mid].isEmpty())
                mid--;
            if(s.equals(words[mid]))
                return mid;
            else if(words[mid].compareTo(s)<0)
                left=mid+1;
            else
                right=mid-1;
        }
        return -1;
    }
}
