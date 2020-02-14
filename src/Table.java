import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.io.IOException;

public class
Table extends JTable{

    private JTable table;
    private String[][]data;
    private String[]column;

    Table() throws IOException{
       table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
       resizeColumnWidth(table);
       filltable();
    }

    public void filltable() throws IOException {
        Read read = new Read();
        column = new String[read.getHeadings().size()];//Heading for the table
        data = new String[0][10];//Empty Array for now
        for (int j = 0; j < read.getHeadings().size(); j++) {
        column[j] = read.getHeadings().get(j);
        }
        table = new JTable(data,column);
    }
    private void resizeColumnWidth(JTable table){
        final TableColumnModel columnModel = table.getColumnModel();
        for(int i=0;i<table.getColumnCount();i++){
            int width =15;
            for(int j=0;j<table.getRowCount();j++){
                TableCellRenderer renderer = table.getCellRenderer(j,i);
                Component comp = table.prepareRenderer(renderer,j,i);
                width = Math.max(comp.getPreferredSize().width+1,width);
            }
            if(width>300)
                width=300;
            columnModel.getColumn(i).setPreferredWidth(width);
        }
    }
    public JTable getTable(){return table;}
}

