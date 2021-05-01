package com.zwk5004;

import machines.AbsMachine;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
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
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.font.TextAttribute;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GUI {
    Simulator simulator;
    JFrame frame;
    JLabel machineLabel, typeLabel, rackSizeLabel, numSamplesLabel, machineInfoLabel,
            supportedTypesLabel, supportedRacksLabel, maxSamplesLabel;
    JComboBox<String> machineSelect, typeSelect, rackSizeSelect;
    JPanel mainPanel, progressPanel;
    JTextField numSamplesInput;
    JButton runButton, cancelButton;
    JDialog modalDialog;
    List<JProgressBar> progressBars;
    List<JLabel> progressLabels;
    boolean isRunning = false;
    AbsMachine selectedMachine;

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
        supportedRacksLabel = new JLabel("Max Racks: 20");
        maxSamplesLabel = new JLabel("Max Samples: 2880");
        runButton = new JButton("Run");
        runButton.addActionListener(e -> {
            // isRunning = true;
            // this.runButton.setVisible(false);
            // this.cancelButton.setVisible(true);
            clickRun();
        });
        cancelButton = new JButton("Cancel");
        cancelButton.setVisible(false);
        cancelButton.addActionListener(e -> {
            isRunning = false;
            this.cancelButton.setVisible(false);
            this.runButton.setVisible(true);
            simulator.cancel();
        });

        String[] machines = new String[]{"HiSeq2000", "HiSeq3000", "Sanger", "PacBio", "IonTorrent"};
        machineSelect = new JComboBox<>(machines);

        machineSelect.addActionListener(e -> {
            if (machineSelect.getSelectedItem() != null) {
                selectedMachine = MachineFactory.loadMachine((String) machineSelect.getSelectedItem(), "");
                supportedTypesLabel.setText("Supported Types: " + String.join(",", selectedMachine.getSupportedTypes()));
                typeSelect.removeAllItems();
                for (String type : selectedMachine.getSupportedTypes()) {
                    typeSelect.addItem(type);
                }
                supportedRacksLabel.setText("Supported Racks: " + String.join(",", selectedMachine.getSupportedRackSize()));
                rackSizeSelect.removeAllItems();
                for (String rackSize : selectedMachine.getSupportedRackSize()) {
                    rackSizeSelect.addItem(rackSize);
                }
                maxSamplesLabel.setText("Max Samples: " + selectedMachine.getMaxSamples());
            }
        });

        String[] sequenceTypes = new String[]{"DNA", "RNA", "mRNA"};
        typeSelect = new JComboBox<>(sequenceTypes);
        String[] rackSizes = new String[]{"16", "32", "64", "100", "144", "288"};
        rackSizeSelect = new JComboBox<>(rackSizes);
        numSamplesInput = new JTextField("", 10);
        numSamplesInput.setSize(100, 14);

        mainPanel = new JPanel();
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
        machineInfoPanel.add(supportedRacksLabel);
        machineInfoPanel.add(maxSamplesLabel);

        headerPanel.add(topPanel);
        headerPanel.add(machineInfoPanel);

        progressPanel = new JPanel();
        progressPanel.setLayout(new BoxLayout(progressPanel, BoxLayout.PAGE_AXIS));

        mainPanel.add(headerPanel, BorderLayout.PAGE_START);
        mainPanel.add(progressPanel);

        frame.add(mainPanel);

        frame.setSize(new Dimension(1000, 1000));
        frame.setVisible(true);
    }

    public void clickRun() {
        this.runButton.setVisible(false);
        this.cancelButton.setVisible(true);
        modalDialog = new JDialog(frame, "Rack Priority", Dialog.ModalityType.DOCUMENT_MODAL);
        modalDialog.setBounds(132, 132, 300, 500);
        Container dialogContainer = modalDialog.getContentPane();
        dialogContainer.setLayout(new BoxLayout(dialogContainer, BoxLayout.PAGE_AXIS));
        dialogContainer.add(new JLabel("Enter in the priority for each rack.  Default is 1."));
        double rackSize = Double.parseDouble(rackSizeSelect.getItemAt(rackSizeSelect.getSelectedIndex()));
        double totalSamples = Double.parseDouble(numSamplesInput.getText());
        int totalRacks = (int)Math.ceil(totalSamples/rackSize);
        List<JTextField> rackPriorities = new ArrayList<>();
        progressBars = new ArrayList<>();
        progressLabels = new ArrayList<>();
        for (int i = 0; i < totalRacks; i++) {
            JPanel panel = new JPanel();
            panel.setLayout(new FlowLayout());
            panel.add(new JLabel("Rack " + (i+1)));
            JTextField rackPriority = new JTextField("1", 10);
            panel.add(rackPriority);
            rackPriorities.add(rackPriority);
            dialogContainer.add(panel);
            JLabel progressLabel = new JLabel("Rack " + (i+1), SwingConstants.LEADING);
            progressLabel.setFont(new Font(null, Font.PLAIN, 16));
            JProgressBar progressBar = new JProgressBar(0, 100);
            progressBar.setStringPainted(true);
            progressLabels.add(progressLabel);
            progressBars.add(progressBar);
        }
        JButton cancel = new JButton("Cancel");
        cancel.addActionListener(e -> {
            modalDialog.setVisible(false);
        });
        JButton run = new JButton("Run");
        run.addActionListener(e -> {
            modalDialog.setVisible(false);
            run(rackPriorities);
        });
        dialogContainer.add(cancel);
        dialogContainer.add(run);
        modalDialog.setVisible(true);
        for (int k = 0; k < totalRacks; k++) {
            progressPanel.add(progressLabels.get(k));
            progressPanel.add(progressBars.get(k));
        }
        mainPanel.add(progressPanel, BorderLayout.CENTER);
        mainPanel.updateUI();
    }

    public void run(List<JTextField> rackPriorities){
        String machine = machineSelect.getItemAt(machineSelect.getSelectedIndex());
        String sequenceType = typeSelect.getItemAt(typeSelect.getSelectedIndex());
        int rackSize = Integer.parseInt(rackSizeSelect.getItemAt(rackSizeSelect.getSelectedIndex()));
        int samplesLeft = Integer.parseInt(numSamplesInput.getText());
        List<Rack> racks = new ArrayList<>();

        // Create a new Simulation, create the selected machine, passing the sequence type to also pick the filter when loading the machine
        simulator = new Simulator(machine, sequenceType);
        simulator.alignProgressBars(progressBars);
        // Everytime we hit run:
        // Created the samples and the racks
        int rackIdx = 0;
        for (JTextField rackPriorityField: rackPriorities) {
            Rack rack = new Rack(getPriorityFromField(rackPriorityField));
            int limit = Math.min(rackSize, samplesLeft);
            for (int j = 0; j < limit; j++) {
                long id = ((long) rackIdx * rackSize) + j;
                Sample sample = new Sample(id);
                rack.addSample(sample);
                samplesLeft--;
            }
            racks.add(rack);
            rackIdx++;
        }

        racks.forEach(simulator::addRack);

        simulator.run();
    }

    private int getPriorityFromField(JTextField field) {
        String text = field.getText();
        try {
            return Integer.parseInt(text);
        } catch (Exception e) {
            // do nothing
        }
        return 1;
    }
}
