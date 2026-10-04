mport java.util.Scanner;

public class InfixToPostfix {
    static char[] stack = new char[100];
    static int top = -1;

    static void push(char c) { stack[++top] = c; }
    static char pop() { return stack[top--]; }
    static char peek() { return stack[top]; }
    static boolean isEmpty() { return top == -1; }

    static int precedence(char c) {
        switch (c) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
        }
        return 0;
    }

    static String convert(String infix) {
        StringBuilder postfix = new StringBuilder();
        for (char c : infix.toCharArray()) {
            if (c == ' ') continue;
            if (Character.isLetterOrDigit(c)) {
                postfix.append(c);
            } else if (c == '(') {
                push(c);
            } else if (c == ')') {
                while (!isEmpty() && peek() != '(')
                    postfix.append(pop());
                if (!isEmpty()) pop();
            } else {
                while (!isEmpty() && peek() != '('
                        && (precedence(peek()) > precedence(c)
                        || (precedence(peek()) == precedence(c) && c != '^')))
                    postfix.append(pop());
                push(c);
            }
        }
        while (!isEmpty())
            postfix.append(pop());
        return postfix.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter infix expression: ");
        String infix = sc.nextLine();
        System.out.println("Postfix expression: " + convert(infix));
        sc.close();
    }
}