import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        PlacementService ps = new PlacementService();

        while (true) {
            System.out.println("\n1. Add Student");
            System.out.println("2. Add Company");
            System.out.println("3. Apply");
            System.out.println("4. Show Students");
            System.out.println("5. Show Companies");
            System.out.println("6. Show Applications");
            System.out.println("7. Exit");

            int ch = sc.nextInt();

            switch (ch) {

                case 1:
                    System.out.print("Enter ID Name Branch CGPA: ");
                    ps.addStudent(sc.nextInt(), sc.nextLine(), sc.next(), sc.nextDouble());
                    break;

                case 2:
                    System.out.print("Enter ID Name MinCGPA: ");
                    ps.addCompany(sc.nextInt(), sc.next(), sc.nextDouble());
                    break;

                case 3:
                    System.out.print("Enter StudentID CompanyID: ");
                    ps.apply(sc.nextInt(), sc.nextInt());
                    break;

                case 4:
                    ps.showStudents();
                    break;

                case 5:
                    ps.showCompanies();
                    break;

                case 6:
                    ps.showApplications();
                    break;

                case 7:
                    System.exit(0);
            }
        }
    }
}