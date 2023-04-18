package todoapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class AddTaskWindow extends JFrame {

    public AddTaskWindow() {
        super();
        setupLayout();
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        setTitle("Add Tasks");

    }

    private void setupLayout() {
        Container contentPane = getContentPane();

    }

}
