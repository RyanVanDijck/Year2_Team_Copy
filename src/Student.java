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
        createModules();
    }

    //fills map with every module with mark as null
    private void createModules(){
        marks.put("CE101-4-FY",null);
        marks.put("CE101-4-SP",null);
        marks.put("CE141-4-AU",null);
        marks.put("CE141-4-FY",null);
        marks.put("CE142-4-AU",null);
        marks.put("CE142-4-FY",null);
        marks.put("CE151-4-AU",null);
        marks.put("CE152-4-SP",null);
        marks.put("CE153-4-AU",null);
        marks.put("CE154-4-SP",null);
        marks.put("CE155-4-SP",null);
        marks.put("CE161-4-AU",null);
        marks.put("CE162-4-SP",null);
        marks.put("CE163-4-AU",null);
        marks.put("CE164-4-SP",null);
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

    public Map<String,Integer> getMap(){
      return this.marks;
    }

    //returns the average mark
    public int getAvgMark(){
        return avgMark;
    }


}
