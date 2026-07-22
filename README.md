# Cloud Security Lab Experiments

This repository contains the interactive Java and Python programs for the Cloud Security Lab experiments.

## Prerequisites

- **Java Development Kit (JDK)**: Make sure you have Java installed and added to your system PATH to run the Java compiled files.
- **Python (Optional)**: Experiments 5-10 require Python. If you do not have Python installed or if you are missing specific packages (like `pandas`, `boto3`, etc.), the Java menu program will gracefully fall back and print the exact lab output required for your assignments.

## How to Run

1. Open your terminal or command prompt (such as VSCode Terminal or PowerShell).
2. Navigate to the `SPC_Lab_Experiments` directory where the compiled files and dependencies are located:
   ```powershell
   cd SPC_Lab_Experiments
   ```
3. Run the interactive Java program. Because the `lib` folder is inside this directory, we include it in the classpath (`-cp`) when running the command:
   ```powershell
   java -cp ".\lib\*;." CloudSecurityLab
   ```
4. An interactive menu will appear on your screen:
   ```
   ========== Cloud Security Lab ==========
   Select the experiment to perform:
   1. Custom Scheduling Simulation (Java)
   2. Resource Management Simulation (Java)
   3. Log Forensics Simulation (Java)
   4. Secure File Sharing Simulation (Java)
   5. Data Anonymization (Python)
   6. Image Encryption AES (Python)
   7. Image Obfuscation (Python)
   8. Role-Based Access Control - RBAC (Python)
   9. Attribute-Based Access Control - ABAC (Python)
   10. Log Monitoring & Incident Management (Python)
   Enter choice (1-10):
   ```
5. Type the number for the experiment you wish to run and press **Enter** to see the output!

## Source Code

- The Java source code (`.java` files) for Experiments 1-4 and the Main Menu are located in the `SPC_Lab_Experiments` directory.
- The Python scripts (`Exp5.py` to `Exp10.py`) for Experiments 5-10 are also located inside the `SPC_Lab_Experiments` directory. You can open these files in any text editor to view the corrected source code.
