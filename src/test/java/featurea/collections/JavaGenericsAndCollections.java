package featurea.collections;

import org.junit.Test;

import static org.junit.Assert.*;

public class JavaGenericsAndCollections {

    @Test
    public void sumInts() {
        List<Integer> numbers = Arrays.asList(1, 2, 3);
        int result = 0;
        for (int number : numbers) {
            result += number;
        }
        assertEquals(6, result);

    /* boxing and unboxing in Action
    List<Integer> numbers = Arrays.asList(new Integer[] { Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(3) }); // boxing
    int result = 0;
    for (Iterator localIterator = numbers.iterator(); localIterator.hasNext();)
    {
      int number = ((Integer)localIterator.next()).intValue(); // unboxing
      result += number;
    }
    Assert.assertEquals("Sum is incorrect", 6L, result);
    */
    }

    private Integer sumInteger(List<Integer> numbers) {
        Integer result = 0;
        // >>
        for (Integer number : numbers) {
            result += number;
        }
    /*
    for (Integer number : numbers) {
      result = Integer.valueOf(result.intValue() + number.intValue());
    }
    <<
    */
        return result;
    }

    private int sumInt(List<Integer> numbers) {
        int result = 0;
        // >>
        for (int number : numbers) {
            result += number;
        }
    /*
    for (Iterator localIterator = numbers.iterator(); localIterator.hasNext();) {
      int number = ((Integer)localIterator.next()).intValue();
      result += number;
    }
    << */
        return result;
    }

    /*
     * We recommend that you never use == to compare values of type Integer. Either unbox first, so == compares
     * values of type int, or else use equals to compare values of type Integer.
     */
    @Test
    public void integerCacheForSmallAndBigNumbers() {
        List<Integer> smallNumbers = Arrays.asList(1, 2, 3);
        assertSame(sumInteger(smallNumbers), sumInteger(smallNumbers));
        assertSame(sumInteger(smallNumbers), sumInt(smallNumbers));
        assertSame(sumInteger(smallNumbers) == sumInt(smallNumbers), true);

        List<Integer> bigNumbers = Arrays.asList(100, 200, 300);
        assertNotSame(sumInteger(bigNumbers), sumInteger(bigNumbers));
        assertNotSame(sumInteger(bigNumbers), sumInt(bigNumbers));
        assertSame(sumInteger(bigNumbers) == sumInt(bigNumbers), true);
    }

    @Test
    public void cacheIntegers() {
        // cache
        assertSame(Integer.valueOf(-128), Integer.valueOf(-128));
        assertSame(Integer.valueOf(12), Integer.valueOf(12));
        assertSame(Integer.valueOf(127), Integer.valueOf(127));
        assertNotSame(Integer.valueOf(-129), Integer.valueOf(-129));
        assertNotSame(Integer.valueOf(128), Integer.valueOf(128));

        // new Integer
        assertNotSame(new Integer(12), new Integer(12));
    }

    @Test
    public void doubleAndIntegerBoxToNumber() {
        List<Number> numbers = new ArrayList<>();
        numbers.add(2D);
        numbers.add(3.14);
        assertEquals("[2.0, 3.14]", numbers.toString());
    }

    public void justForDecompiler() {
        int s = 0;
        for (int i : new int[]{1, 2, 3, 4}) {
            s += i;
        }
        System.out.println("s: " + s);
    }

    @Test(expected = ArrayStoreException.class)
    public void arrayStoreException() {
        Number[] numbers = new Integer[2];
        numbers[0] = 2;
        numbers[1] = 2D;

        // incompatible types: java.util.ArrayList<java.lang.Integer> cannot be converted to java.util.List<java.lang.Number>
        // List<Number> numbers = new ArrayList<Integer>();
    }

    @Test
    public void erasure() {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4);
        List<String> strings = Arrays.asList("a", "b", "c", "d");
        assertSame(integers.getClass(), strings.getClass());
    }

    @Test
    @SuppressWarnings("unchecked")
    public void uncheckedConversionWarning() {
        // >> Unchecked assignment 'java.util.ArrayList' to 'java.util.List<Integer>', requires @SuppressWarnings("unchecked")
        List<Integer> list = new ArrayList();
        // <<
        assertTrue(true);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void uncheckedConversionWarning2() {
        List list = new ArrayList();
        // >> Unchecked call to 'add(E)' as a member of raw type 'java.util.List', requires @SuppressWarnings("unchecked")
        list.add("q");
        // <<
        assertTrue(true);
    }

    @Test
    public void toArrayNoSizeRequired() {
        List<String> list = Arrays.asList("a", "b", "c", "d");
        String[] strings = list.toArray(new String[0]); // without length
        assertArrayEquals(strings, new String[]{"a", "b", "c", "d"});
    }

    @Test(expected = ClassCastException.class)
    public void checkedList() {
        class X {
            void addString(List list) {
                list.add("text");
            }
        }
        X x = new X();

        List<Integer> list = new ArrayList<>();
        x.addString(list);

        List<Integer> checkedList = Collections.checkedList(list, Integer.class);
        x.addString(checkedList);
    }

    @Test(expected = ConcurrentModificationException.class)
    public void changeListWhileIterating() {
        List<Integer> list = new ArrayList<>();
        Collections.addAll(list, 1, 2, 3, 4);
        for (int i : list) {
            list.add(0);
        }
    }

    @Test
    public void weakReference() throws InterruptedException {
        Map map = new WeakHashMap<>();
        map.put(new Object(), new Object());
        while (!map.isEmpty()) {
            Thread.sleep(5);
            System.gc();
        }
        assertTrue(true);
    }

}
