package design_mode.bridge_pattern;

public class RMVBBFile implements VideoFile{
    @Override
    public void decode(String fileName) {
        System.out.println("rmvb视频");
    }
}
