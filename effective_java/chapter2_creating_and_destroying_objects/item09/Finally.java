package effective_java.chapter2_creating_and_destroying_objects.item09;

import java.io.*;

public class Finally {
    public static void main(String[] args) throws IOException {
        String line = readFile("");
        System.out.println(line);
    }

    private static String readFile(String path) throws IOException {
        BufferedReader br=null;
        try {
            br=new BufferedReader(new FileReader(path));
            return br.readLine();
        }finally {
            br.close();
        }
    }
    static void copy(String src, String desc) throws IOException {
        InputStream in = new FileInputStream(src);
        try {
            OutputStream out = new FileOutputStream(desc);
            byte[] bytes = new byte[1024];
            int n;
            try {
                while ((n = in.read(bytes)) != -1) {
                    out.write(bytes, 0, n);
                }
            } finally {
                out.close();
            }
        } finally {
            in.close();
        }
    }
}
