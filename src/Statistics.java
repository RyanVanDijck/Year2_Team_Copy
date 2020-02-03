import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Statistics {

    Read read = new Read();
    double mean,total= 0;
    double Max = Integer.MIN_VALUE;
    double Min = Integer.MAX_VALUE;
    ArrayList<Student> marks = read.getData();

    public Statistics() throws IOException {}


    //Return the mean mark for the module
    public double getMean(String module){
        int count = 0;
            for (Student i : marks) {
                if(i.getMark(module)!=null){
                    total += i.getMark(module);
                    count++;
                }
            }
            mean = total/count;
        return mean;
    }

    //Return Highest mark for that module
    public double getMax(String module){
        for(Student i:marks){
            if(i.getMark(module)!=null){
                double mark = i.getMark(module);
                if(mark>Max){
                    Max = mark;
                }
            }
        }
        return Max;
    }
    //Return Lowest mark for that module;
    public double getMin(String module){
        for(Student i:marks){
            if(i.getMark(module)!=null){
                double mark = i.getMark(module);
                if(mark<Min){
                    Min= mark;
                }
            }
        }
        return Min;
    }

}
