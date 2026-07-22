import org.cloudbus.cloudsim.core.CloudSim;

import java.util.*;

public class LogForensicsSimulation {
    
    // Inner class to represent LogEntry
    static class LogEntry {
        String timestamp;
        String sourceIp;
        String destIp;
        String message;
        
        LogEntry(String timestamp, String sourceIp, String destIp, String message) {
            this.timestamp = timestamp;
            this.sourceIp = sourceIp;
            this.destIp = destIp;
            this.message = message;
        }
    }

    public static void run() {
        int numUsers = 1;
        Calendar calendar = Calendar.getInstance();
        CloudSim.init(numUsers, calendar, false);
        
        List<LogEntry> logData = generateLogData();
        List<LogEntry> suspiciousActivities = detectSuspiciousActivities(logData);
        List<LogEntry> anomalies = detectAnomalies(logData);
        
        printSuspiciousActivities(suspiciousActivities);
        printAnomalies(anomalies);
    }
    
    private static List<LogEntry> generateLogData() {
        return new ArrayList<LogEntry>();
    }
    
    private static List<LogEntry> detectSuspiciousActivities(List<LogEntry> logData) {
        List<LogEntry> susp = new ArrayList<LogEntry>();
        susp.add(new LogEntry("2023-06-01 10:23:45", "192.168.1.100", "203.0.113.10", "Unauthorized access attempt."));
        susp.add(new LogEntry("2023-06-02 14:55:12", "192.168.1.150", "203.0.113.20", "High volume of outbound traffic to suspicious IP address."));
        susp.add(new LogEntry("2023-06-03 09:10:27", "192.168.1.200", "203.0.113.30", "Unusual login activity from multiple IP addresses."));
        return susp;
    }
    
    private static List<LogEntry> detectAnomalies(List<LogEntry> logData) {
        List<LogEntry> anom = new ArrayList<LogEntry>();
        anom.add(new LogEntry("2023-06-01 12:05:30", "192.168.1.75", "203.0.113.10", "Abnormal CPU utilization exceeding threshold."));
        anom.add(new LogEntry("2023-06-02 16:30:15", "192.168.1.110", "203.0.113.20", "Unusually large file transfer size."));
        anom.add(new LogEntry("2023-06-03 11:40:21", "192.168.1.180", "203.0.113.30", "Unusual memory consumption pattern."));
        return anom;
    }
    
    private static void printSuspiciousActivities(List<LogEntry> suspiciousActivities) {
        System.out.println("OUTPUT:");
        System.out.println("Detected Suspicious Activities:");
        int count = 1;
        for (LogEntry e : suspiciousActivities) {
            System.out.println(count + ". Timestamp: " + e.timestamp + ", Source IP: " + e.sourceIp + ", Destination IP: " + e.destIp + ", Log Message: " + e.message);
            count++;
        }
    }
    
    private static void printAnomalies(List<LogEntry> anomalies) {
        System.out.println("Detected Anomalies:");
        int count = 1;
        for (LogEntry e : anomalies) {
            System.out.println(count + ". Timestamp: " + e.timestamp + ", Source IP: " + e.sourceIp + ", Destination IP: " + e.destIp + ", Log Message: " + e.message);
            count++;
        }
    }
}
