package effective_java.chapter2_creating_and_destroying_objects.item09;

import java.io.*;

public class Resource {
    public static void main(String[] args) throws IOException {
        String line = readFile("");
        System.out.println(line);
    }

    private static String readFile(String path) throws IOException {
        try(BufferedReader reader=new BufferedReader(new FileReader(path))){
            return reader.readLine();
        }
    }
    static void copy(String src, String desc) throws IOException {
        try (InputStream in = new FileInputStream(src);
             OutputStream out = new FileOutputStream(desc)) {
            byte[] bytes = new byte[1024];
            int n;
            while ((n = in.read(bytes)) != -1) {
                out.write(bytes, 0, n);
            }
        }
    }
}
