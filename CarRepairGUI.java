import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Arrays;

public class CarRepairGUI extends JFrame {

    private JComboBox<String> comboBox;
    private JList<String> listBox;
    private JTextArea detailsArea;
    private JButton yesButton;
    private JButton noButton;
    private Map<String, CarProblem> carProblems = new HashMap<>();

    private class CarProblem {
        String description;
        List<String> steps;
        String details;

        public CarProblem(String description, List<String> steps, String details) {
            this.description = description;
            this.steps = steps;
            this.details = details;
        }
    }

    public CarRepairGUI() {
        setTitle("Car Repair Diagnostic System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());

        // Adding car problems
        carProblems.put("Select the Issue", new CarProblem("Select the Issue", Arrays.asList(""), ""));
        carProblems.put("Engine Not Starting", new CarProblem("Engine Not Starting", Arrays.asList(
                "1. Check battery connections: Ensure they are clean and tight.",
                "2. Test battery voltage: Should be above 12.4V. Use a multimeter.",
                "3. Check fuel level: Make sure you have enough fuel in the tank.",
                "4. Inspect spark plugs: Look for fouling or damage.",
                "5. Test starter motor: Listen for clicking sounds when turning the key."),
                "Engine Not Starting - Detailed Information\n\n" +
                        "A non-starting engine can be frustrating. Here's a detailed breakdown of potential causes:\n\n"
                        +
                        "- Battery Issues: Corrosion on terminals, loose connections, or a weak/dead battery.\n" +
                        "- Starter Motor: Faulty starter solenoid or motor can prevent engine cranking.\n" +
                        "- Fuel System: Problems with the fuel pump, fuel filter, or injectors.\n" +
                        "- Ignition System: Issues with spark plugs, ignition coil, or timing.\n\n" +
                        "If the engine still won't start, consult a mechanic."));
        carProblems.put("Brake Problems", new CarProblem("Brake Problems", Arrays.asList(
                "1. Check brake fluid level.",
                "2. Inspect brake pads for thickness and wear.",
                "3. Test brake pedal feel.",
                "4. Look for brake fluid leaks.",
                "5. Check brake rotors for scoring or warping."),
                "Brake Problems - Detailed Information\n\n" +
                        "Brake issues include worn pads, air in lines, or fluid leaks. Immediate attention is necessary."));
        carProblems.put("Tire Issues", new CarProblem("Tire Issues", Arrays.asList(
                "1. Check tire pressure.",
                "2. Inspect for punctures.",
                "3. Check tread depth.",
                "4. Look for uneven wear.",
                "5. Inspect for bulges."),
                "Tire Issues - Detailed Information\n\n" +
                        "Low pressure, punctures, or uneven wear can cause tire problems. Regular checks are essential."));
        carProblems.put("Overheating", new CarProblem("Overheating", Arrays.asList(
                "1. Check coolant level.",
                "2. Inspect radiator for leaks.",
                "3. Check radiator fan operation.",
                "4. Inspect hoses for cracks.",
                "5. Test thermostat."),
                "Overheating - Detailed Information\n\n" +
                        "Common causes include low coolant, faulty radiator, or a stuck thermostat."));
        carProblems.put("Strange Noises", new CarProblem("Strange Noises", Arrays.asList(
                "1. Identify noise location.",
                "2. Check belts for wear.",
                "3. Inspect exhaust for leaks.",
                "4. Test wheel bearings.",
                "5. Check suspension components."),
                "Strange Noises - Detailed Information\n\n" +
                        "Unusual noises can indicate belt issues, exhaust leaks, or suspension problems."));

        // Initialize UI components
        comboBox = new JComboBox<>(carProblems.keySet().toArray(new String[0]));
        listBox = new JList<>();
        detailsArea = new JTextArea();
        detailsArea.setEditable(false);
        detailsArea.setLineWrap(true);
        detailsArea.setWrapStyleWord(true);
        JScrollPane detailsScrollPane = new JScrollPane(detailsArea);
        yesButton = new JButton("Issue Fixed");
        noButton = new JButton("Need Professional Help");

        Font font = new Font("Arial", Font.PLAIN, 18);
        comboBox.setFont(font);
        listBox.setFont(font);
        detailsArea.setFont(font);
        yesButton.setFont(font);
        noButton.setFont(font);

        // Layout configuration
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(yesButton);
        buttonPanel.add(noButton);
        mainPanel.add(comboBox, BorderLayout.NORTH);
        mainPanel.add(new JScrollPane(listBox), BorderLayout.CENTER);
        mainPanel.add(detailsScrollPane, BorderLayout.SOUTH);
        mainPanel.add(buttonPanel, BorderLayout.AFTER_LAST_LINE);
        add(mainPanel);

        // Action Listeners
        comboBox.addActionListener(e -> {
            String selectedProblem = (String) comboBox.getSelectedItem();
            if (selectedProblem != null) {
                CarProblem problem = carProblems.get(selectedProblem);
                DefaultListModel<String> listModel = new DefaultListModel<>();
                for (String step : problem.steps) {
                    listModel.addElement(step);
                }
                listBox.setModel(listModel);
                detailsArea.setText(problem.details);
            }
        });

        yesButton.addActionListener(e -> JOptionPane.showMessageDialog(CarRepairGUI.this,
                "Great! Your car issue has been resolved.\nDrive safely!", "Success",
                JOptionPane.INFORMATION_MESSAGE));

        noButton.addActionListener(e -> JOptionPane.showMessageDialog(CarRepairGUI.this,
                "Please visit our service center:\n\nAuto Care Center\n123 Mechanic Street\nPhone: (555) 123-4567\n\nOpen Mon-Sat: 8AM - 6PM",
                "Professional Service Required", JOptionPane.INFORMATION_MESSAGE));

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CarRepairGUI::new);
    }
}
