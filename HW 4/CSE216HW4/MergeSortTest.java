// Nitin Dev
// NDEV
// 112298641

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class MergeSortTest {

    @Test
    public void emptyTEST() {
        assertTrue(MergeSort.sorted(new int[]{}));
    }

    @Test
    public void oneNumberSORTED(){
        assertTrue(MergeSort.sorted(new int[]{5}));
        assertTrue(MergeSort.sorted(new int[]{154}));
        assertTrue(MergeSort.sorted(new int[]{-69}));
        assertTrue(MergeSort.sorted(new int[]{-5839}));
    }

    @Test
    public void alreadySORTED(){
        assertTrue(MergeSort.sorted(new int[]{15, 36}));
        assertTrue(MergeSort.sorted(new int[]{0, 5, 10}));
        assertTrue(MergeSort.sorted(new int[]{-75,-27, 0, 70}));
        assertTrue(MergeSort.sorted(new int[]{-4, 0, 1, 50, 100}));
        assertTrue(MergeSort.sorted(new int[]{-2832, -394, -39, -1, 0, 9, 68, 300, 4873}));
    }

    @Test
    public void notSORTED(){
        assertFalse(MergeSort.sorted(new int[]{932, 8, -46}));
        assertFalse(MergeSort.sorted(new int[]{10, 9, 8}));
        assertFalse(MergeSort.sorted(new int[]{8492, 5, 604, 62, 10}));
        assertFalse(MergeSort.sorted(new int[]{593, 9}));
    }
}