package home;

import fyear.CreateYear;
import master.ClassCreation;
import search.SearchAll;
import student.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home extends JDialog {
    private Container c;
    private JButton cClass,fyear,searchCl,stuReg;
    private JPanel jPanel;

    public Home(){
        setTitle("Home");
        setBounds(0,0,1200,700);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        c = getContentPane();

        jPanel = new JPanel();
        jPanel.setBackground(Color.CYAN);
        jPanel.setLayout(null);
        c.add(jPanel);

       cClass = new JButton("Create Class");
       cClass.setSize(150,20);
       cClass.setLocation(0,10);
        jPanel.add(cClass);

        fyear = new JButton("Create Year");
        fyear.setSize(150,20);
        fyear.setLocation(160,10);
        jPanel.add(fyear);

        searchCl = new JButton("Search Year");
        searchCl.setSize(150,20);
        searchCl.setLocation(320,10);
        jPanel.add(searchCl);

        stuReg = new JButton("Student Registration");
        stuReg.setSize(150,20);
        stuReg.setLocation(480,10);
        jPanel.add(stuReg);

        cClass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClassCreation cclass = new ClassCreation();
                cclass.setVisible(true);
            }
        });

        fyear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateYear createYear = new CreateYear();
                createYear.setVisible(true);
            }
        });

        searchCl.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SearchAll searchAll = new SearchAll();
                searchAll.setVisible(true);
            }
        });

        stuReg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Student stu = new Student();
                stu.setVisible(true);
            }
        });
        setVisible(true);
    }

    public static void main(String[] args) {

        Home home = new Home();

    }
}
