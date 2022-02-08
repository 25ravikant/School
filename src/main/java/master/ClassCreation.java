package master;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
public class ClassCreation extends JDialog{
    private Container c;
    private JLabel clName;
    private JTextField className;
    private JButton saveBtn,searchBtn,closeBtn,updateBtn,deleteBtn;
    private JPanel mainPanel;
    private JTable jTable;
    static DefaultTableModel dtm;
    private JScrollPane sp;

    public ClassCreation()
    {
        setTitle("Create Class");

        setBounds(300, 90, 900, 600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        c=getContentPane();

        mainPanel = new JPanel();
        mainPanel.setBackground(Color.CYAN);
        mainPanel.setLayout(null);
        c.add(mainPanel);

        clName = new JLabel("Class Name");
        clName.setSize(100, 20);
        clName.setLocation(100, 100);
        mainPanel.add(clName);

        className = new JTextField();
        className.setSize(200, 20);
        className.setLocation(200, 100);
        mainPanel.add(className);

        saveBtn = new JButton("Save");
        saveBtn.setSize(100, 20);
        saveBtn.setLocation(250, 140);
        mainPanel.add(saveBtn);

        searchBtn = new JButton("Search");
        searchBtn.setSize(100,20);
        searchBtn.setLocation(500,80);
        mainPanel.add(searchBtn);

        closeBtn = new JButton("Close");
        closeBtn.setSize(100,20);
        closeBtn.setLocation(250,180);
        mainPanel.add(closeBtn);

        updateBtn = new JButton("Update");
        updateBtn.setSize(100,20);
        updateBtn.setLocation(250,220);
        updateBtn.setEnabled(false);
        mainPanel.add(updateBtn);

        deleteBtn = new JButton("Delete");
        deleteBtn.setSize(100,20);
        deleteBtn.setLocation(250,260);
        deleteBtn.setEnabled(false);
        mainPanel.add(deleteBtn);
//        String[][] data = {
//                { "Kundan Kumar Jha", "4031", "CSE" },
//                { "Anand Jha", "6014", "IT" }
//        };
//        String[] columnNames = { "Name", "Roll Number", "Department" };
//        // Initializing the JTable
//        jTable = new JTable(data, columnNames);
//        jTable.setBounds(300, 100, 50, 200);
//        // adding it to JScrollPane
//         sp = new JScrollPane(jTable);
//        sp.setBounds(450, 100, 350, 200);
//        mainPanel.add(sp);

       String scol[] = {"Id","Class Name"};
       dtm = new DefaultTableModel();
       dtm.setColumnIdentifiers(scol);
       jTable = new JTable();
        jTable.setSize(300,200);
        jTable.setLocation(900,100);
       jTable.setModel(dtm);
        sp = new JScrollPane(jTable);
        sp.setBounds(450, 100, 350, 200);
        mainPanel.add(sp);

        saveBtn.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent e) {
                  String sc = className.getText();
                  ClassCreationFields cFields = new ClassCreationFields(sc);
                  int status = ClassCreationDao.addClass(cFields);
                 if(status>0)
                 {
                     dtm.setRowCount(0);
                     JOptionPane.showMessageDialog(null,"Record has been Saved");
                     searchClass();
                     resetMethod();
                 }
            }
        });
        searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dtm.setRowCount(0);
                searchClass();
            }
        });

        jTable.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int a1 = jTable.getSelectedRow();
                String cl_Name = jTable.getValueAt(a1,1).toString();
                className.setText(cl_Name);
                updateBtn.setEnabled(true);
                deleteBtn.setEnabled(true);
                saveBtn.setEnabled(false);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        closeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        updateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int a1 = jTable.getSelectedRow();
                String c_id = jTable.getValueAt(a1,0).toString();
                String c_name = className.getText();
                ClassCreationFields clsvalue = new ClassCreationFields(Integer.parseInt(c_id),c_name);
                int status = ClassCreationDao.updateClass(clsvalue);
                if(status>0){
                   JOptionPane.showMessageDialog(null,"Record has been Updated");
                    searchClass();
                    resetMethod();
                }
                else{
                   JOptionPane.showMessageDialog(null,"Record has been not Updated");
               }
            }
        });
        deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int a1 = jTable.getSelectedRow();
                int c_id = Integer.parseInt(jTable.getValueAt(a1,0).toString());
                ClassCreationDao cDao = new ClassCreationDao();
               int status = cDao.deleteClass(c_id);
               if(status>0)
               {
                   JOptionPane.showMessageDialog(null,"Record has been Deleted");
                   resetMethod();
                   searchClass();

               }
               else {
                   JOptionPane.showMessageDialog(null,"Record has been not Deleted");
               }
            }
        });
        setVisible(true);
    }

    public static void main(String[] args) {
        ClassCreation cls = new ClassCreation();
        searchClass();
    }
    public static void searchClass(){
        dtm.setRowCount(0);
        List<ClassCreationFields> list=ClassCreationDao.getAllClass();
        for(int i=0;i<list.size();i++){
            String data[]= {String.valueOf(list.get(i).getId()),list.get(i).getClass_Name()};
            dtm.addRow(data);
        }
    }
public void resetMethod(){
        className.setText("");
        updateBtn.setEnabled(false);
        deleteBtn.setEnabled(false);
        saveBtn.setEnabled(true);
}
}
