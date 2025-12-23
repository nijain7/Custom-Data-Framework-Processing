public class HashTable<V> {
    private static final Object BRIDGE = new String("[BRIDGE]".toCharArray());
    private int size;
    private int capacity;
    private String[] keys;
    private V[] values;

    public HashTable() {
        size = 0;
        capacity = 4;
        keys = new String[capacity];
        values = (V[]) new Object[capacity];
    }

    public int getSize() {
        return size;
    }

    public int getCapacity() {
        return capacity;
    }

    public String[] getKeyArray() {
        String[] n = new String[capacity];
        for (int i = 0; i < capacity; i++) {
            n[i] = keys[i];
        }
        return n;
    }

    public V[] getDataArray() {
        V[] copy = (V[]) new Object[capacity];
        for (int i = 0; i < capacity; i++) {
            copy[i] = values[i];
        }
        return copy;

    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("printing the hash table ...\n==================\n");
        for (int i = 0; i < capacity; i++) {
            s.append("index:\t" + i + ",\t" + "key:\t" + keys[i] + ",\t" + "data:\t" + values[i]);
            s.append("\n");
        }
        return s.toString();
    }

    public String[] getValidKeys() {
        String[] n = getKeyArray();
        int size = 0;
        for (int i = 0; i < capacity; i++) {
            if (n[i] != null && !n[i].equals(BRIDGE)) {
                size++;
            }
        }
        String[] vKeys = new String[size];
        int vKeysInd = 0;
        for (int i = 0; i < capacity; i++) {
            if (n[i] != null && !n[i].equals(BRIDGE)) {
                vKeys[vKeysInd] = n[i];
                vKeysInd++;
            }
        }
        return vKeys;
    }
    public int getHashIndex(String k){
        int hashValue = 0;
        for (int i = 0; i < k.length(); i++) {
            int letter = k.charAt(i) - 96;
            hashValue += (hashValue * 27 + letter);
        }
        return hashValue % this.getCapacity();
    }
    public V lookup(String k) throws NullPointerException{
        if(k==null){
            throw new NullPointerException("lookup(String key): key is null");
        }
        int h_ind = getHashIndex(k);
        int searched = 0;
        while(searched<=keys.length){
            if(keys[h_ind]==null){
                break;
            }
            if(!keys[h_ind].equals(BRIDGE) && keys[h_ind].equals(k)){
                return values[h_ind];
            }
            h_ind++;
            if(h_ind ==capacity){
               h_ind =0;
            }
            searched++;
        }
        return null;
    }
    public int insert(String k, V v) throws NullPointerException{
        if(k==null){
            throw new NullPointerException("insert(String k, V v): k is null");
        }
        if(v==null){
            throw new NullPointerException("insert(String k, V v): v is null");
        }
        int h_index = getHashIndex(k);
        if(keys[h_index]==null|| keys[h_index].equals(BRIDGE)){
            keys[h_index]=k;
            values[h_index]=v;
            size+=1;
            return h_index;
        }
        while(keys[h_index]!=null && !keys[h_index].equals(BRIDGE)){
            if(keys[h_index].equals(k)){
                values[h_index]=v;
                return h_index;
            }
            h_index +=1;
            if(h_index==capacity){
                h_index =0;
            }
        }
        keys[h_index]=k;
        values[h_index]=v;
        size+=1;
        double loadFactor = ((double)size)/((double)capacity);
        if(loadFactor>=0.55) {
            sizeUp();
        }

        return h_index;
    }
    private void sizeUp(){
        String[] oldKeys = keys;
        V[] oldVals = values;
        int oldCap = capacity;
        capacity = capacity*2;
        values = (V[]) new Object[capacity];
        keys = new String[capacity];
        size=0;
        for(int i =0; i<oldVals.length; i++){
//            if(oldKeys[i]!=null&& !oldKeys[i].equals(BRIDGE)){
//                int h_index = getHashIndex(oldKeys[i]);
//                while(keys[h_index]!=null){
//                    h_index +=1;
//                    if(h_index==capacity){
//                        h_index =0;
//                    }
//                }
//                keys[h_index]=oldKeys[i];
//                values[h_index]=oldVals[i];
//                size++;
//            }
            if(oldKeys[i]!=null&& !oldKeys[i].equals(BRIDGE)){
                insert(oldKeys[i],oldVals[i]);
            }


        }
    }
    private void sizeDown(){
        String[] oldKeys = keys;
        V[] oldVals = values;
        int new_cap = capacity/2;
        int old_cap = capacity;
        if(new_cap<4){
            new_cap =4;
        }
        capacity = new_cap;
        values = (V[]) new Object[capacity];
        keys = new String[capacity];
        for(int i =0; i<old_cap; i++){
            if(oldKeys[i]!=null && !oldKeys[i].equals(BRIDGE)){
                int h_index = getHashIndex(oldKeys[i]);
                if(keys[h_index]==null|| keys[h_index].equals(BRIDGE)){
                    keys[h_index]=oldKeys[i];
                    values[h_index]=oldVals[i];
                    continue;
                }
                while(keys[h_index]!=null){
                    h_index +=1;
                    if(h_index==capacity){
                        h_index =0;
                    }
                }
                keys[h_index]=oldKeys[i];
                values[h_index]=oldVals[i];
            }


        }

    }
    public int delete(String k){
        int h_index= getHashIndex(k);
        int searched =0;
        int removed_ind = h_index;
        while(searched<=capacity){
            if(keys[removed_ind]==null){
                return h_index;
            }
            if(!keys[removed_ind].equals(BRIDGE) && keys[removed_ind].equals(k)){
                keys[removed_ind]= (String) BRIDGE;
                values[removed_ind]=null;
                size-=1;
                break;
            }
            searched+=1;
            removed_ind+=1;
            if(removed_ind==capacity){
                removed_ind=0;
            }
        }
        double loadFactor = ((double)size)/((double)capacity);
        if(loadFactor<=0.3){
            sizeDown();
        }
        return removed_ind;

    }


}


