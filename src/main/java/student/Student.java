package student;


import course.CourseDao;
import course.CourseModel;
import master.ClassCreationDao;
import master.ClassCreationFields;
import search.SearchMethods;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import java.util.List;

public class Student extends JDialog implements SearchMethods
{
    private Container c;
    private JLabel labelName,labName1,labName2,labName3,labName4;
    private JTextField stName,sContact,sEmail,sClass,sCourse,cPrice;
    private JComboBox jClass,jCourse;
    private JButton saveBtn,closeBtn,updateBtn,deleteBtn,searchBtn;
    private JPanel mainPanel;
    private JTable jTable;
    private JScrollPane jp;
    static DefaultTableModel dtm;
    public Student(){
       setTitle("Student Registration Form");
       setBounds(300,90,900,600);
       setDefaultCloseOperation(DISPOSE_ON_CLOSE);
       c = getContentPane();

       mainPanel = new JPanel();
       mainPanel.setBackground(Color.CYAN);
       mainPanel.setLayout(null);
       c.add(mainPanel);

       String colNames[] = {"Id","Name","Contact","Email","Class","Course"};
       dtm = new DefaultTableModel();
       dtm.setColumnIdentifiers(colNames);
       jTable = new JTable();
       jTable.setSize(300,200);
       jTable.setLocation(900,100);
       jTable.setModel(dtm);
       jp = new JScrollPane(jTable);
       jp.setBounds(525, 100, 350, 200);
       mainPanel.add(jp);

       labelName = new JLabel("Student Name");
       labelName.setSize(90,20);
       labelName.setLocation(100,100);
       mainPanel.add(labelName);

       stName = new JTextField();
       stName.setSize(250,20);
       stName.setLocation(220,100);
       mainPanel.add(stName);

       labName1 = new JLabel("Contact No");
       labName1.setSize(90,20);
       labName1.setLocation(100,130);
       mainPanel.add(labName1);

       sContact = new JTextField();
       sContact.setSize(250,20);
       sContact.setLocation(220,130);
       mainPanel.add(sContact);

       labName2 = new JLabel("Email");
       labName2.setSize(90,20);
       labName2.setLocation(100,160);
       mainPanel.add(labName2);

       sEmail = new JTextField();
       sEmail.setSize(250,20);
       sEmail.setLocation(220,160);
       mainPanel.add(sEmail);

       labName3 = new JLabel("Class");
       labName3.setSize(90,20);
       labName3.setLocation(100,190);
       mainPanel.add(labName3);

       jClass =new JComboBox();
       jClass.setSize(250,20);
       jClass.setLocation(220,190);
       jClass.addItem("--Select--");
       mainPanel.add(jClass);

       labName4 = new JLabel("Course");
       labName4.setSize(90,20);
       labName4.setLocation(100,220);
       mainPanel.add(labName4);

       jCourse = new JComboBox();
       jCourse.setSize(250,20);
       jCourse.setLocation(220,220);
       jCourse.addItem("--Select--");
       jCourse.addItem("Java");
       jCourse.addItem("Php");
       jCourse.addItem("Android");
       jCourse.addItem("Spring");
       mainPanel.add(jCourse);

       cPrice = new JTextField();
       cPrice.setSize(90,20);
       cPrice.setLocation(400,250);
       cPrice.setBackground(Color.WHITE);
       mainPanel.add(cPrice);

       searchBtn = new JButton("Search");
       searchBtn.setSize(100,20);
       searchBtn.setLocation(500,80);
       mainPanel.add(searchBtn);

       saveBtn = new JButton("Save");
       saveBtn.setSize(100,20);
       saveBtn.setLocation(280,250);
       mainPanel.add(saveBtn);

       closeBtn = new JButton("Close");
       closeBtn.setSize(100,20);
       closeBtn.setLocation(280,280);
       mainPanel.add(closeBtn);

       updateBtn = new JButton("Update");
       updateBtn.setSize(100,20);
       updateBtn.setLocation(280,310);
       updateBtn.setEnabled(false);
       mainPanel.add(updateBtn);

       deleteBtn = new JButton("Delete");
       deleteBtn.setSize(100,20);
       deleteBtn.setLocation(280,340);
       deleteBtn.setEnabled(false);
       mainPanel.add(deleteBtn);

       jCourse.addItemListener(new ItemListener() {
          @Override
          public void itemStateChanged(ItemEvent e) {
             int a1 = jCourse.getSelectedIndex();
             String cName;
             switch (a1){
                case 1: cName ="Java";
                break;
                case 2: cName="Php";
                break;
                case 3: cName="Android";
                break;
                case 4: cName="Spring";
                break;
                default:cName="No Value Find";
                break;
             }
              cPrice.setText(cName);
          }
       });
       searchBtn.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
             searchStudent();
          }
       });
       saveBtn.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
             String cNa = jClass.getSelectedItem().toString();
             String couName = jCourse.getSelectedItem().toString();
             StudentDao sDao = new StudentDao();
             int status = sDao.searchN(cNa);
             int status1 = sDao.searchCn(couName);
             StudentModel st = new StudentModel();
             st.setStudentName(stName.getText());
             st.setContact(sContact.getText());
             st.setEmail(sEmail.getText());
             st.setClassid(status);
             st.setCourseid(status1);
            int status3 = sDao.saveStudent(st);
             if(status3>0){
                JOptionPane.showMessageDialog(null,"Record has been Saved");
                resetMethod();
                searchStudent();
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
      updateBtn.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            int a1 = jTable.getSelectedRow();
            int stuId = Integer.parseInt(jTable.getValueAt(a1,0).toString());
            String cNa = jClass.getSelectedItem().toString();
            String couName = jCourse.getSelectedItem().toString();
            StudentDao sDao = new StudentDao();
            int status = sDao.searchN(cNa);
            int status1 = sDao.searchCn(couName);
            StudentModel st = new StudentModel();
            st.setId(stuId);
            st.setStudentName(stName.getText());
            st.setContact(sContact.getText());
            st.setEmail(sEmail.getText());
            st.setClassid(status);
            st.setCourseid(status1);
            int status3 = sDao.updateStudent(st);
            if(status3>0){
               JOptionPane.showMessageDialog(null,"Record has been Updated");
               resetMethod();
               searchStudent();
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
            int stuId = Integer.parseInt(jTable.getValueAt(a1,0).toString());
            StudentDao sDao = new StudentDao();
            int status = sDao.deleteStudent(stuId);
            if(status>0){
               JOptionPane.showMessageDialog(null,"Record has been Deleted");
               resetMethod();
               searchStudent();
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
            stName.setText(jTable.getValueAt(a1,1).toString());
            sContact.setText(jTable.getValueAt(a1,2).toString());
            sEmail.setText(jTable.getValueAt(a1,3).toString());
            jClass.setSelectedItem(jTable.getValueAt(a1,4).toString());
            jCourse.setSelectedItem(jTable.getValueAt(a1,5).toString());
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
       setVisible(true);
    }
   public static void searchStudent(){
      dtm.setRowCount(0);
      LinkedList<StudentModel> list = StudentDao.getAll();
      for(StudentModel cm:list){
         String sval[] = {String.valueOf(cm.getId()),cm.getStudentName(),cm.getContact(),cm.getEmail(),cm.getClassName(),cm.getCourseName()};
         dtm.addRow(sval);
      }
   }
    public static void main(String[] args) {
          Student st = new Student();
          st.searchClass();
       searchStudent();
    }


   @Override
   public void searchClass() {
      List<ClassCreationFields> list = ClassCreationDao.getAllClass();
      for(int i=0;i<list.size();i++){
         String ss = list.get(i).getClass_Name();
         jClass.addItem(ss);
      }
   }

   @Override
   public void searchYear() {

   }

   public void resetMethod(){
       stName.setText("");
       sContact.setText("");
       sEmail.setText("");
       jClass.setSelectedIndex(0);
       jCourse.setSelectedIndex(0);
      updateBtn.setEnabled(false);
      deleteBtn.setEnabled(false);
      saveBtn.setEnabled(true);
   }
}
