package design_mode.chain_of_responsibility_pattern;

public class GeneralManager extends Handler {
    public GeneralManager() {
        super(Handler.NUM_SEVEN);
    }

    @Override
    protected void handleLeave(LeaveRequest leave) {
        System.out.println(leave.getName() + "请假" + leave.getNum() + "天," + leave.getReason()+ "。");
        System.out.println("总经理审批：同意。");
    }
}