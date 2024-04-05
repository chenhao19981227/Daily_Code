package design_mode.chain_of_responsibility_pattern;

public class GroupLeader extends Handler{
    public GroupLeader() {
        super(Handler.NUM_ONE,Handler.NUM_THREE);
        setNextHandler(new Manager());
    }
    @Override
    protected void handleLeave(LeaveRequest leave) {
        System.out.println(leave.getName() + "请假" + leave.getNum() + "天," + leave.getReason() + "。");
        System.out.println("小组长审批：同意。");
    }
}
