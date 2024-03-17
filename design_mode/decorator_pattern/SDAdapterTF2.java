package design_mode.decorator_pattern;

public class SDAdapterTF2 implements SDCard{
    private TFCardImpl tfCard;
    public SDAdapterTF2(TFCardImpl tfCard) {
        this.tfCard = tfCard;
    }

    @Override
    public String readSD() {
        return tfCard.readTF();
    }

    @Override
    public void writeSD(String msg) {
        tfCard.writeTF(msg);
    }
}
