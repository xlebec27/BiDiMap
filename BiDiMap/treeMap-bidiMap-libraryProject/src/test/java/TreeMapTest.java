import cs.vsu.ru.treeMap_bidiMap.TreeMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TreeMapTest {
    private TreeMap<Integer, String> treeMap = new TreeMap<>();

    @BeforeEach
    void treeMapGenerate(){
        treeMap = new TreeMap<Integer, String>();
    }

    @Test
    void testPut(){
        treeMap.put(15, "abc");
        treeMap.put(20, "bfb");
        treeMap.put(31, "dbd");
        assertTrue(treeMap.containsKey(15));
        assertTrue(treeMap.containsKey(20));
        assertEquals("dbd", (String) treeMap.getValue(31));
    }

    @Test
    void emptyBidiMap(){
        assertFalse(treeMap.containsKey(1));
    }

    @Test
    void testRemove(){
        treeMap.put(15, "abc");
        treeMap.put(20, "bfb");
        treeMap.put(31, "dbd");
        treeMap.remove(15);
        assertTrue(treeMap.containsKey(20));
        assertFalse(treeMap.containsKey(15));
        assertTrue(treeMap.containsKey(31));
        treeMap.remove(20);
        treeMap.remove(31);
        assertFalse(treeMap.containsKey(20));
        assertFalse(treeMap.containsKey(31));
    }

    @Test
    void testReplace(){
        treeMap.put(15, "abc");
        treeMap.put(20, "bfb");
        treeMap.put(31, "dbd");
        assertEquals("dbd", treeMap.getValue(31));
        treeMap.put(31, "add");
        assertEquals("add", treeMap.getValue(31));
    }

}
