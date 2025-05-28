package com.digitalclock;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MyFrame extends JFrame {

    Calendar calendar;

    // for time
    SimpleDateFormat timeFormat;
    JLabel timeLabel;
    String time;
    // for day
    JLabel dayLabel;
    String day;
    SimpleDateFormat dayFormat;

    // date
    SimpleDateFormat dateFormat;
    JLabel dateLabel;
    String date;


    MyFrame(){
        // basic to-do
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("My Clock Program");
        this.setLayout(new FlowLayout());
        this.setSize(350,300);
        this.setLocation(350,500);
        this.setResizable(false);

        // time
        timeFormat = new SimpleDateFormat("hh:mm:ss a");
        dayFormat = new SimpleDateFormat("EEEE");

        dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        timeLabel = new JLabel();
        timeLabel.setFont(new Font("Verdana", Font.PLAIN, 50));
        timeLabel.setForeground(new Color(0x00FF00));
        timeLabel.setBackground(Color.black);
        timeLabel.setOpaque(true);

        // day
        dayLabel = new JLabel();
        dayLabel.setFont(new Font("Verdana", Font.PLAIN, 45));
        timeLabel.setForeground(new Color(0x00FF00));
        timeLabel.setBackground(Color.black);
        timeLabel.setOpaque(true);

        // date
        dateLabel = new JLabel();
        dateLabel.setFont(new Font("Roboto", Font.BOLD, 35));
        timeLabel.setForeground(new Color(0x00FF00));
        timeLabel.setBackground(Color.black);

        this.add(timeLabel);
        this.add(dayLabel);
        this.add(dateLabel);






        this.setVisible(true);

        // updating time after 1sec
        setTime();
    }

    void setTime(){

        while(true){
            time = timeFormat.format(Calendar.getInstance().getTime());
            timeLabel.setText(time);

            day = dayFormat.format(Calendar.getInstance().getTime());
            dayLabel.setText(day);

            date = dateFormat.format(Calendar.getInstance().getTime());
            dateLabel.setText(date);

            try {
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }


    }
}
