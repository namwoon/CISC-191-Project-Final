import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.sql.*;

public class gui extends JFrame implements ActionListener, ChangeListener {
    private final JTextField search;
    private final JButton searchbut;
    protected JTextArea results;
    protected JScrollPane scrollPane = null;
    private JSlider horse = null;
    private JSlider mpg = null;
    private JTextField horval = null;
    private JButton calculate = null;
    private boolean active;

    private int mpgvalue;
    private int hpvalue;
    gui() {
        int mpgmin = 5;
        int horsemin = 45;
        int horsemax = 230;
        int mpgmax = 50;

        GridBagConstraints layoutConst = null;
        setTitle("auto search");

        search = new JTextField(20);
        JLabel sear = new JLabel("Search name or all: ");
        results = new JTextArea(20,80);
        results.setEditable(false);
        JLabel mpglab = new JLabel("MPG:");
        JLabel horselab = new JLabel(("Horsepower:"));
        horval = new JTextField(20);
        horval.setEditable(false);
        horval.setText("None Currently Selected");
        JLabel slides = new JLabel("MPG , Horse Power");
        calculate = new JButton("Find with MPG and HP");
        calculate.addActionListener(this);
        searchbut = new JButton("Find!");

        JLabel resultstag = new JLabel("Results: ");
        searchbut.addActionListener(this);

        //setting up mpg slider
        mpg =new JSlider(mpgmin, mpgmax);
        mpg.addChangeListener(this);
        mpg.setMajorTickSpacing(5);
        mpg.setMinorTickSpacing(1);
        mpg.setPaintTicks(true);
        mpg.setPaintLabels(true);

        //setting up horsepower slider
        horse = new JSlider(horsemin, horsemax);
        horse.addChangeListener(this);
        horse.setMajorTickSpacing(10);
        horse.setMinorTickSpacing(5);
        horse.setPaintTicks(true);
        horse.setPaintLabels(true);

        setLayout(new GridBagLayout());
        scrollPane = new JScrollPane(results);

        layoutConst = new GridBagConstraints();
        layoutConst.fill = GridBagConstraints.HORIZONTAL;
        layoutConst.gridy = 0;
        layoutConst.gridx = 0;
        layoutConst.insets = new Insets(10,10,0,10);
        add(sear, layoutConst);
        layoutConst.gridx = 5;
        add(searchbut, layoutConst);

        layoutConst.gridy = 0;
        layoutConst.gridx = 1;
        layoutConst.gridheight = 2;
        layoutConst.gridwidth = 4;
        add(search, layoutConst);

        layoutConst.gridy = 4;
        layoutConst.gridx =0;
        add(resultstag, layoutConst);

        layoutConst = new GridBagConstraints();

        layoutConst.insets = new Insets(10, 10, 10, 10);
        layoutConst.fill = GridBagConstraints.HORIZONTAL;
        layoutConst.gridx = 3;
        layoutConst.gridy = 5;
        layoutConst.gridwidth = 3; // 3 cells wide
        add(scrollPane, layoutConst);

        layoutConst.insets = new Insets(20,5,15,0);
        layoutConst.gridy = 9;
        layoutConst.gridx = 0;
        add(mpglab, layoutConst);
        layoutConst.insets = new Insets(20,5,15,30);

        layoutConst.gridy = 7;
        add(horselab, layoutConst);

        //slider1 mpg
        layoutConst.insets = new Insets(10,10,10,1);
        layoutConst.gridwidth = 5;
        layoutConst.gridheight = 1;
        layoutConst.gridx=1;
        layoutConst.gridy = 9;
        add(mpg, layoutConst);

        layoutConst.gridy = 7;
        add(horse, layoutConst);

        layoutConst.gridwidth = 1;
        layoutConst.gridy = 10;
        layoutConst.gridx = 3;
        add(horval, layoutConst);

        layoutConst.gridx = 0;
        add(slides, layoutConst);
        layoutConst.gridx = 5;
        add(calculate, layoutConst);

        results.setText("MPG            cylinders           displacement      horsepower            weight          acceleration        modelYear         origin                    carName\n");


    }

    public void thesql(String all) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection
                ("jdbc:mysql://localhost/miramar","testuser","Pa$$word");
        Statement statement = connection.createStatement();
        ResultSet resultSet;
        if (all.equalsIgnoreCase("ALL")) {
            resultSet = statement.executeQuery("select * from auto;");
        }
        else{
            resultSet = statement.executeQuery("select * from auto where carName = '" + all+ "';");

        }

        while (resultSet.next())
            results.append(resultSet.getString(1) + "\t" +
                    resultSet.getString(2) + "\t" + resultSet.getString(3) + "\t" +
                    resultSet.getString(4) + "\t" + resultSet.getString(5)+ "\t" +
                    resultSet.getString(6)+ "\t" + resultSet.getString(7)+ "\t" +
                    resultSet.getString(8)+ "\t" + resultSet.getString(9)+"\n");


        connection.close();

    }
    public void thesql2() throws SQLException, ClassNotFoundException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection
                ("jdbc:mysql://localhost/miramar","testuser","Pa$$word");
        Statement statement = connection.createStatement();
        ResultSet resultSet;

            resultSet = statement.executeQuery("select * from auto where horsepower>= "+ hpvalue+" and horsepower <"+(hpvalue+1)+
                    " and mpg>= "+ mpgvalue+ " and mpg< " +(mpgvalue+1));

        while (resultSet.next())
            results.append(resultSet.getString(1) + "\t" +
                    resultSet.getString(2) + "\t" + resultSet.getString(3) + "\t" +
                    resultSet.getString(4) + "\t" + resultSet.getString(5)+ "\t" +
                    resultSet.getString(6)+ "\t" + resultSet.getString(7)+ "\t" +
                    resultSet.getString(8)+ "\t" + resultSet.getString(9)+"\n");


        connection.close();

    }
    public static void main(String[] args)  {
     gui bingus = new gui();

     bingus.pack();
     bingus.setVisible(true);
     bingus.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }

    @Override
    public void actionPerformed(ActionEvent e)  {
JButton sourceE = (JButton) e.getSource();
String title = ("MPG            cylinders           displacement      horsepower            weight          acceleration        modelYear         origin                    carName\n");

        try {
            results.setText(title);
            if (sourceE == searchbut) {
                this.thesql(search.getText());
            }
            else if ((sourceE == calculate) &&(!active)) {
                results.setText("Please modify HP and MPG sliders!");
            }
            else if(sourceE == calculate) {
                this.thesql2();
            }
        } catch (SQLException | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        String strsliderval;
        active = true;
        mpgvalue = mpg.getValue();
        hpvalue = horse.getValue();

            strsliderval= mpgvalue + " MPG " + hpvalue + " Horse Power";
            horval.setText(strsliderval);
    }
}

