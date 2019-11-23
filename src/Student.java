//The class for all students
public class Student {
    String student_no; //Unique Student Number
    String ExNo; //Unique Exam Number
    String stage; //The course of study
    Integer ce101;// FY + SP
    Integer ce141;// AU + FY
    Integer ce142;// AU + FY
    Integer ce151;// AU
    Integer ce152;// SP
    Integer ce153;// AU
    Integer ce154;// SP
    Integer ce155;// SP
    Integer ce161;// SP
    Integer ce162;// SP
    Integer ce163;// AU
    Integer ce164;// SP
    Integer ave_mark;

    void temp_print(){
        System.out.println(ExNo +" "+ ce101 + " "+ ave_mark);
    }
}

