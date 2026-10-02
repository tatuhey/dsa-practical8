import java.io.*;
import java.util.*;

public class main {
    public static void main(String[] args) {
        int sel = 100000;
        Scanner sc = new Scanner(System.in);

        while(sel != 0) {
            System.out.println("Select menu:");
            System.out.println("1. Manual\n2. RandomNames7000.csv\n0. exit");
            
            try {
                sel = sc.nextInt();
                switch(sel) {
                    case 1:
                        // manual stuf
                        manualSelection(sc);
                        break;
                    case 2:
                        // randomnames7000csv
                        autoSelection2(sc);
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Wrong selection");
                }
            }  catch (InputMismatchException e) {
                sel = 100000;
                sc.nextLine();
                System.out.println(e + ". Please input selection properly.");
                
            } catch (NoSuchElementException e2) {
                System.out.println(e2);
            }
        
        }

        sc.close();
    }

    public static void manualSelection(Scanner sc) {
        int size = 0;
        String filename;

        try {
            System.out.print("Insert size of heap: ");
            size = sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.println(e + ". Please input size of heap properly.");
        }

        System.out.println("Insert the file name: <filename.csv>");
        filename = sc.nextLine();

        DSAHeap heapArr = new DSAHeap(size);
        readFile(filename, heapArr);

        submenu(sc, heapArr, filename);
    }

    public static void autoSelection2(Scanner sc) {
        DSAHeap heapArr = new DSAHeap(7000);

        readFile("RandomNames7000.csv", heapArr);

        submenu(sc, heapArr, "RandomNames7000.csv");
    }


    public static void submenu(Scanner sc, DSAHeap arr, String filname) {
        int sel = 100000;
        int prio;
        Object val;
        int count;

        while(sel != 0) {
            System.out.println("Current file being worked on: " + filname);
            System.out.println("Select menu:");
            System.out.println("1. Add to heap\n2. Remove from heap\n3. Heap Sort\n4. Display\n5. Export\n0. exit");
            
            try {
                sel = sc.nextInt();
                switch(sel) {
                    case 1:
                        System.out.println("Add an entry");
                        sc.nextLine(); // to clean up terminal

                        System.out.print("Input priority: ");
                        prio = sc.nextInt();    

                        System.out.print("Input value: ");
                        val = sc.nextLine();

                        try {
                            arr.add(prio, val);
                        } catch (NumberFormatException e) {
                            System.out.println("Please insert a proper key and value. " + e.getMessage());
                        }

                        break;
                    case 2:
                        System.out.println("Remove an entry");
                        System.out.print("This entry will be removed. ");

                        arr.displayTop();
                        arr.remove();
                        
                        break;
                    case 3:
                        System.out.print("Input count: ");
                        count = sc.nextInt();

                        try {
                            arr.heapSort(count);
                        } catch (NumberFormatException e) {
                            System.out.println("Please insert a proper key. " + e.getMessage());
                        }

                        break;
                    case 4:
                        System.out.println("Display all entries");
                        arr.display();

                        break;
                    case 5:
                        System.out.println("Exporting heap as a file");
                        sc.nextLine();

                        System.out.print("Insert filename: ");
                        String name = sc.nextLine();

                        arr.export(name);
                        
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Wrong selection");
                        

                }
            }  catch (InputMismatchException e) {
                sel = 100000;
                sc.nextLine();
                System.out.println(e + ". Please input selection properly.");
                
            } catch (NoSuchElementException e2) {
                System.out.println(e2);
            }
        
        }
    }

    public static void readFile(String pFilename, DSAHeap arr) {
        FileInputStream fileStream = null;
        InputStreamReader rdr;
        BufferedReader bufRdr;
        int lineNum;
        String line;
        try {
            fileStream = new FileInputStream(pFilename);
            rdr = new InputStreamReader(fileStream);
            bufRdr = new BufferedReader(rdr);
            lineNum = 0; 
            line = bufRdr.readLine();
            while(line != null)
            {
                lineNum++;
                //writeLog("readFile runs successfully");
                processLine(line, arr);
                line = bufRdr.readLine();
            }
                fileStream.close();
                
        }
        catch(IOException e) {
            if(fileStream != null) {
                try {
                    fileStream.close();
                }
                catch(IOException e2){}
                }
                System.out.println("Error during reading the file. " + e.getMessage());
                //writeLog("Error during reading the file. " + e.getMessage());
        }
    }

    public static void processLine(String row, DSAHeap arr) {
        String[] splitLine;

        splitLine = row.split(",");

        if(splitLine.length >= 2){
            try {
                int prio = Integer.parseInt(splitLine[0]);
                Object value = splitLine[1]; 

                arr.add(prio, value);            

            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Array out of bound" + e.getMessage());
            }
        } else
            System.out.println("Invalid CSV row: " + row);

    }
}