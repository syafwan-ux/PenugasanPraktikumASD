public class Employee implements Comparable<Employee> {
    private int empno;
    private String ename;
    private int salary;
    private String nationalId;
    private int workingTime;

    public Employee(int empno, String ename, int salary, String nationalId, int workingTime) {
        this.empno = empno;
        this.ename = ename;
        this.salary = salary;
        this.nationalId = nationalId;
        this.workingTime = workingTime;
    }

    public int getEmpno() {
        return empno;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getEname() {
        return ename;
    }

    public String getNationalId() {
        return nationalId;
    }

    public int getWorkingTime() {
        return workingTime;
    }

    public void setWorkingTime(int workingTime) {
        this.workingTime = workingTime;
    }

    @Override
    public int compareTo(Employee other) {
        return Integer.compare(this.empno, other.empno);
    }

    @Override
    public String toString() {
        return empno + " " + ename + " " + salary + " " + nationalId + " " + workingTime;
    }
}