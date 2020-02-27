import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;


public class Statistics {

    Read read;

    private ArrayList<Student> marks;
    private Student student;
    String[][] array;
    String[] headings;
    int mark;
    Map<String,Integer> module= new HashMap<>();

    public Statistics(Read read) throws IOException{
        this.read = read;
        marks = read.getData();
        module.put(null,null);
        array = new String[read.getData().size()][19];
        headings = new String[19];
        for (int i = 0;i < read.getHeadings().size()-1;i++){
            headings[i] = read.getHeadings().get(i);
        }
        headings[18] = "Average Mark";
        for (int i = 0; i < read.getData().size() ; i++){
            array[i][0] = read.getData().get(i).getRegNo();
            array[i][1] = read.getData().get(i).getExamNo();
            array[i][2] = read.getData().get(i).getStage();
            for (int j=3; j<18; j++){
                if (!(read.getData().get(i).getMark(headings[j]) == null)){
                    array[i][j] = Integer.toString(read.getData().get(i).getMark(headings[j]));
                }
                else{
                    array[i][j] = "N/A";
                }
            }
        }
    }

    public Statistics() {}


    //Return the mean mark for the module
    public double getMean(String module){
        double mean = 0;
        double total= 0;
        int count = 0;
        for (Student i : marks) {
            if(i.getMark(module)!=null){
                total += i.getMark(module);
                count++;
            }
        }
        mean = total/count;
        if (Double.isInfinite(mean) || Double.isNaN(mean)){
            return 0.0;
        }
        return mean;
    }

    //Return Highest mark for that module
    public double getMax(String module){
        double Max = Integer.MIN_VALUE;
        for(Student i:marks){
            if(i.getMark(module)!=null){
                double mark = i.getMark(module);
                if(mark>Max){
                    Max = mark;
                }
            }
        }
        if (Max ==Integer.MIN_VALUE) {
            return 0.0;
        }
        return Max;
    }
    //Return Lowest mark for that module;
    public double getMin(String module){
        double Min = Integer.MAX_VALUE;
        for(Student i:marks){
            if(i.getMark(module)!=null){
                double mark = i.getMark(module);
                if(mark<Min){
                    Min= mark;
                }
            }
        }
        if (Min == Integer.MAX_VALUE){
            return 0.0;
        }
        return Min;
    }
    //Return the Range value mark for that module;
    public double getRange(String module){
        return getMax(module)-getMin(module);
    }



    //Return Standard Deviation
    public double getSD(String module){
        double standardDeviation = 0.0;
        List<Integer> markslist = new ArrayList<Integer>();
        for (Student i : marks) {
            if (i.getMark(module) != null) {
                markslist.add(i.getMark(module));
            }
        }
        double mean = getMean(module);
        double length = markslist.size();
        for (int num : markslist) {
            double s = Math.pow(num - mean, 2);
            double roundup = Math.round(s * 100) / 100D;
            standardDeviation += roundup;
        }
        if (Double.isNaN(standardDeviation)){
            return 0.0;
        }
        return Math.sqrt(standardDeviation / (length - 1));
    }

    //Method to return the best student in that module
    //Need to catch NullPointException in case that module has no value
    public Student getBestStudent(String module) {
        double bestscore = getMax(module);
        for (Student i : marks) {
            if (i.getMark(module) != null) {
                if (i.getMark(module) == bestscore) {
                    student = i;
                }
            }
        }
       return student;
    }

    //Method to return the worst student in that module
    // Need to catch NullPointException in case that module has no value
    public Student getWorstStudent(String module){
        double lowest = getMin(module);
        for (Student i : marks) {
            if (i.getMark(module) != null) {
                if (i.getMark(module) == lowest) {
                    student = i;
                }
            }
        }
        return student;
    }

    public Map<String,Integer> getStudentBest(String regNo){
        int best = Integer.MIN_VALUE;
        for(int i=0;i<read.getData().size();i++){
            if(array[i][0].equals(regNo)){
                for(int j=3; j<18 ;j++){
                    if(!(read.getData().get(i).getMark(headings[j])== null)){
                        mark = read.getData().get(i).getMark(headings[j]);
                        if(mark > best){
                            best = mark;
                            module.replace(headings[j],best);
                        }
                    }
                }
            }
        }
        System.out.println(module);
        return module;
    }

//    public Map<String, Integer> getStudentWorst(String regNo){
//        int minValue = Integer.MAX_VALUE;
//        Map<String,Integer> worstmodule = new HashMap<>();
//        if(student.getRegNo()!=null && student.getRegNo().equals(regNo))
//            for(String j:modules){
//
//            }
//    return worstmodule;
//    }
}
