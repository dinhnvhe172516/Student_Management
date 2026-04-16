package main;

import constants.Message;
import controller.StudentController;
import dto.StudentRequestDTO;
import java.util.Scanner;
import utils.Validation;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentController controller = new StudentController();

        while (true) {
            System.out.println(Message.MENU);
            System.out.print(Message.INPUT_CHOICE);
            try {
                int choice = Validation.getChoice(sc.nextLine(), 1, 5);
                switch (choice) {
                    case 1:
                        // Add new students continuously until the user stops
                        while (true) {
                            try {
                                StudentRequestDTO dto = new StudentRequestDTO();
                                
                                System.out.print(Message.INPUT_ID);
                                dto.setId(Validation.getString(sc.nextLine()));
                                
                                System.out.print(Message.INPUT_NAME);
                                dto.setName(Validation.getString(sc.nextLine()));
                                
                                System.out.print(Message.INPUT_SEMESTER);
                                dto.getCourse().setSemester(Validation.getString(sc.nextLine()));
                                
                                System.out.print(Message.INPUT_COURSE);
                                dto.getCourse().setCourseName(Validation.getCourse(sc.nextLine()));

                                controller.addStudent(dto);

                                // Prompt to continue if total student enrollments exceed 10
                                if (controller.getStudentSize() >= 10) {
                                    System.out.print(Message.INPUT_CONTINUE);
                                    String c = Validation.getYN(sc.nextLine());
                                    if (c.equals("N")) {
                                        break;
                                    }
                                }
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                        }
                        break;
                    case 2:
                        // Search for a student by name
                        System.out.print(Message.INPUT_SEARCH);
                        String input = Validation.getString(sc.nextLine());
                        controller.searchStudent(input);
                        break;
                    case 3:
                        // Update or delete a student record by ID
                        StudentRequestDTO findDto = new StudentRequestDTO();
                        System.out.print(Message.INPUT_FIND_ID);
                        String id = Validation.getString(sc.nextLine());

                        System.out.print(Message.INPUT_UD);
                        String ud = Validation.getUD(sc.nextLine());
                        if (ud.equals("U")) {
                            findDto.setId(id);
                            System.out.print(Message.INPUT_NAME);
                            findDto.setName(Validation.getString(sc.nextLine()));
                            
                            System.out.print(Message.INPUT_SEMESTER);
                            findDto.getCourse().setSemester(Validation.getString(sc.nextLine()));
                            
                            System.out.print(Message.INPUT_COURSE);
                            findDto.getCourse().setCourseName(Validation.getCourse(sc.nextLine()));
                            
                            controller.updateStudent(findDto);
                            System.out.println(Message.UPDATE_SUCCESS);
                        } else if (ud.equals("D")) {
                            findDto.setId(id);
                            controller.deleteStudent(findDto);
                            System.out.println(Message.DELETE_SUCCESS);
                        }
                        break;
                    case 4:
                        // Generate and display a counting report
                        controller.report();
                        break;
                    case 5:
                        // Exit the application
                        return;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
