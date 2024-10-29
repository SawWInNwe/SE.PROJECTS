import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class HospitalSystem {
    static class Patient {
        int patientId;
        String name;
        int age;
        String medicalProblem;
        String phoneNumber;

        public Patient(int patientId, String name, int age, String medicalProblem, String phoneNumber) {
            this.patientId = patientId;
            this.name = name;
            this.age = age;
            this.medicalProblem = medicalProblem;
            this.phoneNumber = phoneNumber;
        }
    }

    List<Patient> patients;

    public HospitalSystem() {
        this.patients = new ArrayList<>();
    }

    public int generatePatientId(String name, int age, String medicalProblem) {
        String idString = name.substring(0, 3) + age + medicalProblem.substring(0, 3);
        return idString.hashCode(); // Using hash code as a unique ID
    }

    public void insertPatient() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter patient name:");
        String name = scanner.next();
        int age;
        do {
            System.out.println("Enter patient age:");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid age:");
                scanner.next();
            }
            age = scanner.nextInt();
            if (age <= 0) {
                System.out.println("Age must be a positive integer.");
            }
        } while (age <= 0);
        System.out.println("Enter patient's medical problem:");
        String medicalProblem = scanner.next();
        System.out.println("Enter patient's phone number:");
        String phoneNumber = scanner.next();

        int patientId = generatePatientId(name, age, medicalProblem);
        Patient patient = new Patient(patientId, name, age, medicalProblem, phoneNumber);
        patients.add(patient);
        System.out.println("Patient added successfully! Patient ID: " + patientId);
    }

    public Patient searchPatient(int patientId) {
        for (Patient patient : patients) {
            if (patient.patientId == patientId) {
                return patient;
            }
        }
        return null;
    }

    public boolean deletePatient(int patientId) {
        for (Patient patient : patients) {
            if (patient.patientId == patientId) {
                patients.remove(patient);
                return true;
            }
        }
        return false;
    }

    public void insertionSort() {
        for (int i = 1; i < patients.size(); i++) {
            Patient key = patients.get(i);
            int j = i - 1;
            while (j >= 0 && patients.get(j).age > key.age) {
                patients.set(j + 1, patients.get(j));
                j = j - 1;
            }
            patients.set(j + 1, key);
        }
    }

    // Method to handle invalid choices in the main menu
    private void handleInvalidChoice() {
        System.out.println("Invalid choice. Please try again.");
    }

    // Method to display the patient list
    private void displayPatientList(List<Patient> patientList) {
        System.out.println("Patient List:");
        for (Patient patient : patientList) {
            System.out.println("ID: " + patient.patientId + ", Name: " + patient.name + ", Age: " + patient.age + ", Medical Problem: " + patient.medicalProblem + ", Phone Number: " + patient.phoneNumber);
        }
    }

    // Main method to run the application
    public static void main(String[] args) {
        HospitalSystem hospitalSystem = new HospitalSystem();
        Scanner scanner = new Scanner(System.in);

        int choice;
        do {
            System.out.println("\nMenu:");
            System.out.println("1. Add Patient");
            System.out.println("2. Search Patient");
            System.out.println("3. Delete Patient");
            System.out.println("4. Sort Patients (Insertion Sort)");
            System.out.println("5. Exit");
            System.out.println("Enter your choice:");

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    hospitalSystem.insertPatient();
                    break;
                case 2:
                    System.out.println("Enter patient ID to search:");
                    int patientId = scanner.nextInt();
                    Patient foundPatient = hospitalSystem.searchPatient(patientId);
                    if (foundPatient != null) {
                        System.out.println("Patient found:");
                        System.out.println("Name: " + foundPatient.name);
                        System.out.println("Age: " + foundPatient.age);
                        System.out.println("Medical Problem: " + foundPatient.medicalProblem);
                        System.out.println("Phone Number: " + foundPatient.phoneNumber);
                    } else {
                        System.out.println("Patient not found.");
                    }
                    break;
                case 3:
                    System.out.println("Enter patient ID to delete:");
                    int patientIdToDelete = scanner.nextInt();
                    boolean deleted = hospitalSystem.deletePatient(patientIdToDelete);
                    if (deleted) {
                        System.out.println("Patient deleted successfully.");
                    } else {
                        System.out.println("Patient not found.");
                    }
                    break;
                case 4:
                    hospitalSystem.insertionSort();
                    hospitalSystem.displayPatientList(hospitalSystem.patients);
                    break;
                case 5:
                    System.out.println("Exiting program.");
                    break;
                default:
                    hospitalSystem.handleInvalidChoice();
                    break;
            }

        } while (choice != 5);
    }
}
