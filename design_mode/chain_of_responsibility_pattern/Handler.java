package design_mode.chain_of_responsibility_pattern;

import lombok.Setter;

public abstract class Handler {
    protected final static int NUM_ONE = 1;
    protected final static int NUM_THREE = 3;
    protected final static int NUM_SEVEN = 7;
    @Setter
    private Handler nextHandler;
    private int numStart;
    private int numEnd;
    //设置请假天数范围 上不封顶
    public Handler(int numStart) {
        this.numStart = numStart;
    }

    //设置请假天数范围
    public Handler(int numStart, int numEnd) {
        this.numStart = numStart;
        this.numEnd = numEnd;
    }
    public final void submit(LeaveRequest leaveRequest){
        if(0==numStart)return;
        if(leaveRequest.getNum()>=numStart){
            this.handleLeave(leaveRequest);
            //如果还有上级 并且请假天数超过了当前领导的处理范围
            if(null != this.nextHandler && leaveRequest.getNum() > numEnd){
                this.nextHandler.submit(leaveRequest);//继续提交
            } else {
                System.out.println("流程结束");
            }
        }
    }
    protected abstract void handleLeave(LeaveRequest leave);
}
