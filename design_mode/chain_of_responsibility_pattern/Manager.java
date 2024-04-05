package design_mode.chain_of_responsibility_pattern;

public class Manager extends Handler {
    public Manager() {
        super(Handler.NUM_THREE, Handler.NUM_SEVEN);
        setNextHandler(new GeneralManager());
    }

    @Override
    protected void handleLeave(LeaveRequest leave) {
        System.out.println(leave.getName() + "请假" + leave.getNum() + "天," + leave.getReason() + "。");
        System.out.println("部门经理审批：同意。");
    }
}