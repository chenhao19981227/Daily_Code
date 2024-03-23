package design_mode.adapter_pattern;

public class SDCardImpl implements SDCard{
    @Override
    public String readSD() {
        return "readSD";
    }

    @Override
    public void writeSD(String msg) {
        System.out.println(msg+" 写入SD卡");
    }
}
