package com.barbarum.tutorial.code.hierarchy;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class CompanyHierarchy {


    public static void main(String[] args) {

        String[][] csv = new String[5][];
        csv[0] = new String[]{"John", null};
        csv[1] = new String[]{"Mike", "John"};
        csv[2] = new String[]{"Tim", "Mike"};
        csv[3] = new String[]{"Eric", "John"};
        csv[4] = new String[]{"Adam", null};

        printCompanyHierarchy(csv);
    }

    public static void printCompanyHierarchy(String csv[][]) {

        Map<String, Employee> map = new HashMap<>();

        Employee ceo = null;

        LinkedList<Employee> exectives = new LinkedList<>();

        for (String relationship[] : csv) {
            Employee employee = map.computeIfAbsent(relationship[0], Employee::new);

            if (relationship[1] == null) {
                exectives.add(employee);
                continue;
            }

            Employee boss = map.computeIfAbsent(relationship[1], Employee::new);
            boss.addEmployee(employee);
        }


        printHierarchy(exectives, "");
    }

    private static void printHierarchy(LinkedList<Employee> employees, String padding) {
        if (employees.isEmpty()) {
            return;
        }

        employees.forEach(employee -> {
            System.out.println(padding + employee.getName());
            printHierarchy(employee.getEmployees(), padding + " ");
        });
    }


    static class Employee {
        private String name;
        private LinkedList<Employee> employees = new LinkedList<>();

        public Employee(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public LinkedList<Employee> getEmployees() {
            return employees;
        }

        public void addEmployee(Employee employee) {
            this.employees.add(employee);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Employee employee = (Employee) o;

            return name != null ? name.equals(employee.name) : employee.name == null;
        }

        @Override
        public int hashCode() {
            return name != null ? name.hashCode() : 0;
        }
    }
}
