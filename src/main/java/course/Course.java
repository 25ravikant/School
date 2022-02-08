package course;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;

public class Course extends JDialog {
    private Container c;
    private JLabel jLabel1,jLabel2;
    private JButton saveBtn,closeBtn,updateBtn,deleteBtn,resetBtn;
    private JComboBox jComboBox;
    private JTextField jTextField;
    private JPanel mainPanel;
    private JTable jTable;
    static DefaultTableModel dtm;
    private JScrollPane jp;
    public Course(){
        setTitle("Course");
        setBounds(300, 90, 900, 600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        c = getContentPane();

        mainPanel = new JPanel();
        mainPanel.setBackground(Color.CYAN);
        mainPanel.setLayout(null);
        c.add(mainPanel);

        jLabel1 = new JLabel("Course");
        jLabel1.setSize(90,20);
        jLabel1.setLocation(100,100);
        mainPanel.add(jLabel1);

        jComboBox = new JComboBox();
        jComboBox.setSize(200,20);
        jComboBox.setLocation(200,100);
        jComboBox.addItem("--Select--");
        jComboBox.addItem("Java");
        jComboBox.addItem("Php");
        jComboBox.addItem("Android");
        jComboBox.addItem("Spring");
        mainPanel.add(jComboBox);

        String colNames[] = {"Id","Course","Price"};
        dtm = new DefaultTableModel();
        dtm.setColumnIdentifiers(colNames);
        jTable = new JTable();
        jTable.setSize(300,200);
        jTable.setLocation(900,100);
        jTable.setModel(dtm);
        jp = new JScrollPane(jTable);
        jp.setBounds(450, 100, 350, 200);
        mainPanel.add(jp);

        jLabel2 = new JLabel("Price");
        jLabel2.setSize(90,20);
        jLabel2.setLocation(100,130);
        mainPanel.add(jLabel2);

        jTextField = new JTextField();
        jTextField.setSize(200,20);
        jTextField.setLocation(200,130);
        mainPanel.add(jTextField);

        saveBtn = new JButton("Save");
        saveBtn.setSize(100,20);
        saveBtn.setLocation(250,160);
        mainPanel.add(saveBtn);

        closeBtn = new JButton("Close");
        closeBtn.setSize(100,20);
        closeBtn.setLocation(250,190);
        mainPanel.add(closeBtn);

        resetBtn = new JButton("Reset");
        resetBtn.setSize(100,20);
        resetBtn.setLocation(250,220);
        mainPanel.add(resetBtn);

        updateBtn = new JButton("Update");
        updateBtn.setSize(100,20);
        updateBtn.setLocation(250,250);
        updateBtn.setEnabled(false);
        mainPanel.add(updateBtn);

        deleteBtn = new JButton("Delete");
        deleteBtn.setSize(100,20);
        deleteBtn.setLocation(250,280);
        mainPanel.add(deleteBtn);

        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cName = jComboBox.getSelectedItem().toString();
                int cPrice = (Integer.parseInt(jTextField.getText()));
             CourseModel cm = new CourseModel(cName, cPrice);
//             cm.setCourseName(jComboBox.getSelectedItem().toString());
//             cm.setCoursePrice(Integer.parseInt(jTextField.getText()));
             int status = CourseDao.saveCourse(cm);
             if(status>0){
                 JOptionPane.showMessageDialog(null,"Record has been Saved");
                 searchCourse();
                 resetMethod();
             }
             else{
                 JOptionPane.showMessageDialog(null,"Record has been not Saved");
             }
            }
        });
        closeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        resetBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetMethod();
            }
        });
        updateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int a1 = jTable.getSelectedRow();
                String cid = jTable.getValueAt(a1,0).toString();
                CourseModel cm = new CourseModel();
                cm.setId(Integer.parseInt(cid));
                cm.setCourseName(jComboBox.getSelectedItem().toString());
                cm.setCoursePrice(Integer.parseInt(jTextField.getText()));
                int status = CourseDao.updateCourse(cm);
                if(status>0){
                    JOptionPane.showMessageDialog(null,"Record has been Updated");
                    searchCourse();
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
                String cid = jTable.getValueAt(a1,0).toString();
                CourseDao cd = new CourseDao();
               int status = cd.deleteCourse(Integer.parseInt(cid));
                if(status>0){
                    JOptionPane.showMessageDialog(null,"Record has been Deleted");
                    searchCourse();
                    resetMethod();
                }
                else{
                    JOptionPane.showMessageDialog(null,"Record has been not Deleted");
                }
            }
        });

        jTable.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int a1 = jTable.getSelectedRow();
                jComboBox.setSelectedItem(jTable.getValueAt(a1,1).toString());
                jTextField.setText(jTable.getValueAt(a1,2).toString());
                saveBtn.setEnabled(false);
                updateBtn.setEnabled(true);
                deleteBtn.setEnabled(true);
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
        setVisible(true);
    }
    public static void searchCourse(){
        dtm.setRowCount(0);
        LinkedList<CourseModel> list = CourseDao.getAll();
        for(CourseModel cm:list){
            String sval[] = {String.valueOf(cm.getId()),cm.getCourseName(), String.valueOf(cm.getCoursePrice())};
            dtm.addRow(sval);
        }
    }

    public void resetMethod(){
        jTextField.setText("");
        jComboBox.setSelectedIndex(0);
        updateBtn.setEnabled(false);
        deleteBtn.setEnabled(false);
        saveBtn.setEnabled(true);
    }
    public static void main(String[] args) {
        Course course = new Course();
        searchCourse();
    }


}
