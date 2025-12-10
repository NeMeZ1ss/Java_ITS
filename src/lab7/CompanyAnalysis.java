package lab7;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;

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
        return String.format("%s %s (ЗП: %.2f)", firstName, lastName, salary);
    }
}

// 2. Клас Відділ
class Department {
    private String name;
    private Employee head; // Начальник відділу
    private List<Employee> staff; // Список працівників (ArrayList)

    public Department(String name, Employee head) {
        this.name = name;
        this.head = head;
        // Використовуємо ArrayList, оскільки потрібен швидкий доступ (RandomAccess)
        this.staff = new ArrayList<>();
    }

    public void addEmployee(Employee emp) {
        staff.add(emp);
    }

    public String getName() {
        return name;
    }

    public Employee getHead() {
        return head;
    }

    public List<Employee> getStaff() {
        return staff;
    }
}

// 3. Клас Фірма
class Firm {
    private String name;
    private Employee director; // Директор
    private List<Department> departments; // Список відділів

    public Firm(String name, Employee director) {
        this.name = name;
        this.director = director;
        // Використовуємо LinkedList для різноманіття колекцій (або ArrayList)
        this.departments = new LinkedList<>();
    }

    public void addDepartment(Department dep) {
        departments.add(dep);
    }

    public Employee getDirector() {
        return director;
    }

    public List<Department> getDepartments() {
        return departments;
    }
}

// Головний клас для демонстрації
public class CompanyAnalysis {

    public static void main(String[] args) {
        // --- Підготовка даних (Ініціалізація) ---
        Employee director = new Employee("Олександр", "Петренко", 50000);
        Firm myFirm = new Firm("Tech Solutions Ltd.", director);

        // Відділ IT
        Employee headIT = new Employee("Іван", "Сидоренко", 35000);
        Department itDep = new Department("IT Відділ", headIT);
        itDep.addEmployee(new Employee("Марія", "Коваленко", 25000));
        itDep.addEmployee(new Employee("Дмитро", "Бондар", 36000)); // Отримує більше за начальника!
        itDep.addEmployee(new Employee("Ольга", "Жук", 20000));

        // Відділ Маркетингу
        Employee headMarketing = new Employee("Анна", "Лисенко", 30000);
        Department marketDep = new Department("Маркетинг", headMarketing);
        marketDep.addEmployee(new Employee("Сергій", "Ткаченко", 15000));
        marketDep.addEmployee(new Employee("Вікторія", "Мельник", 18000));

        myFirm.addDepartment(itDep);
        myFirm.addDepartment(marketDep);

        System.out.println("=== Аналіз фірми: " + "Tech Solutions Ltd." + " ===\n");

        // -----------------------------------------------------------------------
        // ЗАДАЧА 1: Знайти макс. зарплату (включаючи всіх).
        // Вимога варіанту: а) нетипізований ітератор
        // -----------------------------------------------------------------------
        System.out.println("Задача 1: Максимальна заробітна плата (Нетипізований Iterator)");

        double maxSalary = myFirm.getDirector().getSalary();

        // Отримуємо "сирий" ітератор (raw type)
        Iterator depIteratorRaw = myFirm.getDepartments().iterator();

        while (depIteratorRaw.hasNext()) {
            // Явне приведення типів, оскільки ітератор нетипізований
            Object depObj = depIteratorRaw.next();
            Department dept = (Department) depObj;

            // Перевірка начальника
            if (dept.getHead().getSalary() > maxSalary) {
                maxSalary = dept.getHead().getSalary();
            }

            // Ітератор для співробітників відділу (теж нетипізований)
            Iterator staffIteratorRaw = dept.getStaff().iterator();
            while (staffIteratorRaw.hasNext()) {
                Object empObj = staffIteratorRaw.next();
                Employee emp = (Employee) empObj;

                if (emp.getSalary() > maxSalary) {
                    maxSalary = emp.getSalary();
                }
            }
        }
        System.out.println("-> Максимальна знайдена ЗП: " + maxSalary + "\n");


        // -----------------------------------------------------------------------
        // ЗАДАЧА 2: Визначити відділ, де співробітник отримує більше за начальника.
        // Вимога варіанту: c) типізований цикл «for-each»
        // -----------------------------------------------------------------------
        System.out.println("Задача 2: Відділи з 'багатими' співробітниками (Типізований for-each)");

        List<String> anomalyDepartments = new ArrayList<>();

        for (Department dept : myFirm.getDepartments()) {
            double bossSalary = dept.getHead().getSalary();
            boolean foundAnomaly = false;

            for (Employee emp : dept.getStaff()) {
                if (emp.getSalary() > bossSalary) {
                    foundAnomaly = true;
                    // Можна вийти з внутрішнього циклу, якщо такий знайдений
                    break;
                }
            }

            if (foundAnomaly) {
                anomalyDepartments.add(dept.getName());
            }
        }

        if (anomalyDepartments.isEmpty()) {
            System.out.println("-> Таких відділів не знайдено.");
        } else {
            System.out.println("-> Знайдені відділи: " + anomalyDepartments + "\n");
        }


        // -----------------------------------------------------------------------
        // ЗАДАЧА 3: Скласти список усіх співробітників (всіх рівнів).
        // Вимога варіанту: b) типізований ітератор
        // -----------------------------------------------------------------------
        System.out.println("Задача 3: Повний список персоналу (Типізований Iterator)");

        List<Employee> allStaffList = new ArrayList<>();

        // Спочатку додаємо директора
        allStaffList.add(myFirm.getDirector());

        // Використовуємо типізований ітератор для відділів
        Iterator<Department> deptIteratorTyped = myFirm.getDepartments().iterator();

        while (deptIteratorTyped.hasNext()) {
            Department dept = deptIteratorTyped.next();

            // Додаємо начальника відділу
            allStaffList.add(dept.getHead());

            // Використовуємо типізований ітератор для працівників
            Iterator<Employee> empIteratorTyped = dept.getStaff().iterator();
            while (empIteratorTyped.hasNext()) {
                Employee emp = empIteratorTyped.next();
                allStaffList.add(emp);
            }
        }

        // Вивід результату (для перевірки)
        System.out.println("-> Загальна кількість людей у списку: " + allStaffList.size());
        for (Employee e : allStaffList) {
            System.out.println("   - " + e);
        }
    }
}
