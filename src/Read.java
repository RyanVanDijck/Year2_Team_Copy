import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.io.IOException;

//Reading from the CSV file
public class Read {
    String file = "data.csv"; //Name of file
    String cline; //Line Iterator
    BufferedReader br = new BufferedReader(new FileReader(file)); // File reader
    ArrayList<student> data = new ArrayList<student>(); //list of data

    //Method to transfer information from the file to instances of the
    //student class
    public Read() throws IOException {
        student temp; //Temp student object to store into list
        boolean start = true;//to check that we are not on the first line where the headings are
        while ((cline = br.readLine()) != null){
            temp = new student();//reinitializing object of student class
            String[] i=  cline.split(",");//Spliting by commas/values in the csv file
            //for (String i: entry){
                if (start){
                    start = false;
                }
                else{
                    temp.student_no = i[0];
                    temp.ExNo = i[1];
                    temp.stage = i[2];

                    //Begin Ce101


                    if (i[3] != null && !i[3].isEmpty()){
                        temp.ce101 = Integer.parseInt(i[3]);
                    }
                    else if (i[4] != null && !i[4].isEmpty()){
                        temp.ce101 = Integer.parseInt(i[4]);
                    }
                    else{
                        System.out.println("error");
                    }

                    //End Ce101



                    //Begin Ce141

                    if (i[5] != null && !i[5].isEmpty()){
                        temp.ce141 = Integer.parseInt(i[5]);
                    }
                    else if (i[6] != null && !i[6].isEmpty()){
                        temp.ce141 = Integer.parseInt(i[6]);
                    }
                    else{
                            temp.ce141 = null;
                    }

                    //End ce141

                    //Begin ce142

                    if (i[7] != null && !i[7].isEmpty()){
                        temp.ce142 = Integer.parseInt(i[7]);
                    }
                    else if (i[8] != null && !i[8].isEmpty()){
                        temp.ce142 = Integer.parseInt(i[8]);
                    }
                    else{
                        temp.ce142  = null;
                    }

                    //End Ce142

                    //Ce151

                    if(i[9] == null || i[9].isEmpty()) {
                        System.out.println("Error, Ce151 " + i[0]);
                    }
                    else{
                        temp.ce151 = Integer.parseInt(i[9]);
                    }

                    //Ce152

                    if (i[10] == null || i[10].isEmpty()){
                        temp.ce152 = null;
                    }
                    else{
                        temp.ce152 = Integer.parseInt(i[10]);
                    }

                    //Ce153

                    if (i[11] == null || i[11].isEmpty()){
                        temp.ce153 = null;
                    }
                    else{
                        temp.ce153 = Integer.parseInt(i[11]);
                    }

                    //Ce154

                    if (i[12] == null || i[12].isEmpty()){
                        temp.ce154 = null;
                    }
                    else{
                        temp.ce154 = Integer.parseInt(i[12]);
                    }

                    //Ce155
                    if(i[13] == null || i[13].isEmpty()) {
                        System.out.println("Error, Ce155 " + i[0]);
                    }
                    else{
                        temp.ce155 = Integer.parseInt(i[13]);
                    }

                    //Ce161
                    if(i[14] == null || i[14].isEmpty()) {
                        System.out.println("Error, Ce161 " + i[0]);
                    }
                    else{
                        temp.ce151 = Integer.parseInt(i[14]);
                    }

                    //Ce162
                    if (i[15] == null || i[15].isEmpty()){
                        temp.ce162 = null;
                    }
                    else{
                        temp.ce162 = Integer.parseInt(i[15]);
                    }

                    //Ce163
                    if (i[16] == null || i[16].isEmpty()){
                        temp.ce163 = null;
                    }
                    else{
                        temp.ce162 = Integer.parseInt(i[16]);
                    }

                    //Ce164
                    if (i[17] == null || i[17].isEmpty()){
                        temp.ce164 = null;
                    }
                    else{
                        temp.ce164 = Integer.parseInt(i[17]);
                    }

                    //ave_mark
                    temp.ave_mark = Integer.parseInt(i[19]);
                    data.add(temp);


                }
            //}

        }
        }

}
