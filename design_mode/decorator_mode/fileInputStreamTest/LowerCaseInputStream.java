package design_mode.decorator_mode.fileInputStreamTest;

import java.io.*;

public class LowerCaseInputStream extends FilterInputStream {
    // 利用装饰者模式创建自己的java i/o 类，把输入流的大写字母都改为小写字母
    /**
     * Creates a <code>FilterInputStream</code>
     * by assigning the  argument <code>in</code>
     * to the field <code>this.in</code> so as
     * to remember it for later use.
     *
     * @param in the underlying input stream, or <code>null</code> if
     *           this instance is to be created without an underlying stream.
     */
    protected LowerCaseInputStream(InputStream in) {
        super(in);
    }
    public int read() throws IOException{
        int c=super.read();
        return (c==-1?c:Character.toLowerCase((char) c));
    }
    public int read(byte[] b ,int offset,int len) throws IOException{
        int result =super.read(b,offset,len);
        for (int i = offset; i < offset+result; i++) {
            b[i]=(byte) Character.toLowerCase((char) b[i]);
        }
        return result;
    }
}
