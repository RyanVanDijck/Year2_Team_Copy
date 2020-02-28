import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//The class for all students
public class Student {
    private String regNo; //Unique Student Registration Number
    private String examNo; //Unique Exam Number
    private String stage; //The course of study
    private Map<String,Integer> marks=new HashMap<String, Integer>(); //Student marks and modules stored in map <Module Code, Mark>
    private Integer avgMark; //Average Mark

    Student(String studentNo, String examNo, String stage, int avgMark){
        this.regNo=studentNo;
        this.examNo=examNo;
        this.stage=stage;
        this.avgMark=avgMark;
        //createModules();
    }

    //fills map with every module with mark as null
    public void createModules(ArrayList<String> list){
        for(String readModule: list){
            marks.put(readModule, null);
        }
    }


    //sets the mark given the module and mark
    public void setMark(String module,int mark){ marks.replace(module,mark); }

    //returns the mark of the given module
    public Integer getMark(String module){ return marks.get(module); }

    //returns the registration number
    public String getRegNo(){
        return regNo;
    }

    //returns the exam number
    public String getExamNo(){ return examNo; }

    public String getStage(){
      return this.stage;
    }

    //returns the average mark
    public int getAvgMark(){
        return avgMark;
    }



}
