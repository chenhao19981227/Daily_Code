package design_mode.decorator_pattern;

public class TFCardImpl implements TFCard{

    @Override
    public String readTF() {
        return "readTF";
    }

    @Override
    public void writeTF(String msg) {
        System.out.println(msg+" 写入TF卡");
    }
}
