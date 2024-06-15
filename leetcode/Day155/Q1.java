package leetcode.Day155;

public class Q1 {
    public int compareVersion(String version1, String version2) {
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");
        return compare(v1, v2);
    }

    private int compare(String[] v1, String[] v2) {
        if (v1.length < v2.length) {
            return -compare(v2, v1);
        }

        for (int i = 0; i < v1.length; i++) {
            int x = Integer.parseInt(v1[i]);
            int y = i >= v2.length ? 0 : Integer.parseInt(v2[i]);
            if (x != y) {
                return x > y ? 1 : -1;
            }
        }
        return 0;
    }
}
