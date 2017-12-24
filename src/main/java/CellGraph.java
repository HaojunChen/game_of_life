import java.util.Vector;

public class CellGraph {
    private int width;
    private int height;
    private Vector<Vector<Integer>> seed;// 种子
    public static final int ALIVE = 1;
    public static final int DEAD = 0;

    public CellGraph() {
        // TODO Auto-generated constructor stub
    }

    public CellGraph(int width, int height) {// 初始化细胞图为width*height的细胞图
        super();
        this.width = width;
        this.height = height;
        // 种子全部为死细胞
        this.seed = new Vector<Vector<Integer>>();
        for (int x = 0; x < height; x++) {
            Vector<Integer> one_line_of_cells = new Vector<Integer>();
            for (int y = 0; y < width; y++) {
                one_line_of_cells.add(CellGraph.DEAD);
                ;
            }
            seed.add(one_line_of_cells);
        }
    }

    public CellGraph(int width, int height, Vector<Vector<Integer>> seed) {
        super();
        this.width = width;
        this.height = height;
        this.seed = seed;
    }

    public Vector<Vector<Integer>> getSeed() {
        return seed;
    }

    /* 获得坐标为x,y的细胞下一代的状态 */
    public int get_the_state_of_cell_at_location_x_y_in_the_next_generation(int x, int y) {
        int state_now = seed.get(x).get(y);
        int count = count_the_number_of_surrounding_alive_cells(x, y);
        if (state_now == CellGraph.ALIVE) {
            if (count < 2)
                return CellGraph.DEAD;
            else if (count == 2 || count == 3)
                return seed.get(x).get(y);
            else
                return CellGraph.DEAD;

        } else {
            if (count == 3)
                return CellGraph.ALIVE;
            else
                return CellGraph.DEAD;
        }
    }

    /* 计算细胞位置为x,y的周围活细胞数目 */
    private int count_the_number_of_surrounding_alive_cells(int x, int y) {
        int count = 0;
        if (!beyondRange(x - 1, y - 1) && seed.get(x - 1).get(y - 1) == CellGraph.ALIVE)
            count++;
        if (!beyondRange(x - 1, y) && seed.get(x - 1).get(y) == CellGraph.ALIVE)
            count++;
        if (!beyondRange(x - 1, y + 1) && seed.get(x - 1).get(y + 1) == CellGraph.ALIVE)
            count++;
        if (!beyondRange(x, y + 1) && seed.get(x).get(y + 1) == CellGraph.ALIVE)
            count++;
        if (!beyondRange(x + 1, y + 1) && seed.get(x + 1).get(y + 1) == CellGraph.ALIVE)
            count++;
        if (!beyondRange(x + 1, y) && seed.get(x + 1).get(y) == CellGraph.ALIVE)
            count++;
        if (!beyondRange(x + 1, y - 1) && seed.get(x + 1).get(y - 1) == CellGraph.ALIVE)
            count++;
        if (!beyondRange(x, y - 1) && seed.get(x).get(y - 1) == CellGraph.ALIVE)
            count++;
        return count;
    }

    /* 判断细胞位置是否超出范围 */
    private boolean beyondRange(int x, int y) {
        if (x < 0 || x >= height || y < 0 || y >= width)
            return true;
        return false;
    }

    /* 下一代细胞图 */
    public CellGraph nextGeneration() {
        CellGraph nextGeneration = new CellGraph(width, height);
        Vector<Vector<Integer>> seed = nextGeneration.getSeed();
        for (int x = 0; x < height; x++) {
            for (int y = 0; y < width; y++) {
                try {
                    int state = get_the_state_of_cell_at_location_x_y_in_the_next_generation(x, y);
                    if (state == CellGraph.ALIVE)
                        seed.get(x).set(y, CellGraph.ALIVE);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println(e.getMessage());
                }
            }
        }
        return nextGeneration;
    }

    /* 展示 */
    public void display() {
        for (Vector<Integer> one_line_of_cells : seed) {
            for (int cellState : one_line_of_cells) {
                if (cellState == CellGraph.ALIVE) {
                    System.out.print("*");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    /* hashCode and equals */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + height;
        result = prime * result + ((seed == null) ? 0 : seed.hashCode());
        result = prime * result + width;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CellGraph other = (CellGraph) obj;
        if (height != other.height)
            return false;
        if (seed == null) {
            if (other.seed != null)
                return false;
        } else if (!seed.equals(other.seed))
            return false;
        if (width != other.width)
            return false;
        return true;
    }

    // getter and setter
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setSeed(Vector<Vector<Integer>> seed) {
        this.seed = seed;
    }

}
