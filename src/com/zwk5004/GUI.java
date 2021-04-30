package com.zwk5004;

import ActionListners.run_ActionListener;
import Inputs.Read;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.font.TextAttribute;
import java.util.List;
import java.util.Map;

public class GUI {
    JFrame frame;
    JLabel machineLabel, typeLabel, rackSizeLabel, numSamplesLabel, machineInfoLabel,
            supportedTypesLabel, maxRacksLabel, maxSamplesLabel;
    JComboBox<String> machineSelect, typeSelect, rackSizeSelect;
    JTextField numSamplesInput;
    JButton runButton, cancelButton;
    JProgressBar progressBar1, progressBar2, progressBar3, progressBar4, progressBar5, progressBar6;
    JLabel progressLabel1, progressLabel2, progressLabel3, progressLabel4, progressLabel5, progressLabel6;
    boolean isRunning = false;

    public GUI (){
        frame = new JFrame();

        machineLabel = new JLabel("Machine");
        typeLabel = new JLabel("Type");
        rackSizeLabel = new JLabel("Rack Size");
        numSamplesLabel = new JLabel("Total Samples");
        machineInfoLabel = new JLabel("Machine Info");
        machineInfoLabel.setFont(new Font(null, Font.PLAIN, 16));
        Font font = machineInfoLabel.getFont();
        Map attributes = font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        machineInfoLabel.setFont(font.deriveFont(attributes));
        supportedTypesLabel = new JLabel("Supported Types: DNA, RNA, mRNA");
        maxRacksLabel = new JLabel("Max Racks: 20");
        maxSamplesLabel = new JLabel("Max Samples: 2880");
        runButton = new JButton("Run");
        runButton.addActionListener(e -> {
            isRunning = true;
            this.runButton.setVisible(false);
            this.cancelButton.setVisible(true);
        });
        cancelButton = new JButton("Cancel");
        cancelButton.setVisible(false);
        cancelButton.addActionListener(e -> {
            isRunning = false;
            this.cancelButton.setVisible(false);
            this.runButton.setVisible(true);
        });

        String[] machines = new String[]{"HiSeq2000", "HiSeq3000", "hiSeq", "Sanger", "PacBio", "IonTorrent"};
        machineSelect = new JComboBox<>(machines);

        //machineSelect.addActionListener(new run_ActionListener(this));

        String[] sequenceTypes = new String[]{"DNA", "RNA", "mRNA"};
        typeSelect = new JComboBox<>(sequenceTypes);
        String[] rackSizes = new String[]{"16", "32", "64", "100", "144", "288"};
        rackSizeSelect = new JComboBox<>(rackSizes);
        numSamplesInput = new JTextField("", 10);
        numSamplesInput.setSize(100, 14);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new FlowLayout());
        headerPanel.setBorder(new LineBorder(Color.BLACK));

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.TRAILING));

        inputPanel.add(machineLabel);
        inputPanel.add(machineSelect);
        inputPanel.add(typeLabel);
        inputPanel.add(typeSelect);
        inputPanel.add(rackSizeLabel);
        inputPanel.add(rackSizeSelect);
        inputPanel.add(numSamplesLabel);
        inputPanel.add(numSamplesInput);

        buttonPanel.add(runButton);
        buttonPanel.add(cancelButton);

        topPanel.add(inputPanel, BorderLayout.PAGE_START);
        topPanel.add(buttonPanel, BorderLayout.PAGE_END);

        JPanel machineInfoPanel = new JPanel();
        machineInfoPanel.setLayout(new BoxLayout(machineInfoPanel, BoxLayout.PAGE_AXIS));
        machineInfoPanel.setBorder(new MatteBorder(0, 1, 0, 0, Color.BLACK));
        machineInfoPanel.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(0, 1, 0, 0, Color.BLACK),
                new EmptyBorder(10,10,10,10)));

        machineInfoPanel.add(machineInfoLabel);
        machineInfoPanel.add(supportedTypesLabel);
        machineInfoPanel.add(maxRacksLabel);
        machineInfoPanel.add(maxSamplesLabel);

        headerPanel.add(topPanel);
        headerPanel.add(machineInfoPanel);

        JPanel progressPanel = new JPanel();
        progressPanel.setLayout(new BoxLayout(progressPanel, BoxLayout.PAGE_AXIS));
        progressBar1 = createProgressBar();
        progressBar2 = createProgressBar();
        progressBar3 = createProgressBar();
        progressBar4 = createProgressBar();
        progressBar5 = createProgressBar();
        progressBar6 = createProgressBar();
        progressLabel1 = new JLabel("Rack 1", SwingConstants.LEADING);
        progressLabel1.setFont(new Font(null, Font.PLAIN, 16));
        progressLabel2 = new JLabel("Rack 2", SwingConstants.LEADING);
        progressLabel2.setFont(new Font(null, Font.PLAIN, 16));
        progressLabel3 = new JLabel("Rack 3", SwingConstants.LEADING);
        progressLabel3.setFont(new Font(null, Font.PLAIN, 16));
        progressLabel4 = new JLabel("Rack 4", SwingConstants.LEADING);
        progressLabel4.setFont(new Font(null, Font.PLAIN, 16));
        progressLabel5 = new JLabel("Rack 5", SwingConstants.LEADING);
        progressLabel5.setFont(new Font(null, Font.PLAIN, 16));
        progressLabel6 = new JLabel("Rack 6", SwingConstants.LEADING);
        progressLabel6.setFont(new Font(null, Font.PLAIN, 16));

        progressPanel.add(progressLabel1);
        progressPanel.add(progressBar1);
        progressPanel.add(progressLabel2);
        progressPanel.add(progressBar2);
        progressPanel.add(progressLabel3);
        progressPanel.add(progressBar3);
        progressPanel.add(progressLabel4);
        progressPanel.add(progressBar4);
        progressPanel.add(progressLabel5);
        progressPanel.add(progressBar5);
        progressPanel.add(progressLabel6);
        progressPanel.add(progressBar6);

        mainPanel.add(headerPanel, BorderLayout.PAGE_START);
        mainPanel.add(progressPanel, BorderLayout.CENTER);

        frame.add(mainPanel);

        frame.setSize(new Dimension(1000, 1000));
        frame.setVisible(true);
    }

    private JProgressBar createProgressBar() {
        JProgressBar tempBar = new JProgressBar(0, 100);
        tempBar.setValue(((int) (Math.random() * 100)));
        tempBar.setStringPainted(true);
        return tempBar;
    }

    public void run(){
        String machine = machineSelect.getItemAt(machineSelect.getSelectedIndex());
        String sequenceType = typeSelect.getItemAt(typeSelect.getSelectedIndex());
        int rackSize = Integer.parseInt(rackSizeSelect.getItemAt(rackSizeSelect.getSelectedIndex()));
        int totalSamples = Integer.parseInt(numSamplesInput.getText());
        int totalRacks = totalSamples/rackSize;
        Read file = new Read();
        // Create a new Simulation, create the selected machine
        Simulator simulator = new Simulator(machine);
        // Everytime we hit run:
        // TODO: Figure out priority, figure out ID, Define machines to use sequence method and select filter class method
        // Created the samples and the racks
        for(int i = 0; i < totalRacks; i++){
            Rack rack = new Rack(PRIORITY);
            List<Sample> sampleL = new List;
            for(int j = 0; j < totalSamples; j++){
                sampleL.add(new Sample(ID).setSequence(file.getSequence(sequenceType, 25000)));
            }
            rack.addSamples(sampleL);
            //add the racks to the machine
            simulator.addRack(rack);
        }




    }
}
