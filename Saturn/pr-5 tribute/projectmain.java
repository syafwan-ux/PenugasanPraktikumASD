import java.util.*;
import java.io.*;

public class projectmain {
    private List<Employee> employees = new ArrayList<>();

    public static void main(String[] args) {
        projectmain company = new projectmain();
        company.readEmployeesFromFile("fileData.txt");

        Scanner s = new Scanner(System.in);
        int ch;

        do {
            System.out.println("1. DISPLAY");
            System.out.println("2. FIND");
            System.out.println("3. DELETE");
            System.out.println("4. UPDATE GAJI");
            System.out.println("5. UPDATE MASA KERJA");
            System.out.println("6. INSERT");
            System.out.println("7. GET TOTAL GAJI");
            System.out.println("Enter your choice");
            ch = s.nextInt();

            System.out.println();

            switch (ch) {
                case 1:
                    company.Display();
                    System.out.println();
                    break;
                case 2:
                    company.Find(s);
                    System.out.println();
                    break;
                case 3:
                    company.Delete(s);
                    System.out.println();
                    break;
                case 4:
                    company.UpdateGaji(s);
                    System.out.println();
                    break;
                case 5:
                    company.UpdateMasakerja(s);
                    System.out.println();
                    break;
                case 6:
                    company.Insert(s);
                    System.out.println();
                    break;
                case 7:
                    company.GetTotalGaji();
                    System.out.println();
                    break;
                default:
                    System.out.println("Invalid choice");
                    System.out.println();
            }
        } while (ch != 0);
    }

    private void readEmployeesFromFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                int empno = Integer.parseInt(parts[0]);
                String ename = parts[1];
                int salary = Integer.parseInt(parts[2]);
                String nationalId = parts[3];
                int workingTime = Integer.parseInt(parts[4]);
                employees.add(new Employee(empno, ename, salary, nationalId, workingTime));
            }
            Collections.sort(employees);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    private void writeEmployeesToFile(String filename) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (Employee e : employees) {
                bw.write(e.getEmpno() + " " + e.getEname() + " " + e.getSalary() + " " + e.getNationalId() + " " + e.getWorkingTime());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    private void Display() {
        for (Employee e : employees) {
            System.out.println(e);
        }
    }

    private void Find(Scanner s) {
        System.out.println("Enter employee number to find:");
        int empno = s.nextInt();
        int index = Collections.binarySearch(employees, new Employee(empno, "", 0, "", 0));
        if (index >= 0) {
            System.out.println(employees.get(index));
        } else {
            System.out.println("Employee not found.");
        }
    }

    private void Delete(Scanner s) {
        System.out.println("Enter employee number to delete:");
        int empno = s.nextInt();
        int index = Collections.binarySearch(employees, new Employee(empno, "", 0, "", 0));
        if (index >= 0) {
            employees.remove(index);
            System.out.println("Employee deleted successfully.");
            writeEmployeesToFile("fileData.txt");
        } else {
            System.out.println("Employee not found.");
        }
    }

    private void UpdateGaji(Scanner s) {
        System.out.println("Enter employee number to update salary:");
        int empno = s.nextInt();
        int index = Collections.binarySearch(employees, new Employee(empno, "", 0, "", 0));
        if (index >= 0) {
            System.out.println("Enter new salary:");
            int salary = s.nextInt();
            employees.get(index).setSalary(salary);
            System.out.println("Employee salary updated successfully.");
            writeEmployeesToFile("fileData.txt");
        } else {
            System.out.println("Employee not found.");
        }
    }

    private void UpdateMasakerja(Scanner s) {
        System.out.println("Enter employee number to update service period:");
        int empno = s.nextInt();
        int index = Collections.binarySearch(employees, new Employee(empno, "", 0, "", 0));
        if (index >= 0) {
            System.out.println("Enter new service period: (dalam jam)");
            int workingTime = s.nextInt();
            employees.get(index).setWorkingTime(workingTime);
            System.out.println("Employee service period updated successfully.");
            writeEmployeesToFile("fileData.txt");
        } else {
            System.out.println("Employee not found.");
        }
    }

    private void Insert(Scanner s) {
        System.out.println("Enter employee number:");
        int empno = s.nextInt();
        System.out.println("Enter employee name:");
        String ename = s.next();
        System.out.println("Enter employee salary:");
        int salary = s.nextInt();
        System.out.println("Enter NIP: (dalam angka tanpa spasi)");
        String nationalId = s.next();
        System.out.println("Enter working time: (dalam jam)");
        int workingTime = s.nextInt();

        Employee newEmployee = new Employee(empno, ename, salary, nationalId, workingTime);
        int index = Collections.binarySearch(employees, newEmployee);
        if (index < 0) {
            employees.add(-index - 1, newEmployee);
            System.out.println("Employee inserted successfully.");
            writeEmployeesToFile("fileData.txt");
        } else {
            System.out.println("Employee with this number already exists.");
        }
    }

    private void GetTotalGaji() {
        int totalSalary = 0;
        for (Employee e : employees) {
            totalSalary += e.getSalary();
        }
        System.out.println("Total salary of all employees: " + totalSalary);
    }
}