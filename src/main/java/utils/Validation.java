package utils;

import constants.Message;

public final class Validation {
    private Validation() {}

    public static String getString(String input) throws Exception {
        if (input == null || input.trim().isEmpty()) {
            throw new Exception(Message.EMPTY_INPUT);
        }
        return input.trim();
    }

    public static int getChoice(String input, int min, int max) throws Exception {
        try {
            int choice = Integer.parseInt(input.trim());
            if (choice < min || choice > max) {
                throw new Exception(String.format(Message.INVALID_RANGE, min, max));
            }
            return choice;
        } catch (NumberFormatException e) {
            throw new Exception(Message.INVALID_NUMBER);
        }
    }

    public static String getCourse(String input) throws Exception {
        String course = getString(input);
        if (course.equalsIgnoreCase("Java") || course.equalsIgnoreCase(".Net") || course.equalsIgnoreCase("C/C++")) {
            return course;
        }
        throw new Exception(Message.INVALID_COURSE);
    }

    public static String getUD(String input) throws Exception {
        String s = getString(input);
        if (s.equalsIgnoreCase("U") || s.equalsIgnoreCase("D")) {
            return s.toUpperCase();
        }
        throw new Exception(Message.ONLY_UD);
    }

    public static String getYN(String input) throws Exception {
        String s = getString(input);
        if (s.equalsIgnoreCase("Y") || s.equalsIgnoreCase("N")) {
            return s.toUpperCase();
        }
        throw new Exception(Message.ONLY_YN);
    }
}
