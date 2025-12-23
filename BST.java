public class BST<I extends Comparable<? super I>, T>{

    class BSTNode {
        private I index;
        private T data;
        private BSTNode left;
        private BSTNode right;

        /**
         * Default constructor. Sets all instance variables to be null.
         */
        public BSTNode() {
            index = null;
            data= null;
            left= null;
            right = null;
            // TODO
        }

        /**
         * Constructor. Sets data and index to be _data and _index respectively.
         */
        public BSTNode(I _index, T _data) {
            // TODO
            data = _data;
            index = _index;
        }

        /**
         * Returns the index stored in this node.
         */
        public I getIndex() {
            // TODO
            return index;
        }
        public BSTNode getLeft(){
            return left;
        }
        public BSTNode getRight(){
            return right;
        }
        public void setLeft(BSTNode n){
            left = n;
        }
        public void setRight(BSTNode n){
            right = n;
        }

        /**
         * Returns the data stored in this node.
         */
        public T getData() {
            // TODO
            return data;
        }

        /**
         * Updates the data in this node to the specified value.
         */
        public void setData(T d) {
            data  =d;
            // TODO
        }
//        public void setIndex(I _i){
//            index=_i;
//        }

        /**
         * Returns a string representation of the node, indicating its index and data.
         */
        public String toString() {
            // TODO
            String s = "index:\t" + index + ",\tdata:\t"+ data+ "\n";
            return s;
        }
    }


    private BSTNode root;
    private int size;

    /**
     * Constructor. Initializes an empty BST with root set to null and size set to 0.
     */
    public BST() {
        // TODO
        root = null;
        size = 0;
    }


    /**
     * Performs an in-order traversal of the BST and records indices and data values.
     */
    private String inOrderTraversal(BSTNode node) {
        // TODO
       if(node==null){
           return "";
       }

            String s = "";
            s+=inOrderTraversal(node.getLeft());
            s+= node.toString();
            s+=inOrderTraversal(node.getRight());
            return s;




    }

    /**
     * Returns a string representation of the entire BST using in-order traversal.
     */
    public String toString() {
        // TODO
        StringBuilder s = new StringBuilder();
        s.append("In-order Traversal of the BST ...");
        s.append("\n==================\n");
        s.append(inOrderTraversal(root));
        return s.toString();
    }

    /**
     * Returns the size of the BST, i.e., the number of valid nodes.
     */
    public int getSize() {
        // TODO
        return size;
    }

    /**
     * Adds a new node with the specified index and data to the BST.
     */
    public void addNode(I _index, T _data) {
        // TODO
        if(root==null){
            root = new BSTNode(_index, _data);
            size++;
            return;
        }
        else{
            boolean t = addNodeHelper(_index,_data, root);
            if(t){
               size++;
            }

        }

    }
    private boolean addNodeHelper(I _index, T _data, BSTNode cur){
        if(_index.compareTo(cur.getIndex())==0){
            return false;
        }
        if(_index.compareTo(cur.getIndex())<0){
            if(cur.getLeft()==null){
                cur.setLeft(new BSTNode(_index, _data));
                return true;
            }
            else{
                return addNodeHelper(_index, _data, cur.getLeft());
            }
        }
        else{
            if(cur.getRight()==null){
                cur.setRight(new BSTNode(_index, _data));
                return true;
            }
            else{
                return addNodeHelper(_index, _data, cur.getRight());
            }
        }
    }


    /**
     * Searches for a node with the specified index in the BST.
     */
    public BSTNode searchNode(I _index) {
        // TODO
        return searchNodeHelper(_index, root);


    }
    private BSTNode searchNodeHelper(I _index, BSTNode curNode){
        if(curNode.getIndex().compareTo(_index)==0){
            if(curNode==null){
                return null;
            }
            return curNode;
        }
        if (curNode.getIndex().compareTo(_index)<0){
            if(curNode.getRight()!=null){
                return searchNodeHelper(_index, curNode.getRight());
            }
            else{
                return null;
            }
        }
        else{
            if(curNode.getLeft()!=null){
                return searchNodeHelper(_index, curNode.getLeft());
            }
            else{
                return null;
            }
        }
    }

    /**
     * Removes a node with the specified index from the BST.
     */

    private BSTNode findParent(I _index,BSTNode parNode ){

        if(parNode==null){
            return null;
        }
        if(_index==root.getIndex()){
            return null;
        }
        if(parNode.getRight()!=null && parNode.getRight().getIndex().compareTo(_index)==0){
            return parNode;
        }
        if(parNode.getLeft()!=null && parNode.getLeft().getIndex().compareTo(_index)==0){
            return parNode;
        }
        if (parNode.getIndex().compareTo(_index)<0){
            if(parNode.getRight()!=null){
                return findParent(_index, parNode.getRight());
            }
            else{
                return findParent(_index,parNode.getLeft());
            }
        }
        else{
            if(parNode.getLeft()!=null){
                return findParent(_index, parNode.getLeft());
            }
            else{
                return null;
            }
        }

    }
    public void removeNode(I _index) throws IllegalArgumentException{
        BST.BSTNode n = searchNode(_index);
        if(n == null){
            throw new IllegalArgumentException("removeNode(I _index): No node with an index " + _index+" in the BST");
        }
        BSTNode parNode = findParent(_index, root);
        if(n.getRight()==null && n.getLeft()==null){
            if(n==root){
                root=null;
            }
            else if(parNode.getLeft()==n){
                parNode.setLeft(null);
            }
            else if(parNode.getRight()==n){
                parNode.setRight(null);
            }
            size--;
            return;

        }
        else if(n.getRight()==null|| n.getLeft()==null) {
            if (n == root) {
                if (n.getRight() == null) {
                    root = n.getLeft();
                } else {
                    root = n.getRight();
                }

            } else {
                if (n.getRight() == null) {
                    if (parNode.getIndex().compareTo(_index) < 0) {
                        parNode.setRight(n.getLeft());

                    } else {
                        parNode.setLeft(n.getLeft());

                    }
                } else {
                    if (parNode.getIndex().compareTo(_index) < 0) {
                        parNode.setRight(n.getRight());

                    } else {
                        parNode.setLeft(n.getRight());

                    }
                }
            }
        }
        else if (n.getRight()!=null && n.getLeft()!=null){
                BSTNode successor = n.getRight();
                BSTNode sucParent = n;
                while(successor!=null && successor.getLeft()!=null){
                    sucParent = successor;
                    successor = successor.getLeft();
                }
                if(sucParent!=n){
                    sucParent.setLeft(successor.getRight());
                    successor.setRight(n.getRight());
                }
                successor.setLeft(n.getLeft());
                if(n==root){
                    root=successor;
                }
                else if(parNode.getLeft()==n){
                    parNode.setLeft(successor);
                }
                else if(parNode.getRight()==n){
                    parNode.setRight(successor);
                }

        }
        size=size-1;
        return;



    }

    /**
     * Updates a node's data with a new value, given its index.
     */
    public void updateNode(I _index, T _newData) {

        // TODO
        BSTNode n = searchNode(_index);
        if (n==null){
            throw new IllegalArgumentException("updateNode(I _index, T _newData): No node with an index " + _index + " in the BST");
        }
        n.setData(_newData);
    }

    
/************************************ GRADING CODE (DO NOT MODIFY) ************************************ */
    /**
     * Performs a pre-order traversal of the BST.
     */
    private void preOrderTraversal(BSTNode node, int[] idx, String[] arr, boolean dataFlag) {
        // DO NOT CHANGE THIS. THIS FOR TESTING PURPOSES
        if(node == null)
            return;

        if(dataFlag)
            arr[idx[0]] = String.valueOf(node.getData());
        else
            arr[idx[0]] = String.valueOf(node.getIndex());
        idx[0]++;
        
        preOrderTraversal(node.left, idx, arr, dataFlag);
        preOrderTraversal(node.right, idx, arr, dataFlag);
    }

    /**
     * Returns an array of data values in pre-order traversal order.
     * @return A String array containing the data values of all nodes in pre-order order
     */
    public String[] getDataArray() {
        /// DO NOT CHANGE THIS. THIS FOR TESTING PURPOSES
        String[] dataArr = new String[size];
        preOrderTraversal(this.root, new int[1], dataArr, true);
        return dataArr;
    }

    /**
     * Returns an array of index values in pre-order traversal order.
     * @return A String array containing the index values of all nodes in pre-order order
     */
    public String[] getIndexArray() {
        // DO NOT CHANGE THIS. THIS FOR TESTING PURPOSES
        String[] indexArr = new String[size];
        preOrderTraversal(this.root, new int[1], indexArr, false);
        return indexArr;
    }

/****************************************************************************************************** */

}
