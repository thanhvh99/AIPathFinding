package AIPathFinding;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public final class LevelManager {

    private static final String RELATIVE_PATH = "/Level/";

    private LevelManager() {
    }

    public static int[][] loadLevel(int level) {
        String path = RELATIVE_PATH + "Level" + level + ".txt";
        ArrayList<String> list = new ArrayList<>();
        int col = 0;
        try {
            InputStream stream = LevelManager.class.getResourceAsStream(path);
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            String dummy;
            while ((dummy = reader.readLine()) != null) {
                col = Math.max(col, dummy.length());
                list.add(dummy);
            }
        } catch (Exception e) {
            return null;
        }
        int row = list.size();
        int[][] result = new int[row][col];
        for (int i = 0; i < row; i++) {
            String string = list.get(i);
            for (int j = 0; j < string.length(); j++) {
                result[i][j] = string.charAt(j) - '0';
            }
        }
        return result;
    }
}
