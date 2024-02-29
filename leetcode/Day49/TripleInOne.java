package leetcode.Day49;

class TripleInOne {
    int[][] tripleOne;
    int[] index;
    int maxLen;
    public TripleInOne(int stackSize) {
        tripleOne=new int[3][stackSize];
        index=new int[3];
        maxLen=stackSize;
    }
    
    public void push(int stackNum, int value) {
        if(index[stackNum]==maxLen)return;
        tripleOne[stackNum][index[stackNum]]=value;
        index[stackNum]++;
    }
    
    public int pop(int stackNum) {
        if(index[stackNum]==0)return -1;
        return tripleOne[stackNum][--index[stackNum]];
    }
    
    public int peek(int stackNum) {
        if(index[stackNum]==0)return -1;
        return tripleOne[stackNum][index[stackNum]-1];
    }
    
    public boolean isEmpty(int stackNum) {
        return index[stackNum]==0;
    }
}