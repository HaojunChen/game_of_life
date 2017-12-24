import static org.junit.Assert.*;

import java.util.Vector;
import org.junit.Test;

public class CellGraphTest {
    @Test /* 活细胞 - 周围活细胞数<2 - 死 */
    public void the_state_of_cell_at_location_1_1_in_the_next_generation() {
        CellGraph cellGraph = new CellGraph(5, 6);// 初始化细胞图（5*6且细胞全部为死亡状态）
        Vector<Vector<Integer>> seed = cellGraph.getSeed();
        seed.get(1).set(1, CellGraph.ALIVE);
        seed.get(1).set(3, CellGraph.ALIVE);
        seed.get(2).set(1, CellGraph.ALIVE);
        seed.get(2).set(3, CellGraph.ALIVE);
        seed.get(3).set(1, CellGraph.ALIVE);
        seed.get(3).set(2, CellGraph.ALIVE);
        seed.get(3).set(3, CellGraph.ALIVE);
        seed.get(4).set(2, CellGraph.ALIVE);

        assertEquals(CellGraph.DEAD, cellGraph.get_the_state_of_cell_at_location_x_y_in_the_next_generation(1, 1));
    }

    @Test /* 活细胞 - 周围活细胞数==2|3 - 不变 */
    public void the_state_of_cell_at_location_3_1_in_the_next_generation() {
        CellGraph cellGraph = new CellGraph(5, 6);// 初始化细胞图（5*6且细胞全部为死亡状态）
        Vector<Vector<Integer>> seed = cellGraph.getSeed();
        seed.get(1).set(1, CellGraph.ALIVE);
        seed.get(1).set(3, CellGraph.ALIVE);
        seed.get(2).set(1, CellGraph.ALIVE);
        seed.get(2).set(3, CellGraph.ALIVE);
        seed.get(3).set(1, CellGraph.ALIVE);
        seed.get(3).set(2, CellGraph.ALIVE);
        seed.get(3).set(3, CellGraph.ALIVE);
        seed.get(4).set(2, CellGraph.ALIVE);

        assertEquals(cellGraph.getSeed().get(2).get(3).intValue(),
                cellGraph.get_the_state_of_cell_at_location_x_y_in_the_next_generation(3, 1));
    }

    @Test /* 活细胞 - 周围活细胞数>3 - 死 */
    public void the_state_of_cell_at_location_3_2_in_the_next_generation() {
        CellGraph cellGraph = new CellGraph(5, 6);// 初始化细胞图（5*6且细胞全部为死亡状态）
        Vector<Vector<Integer>> seed = cellGraph.getSeed();
        seed.get(1).set(1, CellGraph.ALIVE);
        seed.get(1).set(3, CellGraph.ALIVE);
        seed.get(2).set(1, CellGraph.ALIVE);
        seed.get(2).set(3, CellGraph.ALIVE);
        seed.get(3).set(1, CellGraph.ALIVE);
        seed.get(3).set(2, CellGraph.ALIVE);
        seed.get(3).set(3, CellGraph.ALIVE);
        seed.get(4).set(2, CellGraph.ALIVE);

        assertEquals(CellGraph.DEAD, cellGraph.get_the_state_of_cell_at_location_x_y_in_the_next_generation(3, 2));
    }

    @Test /* 死细胞 - 周围活细胞数!=3 - 死 */
    public void the_state_of_cell_at_location_0_2_in_the_next_generation() {
        CellGraph cellGraph = new CellGraph(5, 6);// 初始化细胞图（5*6且细胞全部为死亡状态）
        Vector<Vector<Integer>> seed = cellGraph.getSeed();
        seed.get(1).set(1, CellGraph.ALIVE);
        seed.get(1).set(3, CellGraph.ALIVE);
        seed.get(2).set(1, CellGraph.ALIVE);
        seed.get(2).set(3, CellGraph.ALIVE);
        seed.get(3).set(1, CellGraph.ALIVE);
        seed.get(3).set(2, CellGraph.ALIVE);
        seed.get(3).set(3, CellGraph.ALIVE);
        seed.get(4).set(2, CellGraph.ALIVE);

        assertEquals(CellGraph.DEAD, cellGraph.get_the_state_of_cell_at_location_x_y_in_the_next_generation(0, 2));
    }

    @Test /* 死细胞 - 周围活细胞数==3 - 活 */
    public void the_state_of_cell_at_location_2_0_in_the_next_generation() {
        CellGraph cellGraph = new CellGraph(5, 6);// 初始化细胞图（5*6且细胞全部为死亡状态）
        Vector<Vector<Integer>> seed = cellGraph.getSeed();
        seed.get(1).set(1, CellGraph.ALIVE);
        seed.get(1).set(3, CellGraph.ALIVE);
        seed.get(2).set(1, CellGraph.ALIVE);
        seed.get(2).set(3, CellGraph.ALIVE);
        seed.get(3).set(1, CellGraph.ALIVE);
        seed.get(3).set(2, CellGraph.ALIVE);
        seed.get(3).set(3, CellGraph.ALIVE);
        seed.get(4).set(2, CellGraph.ALIVE);

        assertEquals(CellGraph.ALIVE, cellGraph.get_the_state_of_cell_at_location_x_y_in_the_next_generation(2, 0));
    }

    @Test /* 全部为死细胞 */
    public void testNextGeneration_deadSeed() {
        CellGraph cellGraph = new CellGraph(5, 6);
        assertEquals(cellGraph, cellGraph.nextGeneration());
    }

    @Test /* 该细胞图下一代与本代相等 */
    public void testNextGeneration_notChangeSeed() {
        CellGraph cellGraph = new CellGraph(4, 4);
        Vector<Vector<Integer>> seed = cellGraph.getSeed();
        seed.get(1).set(1, CellGraph.ALIVE);
        seed.get(1).set(2, CellGraph.ALIVE);
        seed.get(2).set(1, CellGraph.ALIVE);
        seed.get(2).set(2, CellGraph.ALIVE);
        assertEquals(cellGraph, cellGraph.nextGeneration());
    }

    @Test /* 普通的种子 */
    public void testNextGeneration_commonSeed() {
        CellGraph cellGraph = new CellGraph(4, 4);
        Vector<Vector<Integer>> seed = cellGraph.getSeed();
        seed.get(1).set(0, CellGraph.ALIVE);
        seed.get(1).set(1, CellGraph.ALIVE);
        seed.get(1).set(2, CellGraph.ALIVE);
        seed.get(1).set(3, CellGraph.ALIVE);

        CellGraph cellGraph2 = new CellGraph(4, 4);
        Vector<Vector<Integer>> seed2 = cellGraph2.getSeed();
        seed2.get(0).set(1, CellGraph.ALIVE);
        seed2.get(0).set(2, CellGraph.ALIVE);
        seed2.get(1).set(1, CellGraph.ALIVE);
        seed2.get(1).set(2, CellGraph.ALIVE);
        seed2.get(2).set(1, CellGraph.ALIVE);
        seed2.get(2).set(2, CellGraph.ALIVE);
        assertEquals(cellGraph2, cellGraph.nextGeneration());
    }
}
