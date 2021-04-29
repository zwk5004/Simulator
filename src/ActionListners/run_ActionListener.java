package ActionListners;

import com.zwk5004.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class run_ActionListener implements ActionListener {
    private GUI gui;

    public run_ActionListener(GUI adaptee){
        this.gui = adaptee;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        gui.run();
    }
}
