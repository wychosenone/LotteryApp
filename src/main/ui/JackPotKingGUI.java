package ui;

import model.LottoMax;
import model.TicketNo;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;


/*
 * BorderDemo.java requires the following file:
 *    images/wavy.gif
 */
public class JackPotKingGUI extends JFrame {
    static JackPotKingGUI frame;

    private DefaultListModel listModel;
    private JList list;
    private JButton jbAdd = new JButton("Add new Record");
    private JButton jbLoad = new JButton("Load History");
    private JButton jbPredication = new JButton("Predication");
    private JButton jbSimulation = new JButton("Simulation");
    private JButton jbStat = new JButton("View stat");
    private JTextField jtextTicketNo = new JTextField();

    private static final String HISTORY_FILE = "./data/historyInfo.txt";
    private LottoMax lottoMax;

    public JackPotKingGUI(String name) {
        super(name);
        setResizable(true);
        try {
            lottoMax = new LottoMax(HISTORY_FILE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setupjbLoad() {
        jbLoad.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                listModel.removeAllElements();
                for (TicketNo row : lottoMax.getHistory().getTicketNoList()) {
                    listModel.addElement(row.toString());
                }
                playAudio();
            }
        });
    }

    private void setupjbPredication() {
        jbPredication.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog d = new JDialog(frame, "Prediction");
                int[] r = lottoMax.doPrediction();
                TicketNo t = new TicketNo(r);
                JLabel l = new JLabel(t.toString());
                d.add(l);
                d.setSize(150, 150);
                d.setVisible(true);
                playAudio();
            }
        });
    }

    private void setupjbAdd() {
        jbAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String s = jtextTicketNo.getText();
                    int[] data = new int[7];
                    int p = 0;
                    for (String d : s.split(" ")) {
                        data[p++] = Integer.parseInt(d);
                    }

                    lottoMax.addNewRecord(data);
                    TicketNo t = new TicketNo(data);
                    listModel.addElement(t.toString());
                    playAudio();

                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        });
    }

    private void setupjbSimulation() {
        jbSimulation.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int[] data = parseNo(jtextTicketNo.getText());
                    int[] ret = lottoMax.doSimulator(data);
                    String message = "you match " + ret[0] + " numbers,";
                    String t = "";
                    if (ret[0] > 0) {
                        for (int i = 1; i <= ret[0]; i++) {
                            t = t + ret[i] + " ";
                        }
                        message += ("They are: " + t);
                    }
                    JDialog d = new JDialog(frame, "Simulation");
                    d.add(new JLabel(message), BorderLayout.CENTER);
                    d.setSize(300, 150);
                    d.setVisible(true);
                    playAudio();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
    }


    private void setupButtons() {
        setupjbLoad();
        setupjbPredication();
        setupjbAdd();
        setupjbSimulation();

        jbStat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] ret = lottoMax.viewStat();
                JDialog d = new JDialog(frame, "View Stat");
                d.add(new JLabel("Hot: " + ret[0] + "/Cold: " + ret[1]));
                d.setSize(300, 150);
                d.setVisible(true);
                playAudio();
            }
        }
        );
    }


    public void addComponentsToPane(final Container pane) {
        JPanel compsToExperiment = new JPanel();
        compsToExperiment.setLayout(new GridLayout(1, 2));
        JPanel listPanel = new JPanel();
        JPanel controlPanel = new JPanel();
        listModel = new DefaultListModel();
        //Create the list and put it in a scroll pane.
        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.setVisibleRowCount(15);
        DefaultListCellRenderer renderer = (DefaultListCellRenderer) list.getCellRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);
        JScrollPane listScrollPane = new JScrollPane(list);
        listPanel.add(listScrollPane);
        compsToExperiment.add(listPanel);
        setControlPanel(controlPanel);
        compsToExperiment.add(controlPanel);
        pane.add(compsToExperiment);
    }

    public void setControlPanel(JPanel controlPanel) {
        controlPanel.setLayout(new GridLayout(7, 1));
        try {
            BufferedImage myPicture = ImageIO.read(new File("./data/lotto.png"));
            Image mp1 = myPicture.getScaledInstance(60, 30, Image.SCALE_DEFAULT);
            JLabel picLabel = new JLabel(new ImageIcon(mp1));
            picLabel.setSize(100, 20);
            controlPanel.add(picLabel);
        } catch (IOException e) {
            e.printStackTrace();
        }
        controlPanel.add(jtextTicketNo);
        controlPanel.add(jbAdd);
        controlPanel.add(jbLoad);
        controlPanel.add(jbPredication);
        controlPanel.add(jbSimulation);
        controlPanel.add(jbStat);
        setupButtons();
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method is invoked from the
     * event dispatch thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        frame = new JackPotKingGUI("GridLayoutDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Set up the content pane.
        frame.addComponentsToPane(frame.getContentPane());
        //Display the window.
        frame.setSize(500, 300);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        /* Use an appropriate Look and Feel */
        try {
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        /* Turn off metal's use of bold fonts */
        UIManager.put("swing.boldMetal", Boolean.FALSE);

        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private void playAudio() {
        try {
            Clip clip;
            AudioInputStream audioIn =
                    AudioSystem.getAudioInputStream(
                            new BufferedInputStream(Files.newInputStream(new File("./data/sound2.wav").toPath(),
                                    StandardOpenOption.READ)));
            ;
            clip = AudioSystem.getClip();

            clip.open(audioIn);

            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private int[] parseNo(String s) {
        int[] data = new int[7];
        int p = 0;
        for (String d : s.split(" ")) {
            data[p++] = Integer.parseInt(d);
        }
        return data;
    }
}