package main;

import constants.Message;
import controller.StudentController;
import dto.CourseDTO;
import dto.StudentRequestDTO;
import java.util.Scanner;
import utils.Validation;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentController controller = new StudentController();

        while (true) {
            try {
                System.out.println(Message.MENU);
                System.out.print(Message.INPUT_CHOICE);
                
                String choiceRaw = sc.nextLine().trim();
                int choice = Validation.getChoices(choiceRaw, 1, 5);

                switch (choice) {
                    case 1:
                        while (true) {
                            try {
                                StudentRequestDTO requestDTO = new StudentRequestDTO();
                                CourseDTO courseDTO = new CourseDTO();

                                System.out.print(Message.INPUT_ID);
                                requestDTO.setId(Validation.getString(sc.nextLine()));
                                
                                System.out.print(Message.INPUT_NAME);
                                requestDTO.setName(Validation.getString(sc.nextLine()));
                                
                                System.out.print(Message.INPUT_SEMESTER);
                                courseDTO.setSemester(Validation.getString(sc.nextLine()));
                                
                                System.out.print(Message.INPUT_COURSE);
                                courseDTO.setCourse(Validation.getCourse(sc.nextLine()));
                                
                                requestDTO.setCourse(courseDTO);
                                controller.addStudent(requestDTO);

                                if (controller.getStudentSize() >= 10) {
                                    System.out.print(Message.INPUT_CONTINUE);
                                    String check = Validation.checkYesOrNo(sc.nextLine());
                                    if (check.equalsIgnoreCase("N")) {
                                        break;
                                    }
                                }
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                        }
                        break;
                    case 2:
                        System.out.print(Message.INPUT_SEARCH);
                        String inputSearch = Validation.getString(sc.nextLine());
                        controller.searchStudent(inputSearch);
                        break;
                    case 3:
                        try {
                            System.out.print(Message.INPUT_FIND_ID);
                            String id = Validation.getString(sc.nextLine());

                            System.out.print(Message.INPUT_UD);
                            String ud = Validation.checkUpdateOrDelete(sc.nextLine());
                            
                            StudentRequestDTO findDto = new StudentRequestDTO();
                            findDto.setId(id);

                            if (ud.equalsIgnoreCase("U")) {
                                findDto.setCourse(new CourseDTO());
                                System.out.print(Message.INPUT_NAME);
                                findDto.setName(Validation.getString(sc.nextLine()));

                                System.out.print(Message.INPUT_SEMESTER);
                                findDto.getCourse().setSemester(Validation.getString(sc.nextLine()));

                                System.out.print(Message.INPUT_COURSE);
                                findDto.getCourse().setCourse(Validation.getCourse(sc.nextLine()));

                                controller.updateStudent(findDto);
                                System.out.println(Message.UPDATE_SUCCESS);
                            } else if (ud.equalsIgnoreCase("D")) {
                                controller.deleteStudent(findDto);
                                System.out.println(Message.DELETE_SUCCESS);
                            }
                        } catch (Exception e) {
                            System.out.println( e.getMessage());
                        }
                        break;
                    case 4:
                        controller.reportList();
                        break;
                    case 5:
                        return;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}