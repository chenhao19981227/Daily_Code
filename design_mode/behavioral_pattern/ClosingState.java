package design_mode.behavioral_pattern;

public class ClosingState extends LiftState{
    @Override
    public void open() {
        super.context.setLiftState(Context.openingState);
        super.context.open();
    }

    @Override
    public void close() {
        System.out.println("电梯门关闭...");
    }

    @Override
    public void run() {
        super.context.setLiftState(Context.runningState);
        super.context.run();
    }

    @Override
    public void stop() {
        super.context.setLiftState(Context.stoppingState);
        super.context.stop();
    }
}
