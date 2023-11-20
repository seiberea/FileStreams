import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class RandProductSearchFrame  extends JFrame
{
    private JPanel mainPanel, titlePanel, inputPanel, displayPanel, buttonPanel;
    private JLabel titleLabel, inputLabel;
    private JTextField inputTextField;
    private JTextArea displayTextArea;
    private JScrollPane displayScroll;
    private JButton searchButton, quitButton;
    private ActionListener quit = new quitListener();
    private ActionListener search = new searchListener();
    private String input, display;

    RandProductSearchFrame()
    {
        setTitle("Random Product Search");
        setSize(1000,800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel = new JPanel();
        titlePanel = new JPanel();
        inputPanel = new JPanel();
        displayPanel = new JPanel();
        buttonPanel = new JPanel();
        titleLabel = new JLabel("Random Product Search");
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 40));
        inputLabel = new JLabel("Search for:");
        inputLabel.setFont(new Font("Times New Roman", Font.BOLD, 25));
        inputTextField = new JTextField();
        displayTextArea = new JTextArea(6, 75);
        displayScroll = new JScrollPane(displayTextArea);
        TitledBorder displayBorder = BorderFactory.createTitledBorder("Filtered Products");
        displayBorder.setTitleFont(new Font("Times New Roman", Font.BOLD, 20));
        displayTextArea.setBorder(displayBorder);

        searchButton = new JButton("Search");
        searchButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));
        searchButton.addActionListener(search);
        quitButton = new JButton("Quit");
        quitButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));
        quitButton.addActionListener(quit);

        add(mainPanel);
        mainPanel.setLayout(new GridLayout(4,1,50,50));

        mainPanel.add(titlePanel);
        titlePanel.add(titleLabel);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);

        mainPanel.add(inputPanel);
        inputPanel.setLayout(new GridLayout(2,1,150,0));
        inputPanel.add(inputLabel);
        inputPanel.add(inputTextField);
        inputTextField.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        mainPanel.add(displayPanel);
        displayPanel.add(displayScroll);
        displayTextArea.setFont(new Font("Times New Roman", Font.BOLD, 15));

        mainPanel.add(buttonPanel);
        buttonPanel.add(searchButton);
        buttonPanel.add(quitButton);
    }

    private class searchListener implements ActionListener
    {
        public void actionPerformed(ActionEvent AE)
        {
            displayTextArea.setText("");
            input = inputTextField.getText();
            File workingDirectory = new File(System.getProperty("user.dir"));
            Path file = Paths.get(workingDirectory.getPath() + "\\ProductsRecreation.txt");
            try
            {
                RandomAccessFile inFile = new RandomAccessFile(file.toString(), "r");
                while ((display = inFile.readLine()) != null)
                {
                    if (display.contains(input))
                    {
                        displayTextArea.append(display + "\n");
                    }
                }
            }
            catch (FileNotFoundException e)
            {
                System.out.println("File not found!!!");
                e.printStackTrace();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    private class quitListener implements ActionListener
    {
        public void actionPerformed(ActionEvent AE)
        {
            System.exit(0);
        }
    }
}
