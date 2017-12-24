import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;

public class Client {

    public static void main(String[] args) {
        CellGraph cellGraph = null;
        int width = 0, height = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("请输入宽：");
            width = Integer.parseInt(br.readLine());
            System.out.println("请输入高：");
            height = Integer.parseInt(br.readLine());
            cellGraph= new CellGraph(width,height);
            System.out.println("请输入种子：");
            Vector<Vector<Integer>> seed = new Vector<Vector<Integer>>();
            for (int x = 0; x < height; x++) {
                String one_line_of_cells_string = br.readLine();
                String[] one_line_of_cells_array = one_line_of_cells_string.split(" ");
                Vector<Integer> one_line_of_cells = new Vector<Integer>();
                if (one_line_of_cells_array.length != width)
                    return;
                for (int y = 0; y < width; y++) {
                    if (CellGraph.ALIVE == Integer.parseInt(one_line_of_cells_array[y])) {
                        one_line_of_cells.addElement(CellGraph.ALIVE);
                    } else {
                        one_line_of_cells.addElement(CellGraph.DEAD);
                    }
                }
                seed.addElement(one_line_of_cells);
            }
            cellGraph.setSeed(seed);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        CellGraph tmp = cellGraph;
        System.out.println("seed");
        tmp.display();
        for (int i = 0; i < 100; i++) {
            try {
                Thread.currentThread().sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("generation"+(i+1));
            tmp = tmp.nextGeneration();
            tmp.display();
        }
    }
}
