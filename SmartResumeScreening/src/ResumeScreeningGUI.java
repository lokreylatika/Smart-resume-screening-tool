import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class ResumeScreeningGUI extends JFrame {

    private JEditorPane outputArea;

    private String jobDescriptionPath;
    private String resumeFolderPath;

    private JLabel jobLabel;
    private JLabel folderLabel;

    public ResumeScreeningGUI() {

        // ================= LOOK AND FEEL =================

        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // ================= WINDOW =================

        setTitle("Smart Resume Screening Tool");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        getContentPane().setBackground(
                new Color(248, 245, 255));

        // ================= MAIN TOP PANEL =================

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.setBackground(
                new Color(248, 245, 255));

        // ================= TITLE =================

        JLabel title = new JLabel(
                "SMART RESUME SCREENING TOOL",
                SwingConstants.CENTER);

        title.setFont(
                new Font("Segoe UI",
                        Font.BOLD,
                        30));

        title.setForeground(
                new Color(142, 68, 173));

        title.setBorder(
                BorderFactory.createEmptyBorder(
                        20, 10, 15, 10));

        // ================= BUTTON PANEL =================

        JPanel buttonPanel = new JPanel();

        buttonPanel.setBackground(
                new Color(248, 245, 255));

        JButton jobButton =
                new JButton("Choose Job Description");

        JButton resumeButton =
                new JButton("Choose Resume Folder");

        JButton analyzeButton =
                new JButton("Analyze Resumes");

        // Button colors

        jobButton.setBackground(
                new Color(255, 182, 193));

        resumeButton.setBackground(
                new Color(173, 216, 230));

        analyzeButton.setBackground(
                new Color(186, 220, 88));

        // Button text colors

        jobButton.setForeground(
                new Color(70, 50, 70));

        resumeButton.setForeground(
                new Color(50, 60, 70));

        analyzeButton.setForeground(
                new Color(40, 70, 30));

        // Remove focus border

        jobButton.setFocusPainted(false);
        resumeButton.setFocusPainted(false);
        analyzeButton.setFocusPainted(false);

        // Font

        Font buttonFont =
                new Font("Segoe UI",
                        Font.BOLD,
                        14);

        jobButton.setFont(buttonFont);
        resumeButton.setFont(buttonFont);
        analyzeButton.setFont(buttonFont);

        // Add buttons

        buttonPanel.add(jobButton);
        buttonPanel.add(resumeButton);
        buttonPanel.add(analyzeButton);

        // ================= INFORMATION PANEL =================

        JPanel infoPanel = new JPanel();

        infoPanel.setBackground(
                new Color(248, 245, 255));

        jobLabel =
                new JLabel(
                        "No Job Description Selected");

        folderLabel =
                new JLabel(
                        "No Resume Folder Selected");

        jobLabel.setForeground(
                new Color(110, 110, 110));

        folderLabel.setForeground(
                new Color(110, 110, 110));

        jobLabel.setFont(
                new Font("Segoe UI",
                        Font.PLAIN,
                        13));

        folderLabel.setFont(
                new Font("Segoe UI",
                        Font.PLAIN,
                        13));

        infoPanel.add(jobLabel);

        infoPanel.add(
                Box.createHorizontalStrut(30));

        infoPanel.add(folderLabel);

        // Add panels to top panel

        topPanel.add(
                title,
                BorderLayout.NORTH);

        topPanel.add(
                buttonPanel,
                BorderLayout.CENTER);

        topPanel.add(
                infoPanel,
                BorderLayout.SOUTH);

        // ================= OUTPUT AREA =================

        outputArea = new JEditorPane();

        outputArea.setContentType(
                "text/html");

        outputArea.setEditable(false);

        outputArea.setBackground(
                new Color(248, 245, 255));

        outputArea.setBorder(null);

        // ================= SCROLL PANEL =================

        JScrollPane scrollPane =
                new JScrollPane(outputArea);

        scrollPane.setBorder(
                BorderFactory.createEmptyBorder());
        
        scrollPane.getViewport().setBackground(
                new Color(248, 245, 255));

        // ================= CARD =================

        JPanel card =
                new JPanel(new BorderLayout());

        card.setBackground(
                new Color(248, 245, 255));

        card.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createEmptyBorder(
                                10, 20, 20, 20),

                        BorderFactory.createLineBorder(
                                new Color(225, 215, 235),
                                1)));

        card.add(
                scrollPane,
                BorderLayout.CENTER);

        // ================= ADD TO FRAME =================

        add(
                topPanel,
                BorderLayout.NORTH);

        add(
                card,
                BorderLayout.CENTER);

        // ==================================================
        // JOB DESCRIPTION BUTTON
        // ==================================================

        jobButton.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(
                            ActionEvent e) {

                        JFileChooser chooser =
                                new JFileChooser();

                        int result =
                                chooser.showOpenDialog(
                                        ResumeScreeningGUI.this);

                        if (result ==
                                JFileChooser.APPROVE_OPTION) {

                            jobDescriptionPath =
                                    chooser
                                            .getSelectedFile()
                                            .getAbsolutePath();

                            jobLabel.setText(
                                    "Job Description: "
                                    + chooser
                                            .getSelectedFile()
                                            .getName());
                        }
                    }
                });

        // ==================================================
        // RESUME FOLDER BUTTON
        // ==================================================

        resumeButton.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(
                            ActionEvent e) {

                        JFileChooser chooser =
                                new JFileChooser();

                        chooser.setFileSelectionMode(
                                JFileChooser.DIRECTORIES_ONLY);

                        int result =
                                chooser.showOpenDialog(
                                        ResumeScreeningGUI.this);

                        if (result ==
                                JFileChooser.APPROVE_OPTION) {

                            resumeFolderPath =
                                    chooser
                                            .getSelectedFile()
                                            .getAbsolutePath();

                            folderLabel.setText(
                                    "Resume Folder: "
                                    + chooser
                                            .getSelectedFile()
                                            .getName());
                        }
                    }
                });

        // ==================================================
        // ANALYZE BUTTON
        // ==================================================

        analyzeButton.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(
                            ActionEvent e) {

                        runScreening();
                    }
                });

        setVisible(true);
    }

    // ======================================================
    // SCREENING METHOD
    // ======================================================

    private void runScreening() {

        try {

            // ================= CHECK INPUT =================

            if (jobDescriptionPath == null ||
                    resumeFolderPath == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please select both the Job Description "
                        + "and Resume Folder.",
                        "Missing Input",
                        JOptionPane.WARNING_MESSAGE);

                return;
            }

            // ================= LOAD JOB DESCRIPTION =================

            JobDescription jd =
                    JobDescription.loadJobDescription(
                            jobDescriptionPath);

            // ================= LOAD RESUME FOLDER =================

            File folder =
                    new File(resumeFolderPath);

            File[] files =
                    folder.listFiles();

            if (files == null ||
                    files.length == 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "No resume files found in the selected folder.",
                        "No Resumes",
                        JOptionPane.WARNING_MESSAGE);

                return;
            }

            // ================= COUNT VALID FILES =================

            int validFiles = 0;

            for (File file : files) {

                if (file.isFile()) {
                    validFiles++;
                }
            }

            if (validFiles == 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "The selected folder does not contain resumes.",
                        "No Resumes",
                        JOptionPane.WARNING_MESSAGE);

                return;
            }

            // ================= CREATE CANDIDATE ARRAY =================

            Candidate[] candidates =
                    new Candidate[validFiles];

            int count = 0;

            // ================= PARSE RESUMES =================

            for (File file : files) {

                if (file.isFile()) {

                    candidates[count] =
                            ResumeParser.parseResume(
                                    file.getAbsolutePath());

                    count++;
                }
            }

            // ================= CALCULATE SCORE =================

            for (int i = 0;
                 i < count;
                 i++) {

                ResumeScorer.calculateScore(
                        candidates[i],
                        jd.getRequiredSkills(),
                        jd.getRequiredExperience(),
                        jd.getRequiredCertifications());
            }

            // ================= MAX HEAP =================

            MaxHeap heap =
                    new MaxHeap(count);

            for (int i = 0;
                 i < count;
                 i++) {

                heap.insert(
                        candidates[i]);
            }

            // ================= HTML RESULT =================

            StringBuilder html =
                    new StringBuilder();

            html.append(
                    "<html>");

            html.append(
                    "<head>");

            html.append(
                    "<style>");

            html.append(
                    "body {"
                    + "font-family:Segoe UI, Arial;"
                    + "background-color:#f8f5ff;"
                    + "text-align:center;"
                    + "margin:0;"
                    + "padding:10px;"
                    + "}");
            
            html.append(
                    ".heading {"
                    + "color: #8e44ad;"
                    + "font-size: 24px;"
                    + "font-weight: bold;"
                    + "margin: 5px 0 15px 0;"
                    + "}");

            html.append(
                    ".rank {"
                    + "color: #8e44ad;"
                    + "font-size: 22px;"
                    + "font-weight: bold;"
                    + "}");

            html.append(
                    ".name {"
                    + "font-size: 21px;"
                    + "font-weight: bold;"
                    + "color: #333333;"
                    + "}");

            html.append(
                    ".details {"
                    + "font-size: 16px;"
                    + "color: #555555;"
                    + "}");

            html.append(
                    ".score {"
                    + "font-size: 20px;"
                    + "font-weight: bold;"
                    + "color: #8e44ad;"
                    + "}");

            html.append(
                    ".recommended {"
                    + "color: #27ae60;"
                    + "font-weight: bold;"
                    + "}");

            html.append(
                    ".normal {"
                    + "color: #d68910;"
                    + "font-weight: bold;"
                    + "}");

            html.append(
                    ".low {"
                    + "color: #c0392b;"
                    + "font-weight: bold;"
                    + "}");

            html.append(
                    ".complete {"
                    + "color: #27ae60;"
                    + "font-size: 16px;"
                    + "font-weight: bold;"
                    + "margin-top: 20px;"
                    + "}");

            html.append(
                    "</style>");

            html.append(
                    "</head>");

            html.append(
                    "<body>");

            html.append(
                    "<div class='heading'>"
                    + "SMART RESUME SCREENING RESULTS"
                    + "</div>");

            // ================= DISPLAY RANKING =================

            int rank = 1;

            while (!heap.isEmpty()) {

                Candidate c = heap.extractMax();

                String status;
                String statusClass;

                if (c.getScore() >= 100) {

                    status = "Highly Recommended";
                    statusClass = "recommended";

                } else if (c.getScore() >= 70) {

                    status = "Recommended";
                    statusClass = "normal";

                } else {

                    status = "Needs Improvement";
                    statusClass = "low";
                }

                // Candidate card
                html.append(
                        "<table align='center' width='500' "
                        + "cellpadding='15' cellspacing='0' "
                        + "style='"
                        + "background-color:white;"
                        + "border:1px solid #e0d7eb;"
                        + "margin-bottom:15px;"
                        + "'>");

                html.append(
                        "<tr>"
                        + "<td align='center'>");

                html.append(
                        "<div class='rank'>"
                        + "RANK #"
                        + rank
                        + "</div>");

                html.append(
                        "<br>");

                html.append(
                        "<div class='name'>"
                        + escapeHTML(c.getName())
                        + "</div>");

                html.append(
                        "<br>");

                html.append(
                        "<div class='details'>"
                        + "Experience: "
                        + c.getExperienceYears()
                        + " years"
                        + "</div>");

                html.append(
                        "<br>");

                html.append(
                        "<div class='score'>"
                        + "Score: "
                        + c.getScore()
                        + "</div>");

                html.append(
                        "<br>");

                html.append(
                        "<div class='"
                        + statusClass
                        + "'>"
                        + "Status: "
                        + status
                        + "</div>");

                html.append(
                        "</td>"
                        + "</tr>");

                html.append(
                        "</table>");

                rank++;
            }

            // ================= COMPLETION =================

            html.append(
                    "<div class='complete'>"
                    + "Screening Completed Successfully!"
                    + "</div>");

            html.append(
                    "</body>");

            html.append(
                    "</html>");

            // ================= DISPLAY HTML =================

            outputArea.setText(
                    html.toString());

            outputArea.setCaretPosition(0);

        }
        catch (Exception e) {

            String error =
                    "<html>"
                    + "<body style='"
                    + "font-family:Segoe UI;"
                    + "text-align:center;"
                    + "padding:40px;'>"

                    + "<h2 style='color:#c0392b;'>"
                    + "Error"
                    + "</h2>"

                    + "<p>"
                    + escapeHTML(
                            e.getMessage())
                    + "</p>"

                    + "</body>"
                    + "</html>";

            outputArea.setText(
                    error);
        }
    }

    // ======================================================
    // HTML ESCAPE METHOD
    // ======================================================

    private static String escapeHTML(
            String text) {

        if (text == null) {
            return "";
        }

        return text
                .replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#39;");
    }

    // ======================================================
    // MAIN
    // ======================================================

    public static void main(
            String[] args) {

        SwingUtilities.invokeLater(
                new Runnable() {

                    @Override
                    public void run() {

                        new ResumeScreeningGUI();
                    }
                });
    }
}