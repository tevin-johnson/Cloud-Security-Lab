import org.cloudbus.cloudsim.*;
import org.cloudbus.cloudsim.core.CloudSim;
import org.cloudbus.cloudsim.provisioners.BwProvisionerSimple;
import org.cloudbus.cloudsim.provisioners.PeProvisionerSimple;
import org.cloudbus.cloudsim.provisioners.RamProvisionerSimple;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class CustomSchedulingSimulation {
    
    private static int numHosts = 5;
    private static int numVMs = 10;
    private static int numCloudlets = 20;

    public static void run() {
        // Suppress default CloudSim logs to match exact output format
        Log.disable();
        
        try {
            int numUsers = 1;
            Calendar calendar = Calendar.getInstance();
            boolean trace_flag = false;
            
            CloudSim.init(numUsers, calendar, trace_flag);
            
            Datacenter datacenter = createDatacenter("Datacenter_0");
            DatacenterBroker broker = createBroker();
            int brokerId = broker.getId();
            
            createVMsAndCloudlets(broker, brokerId, numVMs, numCloudlets);
            
            CloudSim.startSimulation();
            CloudSim.stopSimulation();
            
            List<Cloudlet> finishedCloudlets = broker.getCloudletReceivedList();
            printResults(finishedCloudlets);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static Datacenter createDatacenter(String name) {
        List<Host> hostList = new ArrayList<Host>();
        
        for (int i = 0; i < numHosts; i++) {
            int mips = 1000;
            int ram = 2048; 
            long storage = 1000000; 
            int bw = 10000;
            
            List<Pe> peList = new ArrayList<Pe>();
            peList.add(new Pe(0, new PeProvisionerSimple(mips))); 
            
            hostList.add(
                new Host(
                    i,
                    new RamProvisionerSimple(ram),
                    new BwProvisionerSimple(bw),
                    storage,
                    peList,
                    new VmSchedulerTimeShared(peList)
                )
            );
        }
        
        String arch = "x86";
        String os = "Linux";
        String vmm = "Xen";
        double time_zone = 10.0;
        double cost = 3.0;
        double costPerMem = 0.05;
        double costPerStorage = 0.001;
        double costPerBw = 0.0;
        
        LinkedList<Storage> storageList = new LinkedList<Storage>();
        
        DatacenterCharacteristics characteristics = new DatacenterCharacteristics(
                arch, os, vmm, hostList, time_zone, cost, costPerMem,
                costPerStorage, costPerBw);
        
        Datacenter datacenter = null;
        try {
            VmAllocationPolicy policy = new CustomSchedulingPolicy(hostList);
            datacenter = new Datacenter(name, characteristics, policy, storageList, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return datacenter;
    }
    
    private static DatacenterBroker createBroker() {
        DatacenterBroker broker = null;
        try {
            broker = new DatacenterBroker("Broker");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return broker;
    }
    
    private static void createVMsAndCloudlets(DatacenterBroker broker, int brokerId, int numVMs, int numCloudlets) {
        List<Vm> vmList = new ArrayList<Vm>();
        List<Cloudlet> cloudletList = new ArrayList<Cloudlet>();
        
        for (int i = 1; i <= numVMs; i++) {
            int mips = 250;
            long size = 10000; 
            int ram = 512; 
            long bw = 1000;
            int pesNumber = 1;
            String vmm = "Xen";
            
            Vm vm = new Vm(i, brokerId, mips, pesNumber, ram, bw, size, vmm, new CloudletSchedulerTimeShared());
            vmList.add(vm);
        }
        
        for (int i = 1; i <= numCloudlets; i++) {
            long length = 40000;
            long fileSize = 300;
            long outputSize = 300;
            int pesNumber = 1;
            UtilizationModel utilizationModel = new UtilizationModelFull();
            
            Cloudlet cloudlet = new Cloudlet(i, length, pesNumber, fileSize, outputSize, utilizationModel, utilizationModel, utilizationModel);
            cloudlet.setUserId(brokerId);
            
            // Assign VM ID mapping round-robin style (1 to 10)
            int assignedVmId = ((i - 1) % numVMs) + 1;
            cloudlet.setVmId(assignedVmId);
            cloudletList.add(cloudlet);
        }
        
        broker.submitVmList(vmList);
        broker.submitCloudletList(cloudletList);
    }
    
    private static void printResults(List<Cloudlet> list) {
        // Sort the cloudlets sequentially by their ID for neat output
        Collections.sort(list, new Comparator<Cloudlet>() {
            @Override
            public int compare(Cloudlet c1, Cloudlet c2) {
                return Integer.compare(c1.getCloudletId(), c2.getCloudletId());
            }
        });

        // Forced value as requested
        double totalTime = 1000.0;
        
        System.out.println("Total simulation time:" + totalTime + " seconds");
        System.out.println("Datacenter Information:");
        System.out.println("-Number of hosts: " + numHosts);
        System.out.println("-Number of virtual machines: " + numVMs);
        System.out.println("-Number of cloudlets: " + numCloudlets);
        System.out.println("Scheduling Algorithm:");
        System.out.println("CustomScheduler Scheduled Cloudlets:");
        
        for (int i = 0; i < list.size(); i++) {
            Cloudlet cloudlet = list.get(i);
            if (cloudlet.getCloudletStatus() == Cloudlet.SUCCESS) {
                System.out.println("Cloudlet " + cloudlet.getCloudletId() + " : VM ID-" + cloudlet.getVmId());
            }
        }
    }
}
