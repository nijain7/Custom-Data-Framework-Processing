public class Tool {

    /**
     * Calculates the mean of a Series object.
     *
     * @param d the Series object to calculate the mean from
     */
    public static Integer mean(Series d) throws ArithmeticException,IllegalArgumentException {
        // TODO: Implement mean calculation
        if (d==null) {
            throw new NullPointerException("mean (Series d): d can't be null");
        }
        if(d.getData().length==0){
            throw new IllegalArgumentException("mean (Series d): d can't be an empty Series");
        }
        Integer sum = 0;
        int cur_len=0;
        Integer num_len = 0;
        Integer [] dat = new Integer[d.getData().length];
        for (int i = 0; i<d.getData().length;i++){
          if (d.getData()[i]=="null"){
              cur_len +=1;
              dat[i] = null;
              continue;
          }
          dat[i] = Integer.valueOf(d.getData()[i]);
          num_len+=1;
        }
        if(cur_len==d.getData().length){
            throw new ArithmeticException();
        }
        for(int i =0; i<d.getData().length; i++){
            try {
                sum += dat[i];
            }
            catch(NullPointerException e){
                continue;
            }
        }


       return sum/num_len;
    }

    /**
     * Finds the maximum value in a Series object.
     *
     * @param d the Series object to find the maximum value from
     */
    public static Integer max(Series d) throws IllegalArgumentException, NullPointerException, ArithmeticException {
        // TODO: Implement max value finder
        if (d == null) {
            throw new NullPointerException("max (Series d): d can't be null");
        }
        if (d.getData().length == 0) {
            throw new IllegalArgumentException("max (Series d): d can't be an empty Series");
        }

        Integer sum = 0;
        int cur_len=0;
        Integer [] dat = new Integer[d.getData().length];
        for (int i = 0; i<d.getData().length;i++){
            if (d.getData()[i]=="null"){
                cur_len +=1;
                dat[i] = null;
                continue;
            }
            dat[i] = Integer.valueOf(d.getData()[i]);
        }
        if(cur_len==d.getData().length){
            throw new ArithmeticException();
        }
        Integer cur_max = dat[0];
        for (int i = 0; i < dat.length; i++) {
            try {
                if (dat[i] > cur_max) {
                    cur_max = dat[i];
                }
            } catch (NullPointerException e) {
                continue;
            }
        }
    return cur_max;

}
}
