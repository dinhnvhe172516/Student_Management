package constants;

public final class Message {
    private Message(){}

    public static final String MENU = 
            "WELCOME TO STUDENT MANAGEMENT\n" +
            "1. Create\n" +
            "2. Find and Sort\n" +
            "3. Update/Delete\n" +
            "4. Report\n" +
            "5. Exit\n" +
            "(Please choose 1 to Create, 2 to Find and Sort, 3 to Update/Delete, 4 to Report and 5 to Exit program).";
            
    public static final String INPUT_CHOICE = "Choose: ";
    public static final String INPUT_ID = "ID: ";
    public static final String INPUT_NAME = "Name: ";
    public static final String INPUT_SEMESTER = "Semester: ";
    public static final String INPUT_COURSE = "Course: ";
    
    public static final String INPUT_SEARCH = "Input student name to search: ";
    public static final String INPUT_FIND_ID = "Enter student ID to find: ";
    public static final String INPUT_UD = "Do you want to update (U) or delete (D) student? ";
    public static final String INPUT_CONTINUE = "Do you want to continue (Y/N)? Choose Y to continue, N to return main screen. ";
    
    public static final String EMPTY_INPUT = "Input must not be empty";
    public static final String INVALID_NUMBER = "The number input is invalid";
    public static final String INVALID_RANGE = "The input range is not valid. Please enter in range %d - %d";
    public static final String DUPLICATE = "Duplicate student registration.";
    public static final String ONLY_UD = "Only U or D!";
    public static final String ONLY_YN = "Only Y or N!";
    public static final String INVALID_COURSE = "Invalid course (Must be Java, .Net, C/C++)";
    public static final String NO_STUDENT_AVAILABLE = "Not found";
    public static final String DATABASE_EMPTY = "Database is empty";
    public static final String UPDATE_SUCCESS = "Update success";
    public static final String DELETE_SUCCESS = "Delete success";
}
