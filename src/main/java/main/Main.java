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
                        case1Loop:
                        while (true) {
                            StudentRequestDTO requestDTO = new StudentRequestDTO();
                            CourseDTO courseDTO = new CourseDTO();

                            while (true) {
                                try {
                                    System.out.print(Message.INPUT_ID);
                                    requestDTO.setId(Validation.getString(sc.nextLine()));
                                    break;
                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                }
                            }

                            while (true) {
                                try {
                                    System.out.print(Message.INPUT_NAME);
                                    requestDTO.setName(Validation.getString(sc.nextLine()));
                                    break;
                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                }
                            }

                            while (true) {
                                try {
                                    System.out.print(Message.INPUT_SEMESTER);
                                    courseDTO.setSemester(Validation.getString(sc.nextLine()));
                                    break;
                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                }
                            }

                            while (true) {
                                try {
                                    System.out.print(Message.INPUT_COURSE);
                                    courseDTO.setCourse(Validation.getCourse(sc.nextLine()));
                                    break;
                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                }
                            }

                            try {
                                requestDTO.setCourse(courseDTO);
                                controller.addStudent(requestDTO);

                                if (controller.getStudentSize() >= 2) {
                                    while (true) {
                                        try {
                                            System.out.print(Message.INPUT_CONTINUE);
                                            String check = Validation.checkYesOrNo(sc.nextLine());
                                            if (check.equalsIgnoreCase("N")) {
                                                break case1Loop; 
                                            }
                                            break;
                                        } catch (Exception e) {
                                            System.out.println(e.getMessage());
                                        }
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
                            String id = "";
                            while (true) {
                                try {
                                    System.out.print(Message.INPUT_FIND_ID);
                                    id = Validation.getString(sc.nextLine());
                                    break;
                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                }
                            }

                            String ud = "";
                            while (true) {
                                try {
                                    System.out.print(Message.INPUT_UD);
                                    ud = Validation.checkUpdateOrDelete(sc.nextLine());
                                    break;
                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                }
                            }

                            StudentRequestDTO findDto = new StudentRequestDTO();
                            findDto.setId(id);

                            if (ud.equalsIgnoreCase("U")) {
                                findDto.setCourse(new CourseDTO());
                                while (true) {
                                    try {
                                        System.out.print(Message.INPUT_NAME);
                                        findDto.setName(Validation.getString(sc.nextLine()));
                                        break;
                                    } catch (Exception e) {
                                        System.out.println(e.getMessage());
                                    }
                                }

                                while (true) {
                                    try {
                                        System.out.print(Message.INPUT_SEMESTER);
                                        findDto.getCourse().setSemester(Validation.getString(sc.nextLine()));
                                        break;
                                    } catch (Exception e) {
                                        System.out.println(e.getMessage());
                                    }
                                }

                                while (true) {
                                    try {
                                        System.out.print(Message.INPUT_COURSE);
                                        findDto.getCourse().setCourse(Validation.getCourse(sc.nextLine()));
                                        break;
                                    } catch (Exception e) {
                                        System.out.println(e.getMessage());
                                    }
                                }

                                controller.updateStudent(findDto);
                                System.out.println(Message.UPDATE_SUCCESS);
                            } else if (ud.equalsIgnoreCase("D")) {
                                controller.deleteStudent(findDto);
                                System.out.println(Message.DELETE_SUCCESS);
                            }
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
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