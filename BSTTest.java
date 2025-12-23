import org.junit.jupiter.api.Test;

class BSTTest {
    private BST b;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        b = new BST();
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }
    @Test
    void toStringTest(){
        System.out.println(b.toString());

    }

    @Test
    void addNodeTest(){
        b.addNode(3,"hi");
        b.addNode(2, "hello");
        b.addNode(5, "hey");
        System.out.println(b.getSize());
        System.out.println(b.toString());
    }
    @Test
    void searchNodeTest(){
        b.addNode(3,"hi");
        b.addNode(2,"hello");
        b.addNode(5,"hey");
        BST.BSTNode test1 = b.searchNode(2);
        BST.BSTNode test2 = b.searchNode(4);
        System.out.println(test1.toString());
        System.out.println(test2);
    }
    @Test
    void updateNodeTest(){
        b.addNode(3,"hi");
        b.addNode(2,"hello");
        b.addNode(5,"hey");
        b.updateNode(3,"updated");
        System.out.println(b.toString());
    }
    @Test
    void removeNodeTest(){
        b.addNode(3,"hi");
        b.addNode(2,"hello");
        b.addNode(5,"hey");
        b.addNode(6,"first");
        b.addNode(4,"second");
        System.out.println(b.toString());
        b.removeNode(3);
        b.removeNode(5);
        b.removeNode(6);
        System.out.println(b.toString());
    }



}