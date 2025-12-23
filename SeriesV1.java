public class SeriesV1<T> implements Series<T> {

     /** Row names of the series. */
     private LL<T> seriesData;
 
     /** The Integer array that contains the list of data that constitutes a Series object. */

 
     /**
      * Constructs a new Series object.
      *
      * @param _rowNames an array of row names
      * @param _data an array of Integer data
      */
     public SeriesV1(String[] _rowNames, T[] _data) throws NullPointerException,IllegalArgumentException {
          // TODO: Implement constructor
          seriesData = new LL();

          if (_data == null) {
               throw new NullPointerException("Series(String[] _index, T[] _data): _data can't be null. Terminating the program");
          }
//         data = _data;


               try {
                    if (_data.length != _rowNames.length) {
                         throw new IllegalArgumentException("Series(String[] _index, T[] _data): the length of _index and _data must be the same");
                    }
                    int len = _rowNames.length;
                    for (int i = 0; i < _rowNames.length; i++) {
                         if (_rowNames[i] == null) {
                              throw new IllegalArgumentException("Series(String[] _index, T[] _data): _rowNames is not valid");
                         }
                         seriesData.appendNode(_rowNames[i], _data[i]);
                    }
               }

//                    else {
//                         rowNames[i] = _rowNames[i];
//                    }
//               }
//
//          }
          catch (NullPointerException e) {
               for (int i = 0; i < _data.length; i++) {
                    seriesData.appendNode("", _data[i]);
               }
//
//          }


          }
     }
 
     /**
      * Returns a string representation of the Series object.
      */
     public String toString() {
         // TODO: Implement toString method
//          String s = "Printing Series...\n\n";
//          for(int i = 0; i<data.length; i++){
//               String cur_line = rowNames[i] + "\t" + data[i];
//               s+=cur_line+"\n";
//          }

          return seriesData.toString();
     }
 
     /**
      * Returns the length of the series object.
      */
     public int getLength() {
         // TODO: Implement getLength method
          return seriesData.getLength();
     }
 
     /**
      * Returns the row names of this Series object.
      */
     public String[] getRowNames() {
         // TODO: Implement getRowNames method
//          String[] rn = new String[rowNames.length];
//          for (int i = 0; i<rowNames.length; i++) {
//               rn[i] = rowNames[i];
//
//          }

          return seriesData.getIndexArray();
     }
 
     /**
      * Returns the data of this Series object as strings.
      */
     public String[] getData() {
         // TODO: Implement getData method
//          String[] strData = new String[data.length];
//          for(int i = 0; i<data.length; i++){
//               strData[i]= String.valueOf(data[i]);
//
//          }
          return seriesData.getDataArray();
     }
 
     /**
      * Adds a new pair of rowName and data at the end of the Series object.
      *
      * @param rn the row name to be added
      * @param d the Integer data value to be added
      */
     public void append(String rn, T d) {
         // TODO: Implement append method
//          if(rn==null|| rn== ""){
//               rn = String.valueOf(data.length);
//          }
//          T[] n_data = (T[]) new Object[data.length+1];
//          String[] n_row = new String[data.length+1];
//          for (int i = 0; i<data.length; i++){
//               n_data[i] = data[i];
//               n_row[i] = rowNames[i];
//          }
//          n_data[n_data.length-1]=d;
//          n_row[n_row.length-1]= rn;
//          data = n_data;
//          rowNames = n_row;
          seriesData.appendNode(rn,d);
     }
 
     /**
      * Retrieves a data value given a row name.
      *
      * @param rn the row name to search for
      */
     public T loc(String rn) throws NullPointerException, IllegalArgumentException{
         // TODO: Implement loc method
          int idx=-1;
          if(rn==null){
               throw new NullPointerException("loc(String rn): rn can't be null");
          }
          if(rn.equals("")){
               throw new IllegalArgumentException("loc(String rn): rn can't be an empty string");
          }

//          for (int i = 0; i<rowNames.length; i++){
//               if(rn== rowNames[i]){
//                    idx =i;
//                    break;
//               }
//          }
//          if (idx ==-1){
//               return null;
//          }
//       return data[idx];
          if(seriesData.searchNode(rn)==null){
               return null;
          }
          LL.LLNode n = seriesData.searchNode(rn);
          return (T)n.getData();

     }
 
     /**
      * Retrieves multiple data values given an array of row names.
      *
      * @param rn an array of row names to search for
      */
     public T[] loc(String[] rn) throws NullPointerException, IllegalArgumentException {
         // TODO: Implement loc method for multiple row names
          if (rn==null){
               throw new NullPointerException("loc(String[] rn): rn[] can't be null");
          }
          if(rn.length==0){
               throw new IllegalArgumentException("loc(String[] rn): rn[] can't be an empty array");
          }
          T[] cur_dat = (T[]) new Object[rn.length];
//          for(int i =0; i<rn.length; i++){
//               cur_dat[i] = loc(rn[i]);
//
//          }
//          return cur_dat;
          for(int i = 0; i<rn.length; i++){
               if(seriesData.searchNode(rn[i])==null){
                    cur_dat[i] = null;
               }
               else{
                    cur_dat[i] = (T)seriesData.searchNode(rn[i]).getData();
               }
          }
          return cur_dat;

     }
 
     /**
      * Retrieves a data value based on its integer index.
      *
      * @param ind the index f the data to retrieve
      */
     public T iloc(int ind) {
         // TODO: Implement iloc method
         try {
              String[] dat = seriesData.getIndexArray();
              String rn = dat[ind];
              return loc(rn);
         }
         catch(IndexOutOfBoundsException e){
              System.out.println("the index "+ ind + " is not valid.. returning null");
              return null;
          }
     }
 
     /**
      * Removes a pair of rowname-data from the Series, given a row name.
      *
      * @param rn the row name of the pair to be removed
      */
     public boolean drop(String rn) throws NullPointerException, IllegalArgumentException{
          boolean found = false;
          if(rn==null) {
               throw new NullPointerException("drop(String rn): rn can't be null");
          }
          if(rn == "") {
               throw new IllegalArgumentException("drop(String rn): rn can't be an empty String");
          }
//          for (int i = 0; i<data.length;i++){
//               if(rn == rowNames[i]) {
//                    found = true;
//                    break;
//               }
//          }
//          if (!found){
//               return found;
//          }
//          String[] n_rows= new String[data.length-1];
//          T[] n_data = (T[]) new Object[data.length-1];
//          int count =0;
//          for(int i = 0; i<data.length; i++){
//               if(rowNames[i]!=rn && count==0){
//                    n_rows[i]= rowNames[i];
//                    n_data[i] = data[i];
//               }
//               else if(rowNames[i]!=rn && count>0){
//                    n_rows[i-1]= rowNames[i];
//                    n_data[i-1]= data[i];
//               }
//               else if(rowNames[i]==rn){
//                    count +=1;
//               }
//          }
//          rowNames = n_rows;
//          data = n_data;
          try{
               seriesData.removeNode(rn);
          }
          catch(IllegalArgumentException e){
               return false;
          }
          return true;
     }


 
     /**
      * Replace any data value that is null with value.
      *
      * @param value the new value to replace null values
      */
     public void fillNull(T value) throws IllegalArgumentException {
          // TODO: Implement fillNull method
          if (value == null) {
               throw new IllegalArgumentException("fillNull(T value): value can't be null");
          }
//          for (int i = 0; i < data.length; i++) {
//               if (data[i]==null){
//                    data[i] = value;
//               }

          String[] dat = seriesData.getDataArray();
          String[] ind = seriesData.getIndexArray();
          for (int i = 0; i<dat.length; i++){
               if(dat[i]=="null"){
                    seriesData.updateNode(ind[i],value);
               }
          }

     }
 
     /**
      * Replace any data value that is null with the mean of the Series.
      *
      */
//     public void fillNullWithMean() throws IllegalArgumentException{
//         // TODO: Implement fillNullWithMean method
//         // Handle ArithmeticException that could be raised from mean()
//          Integer m=null;
//          try{
//               m = Tool.mean(this);
//          }
//          catch(ArithmeticException e){
//               throw new IllegalArgumentException();
//          }
//         finally{
//               fillNull(m);
//          }

     }
