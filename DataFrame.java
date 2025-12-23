public class DataFrame {
    private HashTable<SeriesV2<Object>> tabularData;
    private int numRows;
    private int numCols;

    public DataFrame(){
        numRows =0;
        numCols=0;
        tabularData = new HashTable<>();
    }
    public DataFrame(String _k, SeriesV2<Object> _series){
        numCols =1;
        numRows = _series.getLength();
        tabularData = new HashTable<>();
        tabularData.insert(_k, _series);
    }
    public SeriesV2<Object> colLoc(String k){
        return tabularData.lookup(k);
    }
    public String toString(){
        String[] keys = tabularData.getValidKeys();
        Object[] dat = tabularData.getDataArray();
        int keys_index = 0;
        StringBuilder s = new StringBuilder();
        s.append("printing the dataframe ...\n==================\n");
        for(int i=0; i<dat.length;i++){
            SeriesV2<Object> cur_row = (SeriesV2<Object>)dat[i];
            if(dat[i]!=null){
                s.append("[colName:\t" + keys[keys_index] + "]\n");
                keys_index++;
                s.append(dat[i].toString() +"\n");

            }
        }
        return s.toString();
    }
    public int getNumRows(){
        return numRows;
    }
    public int getNumCols(){
        return numCols;
    }
    public String[] getColNames(){
        String[] ogNames = tabularData.getValidKeys();
        String[] deepCopy = new String[ogNames.length];
        for(int i =0; i<ogNames.length; i++){
            deepCopy[i] = ogNames[i];
        }
        return deepCopy;
    }
    public void addColumn(String k, SeriesV2<Object> s) throws IllegalArgumentException{
        if(s.getLength()!=numRows){
            throw new IllegalArgumentException("addColumn(String k, SeriesV2<Object> s): the length of s does not match the dataframe's # of rows");
        }
        tabularData.insert(k, s);
        numCols++;

    }
    public void removeColumn(String k){
        tabularData.delete(k);
        numCols-=1;
    }

}
