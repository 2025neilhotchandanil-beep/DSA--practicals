import java.util.Scanner;

class Node {
    int roll;
    String name;
    String course;
    Node next;

    Node(int roll, String name, String course) {
        this.roll = roll;
        this.name = name;
        this.course = course;
        this.next = null;
    }
}

class StudentList {
    Node head = null;

    // Enroll: insert at end
    void enroll(int roll, String name, String course) {
        if (search(roll) != null) {
            System.out.println("Roll no " + roll + " already enrolled.");
            return;
        }
        Node newNode = new Node(roll, name, course);
        if (head == null) {
            head = newNode;
        } else {
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
        System.out.println("Student enrolled successfully.");
    }

    // Withdraw: delete by roll number
    void withdraw(int roll) {
        if (head == null) {
            System.out.println("List is empty. No student to withdraw.");
            return;
        }
        if (head.roll == roll) {
            head = head.next;
            System.out.println("Student " + roll + " withdrawn.");
            return;
        }
        Node temp = head;
        while (temp.next != null && temp.next.roll != roll) {
            temp = temp.next;
        }
        if (temp.next == null) {
            System.out.println("Roll no " + roll + " not found.");
        } else {
            temp.next = temp.next.next;
            System.out.println("Student " + roll + " withdrawn.");
        }
    }