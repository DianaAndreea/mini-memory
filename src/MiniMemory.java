import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.Random;


public class MiniMemory extends JFrame {

    static String files[] = {"res/dogie.jpg", "res/patrick.jpg","res/spongebob.jpg", "res/silvester.jpg",
        "res/tweety.jpg", "res/buttercup.jpg","res/johnny.png","res/pikachu.png"};
    static JButton butoane[];
    ImageIcon closedIcon;
    int numButtons;
    ImageIcon icons[];
    Timer myTimer;
    
    int numClicks = 0;
    int oddClickIndex = 0;
    int currentIndex = 0;

    public MiniMemory() {
        setTitle("Laborator 7 - Joc Mini Memory");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(new GridLayout(4, files.length));
      

        closedIcon = new ImageIcon("res/star.jpg");
        numButtons = files.length * 2;
        butoane = new JButton[numButtons];
        icons = new ImageIcon[numButtons];
        for (int i = 0, j = 0; i < files.length; i++) {
            icons[j] = new ImageIcon(files[i]);
             butoane[j] = new JButton("");
             butoane[j].addActionListener(new ImageButtonListener());
             butoane[j].setBackground(Color.BLACK);
             butoane[j].setIcon(closedIcon);
            add( butoane[j++]);

            icons[j] = icons[j - 1];
             butoane[j] = new JButton("");
             butoane[j].addActionListener(new ImageButtonListener());
             butoane[j].setBackground(Color.BLACK);
             butoane[j].setIcon(closedIcon);
            add( butoane[j++]);
        }

        Random gen = new Random();
        for (int i = 0; i < numButtons; i++) {
            int rand = gen.nextInt(numButtons);
            ImageIcon temp = icons[i];
            icons[i] = icons[rand];
            icons[rand] = temp;
        }

        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        myTimer = new Timer(1000, new TimerListener());
        myTimer.start();
    }

    private class TimerListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
             butoane[currentIndex].setIcon(closedIcon);
             butoane[oddClickIndex].setIcon(closedIcon);
            myTimer.stop();
        }
    }

    private class ImageButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            if (myTimer.isRunning())
                return;
            
            numClicks++;

            for (int i = 0; i < numButtons; i++) {
                if (e.getSource() ==  butoane[i]) {
                     butoane[i].setIcon(icons[i]);
                    currentIndex = i;
                }
            }

            if (numClicks % 2 == 0) {
                if (currentIndex == oddClickIndex) {
                    numClicks--;
                    return;
                }
                if (icons[currentIndex] != icons[oddClickIndex]) {
                    myTimer.start(); 
                }
            } else {
                oddClickIndex = currentIndex;
            }
        }
    }

    public static void main(String[] args) {
        new MiniMemory();
    }
}
