package leetcode.Day51;

import java.util.ArrayDeque;
import java.util.Deque;

class AnimalShelf {
    Deque<int[]> queueCat;
    Deque<int[]> queueDog;
    public AnimalShelf() {
        queueCat=new ArrayDeque<>();
        queueDog=new ArrayDeque<>();
    }
    
    public void enqueue(int[] animal) {
        if(animal[1]==0)
            queueCat.offer(animal);
        else
            queueDog.offer(animal);
    }
    public int[] dequeueAny() {
        if(queueCat.isEmpty()&&queueDog.isEmpty())
            return new int[]{-1,-1};
        if(queueDog.isEmpty())
            return queueCat.poll();
        if(queueCat.isEmpty()){
            return queueDog.poll();
        }
        return queueCat.peek()[0]<queueDog.peek()[0]?queueCat.poll():queueDog.poll();
    }
    
    public int[] dequeueDog() {
        return queueDog.isEmpty()?new int[]{-1,-1}:queueDog.poll();
    }
    
    public int[] dequeueCat() {
        return queueCat.isEmpty()?new int[]{-1,-1}:queueCat.poll();
    }
}