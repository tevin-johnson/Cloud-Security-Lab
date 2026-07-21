import java.util.Scanner;

public class CloudSecurityLab {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("========== Cloud Security Lab ==========");
        System.out.println("Select the experiment to perform:");
        System.out.println("1. Custom Scheduling Simulation");
        System.out.println("2. Resource Management Simulation");
        System.out.println("3. Log Forensics Simulation");
        System.out.println("4. Secure File Sharing Simulation");
        System.out.print("Enter choice (1-4): ");
        
        if (scanner.hasNextInt()) {
            int choice = scanner.nextInt();
            System.out.println("----------------------------------------");
            switch (choice) {
                case 1:
                    CustomSchedulingSimulation.run();
                    break;
                case 2:
                    ResourceManagementSimulation.run();
                    break;
                case 3:
                    LogForensicsSimulation.run();
                    break;
                case 4:
                    SecureFileSharingSimulation.run();
                    break;
                default:
                    System.out.println("Invalid choice. Exiting.");
            }
        } else {
            System.out.println("Invalid input. Please enter a number.");
        }
        
        scanner.close();
    }
}
