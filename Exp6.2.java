implement Java program that uses lambda expressions and Stream API to filter students who scored above 75%, sort them by marks, and display their names.

Step 1: Create the Student Class
- Define a Student class with attributes:
    name (String)
    marks (double)
- Implement a constructor to initialize these values.
- Add a display method to print student details.

Step 2: Create the StudentFilterSort Class
- Create a list of students with sample data.
- Use Streams Class to:
      Filter students who scored above 75%.
      Sort students by marks in descending order.
      Collect the results into a new list.
- Use forEach() with a method reference to display results.ts.


    code:

import java.util.*;
import java.util.stream.*;

public class StudentFilterSort {

    // Student Class
    public static class Student {
        private String name;
        private double marks;

        // Constructor to initialize values
        public Student(String name, double marks) {
            this.name = name;
            this.marks = marks;
        }

        // Getter methods
        public String getName() {
            return name;
        }

        public double getMarks() {
            return marks;
        }

        // Display method
        public void display() {
            System.out.println(name + " (" + marks + ")");
        }
    }

    public static void main(String[] args) {
        // Sample data: List of students
        List<Student> students = new ArrayList<>();
        students.add(new Student("Alice", 80));
        students.add(new Student("Bob", 72));
        students.add(new Student("Charlie", 90));
        students.add(new Student("David", 65));
        students.add(new Student("Eve", 85));

        // Filtering students who scored above 75% and sorting by marks in descending order
        System.out.println("Normal Case:");
        students.stream()
                .filter(student -> student.getMarks() > 75)  // Filter students who scored above 75%
                .sorted((s1, s2) -> Double.compare(s2.getMarks(), s1.getMarks())) // Sort by marks (descending)
                .forEach(student -> System.out.println(student.getName()));  // Display names

        // Test Case 2: All Below 75%
        System.out.println("\nAll Below 75%:");
        List<Student> students2 = new ArrayList<>();
        students2.add(new Student("Bob", 70));
        students2.add(new Student("David", 60));
        students2.add(new Student("Frank", 65));
        
        students2.stream()
                .filter(student -> student.getMarks() > 75)
                .sorted((s1, s2) -> Double.compare(s2.getMarks(), s1.getMarks()))
                .forEach(student -> System.out.println(student.getName()));

        // Test Case 3: Same Marks
        System.out.println("\nSame Marks:");
        List<Student> students3 = new ArrayList<>();
        students3.add(new Student("Alice", 80));
        students3.add(new Student("Bob", 80));
        students3.add(new Student("Charlie", 85));

        students3.stream()
                .filter(student -> student.getMarks() > 75)
                .sorted((s1, s2) -> {
                    int markComparison = Double.compare(s2.getMarks(), s1.getMarks());
                    return (markComparison != 0) ? markComparison : s1.getName().compareTo(s2.getName());
                })
                .forEach(student -> System.out.println(student.getName()));

        // Test Case 4: Single Student Above 75%
        System.out.println("\nSingle Student Above 75%:");
        List<Student> students4 = new ArrayList<>();
        students4.add(new Student("Alice", 60));
        students4.add(new Student("Bob", 50));
        students4.add(new Student("Charlie", 90));

        students4.stream()
                .filter(student -> student.getMarks() > 75)
                .sorted((s1, s2) -> Double.compare(s2.getMarks(), s1.getMarks()))
                .forEach(student -> System.out.println(student.getName()));
    }
}




Test Case	                        Input Data	                                                 Expected Output
Case 1:         Normal Case	Alice (80), Bob (72), Charlie (90), David (65), Eve (85)	         Charlie, Eve, Alice (Sorted by marks)
Case 2:         All Below 75%	Bob (70), David (60), Frank (65)	                               No output (Empty List)
Case 3:         Same Marks	Alice (80), Bob (80), Charlie (85)                                 Charlie, Alice, Bob (Sorted by marks, then by name)
Case 4:         Single Student Above 75%	Alice (60), Bob (50), Charlie (90)	                 Charlie (Only one student)
