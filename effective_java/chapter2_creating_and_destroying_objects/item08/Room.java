package effective_java.chapter2_creating_and_destroying_objects.item08;


import java.lang.ref.Cleaner;

public class Room implements AutoCloseable{
    private static final Cleaner cleaner=Cleaner.create();
    private static class State implements Runnable{
        int numJunkPiles;
        State(int numJunkPiles){
            this.numJunkPiles=numJunkPiles;
        }
        @Override
        public void run() {
            System.out.println("Clean room");
            numJunkPiles=0;
        }
    }
    private final State state;
    private final Cleaner.Cleanable cleanable;
    public Room(int numJunkPiles) {
        state=new State(numJunkPiles);
        this.cleanable = cleaner.register(this,state);
    }
    @Override
    public void close() throws Exception {
        cleanable.clean();
    }
}
