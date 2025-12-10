package lab8;

import java.util.HashSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

// 1. Клас Працівник
class Employee {
    private String firstName;
    private String lastName;
    private double salary;

    public Employee(String firstName, String lastName, double salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    @Override
    public String toString() {
        return String.format("%s %s (%.2f)", firstName, lastName, salary);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        // Вважаємо працівників однаковими, якщо співпадають ім'я, прізвище та зарплата
        return Double.compare(employee.salary, salary) == 0 &&
                Objects.equals(firstName, employee.firstName) &&
                Objects.equals(lastName, employee.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, salary);
    }
}

// 2. Клас Відділ
class Department {
    private String name;
    private Employee head;

    private Set<Employee> staff;

    public Department(String name, Employee head) {
        this.name = name;
        this.head = head;
        this.staff = new HashSet<>(); // HashSet не гарантує порядок, але швидкий
    }

    public boolean addEmployee(Employee emp) {
        // Метод add у Set повертає false, якщо такий елемент вже є
        return staff.add(emp);
    }

    public String getName() {
        return name;
    }

    public Employee getHead() {
        return head;
    }

    public Set<Employee> getStaff() {
        return staff;
    }

    // Перевизначення для Department (унікальність за назвою відділу)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

// 3. Клас Фірма
class Firm {
    private String name;
    private Employee director;

    private Set<Department> departments;

    public Firm(String name, Employee director) {
        this.name = name;
        this.director = director;
        this.departments = new HashSet<>();
    }

    public void addDepartment(Department dep) {
        departments.add(dep);
    }

    public Set<Department> getDepartments() {
        return departments;
    }

    /**
     * (MAP):
     * Створює звіт, де ключ - назва відділу, а значення - середня зарплата по відділу.
     */
    public Map<String, Double> getAverageSalaryReport() {
        Map<String, Double> report = new HashMap<>();

        for (Department dept : departments) {
            double totalSalary = 0;
            int count = 0;

            // Додаємо зарплату співробітників
            for (Employee emp : dept.getStaff()) {
                totalSalary += emp.getSalary();
                count++;
            }

            // Додаємо зарплату начальника відділу (якщо він теж вважається частиною витрат відділу)
            if (dept.getHead() != null) {
                totalSalary += dept.getHead().getSalary();
                count++;
            }

            double average = (count > 0) ? totalSalary / count : 0.0;
            report.put(dept.getName(), average);
        }
        return report;
    }
}

// Головний клас
public class CompanySetAndMap {

    public static void main(String[] args) {
        // --- Створення даних ---
        Employee director = new Employee("Олег", "Винник", 60000);
        Firm myFirm = new Firm("Global Systems", director);

        // Відділ Розробки
        Employee headDev = new Employee("Андрій", "Шевченко", 40000);
        Department devDept = new Department("Development", headDev);

        Employee dev1 = new Employee("Ірина", "Козак", 25000);
        Employee dev2 = new Employee("Василь", "Стус", 28000);

        devDept.addEmployee(dev1);
        devDept.addEmployee(dev2);

        // Тест HASHSET: Спроба додати дублікат
        // Створюємо нового об'єкта з тими ж даними
        Employee devDuplicate = new Employee("Ірина", "Козак", 25000);
        boolean isAdded = devDept.addEmployee(devDuplicate);

        System.out.println("=== Тест HashSet (унікальність) ===");
        System.out.println("Спроба додати дублікат працівника 'Ірина Козак': " + (isAdded ? "Успіх" : "Відхилено"));
        System.out.println("Кількість працівників у Dev відділі: " + devDept.getStaff().size()); // Має бути 2, а не 3
        System.out.println();

        // Відділ HR
        Employee headHR = new Employee("Леся", "Українка", 32000);
        Department hrDept = new Department("Human Resources", headHR);
        hrDept.addEmployee(new Employee("Панас", "Мирний", 18000));
        hrDept.addEmployee(new Employee("Іван", "Франко", 19500));
        hrDept.addEmployee(new Employee("Марко", "Вовчок", 19000));

        myFirm.addDepartment(devDept);
        myFirm.addDepartment(hrDept);

        // --- Тест MAP ---
        System.out.println("=== Тест Map (Звіт по середній зарплаті) ===");

        // Отримуємо мапу <Назва відділу, Середня ЗП>
        Map<String, Double> salaryReport = myFirm.getAverageSalaryReport();

        // Перебір мапи (Map.Entry)
        for (Map.Entry<String, Double> entry : salaryReport.entrySet()) {
            String deptName = entry.getKey();
            Double avgSal = entry.getValue();
            System.out.printf("Відділ: %-20s | Середня ЗП: %.2f%n", deptName, avgSal);
        }

        // Map: пошук "найдорожчого" відділу
        String maxPaidDept = null;
        double maxAvgSalary = -1;

        for (String deptName : salaryReport.keySet()) {
            double currentAvg = salaryReport.get(deptName);
            if (currentAvg > maxAvgSalary) {
                maxAvgSalary = currentAvg;
                maxPaidDept = deptName;
            }
        }
        System.out.println("\nВідділ з найвищою середньою зарплатою: " + maxPaidDept);
    }
}