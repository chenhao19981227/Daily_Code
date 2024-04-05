package design_mode.chain_of_responsibility_pattern;

import lombok.Getter;

@Getter
public class LeaveRequest {
    private final String name;
    private final int num;
    private final String reason;

    public LeaveRequest(String name, int num, String reason) {
        this.name = name;
        this.num = num;
        this.reason = reason;
    }
}
