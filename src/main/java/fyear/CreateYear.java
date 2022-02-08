package fyear;

import master.ClassCreationDao;
import master.ClassCreationFields;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class CreateYear extends JDialog {
    private Container c;
    private JLabel fyear;
    private JTextField fyearName;
    private JButton saveBtn,closeBtn,updateBtn,deleteBtn,searchBtn;
    private JTable jTable;
    private JPanel mainPanel;
    private JScrollPane jp;
   static DefaultTableModel dtm;
    public CreateYear(){

        setTitle("Create Financial Year");
        setBounds(300, 90, 900, 600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        c = getContentPane();

        mainPanel = new JPanel();
        mainPanel.setBackground(Color.CYAN);
        mainPanel.setLayout(null);
        c.add(mainPanel);

        fyear = new JLabel("Year Name");
        fyear.setSize(100,20);
        fyear.setLocation(100,100);
        mainPanel.add(fyear);

        fyearName = new JTextField();
        fyearName.setSize(200,20);
        fyearName.setLocation(200,100);
        mainPanel.add(fyearName);

        saveBtn = new JButton("Save");
        saveBtn.setSize(100,20);
        saveBtn.setLocation(250,140);
        mainPanel.add(saveBtn);

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

        searchBtn = new JButton("Search");
        searchBtn.setSize(100,20);
        searchBtn.setLocation(500,80);
        mainPanel.add(searchBtn);

        String colName[] = {"Id","Year Name"};
        dtm = new DefaultTableModel();
        dtm.setColumnIdentifiers(colName);
        jTable = new JTable();
        jTable.setSize(300,200);
        jTable.setLocation(900,100);
        jTable.setModel(dtm);
        jp = new JScrollPane(jTable);
        jp.setBounds(450, 100, 350, 200);
        mainPanel.add(jp);
        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fName = fyearName.getText();
                yearDao yDao = new yearDao();
               int status =  yDao.saveMethod(fName);
                if(status>0)
                {
                    JOptionPane.showMessageDialog(null,"Record has been Saved");
                    searchYear();
                    resetMethod();
                }
                else {
                    JOptionPane.showMessageDialog(null,"Record has been Not Saved");
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
                String f_id = jTable.getValueAt(a1,0).toString();
                String f_name = fyearName.getText();
                yearDao yearDao = new yearDao();
                int status = yearDao.updateYear(Integer.parseInt(f_id),f_name);
                if(status>0){
                    JOptionPane.showMessageDialog(null,"Record has been Updated");
                    resetMethod();
                    searchYear();
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
                int f_id = Integer.parseInt(jTable.getValueAt(a1,0).toString());
             yearDao yearDao = new yearDao();
             int status = yearDao.deleteId(f_id);
                if(status>0)
                {
                    JOptionPane.showMessageDialog(null,"Record has been Deleted");
                    resetMethod();
                    searchYear();

                }
                else {
                    JOptionPane.showMessageDialog(null,"Record has been not Deleted");
                }

            }
        });
        searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchYear();
            }
        });
        jTable.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int a1 = jTable.getSelectedRow();
                String f_val = jTable.getValueAt(a1,1).toString();
                fyearName.setText(f_val);
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

    public static void main(String[] args) {
        CreateYear cYear = new CreateYear();
        searchYear();
    }


    public static void searchYear()
    {
        dtm.setRowCount(0);
        ArrayList<FClass> yDao = yearDao.getAll();
        for(FClass fy:yDao){
            String sval[] ={String.valueOf(fy.getId()), fy.getF_year()};
            dtm.addRow(sval);
        }
    }
    public void resetMethod()
    {
        fyearName.setText("");
        saveBtn.setEnabled(true);
        updateBtn.setEnabled(false);
        deleteBtn.setEnabled(false);
    }
}
