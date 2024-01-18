package design_mode.decorator_mode.fileInputStreamTest;

import java.io.*;

public class test {
    public static void main(String[] args) throws IOException {
        int c;
        try {
            InputStream in =new LowerCaseInputStream(new BufferedInputStream(new FileInputStream("design_mode/decorator_mode/fileInputStreamTest/test.txt")));
            while ((c=in.read())>=0){
                System.out.print((char) c);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
