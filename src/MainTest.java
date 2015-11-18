import org.junit.Assert;
import org.junit.Test;

public class MainTest {

    @Test
    public void testSolve() throws Exception {
        int [] excluded = {1};
        Assert.assertEquals(2, Main.steps(0, 10, excluded));

        int [] excluded1 = {9};
        int solve = Main.steps(100, 95, excluded1);
        Assert.assertEquals(5, solve);

        int [] excluded3 = {9, 8};
        int solve3 = Main.steps(100, 90, excluded3);
        Assert.assertEquals(10, solve3);

        int [] excluded4 = {9, 8, 7, 6, 5, 4, 3, 2};
        int solve4 = Main.steps(100, 90, excluded4);
        Assert.assertEquals(10, solve4);
    }

    @Test
    public void testContainsExcluded() throws Exception {
        int [] excluded = {2};
        boolean containsExcluded = Main.containsExcluded(42, excluded);
        Assert.assertEquals(true, containsExcluded);
    }
}