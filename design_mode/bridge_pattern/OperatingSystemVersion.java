package design_mode.bridge_pattern;

public abstract class OperatingSystemVersion {
    protected VideoFile videoFile;
    public OperatingSystemVersion(VideoFile videoFile){
        this.videoFile=videoFile;
    }
    public abstract void play(String fileName);
}
