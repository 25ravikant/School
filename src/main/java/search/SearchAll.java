package search;

import fyear.FClass;
import fyear.yearDao;
import master.ClassCreationDao;
import master.ClassCreationFields;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class SearchAll extends JDialog implements SearchMethods{
    private Container c;
    static DefaultTableModel dtm,dtm1;
    private JButton searchBtn,searchBtn2;
    private JTable jTable,jTable2;
    private JPanel mainPanel;
    private JScrollPane jp;
    public SearchAll(){
        setTitle("Search");
        setBounds(300, 90, 900, 600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        c = getContentPane();

        mainPanel = new JPanel();
        mainPanel.setBackground(Color.CYAN);
        mainPanel.setLayout(null);
        c.add(mainPanel);

        searchBtn = new JButton("Search");
        searchBtn.setSize(100,20);
        searchBtn.setLocation(100,80);
        mainPanel.add(searchBtn);

        String colName[] = {"Id","Year Name"};
        dtm = new DefaultTableModel();
        dtm.setColumnIdentifiers(colName);
        jTable = new JTable();
        jTable.setSize(300,200);
        jTable.setLocation(100,100);
        jTable.setModel(dtm);
        jp = new JScrollPane(jTable);
        jp.setBounds(100, 100, 350, 200);
        mainPanel.add(jp);

        searchBtn2 = new JButton("Search");
        searchBtn2.setSize(100,20);
        searchBtn2.setLocation(500,80);
        mainPanel.add(searchBtn2);

        String colNames[] = {"Id","Class Name"};
        dtm1 = new DefaultTableModel();
        dtm1.setColumnIdentifiers(colNames);
        jTable2 = new JTable();
        jTable2.setSize(300,200);
        jTable2.setLocation(500,100);
        jTable2.setModel(dtm1);
        jp = new JScrollPane(jTable2);
        jp.setBounds(500, 100, 350, 200);
        mainPanel.add(jp);

        searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchYear();
            }
        });

        searchBtn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchClass();
            }
        });
       setVisible(true);
    }


    @Override
    public void searchClass() {
        dtm1.setRowCount(0);
        List<ClassCreationFields> list= ClassCreationDao.getAllClass();
        for(ClassCreationFields cfields : list){
            String data[]= {String.valueOf(cfields.getId()),cfields.getClass_Name()};
            dtm1.addRow(data);
        }
    }

    @Override
    public void searchYear() {
        dtm.setRowCount(0);
        ArrayList<FClass> yDao = yearDao.getAll();
        for(FClass fy:yDao){
            String sval[] ={String.valueOf(fy.getId()), fy.getF_year()};
            dtm.addRow(sval);
        }
    }

    public static void main(String[] args) {
        SearchAll searchAll = new SearchAll();
        searchAll.searchYear();
        searchAll.searchClass();
    }
}
