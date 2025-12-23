public class LL<T> {
    private LLNode head;
    private LLNode tail;
    private int length;
    public class LLNode {
        private String index;
        private T data;
        private LLNode next;

        public LLNode(){
            index = null;
            data= null;
            next= null;
        }
        public LLNode(String _index, T _data){
            data = _data;
            index = _index;
        }
        public String getIndex(){
            return index;
        }
        public T getData(){
            return data;
        }
        public void setData(T d){
            data = d;
        }
        public LLNode getNext(){
            return next;
        }
        public void setNext(LLNode n){
            next = n;
        }
    }


    public LL(){
        length=0;
        head = new LLNode();
        tail = new LLNode();
    }
    public String toString(){
        StringBuilder s = new StringBuilder();
        s.append("print the series ...\n==================\n");
        LLNode n =head;
        while(n!=null){
            if (n.getIndex()==null){
                s.append("null");
            }
            else{
                s.append(n.getIndex());
            }
            s.append("\t: ");
            if (n.getData()==null){
                s.append("null");
                s.append("\n");
            }
            else{
                s.append(n.getData().toString());
                s.append("\n");
            }
            n=n.getNext();
        }
        s.append("null\t: null\n");
        return s.toString();
    }
    public int getLength(){
        LLNode n = head.getNext();
        int count =0;
        while(n!=null) {
            count++;
            n = n.getNext();

        }
        return count;
    }
    public String[] getDataArray(){
        LLNode n = head.getNext();
        String[] dat = new String[this.getLength()];
        int ind= 0;
        while(n!=null){
            if(n.getData()==null){
                dat[ind]= "null";
            }
            else{
                dat[ind] = n.getData().toString();
            }
            ind++;
            n = n.getNext();
        }
        return dat;
    }

    public String[] getIndexArray(){
        LLNode n = head.getNext();
        String[] dat = new String[this.getLength()];
        int ind= 0;
        while(n!=null){
            if(n.getIndex()==null){
                dat[ind]= "null";
            }
            else{
                dat[ind] = n.getIndex();
            }
            ind++;
            n = n.getNext();
        }
        return dat;
    }
    public void appendNode(String _index, T _data){
        if(_index == null || _index == ""){
            _index = String.valueOf(this.getLength());
        }
         LLNode newNode = new LLNode(_index, _data);
         LLNode current = head;
         while(current.getNext()!= null){
             current= current.getNext();
         }
         current.setNext(newNode);

    }
    public LLNode searchNode(String _index){
        LLNode current = head;
        while(current!=null){
            if(current.getIndex()==null){
                current=current.getNext();
                continue;
            }
            if (current.getIndex().equals(_index)){
                return current;
            }
            current = current.getNext();
        }
        return null;
    }
    public void removeNode(String _index) throws IllegalArgumentException{
        boolean found = false;
        LLNode current = head;
        while(current.getNext()!=null){
            if (current.getNext().getIndex()!=null && current.getNext().getIndex().equals(_index)){
                current.setNext(current.getNext().getNext());
                found = true;
                return;
            }
            current = current.getNext();
        }
        if(!found){
            throw new IllegalArgumentException("removeNode(String _index): No node with an index " + _index+ " in the list");

        }

    }
    public void updateNode(String _index, T value) throws IllegalArgumentException{
        boolean found = false;
        LLNode current = head;
        while(current!=null){
            if(current.getIndex()!=null && current.getIndex().equals(_index)){
                current.setData(value);
                found = true;
                return;
            }
            current = current.getNext();
        }
        if(!found){
            throw new IllegalArgumentException("updateNode(String _index, T value): No node with an index " + _index+  " in the list");
        }
    }

}
