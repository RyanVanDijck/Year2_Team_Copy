import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;


public class Statistics {

    Read read;

    private ArrayList<Student> students;
    private String[][] array;
    private String[] headings;
    Map<String, Integer> module = new HashMap<>();

    public Statistics(Read read) throws IOException {
        this.read = read;
        students = read.getData();
        module.put(null, null);
        array = new String[read.getData().size()][19];
        headings = new String[19];
        for (int i = 0; i < read.getHeadings().size() - 1; i++) {
            headings[i] = read.getHeadings().get(i);
        }
        headings[18] = "Average Mark";
        for (int i = 0; i < read.getData().size(); i++) {
            array[i][0] = read.getData().get(i).getRegNo();
            array[i][1] = read.getData().get(i).getExamNo();
            array[i][2] = read.getData().get(i).getStage();
            for (int j = 3; j < 18; j++) {
                if (!(read.getData().get(i).getMark(headings[j]) == null)) {
                    array[i][j] = Integer.toString(read.getData().get(i).getMark(headings[j]));
                } else {
                    array[i][j] = "N/A";
                }
            }
        }
    }

    //Return the mean mark for the module
    public double getMean(String module) {
        double mean = 0;
        double total = 0;
        int count = 0;
        for (Student i : students) {
            if (i.getMark(module) != null) {
                total += i.getMark(module);
                count++;
            }
        }
        mean = total / count;
        if (Double.isInfinite(mean) || Double.isNaN(mean)) {
            return 0.0;
        }
        return mean;
    }

    //Return Highest mark for that module
    public double getMax(String module) {
        double Max = Integer.MIN_VALUE;
        for (Student i : students) {
            if (i.getMark(module) != null) {
                double mark = i.getMark(module);
                if (mark > Max) {
                    Max = mark;
                }
            }
        }
        if (Max == Integer.MIN_VALUE) {
            return 0.0;
        }
        return Max;
    }

    //Return Lowest mark for that module;
    public double getMin(String module) {
        double Min = Integer.MAX_VALUE;
        for (Student i : students) {
            if (i.getMark(module) != null) {
                double mark = i.getMark(module);
                if (mark < Min) {
                    Min = mark;
                }
            }
        }
        if (Min == Integer.MAX_VALUE) {
            return 0.0;
        }
        return Min;
    }

    //Return the Range value mark for that module;
    public double getRange(String module) {
        return getMax(module) - getMin(module);
    }


    //Return Standard Deviation
    public double getSD(String module) {
        double standardDeviation = 0.0;
        List<Integer> markslist = new ArrayList<Integer>();
        for (Student i : students) {
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
        if (Double.isNaN(standardDeviation)) {
            return 0.0;
        }
        return Math.sqrt(standardDeviation / (length - 1));
    }

    public ArrayList<Student> rankStudents(String module) {
        ArrayList<Student> ranked = new ArrayList<>();
        for (Student s : students) if (s.getMark(module) != null) ranked.add(s);
        ranked.sort(Comparator.comparing(s -> s.getMark(module)));
        Collections.reverse(ranked);
        return ranked;
    }

    //Method to get the best student for the module
    public Student getBestStudent(String module) {
        ArrayList<Student> ranked = rankStudents(module);
        return ranked.get(0);
    }

    //Method to get the worst student for the module
    public Student getWorstStudent(String module) {
        ArrayList<Student> ranked = rankStudents(module);
        return ranked.get(ranked.size() - 1);
    }

    //Method to get n best students for the module
    public Student[] getBestStudents(String module, int n) {
        ArrayList<Student> ranked = rankStudents(module);
        Student[] best = new Student[n];
        for (int i = 0; i < n; i++) best[i] = ranked.get(i);
        return best;
    }

    //Method to get n worst students for the module
    public Student[] getWorstStudents(String module, int n) {
        ArrayList<Student> ranked = rankStudents(module);
        Student[] worst = new Student[n];
        for (int i = 0; i < n; i++) worst[i] = ranked.get(i + (ranked.size() - n));
        return worst;
    }

    public Map<String, Integer> getMarksByRegNo(String regNo) {
        Map<String, Integer> StudentModules = new HashMap<>();
        for (Student i : students) {
            if (i.getRegNo().equals(regNo)) {
                for (int z = 3; z < 18; z++) {
                    StudentModules.put(headings[z], i.getMark(headings[z]));
                }
            }
        }
        return StudentModules;
    }

    public Map<String, Integer> getBestMarkByRegNo(String regNo) {
        Map<String, Integer> best = new HashMap<>();
        Map<String, Integer> modules = getMarksByRegNo(regNo);
        String maxKey = Collections.max(modules.keySet());
        Integer maxMark = modules.get(maxKey);
        best.put(maxKey, maxMark);
        return best;
    }

    public Map<String, Integer> getWorstMarkByRegNo(String regNo) {
        Map<String, Integer> worst = new HashMap<>();
        Map<String, Integer> modules = getMarksByRegNo(regNo);
        String Key = Collections.min(modules.keySet());
        Integer Mark = modules.get(Key);
        worst.put(Key, Mark);
        return worst;
    }

//    public Map<String,Integer> sortByValue(Map<String,Integer> modules){
//        return modules.entrySet().stream()
//                .sorted((Map.Entry.<String,Integer>comparingByValue().reversed()))
//                .collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue,(e1,e2)->e1,LinkedHashMap::new));
//    }

}

