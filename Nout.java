
public class Nout {
    private String serialNumber;
    private int screenSizeInch;
    private int ramSizeGb;
    private int diskSizeGb;
    private String os;
    private String color;

    public Nout(String serialNumber, int screenSizeInch, int ramSizeGb, int diskSizeGb, String os, String color) {
        this.serialNumber = serialNumber;
        this.screenSizeInch = screenSizeInch;
        this.ramSizeGb = ramSizeGb;
        this.diskSizeGb = diskSizeGb;
        this.os = os;
        this.color = color;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public int getScreenSizeInch() {
        return screenSizeInch;
    }

    public int getRamSizeGb() {
        return ramSizeGb;
    }

    public int getDiskSizeGb() {
        return diskSizeGb;
    }

    public String getOs() {
        return os;
    }

    public String getColor() {
        return color;
    }
}