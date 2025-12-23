import org.junit.jupiter.api.Test;

class HashTableTest {
    private HashTable h;
    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        h = new HashTable();
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }
    @Test
    void toStringTest(){
        System.out.println(h.toString());
    }
    @Test
    void getValidKeys(){
        String[] n = h.getValidKeys();
        System.out.println(n.length);
    }
    @Test
    void lookupTest(){
        h.insert("hi", 2);
        System.out.println(h.lookup("hi"));
    }
    @Test
    void insertTest(){
        h.insert("hello", 3);
        System.out.println(h.toString());
        System.out.println(h.insert("hi", 2));
        System.out.println(h.toString());
        System.out.println(h.insert("hey",1));
        System.out.println(h.toString());
        System.out.println(h.insert("doubleup", 6));
        System.out.println(h.toString());
    }
    @Test
    void deleteTest(){
        h.insert("hello", 3);
        h.insert("hi", 2);
        h.insert("hey",1);
        h.insert("doubleup", 6);
        System.out.println(h.toString());
        h.delete("hello");
        System.out.println(h.toString());
        h.delete("hi");
        h.delete("hey");
        System.out.println(h.toString());

    }

}