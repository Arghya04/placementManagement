import java.sql.*;

public class PlacementService {

    // Add Student
    public void addStudent(int id, String name, String branch, double cgpa) {
        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO students VALUES (?, ?, ?, ?)");

            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setString(3, branch);
            ps.setDouble(4, cgpa);

            ps.executeUpdate();
            System.out.println("Student Added!");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // Add Company
    public void addCompany(int id, String name, double cgpa) {
        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO companies VALUES (?, ?, ?)");

            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setDouble(3, cgpa);

            ps.executeUpdate();
            System.out.println("Company Added!");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // Apply
    public void apply(int sid, int cid) {
        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement ps1 = con.prepareStatement(
                    "SELECT cgpa FROM students WHERE id=?");
            ps1.setInt(1, sid);
            ResultSet rs1 = ps1.executeQuery();

            PreparedStatement ps2 = con.prepareStatement(
                    "SELECT min_cgpa FROM companies WHERE id=?");
            ps2.setInt(1, cid);
            ResultSet rs2 = ps2.executeQuery();

            if (rs1.next() && rs2.next()) {

                double studentCgpa = rs1.getDouble(1);
                double minCgpa = rs2.getDouble(1);

                String status = (studentCgpa >= minCgpa) ? "SELECTED" : "REJECTED";

                PreparedStatement ps3 = con.prepareStatement(
                        "INSERT INTO applications(student_id, company_id, status) VALUES (?, ?, ?)");

                ps3.setInt(1, sid);
                ps3.setInt(2, cid);
                ps3.setString(3, status);

                ps3.executeUpdate();

                System.out.println("Result: " + status);
            } else {
                System.out.println("Invalid IDs!");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // Show Students
    public void showStudents() {
        try {
            Connection con = DBConnection.getConnection();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM students");

            while (rs.next()) {
                System.out.println(rs.getInt(1) + " " + rs.getString(2) + " "
                        + rs.getString(3) + " CGPA: " + rs.getDouble(4));
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // Show Companies
    public void showCompanies() {
        try {
            Connection con = DBConnection.getConnection();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM companies");

            while (rs.next()) {
                System.out.println(rs.getInt(1) + " " + rs.getString(2)
                        + " MinCGPA: " + rs.getDouble(3));
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // Show Applications
    public void showApplications() {
        try {
            Connection con = DBConnection.getConnection();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM applications");

            while (rs.next()) {
                System.out.println("Student " + rs.getInt(2)
                        + " → Company " + rs.getInt(3)
                        + " | " + rs.getString(4));
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
