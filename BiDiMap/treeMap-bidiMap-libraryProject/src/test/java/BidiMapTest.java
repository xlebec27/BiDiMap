import cs.vsu.ru.treeMap_bidiMap.BidiMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BidiMapTest {

    BidiMap<Integer, String> bidiMap = new BidiMap<>();

    @BeforeEach
    void bidiMapGenerate(){
        BidiMap<Integer, String> bidiMap = new BidiMap<>();
    }

    @Test
    public void testBidiMap() {
        bidiMap.put(15, "abc");
        bidiMap.put(20, "adb");
        bidiMap.put(31, "bfe");
        bidiMap.removeByKey(15);
        bidiMap.removeByValue("adb");
        bidiMap.put(31,"adb");
        assertFalse(bidiMap.containsKey(15));
        assertFalse(bidiMap.containsKey(20));
        assertTrue(bidiMap.containsValue("adb"));
        assertFalse(bidiMap.containsValue("bfe"));
    }

    @Test
    void emptyBidiMap(){
        assertFalse(bidiMap.containsKey(1));
        assertFalse(bidiMap.containsValue("AAA"));
    }

    @Test
    void testRemove(){
        bidiMap.put(15, "abc");
        bidiMap.put(20, "bfb");
        bidiMap.put(31, "dbd");
        bidiMap.removeByKey(15);
        assertTrue(bidiMap.containsKey(20));
        assertFalse(bidiMap.containsValue("abc"));
        assertTrue(bidiMap.containsValue("dbd"));
        bidiMap.removeByValue("bfb");
        bidiMap.removeByValue("dbd");
        assertFalse(bidiMap.containsKey(20));
        assertFalse(bidiMap.containsKey(31));
        assertFalse(bidiMap.containsValue("bfb"));
        assertFalse(bidiMap.containsValue("dbd"));
    }

    @Test
    void testReplace(){
        bidiMap.put(15, "abc");
        bidiMap.put(20, "bfb");
        bidiMap.put(31, "dbd");
        assertEquals("dbd", bidiMap.getValue(31));
        bidiMap.put(31, "add");
        assertEquals(31, (int) bidiMap.getKey("add"));
        bidiMap.put(27, "abc");
        assertEquals(27, (int) bidiMap.getKey("abc"));
        assertFalse(bidiMap.containsKey(15));
    }
}
