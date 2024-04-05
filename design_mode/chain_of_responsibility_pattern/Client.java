package design_mode.chain_of_responsibility_pattern;

public class Client {
    public static void main(String[] args) {
        LeaveRequest leave = new LeaveRequest("ch",4,"发烧");
        GroupLeader groupLeader = new GroupLeader();
        groupLeader.submit(leave);
    }
}
