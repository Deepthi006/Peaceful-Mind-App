
// Import necessary Swing components for the GUI (Graphical User Interface).
import javax.swing.*;
// Import classes needed for rendering HTML content in Swing components.
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
// Import AWT (Abstract Window Toolkit) classes for graphics, colors, and layout management.
import java.awt.*;
// Import the ActionEvent class to handle events like button clicks.
import java.awt.event.ActionEvent;
// Import classes for data structures like ArrayList, HashMap, List, and Map.
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
// Import the Collectors class for working with Java Streams.
import java.util.stream.Collectors;


// The main class for our application. It extends JFrame, which is the main window.
public class MainFrame extends JFrame {

    // This is the constructor for the MainFrame class. It's called when a new MainFrame object is created.
    public MainFrame() {
        // 1. Main Window Setup
        // Sets the title that appears in the top bar of the window.
        setTitle("Peaceful Mind");
        // Specifies that the application should exit when the user closes the window.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Sets the initial size of the window in pixels (width, height).
        setSize(950, 750);
        // Centers the window on the screen when it first opens.
        setLocationRelativeTo(null);

        // 2. Header Panel 
        // Creates a new JPanel, which is a container for other components.
        JPanel headerPanel = new JPanel();
        // Sets the background color of the header panel to a dark gray.
        headerPanel.setBackground(new Color(45, 52, 54));
        // Adds a decorative border at the bottom of the header for a visual separator.
        headerPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 4, 0, new Color(85, 92, 94)));
        // Uses HTML to create a multi-colored, bold title string.
        String titleText = "<html><center><b>" +
                "<font color='#FF00DE'>P</font>" +
                "<font color='#FF00A6'>E</font>" +
                "<font color='#FF006E'>A</font>" +
                "<font color='#FF0036'>C</font>" +
                "<font color='#FF2100'>E</font>" +
                "<font color='#FF5A00'>F</font>" +
                "<font color='#FF9300'>U</font>" +
                "<font color='#FFCC00'>L</font>" +
                "&nbsp;" +
                "<font color='#BAFF00'>M</font>" +
                "<font color='#88FF00'>I</font>" +
                "<font color='#55FF00'>N</font>" +
                "<font color='#22FF00'>D</font>" +
                "</b></center></html>";
        // Creates a JLabel to display the HTML title text.
        JLabel titleLabel = new JLabel(titleText);
        // Sets the font and size of the title.
        titleLabel.setFont(new Font("Serif", Font.BOLD, 56));
        // Adds the title label to the header panel.
        headerPanel.add(titleLabel);

        //  3. Tabbed Panel for All Features 
        // Creates a JTabbedPane, which allows switching between different panels (our features).
        JTabbedPane tabbedPane = new JTabbedPane();
        // Sets the font for the text on the tabs themselves.
        tabbedPane.setFont(new Font("SansSerif", Font.BOLD, 16));

        // Adds the first tab, named "Mood Tracker", and associates it with a new MoodTrackerPanel instance.
        tabbedPane.addTab("📊 Mood Tracker", new MoodTrackerPanel());
        // Adds the second tab, "Quotes", associated with a QuotePanel instance.
        tabbedPane.addTab("📜 Quotes", new QuotePanel());
        // Adds the third tab, "Stress Relief", associated with a StressReliefPanel instance.
        tabbedPane.addTab("🧘 Stress Relief", new StressReliefPanel());
        // Adds the fourth tab, "Chat", associated with a ChatPanel instance.
        tabbedPane.addTab("🤖 Chat", new ChatPanel());

        //  4. Add All Components to the Window 
        // Adds the headerPanel to the top (NORTH) region of the main window's BorderLayout.
        add(headerPanel, BorderLayout.NORTH);
        // Adds the tabbedPane to the center region, filling the rest of the window.
        add(tabbedPane, BorderLayout.CENTER);
    }

    // This is the main method, the entry point for any Java application.
    public static void main(String[] args) {
        // SwingUtilities.invokeLater ensures that all UI updates happen on the Event Dispatch Thread (EDT),
        // which is the standard and safe way to manage a Swing GUI.
        SwingUtilities.invokeLater(() -> {
            try {
                // Sets the look and feel of the application to match the user's operating system (e.g., Windows, macOS).
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                // If setting the look and feel fails, print the error stack trace to the console.
                e.printStackTrace();
            }
            // Creates a new instance of our MainFrame and makes it visible on the screen.
            new MainFrame().setVisible(true);
        });
    }

    //  DSA 1: Time-Series Analysis and Pattern Finding 
    // A static inner class for the Mood Tracker panel. It's a JPanel, so it can hold other components.
    static class MoodTrackerPanel extends JPanel {
        // DATA STRUCTURE: An ArrayList to store the user's mood history. This acts as a time-series dataset.
        private final List<MoodLog> moodHistory = new ArrayList<>();

        // A private static inner class to define the structure for each mood entry.
        private static class MoodLog {
            // Stores the mood string (e.g., "Happy 😊").
            final String mood;
            // The constructor for a MoodLog object.
            MoodLog(String mood) {
                this.mood = mood;
            }
            // A helper method to get just the mood word (e.g., "Happy") without the emoji.
            public String getMood() {
                return mood.split(" ")[0];
            }
        }

        // Overrides the paintComponent method to draw a custom background.
        @Override
        protected void paintComponent(Graphics g) {
            // Calls the parent class's method to handle default painting.
            super.paintComponent(g);
            // Casts the Graphics object to Graphics2D for more advanced drawing capabilities.
            Graphics2D g2d = (Graphics2D) g;
            // Defines the two colors for our gradient.
            Color color1 = new Color(230, 245, 230); // Lighter Green
            Color color2 = new Color(144, 238, 144); // Light Green
            // Creates a GradientPaint object that transitions from color1 at the top to color2 at the bottom.
            GradientPaint gp = new GradientPaint(0, 0, color1, 0, getHeight(), color2);
            // Sets the Graphics2D object's paint to our new gradient.
            g2d.setPaint(gp);
            // Fills the entire panel with the gradient color.
            g2d.fillRect(0, 0, getWidth(), getHeight());
        }

        // The constructor for the MoodTrackerPanel.
        public MoodTrackerPanel() {
            // Sets the layout manager for this panel to BorderLayout.
            setLayout(new BorderLayout(10, 10));
            // Adds some empty space (padding) around the edges of the panel.
            setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

            //  UI Setup for Input 
            // A panel to hold the input components, using FlowLayout to arrange them in a row.
            JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
            // Makes the panel transparent so our custom gradient background is visible.
            inputPanel.setOpaque(false);
            // A label to prompt the user.
            JLabel label = new JLabel("How are you feeling today?");
            // Sets the font for the label.
            label.setFont(new Font("SansSerif", Font.BOLD, 22));
            // Adds the label to the input panel.
            inputPanel.add(label);

            // An array of strings containing the mood options for the user.
            String[] moods = {"Happy 😊", "Calm 😌", "Stressed 😫", "Sad 😢", "Energetic 💪", "Tired 😴"};
            // A JComboBox (dropdown menu) created from the moods array.
            JComboBox<String> moodBox = new JComboBox<>(moods);
            // Sets the font for the items in the dropdown.
            moodBox.setFont(new Font("SansSerif", Font.PLAIN, 18));
            // Adds the dropdown to the input panel.
            inputPanel.add(moodBox);

            // A button for the user to click to log their mood.
            JButton logMoodButton = new JButton("Log Mood");
            // Sets the font for the button's text.
            logMoodButton.setFont(new Font("SansSerif", Font.BOLD, 18));
            // Adds the button to the input panel.
            inputPanel.add(logMoodButton);

            //  UI Setup for Analysis 
            // A panel to hold the analysis buttons, using GridLayout to arrange them in a 2x1 grid.
            JPanel analysisPanel = new JPanel(new GridLayout(2, 1, 10, 10));
            // Makes this panel transparent as well.
            analysisPanel.setOpaque(false);
            // A button to trigger the streak calculation.
            JButton streakButton = new JButton("Calculate Longest Happy Streak");
            // A button to trigger the frequency calculation.
            JButton frequentButton = new JButton("Find Most Frequent Mood");
            // Adds the buttons to the analysis panel.
            analysisPanel.add(streakButton);
            analysisPanel.add(frequentButton);

            // Adds the input panel to the top (NORTH) of this MoodTrackerPanel.
            add(inputPanel, BorderLayout.NORTH);
            // Adds the analysis panel to the center.
            add(analysisPanel, BorderLayout.CENTER);

            // --- Event Handling ---
            // Adds an ActionListener to the log mood button. The code inside the lambda expression runs when the button is clicked.
            logMoodButton.addActionListener(e -> {
                // Gets the selected mood from the dropdown box.
                String selectedMood = (String) moodBox.getSelectedItem();
                // Creates a new MoodLog object and adds it to our history list.
                moodHistory.add(new MoodLog(selectedMood));
                // Shows a confirmation message to the user.
                JOptionPane.showMessageDialog(this, "Mood logged: " + selectedMood, "Success", JOptionPane.INFORMATION_MESSAGE);
            });

            // Adds an ActionListener to the streak button.
            streakButton.addActionListener(e -> {
                // Calls our streak-finding algorithm.
                int longestStreak = findLongestStreak("Happy");
                // Shows the result in a message dialog.
                JOptionPane.showMessageDialog(this, "Longest streak of feeling Happy: " + longestStreak + " entries.", "Analysis Result", JOptionPane.INFORMATION_MESSAGE);
            });

            // Adds an ActionListener to the frequency button.
            frequentButton.addActionListener(e -> {
                // Calls our frequency-finding algorithm.
                String mostFrequent = findMostFrequentMood();
                // Shows the result in a message dialog.
                JOptionPane.showMessageDialog(this, "Most frequent mood logged: " + mostFrequent, "Analysis Result", JOptionPane.INFORMATION_MESSAGE);
            });
        }

        // ALGORITHM 1: Finds the longest consecutive streak of a given mood.
        private int findLongestStreak(String mood) {
            // If there's no history, the streak is 0.
            if (moodHistory.isEmpty()) return 0;
            // A variable to store the longest streak found so far.
            int longestStreak = 0;
            // A variable to track the current consecutive streak.
            int currentStreak = 0;
            // Loop through each log in the history (Linear Scan O(n)).
            for (MoodLog log : moodHistory) {
                // If the current log's mood matches the one we're looking for...
                if (log.getMood().equalsIgnoreCase(mood)) {
                    // ...increment the current streak.
                    currentStreak++;
                } else {
                    // Otherwise, the streak is broken. See if the just-ended streak was the longest.
                    longestStreak = Math.max(longestStreak, currentStreak);
                    // Reset the current streak counter to 0.
                    currentStreak = 0;
                }
            }
            // After the loop, check one last time in case the longest streak was at the very end.
            return Math.max(longestStreak, currentStreak);
        }

        // ALGORITHM 2: Finds the most frequently logged mood.
        private String findMostFrequentMood() {
            // If there's no history, return "None".
            if (moodHistory.isEmpty()) return "None";
            // DATA STRUCTURE: A HashMap to store the frequency of each mood. Key is the mood (String), Value is the count (Integer).
            Map<String, Integer> frequencyMap = new HashMap<>();
            // Loop through each log in the history (O(n)).
            for (MoodLog log : moodHistory) {
                // For each mood, update its count in the HashMap. getOrDefault is a convenient way to handle the first time a mood is seen.
                frequencyMap.put(log.getMood(), frequencyMap.getOrDefault(log.getMood(), 0) + 1);
            }

            // Variables to track the most frequent mood found so far.
            String mostFrequent = "None";
            int maxCount = 0;
            // Loop through the entries in our frequency map.
            for (Map.Entry<String, Integer> entry : frequencyMap.entrySet()) {
                // If the count of the current entry is greater than the max count seen so far...
                if (entry.getValue() > maxCount) {
                    // ...update the max count and the most frequent mood.
                    maxCount = entry.getValue();
                    mostFrequent = entry.getKey();
                }
            }
            // Return the most frequent mood.
            return mostFrequent;
        }
    }

    //  DSA 2: Efficient Text Search with an Inverted Index 
    static class QuotePanel extends JPanel {
        // A 2D array to store the quotes and their authors.
        private final String[][] quotes = {
            {"\"The only way to do great work is to love what you do.\"", "Steve Jobs"},
            {"\"The mind is everything. What you think you become.\"", "Buddha"},
            {"\"The journey of a thousand miles begins with a single step.\"", "Lao Tzu"},
            {"\"Believe you can and you're halfway there.\"", "Theodore Roosevelt"}
        };

        // DATA STRUCTURE: The Inverted Index. A HashMap where the key is a word, and the value is a list of indices of quotes containing that word.
        private final Map<String, List<Integer>> invertedIndex = new HashMap<>();
        // A JTextArea to display the search results.
        private final JTextArea resultsArea = new JTextArea(10, 40);

        // Overrides paintComponent to draw a custom gradient background.
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            Color color1 = new Color(240, 255, 240); // Honeydew
            Color color2 = new Color(193, 225, 193); // Light Mint
            GradientPaint gp = new GradientPaint(0, 0, color1, getWidth(), getHeight(), color2);
            g2d.setPaint(gp);
            g2d.fillRect(0, 0, getWidth(), getHeight());
        }

        // Constructor for the QuotePanel.
        public QuotePanel() {
            // ALGORITHM: Build the inverted index as soon as the panel is created.
            buildInvertedIndex();
            // Sets the layout for the panel.
            setLayout(new BorderLayout(10, 10));
            // Adds padding around the panel.
            setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

            // A panel to hold all the top components (search and suggestions).
            JPanel topPanel = new JPanel();
            // Make the panel transparent.
            topPanel.setOpaque(false);
            // Sets the layout to arrange components vertically.
            topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));

            // A panel for the search bar and button.
            JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            // Make it transparent.
            searchPanel.setOpaque(false);
            // The text field where the user types their search query.
            JTextField searchField = new JTextField(20);
            // The button to initiate the search.
            JButton searchButton = new JButton("Search Quotes");
            
            // The label next to the search bar.
            JLabel searchLabel = new JLabel("Search for a word:");
            // Set the label's text color to dark gray for better contrast.
            searchLabel.setForeground(Color.DARK_GRAY);
            // Add components to the search panel.
            searchPanel.add(searchLabel);
            searchPanel.add(searchField);
            searchPanel.add(searchButton);
            
            // --- UI Enhancement: High-contrast, dark search field ---
            // Set the font for the text field.
            searchField.setFont(new Font("SansSerif", Font.BOLD, 16));
            // Set the background color to a dark gray.
            searchField.setBackground(new Color(60, 63, 65)); 
            // Set the text color to white.
            searchField.setForeground(Color.WHITE); 
            // Set the blinking cursor color to white.
            searchField.setCaretColor(Color.WHITE); 
            // Add a styled border.
            searchField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(85, 92, 94)),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
            ));
            
            // A panel for the clickable suggestion words.
            JPanel suggestionsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
            // Make it transparent.
            suggestionsPanel.setOpaque(false);
            // The label for the suggestions.
            JLabel suggestionLabel = new JLabel("Suggestions:");
            // Set its text color to dark gray.
            suggestionLabel.setForeground(Color.DARK_GRAY);
            // Add the label to the panel.
            suggestionsPanel.add(suggestionLabel);
            // An array of suggestion words.
            String[] suggestionWords = {"love", "mind", "journey", "work"};
            // Loop through the words to create a button for each.
            for (String word : suggestionWords) {
                JButton suggestionButton = new JButton(word);
                // Add an ActionListener to each suggestion button.
                suggestionButton.addActionListener(e -> {
                    // When clicked, set the search field's text to the suggestion word.
                    searchField.setText(word);
                    // Programmatically "click" the main search button to perform the search.
                    searchButton.doClick();
                });
                // Add the suggestion button to its panel.
                suggestionsPanel.add(suggestionButton);
            }
            
            // Add the search and suggestion panels to the main top panel.
            topPanel.add(searchPanel);
            topPanel.add(suggestionsPanel);

            // --- UI setup for the Results Area ---
            // Makes the text area non-editable by the user.
            resultsArea.setEditable(false);
            // Sets the font for the results (larger and not italic for clarity).
            resultsArea.setFont(new Font("Serif", Font.PLAIN, 22));
            // Enables automatic line wrapping.
            resultsArea.setLineWrap(true);
            // Wraps lines at word boundaries.
            resultsArea.setWrapStyleWord(true);
            // Set a solid, light background color to ensure text is readable.
            resultsArea.setBackground(new Color(250, 255, 250));
            // Adds a styled border.
            resultsArea.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(60, 179, 113), 2), 
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
            ));
            // Sets the text color to black for maximum contrast.
            resultsArea.setForeground(Color.BLACK);

            // --- FINAL FIX for Button Visibility ---
            // Set the button's background to a dark color.
            searchButton.setBackground(new Color(45, 52, 54));
            // Set the button's text to white.
            searchButton.setForeground(Color.WHITE);
            // Set the button's font.
            searchButton.setFont(new Font("SansSerif", Font.BOLD, 14));
            // Force the button to be opaque, ensuring its background color is painted.
            searchButton.setOpaque(true);
            // Prevent the OS from painting its own default border, which can cause visibility issues.
            searchButton.setBorderPainted(false);

            // Add the top panel and the results area (inside a scroll pane) to the main QuotePanel.
            add(topPanel, BorderLayout.NORTH);
            add(new JScrollPane(resultsArea), BorderLayout.CENTER);

            // Add an ActionListener to the search button.
            searchButton.addActionListener(e -> {
                // Get the user's query, trim whitespace, and convert to lower case.
                String query = searchField.getText().trim().toLowerCase();
                // Call our efficient search algorithm.
                List<String> results = searchQuotes(query);
                // If no results were found, display a message.
                if (results.isEmpty()) {
                    resultsArea.setText("No quotes found containing the word: '" + query + "'");
                } else {
                    // Otherwise, join the results with newlines and display them.
                    resultsArea.setText(String.join("\n\n", results));
                }
            });
        }

        // ALGORITHM: This method builds the inverted index data structure.
        private void buildInvertedIndex() {
            // Loop through each quote in our dataset.
            for (int i = 0; i < quotes.length; i++) {
                // Pre-process the quote: remove punctuation, convert to lower case, and split into words.
                String[] words = quotes[i][0].replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");
                // Loop through each word in the quote.
                for (String word : words) {
                    // Get the list of indices for this word from the index. If it's not there, create a new empty list.
                    List<Integer> indices = invertedIndex.getOrDefault(word, new ArrayList<>());
                    // To avoid duplicates, only add the index if it's not already in the list.
                    if (!indices.contains(i)) {
                        indices.add(i);
                    }
                    // Put the updated list back into the HashMap.
                    invertedIndex.put(word, indices);
                }
            }
        }

        // ALGORITHM: This method performs the search using the inverted index.
        private List<String> searchQuotes(String word) {
            // This is the core of the algorithm: a single, highly efficient HashMap lookup (O(1) on average).
            List<Integer> indices = invertedIndex.get(word);
            // If the word is not in our index, return an empty list.
            if (indices == null) {
                return new ArrayList<>();
            }
            // Use a Java Stream to map the found indices back to their full quote strings.
            return indices.stream()
                .map(index -> quotes[index][0] + " - " + quotes[index][1])
                .collect(Collectors.toList());
        }
    }

    //  DSA 3: 0/1 Knapsack Problem for Optimization
    static class StressReliefPanel extends JPanel {
        // A list to hold all available stress relief activities.
        private final List<Activity> activities = new ArrayList<>();

        // A private class to structure the properties of each activity.
        private static class Activity {
            final String name; // Name of the activity.
            final int timeRequired; // This is the "weight" in the Knapsack problem.
            final int effectiveness; // This is the "value" in the Knapsack problem.

            // Constructor for the Activity class.
            Activity(String name, int time, int effectiveness) {
                this.name = name;
                this.timeRequired = time;
                this.effectiveness = effectiveness;
            }
        }

        // Overrides paintComponent to draw a custom gradient background.
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            Color color1 = new Color(230, 245, 230);
            Color color2 = new Color(144, 238, 144);
            GradientPaint gp = new GradientPaint(0, 0, color1, 0, getHeight(), color2);
            g2d.setPaint(gp);
            g2d.fillRect(0, 0, getWidth(), getHeight());
        }

        // Constructor for the StressReliefPanel.
        public StressReliefPanel() {
            // Populate our list of activities with their respective time and effectiveness scores.
            activities.add(new Activity("Box Breathing", 5, 25));
            activities.add(new Activity("4-7-8 Breathing", 7, 40));
            activities.add(new Activity("Mindful Grounding", 10, 60));
            activities.add(new Activity("Positive Focus", 12, 75));
            activities.add(new Activity("Listen to Rain", 15, 90));
            
            // Set the main layout and add padding.
            setLayout(new BorderLayout(10, 20));
            setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

            // A panel for the user input components.
            JPanel inputPanel = new JPanel(new FlowLayout());
            // Make the panel transparent.
            inputPanel.setOpaque(false);
            // A text field for the user to enter their available time.
            JTextField timeField = new JTextField("15", 5);
            // The button to trigger the optimization algorithm.
            JButton optimizeButton = new JButton("Find Optimal Routine");

            // The label for the time field.
            JLabel timeLabel = new JLabel("Available time (minutes):");
            // Set the label's text color to dark gray.
            timeLabel.setForeground(Color.DARK_GRAY);
            // Add components to the input panel.
            inputPanel.add(timeLabel);
            inputPanel.add(timeField);
            inputPanel.add(optimizeButton);

            //  UI Enhancement: High-contrast, dark time field 
            // Set the font and styling for the time input field.
            timeField.setFont(new Font("SansSerif", Font.BOLD, 14));
            timeField.setBackground(new Color(60, 63, 65));
            timeField.setForeground(Color.WHITE);
            timeField.setCaretColor(Color.WHITE);
            timeField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(85, 92, 94)),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
            ));

            // The text area to display the resulting optimal routine.
            JTextArea routineArea = new JTextArea(10, 40);
            // Make it non-editable.
            routineArea.setEditable(false);
            // Set its font.
            routineArea.setFont(new Font("SansSerif", Font.PLAIN, 16));
            // Set a solid, light background to ensure readability.
            routineArea.setBackground(new Color(250, 255, 250));
            // Add a styled border.
            routineArea.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(34, 139, 34), 2),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
            ));
            // Set the text color to black.
            routineArea.setForeground(Color.BLACK);
            
            // --- FINAL FIX for Button Visibility ---
            // Set the button's background to a dark color.
            optimizeButton.setBackground(new Color(45, 52, 54));
            // Set the button's text to white.
            optimizeButton.setForeground(Color.WHITE);
            // Set the button's font.
            optimizeButton.setFont(new Font("SansSerif", Font.BOLD, 14));
            // Force the button to be opaque to ensure its background color is painted.
            optimizeButton.setOpaque(true);
            // Prevent the OS from painting its own default border.
            optimizeButton.setBorderPainted(false);
            
            // A wrapper panel that uses GridBagLayout to center its content.
            JPanel centerWrapper = new JPanel(new GridBagLayout());
            // Make the wrapper transparent.
            centerWrapper.setOpaque(false);
            // Add the results area (inside a scroll pane) to the center of the wrapper.
            centerWrapper.add(new JScrollPane(routineArea)); 
            
            // Add the input panel to the top of the main panel.
            add(inputPanel, BorderLayout.NORTH);
            // Add the center-aligned wrapper panel to the center of the main panel.
            add(centerWrapper, BorderLayout.CENTER); 

            // Add an ActionListener to the optimize button.
            optimizeButton.addActionListener(e -> {
                try {
                    // Get the available time from the text field and parse it as an integer.
                    int availableTime = Integer.parseInt(timeField.getText());
                    // Call our Knapsack algorithm to get the optimal routine.
                    List<Activity> optimalRoutine = findOptimalRoutine(availableTime);
                    
                    // Use a StringBuilder to construct the output string.
                    StringBuilder result = new StringBuilder("Optimal Stress Relief Routine:\n\n");
                    // Variables to sum up the total time and effectiveness.
                    int totalTime = 0;
                    int totalEffectiveness = 0;

                    // Loop through the activities in the optimal routine.
                    for (Activity activity : optimalRoutine) {
                        // Append the details of each activity to the result string.
                        result.append(String.format("- %s (%d mins, %d effectiveness)\n",
                            activity.name, activity.timeRequired, activity.effectiveness));
                        // Add to the totals.
                        totalTime += activity.timeRequired;
                        totalEffectiveness += activity.effectiveness;
                    }
                    // Append the final totals to the result string.
                    result.append(String.format("\nTotal Time: %d mins\nTotal Effectiveness: %d",
                        totalTime, totalEffectiveness));

                    // Set the text of the results area to our formatted string.
                    routineArea.setText(result.toString());

                } catch (NumberFormatException ex) {
                    // If the user enters non-numeric text, show an error message.
                    JOptionPane.showMessageDialog(this, "Please enter a valid number for time.", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            });
        }

        // ALGORITHM: Solves the 0/1 Knapsack problem using Dynamic Programming.
        private List<Activity> findOptimalRoutine(int maxTime) {
            // Get the number of available activities.
            int n = activities.size();
            // Create the DP table. dp[i][t] will store the max effectiveness using the first 'i' activities with 't' time.
            int[][] dp = new int[n + 1][maxTime + 1];

            // Build the DP table bottom-up.
            // Loop through each activity.
            for (int i = 1; i <= n; i++) {
                // Get the current activity (note the i-1 index because lists are 0-indexed).
                Activity currentActivity = activities.get(i - 1);
                // Loop through each possible time from 1 to maxTime.
                for (int t = 1; t <= maxTime; t++) {
                    // If the current activity's time requirement is less than or equal to the current time 't'...
                    if (currentActivity.timeRequired <= t) {
                        // ...we have a choice: either include the activity or not.
                        // We take the maximum of (value of this activity + value from remaining time) OR (value of not including this activity).
                        dp[i][t] = Math.max(
                            currentActivity.effectiveness + dp[i - 1][t - currentActivity.timeRequired],
                            dp[i - 1][t]
                        );
                    } else {
                        // If we don't have enough time for this activity, we can't include it.
                        // The value is the same as the value without this activity.
                        dp[i][t] = dp[i - 1][t];
                    }
                }
            }

            // The bottom-right cell of the DP table contains the maximum possible effectiveness.
            // Now, we backtrack through the table to find which activities were included.
            List<Activity> routine = new ArrayList<>();
            int t = maxTime;
            // Start from the last activity and work backwards.
            for (int i = n; i > 0 && dp[i][t] > 0; i--) {
                // If the value at dp[i][t] is different from dp[i-1][t], it means activity 'i' was included.
                if (dp[i][t] != dp[i - 1][t]) {
                    // Get the activity that was included.
                    Activity includedActivity = activities.get(i - 1);
                    // Add it to our routine list.
                    routine.add(includedActivity);
                    // Subtract its time from our remaining time and continue backtracking.
                    t -= includedActivity.timeRequired;
                }
            }
            // Return the list of activities that make up the optimal routine.
            return routine;
        }
    }

    // --- DSA 4: Graph Traversal for Stateful Conversation ---
    static class ChatPanel extends JPanel {
        // A JTextPane is used to render the styled HTML messages.
        private final JTextPane chatPane;
        // The input field for the user to type their message.
        private final JTextField inputField;
        // An instance of our Chatbot logic class.
        private final Chatbot chatbot = new Chatbot();
        // The document and editor kit are needed to programmatically insert HTML into the JTextPane.
        private final HTMLDocument doc;
        private final HTMLEditorKit editorKit;

        // Constructor for the ChatPanel.
        public ChatPanel() {
            // Set the layout and add padding.
            setLayout(new BorderLayout(10, 10));
            setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            // Set a light background color.
            setBackground(new Color(240, 255, 240)); // Honeydew background

            // Initialize the JTextPane.
            chatPane = new JTextPane();
            // Make it non-editable by the user.
            chatPane.setEditable(false);
            // Set its content type to handle HTML.
            chatPane.setContentType("text/html");
            // Get the editor kit and document for later use.
            editorKit = (HTMLEditorKit) chatPane.getEditorKit();
            doc = (HTMLDocument) chatPane.getDocument();
            
            // Add the chat pane to a scroll pane so it can scroll if the content is long.
            add(new JScrollPane(chatPane), BorderLayout.CENTER);

            // A panel for the input field and send button.
            JPanel inputPanel = new JPanel(new BorderLayout(10, 0));
            // Make it transparent.
            inputPanel.setOpaque(false);
            // Initialize the input field.
            inputField = new JTextField();
            // Set its font.
            inputField.setFont(new Font("SansSerif", Font.PLAIN, 16));
            // Add an ActionListener so the user can press Enter to send.
            inputField.addActionListener(this::sendMessage);
            // Add the field to the center of the input panel.
            inputPanel.add(inputField, BorderLayout.CENTER);
            
            // The send button.
            JButton sendButton = new JButton("➤");
            // Set its font.
            sendButton.setFont(new Font("Arial", Font.BOLD, 18));
            // Add an ActionListener so the user can click it to send.
            sendButton.addActionListener(this::sendMessage);
            // Add the button to the right (EAST) of the input panel.
            inputPanel.add(sendButton, BorderLayout.EAST);
            
            // Add the input panel to the bottom (SOUTH) of the main ChatPanel.
            add(inputPanel, BorderLayout.SOUTH);

            // Display the chatbot's initial greeting message.
            appendMessage(chatbot.getCurrentResponse(), false);
        }

        // This method is called when the user presses Enter or clicks the send button.
        private void sendMessage(ActionEvent event) {
            // Get the text from the input field and remove leading/trailing whitespace.
            String userInput = inputField.getText().trim();
            // Only process if the input is not empty.
            if (!userInput.isEmpty()) {
                // Display the user's message in the chat pane.
                appendMessage(userInput, true);
                // Process the user's input through the chatbot's logic.
                chatbot.processInput(userInput);
                // Display the chatbot's response.
                appendMessage(chatbot.getCurrentResponse(), false);
                // Clear the input field for the next message.
                inputField.setText("");
            }
        }
        
        // A helper method to add a styled HTML message to the chat pane.
        private void appendMessage(String message, boolean isUser) {
            try {
                // Determine the alignment and colors based on whether the message is from the user or the bot.
                String alignment = isUser ? "right" : "left";
                String bgColor = isUser ? "#007bff" : "#e9e9eb"; // Blue for user, gray for bot
                String textColor = isUser ? "white" : "black";

                // Construct the HTML string for a message bubble.
                String html = "<div style='text-align: " + alignment + "; margin-top: 5px; margin-bottom: 5px;'>"
                            + "<span style='background-color: " + bgColor + "; color: " + textColor + "; padding: 8px 12px; border-radius: 15px;'>"
                            + "<b>" + message + "</b>"
                            + "</span></div>";

                // Insert the HTML into the document at the end.
                editorKit.insertHTML(doc, doc.getLength(), html, 0, 0, null);
                // Automatically scroll the chat pane to the bottom to show the latest message.
                chatPane.setCaretPosition(doc.getLength());
            } catch (Exception e) {
                // If there's an error inserting HTML, print it to the console.
                e.printStackTrace();
            }
        }
    }

    // This class contains the logic for the chatbot.
    static class Chatbot {
        // DATA STRUCTURE: A HashMap to represent the conversation graph. The key is the node ID (Integer), and the value is the ConversationNode object.
        private final Map<Integer, ConversationNode> conversationGraph = new HashMap<>();
        // This variable keeps track of the chatbot's current position (state) in the graph.
        private ConversationNode currentNode;

        // A private class to define the structure of a node in our conversation graph.
        private static class ConversationNode {
            final String response; // The message the chatbot says at this node.
            // The edges from this node. Key is a user keyword (String), Value is the ID of the next node (Integer).
            final Map<String, Integer> edges = new HashMap<>();

            // Constructor for a ConversationNode.
            ConversationNode(String response) {
                this.response = response;
            }

            // A helper method to add an edge from this node to another.
            void addEdge(String keyword, int nextNodeId) {
                edges.put(keyword, nextNodeId);
            }
        }

        // The constructor for the Chatbot.
        public Chatbot() {
            // Build the graph structure.
            buildConversationGraph();
            // Set the starting point of the conversation to the root node (ID 0).
            currentNode = conversationGraph.get(0);
        }

        // ALGORITHM: This method defines the structure of the conversation graph.
        private void buildConversationGraph() {
            // Node 0: The initial greeting.
            ConversationNode start = new ConversationNode("Hello! How can I help you? (Try: 'feeling sad' or 'stressed')");
            // Add edges from this node. If user says "sad", go to node 1. If "stressed", go to node 2.
            start.addEdge("sad", 1);
            start.addEdge("stressed", 2);
            // Add the node to the graph.
            conversationGraph.put(0, start);

            // Node 1: The response to feeling sad.
            ConversationNode sadResponse = new ConversationNode("I'm sorry. Would you like a quote or an exercise? (Try: 'quote' or 'exercise')");
            // Add edges for the user's possible replies.
            sadResponse.addEdge("quote", 3);
            sadResponse.addEdge("exercise", 4);
            // Add the node to the graph.
            conversationGraph.put(1, sadResponse);

            // Node 2: The response to feeling stressed.
            ConversationNode stressedResponse = new ConversationNode("Stress is tough. I recommend the 'Stress Relief' tab. Or I can suggest a quick exercise. (Try: 'suggest')");
            stressedResponse.addEdge("suggest", 4);
            conversationGraph.put(2, stressedResponse);
            
            // Node 3: A leaf node offering a quote.
            ConversationNode quoteSuggestion = new ConversationNode("The 'Quotes' tab has lovely words. Anything else?");
            // Edge to go back to the start if the user wants more help.
            quoteSuggestion.addEdge("yes", 0);
            conversationGraph.put(3, quoteSuggestion);

            // Node 4: A leaf node offering an exercise.
            ConversationNode exerciseSuggestion = new ConversationNode("Try 4-7-8 breathing. It's a great tranquilizer. Anything else?");
            exerciseSuggestion.addEdge("yes", 0);
            conversationGraph.put(4, exerciseSuggestion);

            // Node 99: A fallback node for when the chatbot doesn't understand.
            ConversationNode fallback = new ConversationNode("I'm listening. You can always start over by saying 'hello'.");
            fallback.addEdge("hello", 0);
            conversationGraph.put(99, fallback);
        }
        
        // ALGORITHM: This method processes user input by traversing the graph.
        public void processInput(String userInput) {
            // Convert the input to lower case for case-insensitive matching.
            String lowerInput = userInput.toLowerCase();
            // A variable to store the ID of the next node. Initialize to -1 (no path found).
            int nextNodeId = -1;

            // Loop through all the possible keywords (edges) from the current node.
            for (String keyword : currentNode.edges.keySet()) {
                // If the user's input contains a keyword...
                if (lowerInput.contains(keyword)) {
                    // ...get the ID of the next node and stop searching.
                    nextNodeId = currentNode.edges.get(keyword);
                    break;
                }
            }
            
            // If we found a valid path (nextNodeId is not -1 and the node exists in the graph)...
            if (nextNodeId != -1 && conversationGraph.containsKey(nextNodeId)) {
                // ...move the chatbot to that new node (state).
                currentNode = conversationGraph.get(nextNodeId);
            } else {
                // Otherwise, move to the fallback node (ID 99).
                currentNode = conversationGraph.get(99);
            }
        }

        // A public method to get the response from the chatbot's current node.
        public String getCurrentResponse() {
            return currentNode.response;
        }
    }
}

