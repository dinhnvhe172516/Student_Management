/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import constants.Message;

/**
 *
 * @author Admin
 */
public class Validation {

    private Validation() {
    }

    public static String getString(String input) throws Exception {
        if (input == null || input.isEmpty()) {
            throw new Exception(Message.EMPTY_INPUT);
        }
        return input.trim();
    }

    public static int getChoices(String input, int min, int max) throws Exception {
        try {
            int choice = Integer.parseInt(input);
            if (choice < min || choice > max) {
                throw new Exception(Message.INVALID_RANGE);
            }
            return choice;
        } catch (NumberFormatException e) {
            throw new Exception(Message.INVALID_NUMBER);
        }
    }

    public static String getCourse(String input) throws Exception {
        if (input.equalsIgnoreCase("Java")
                || input.equalsIgnoreCase(".Net")
                || input.equalsIgnoreCase("C/C++")) {
            return input;
        }
        throw new Exception(Message.INVALID_COURSE);
    }

    public static String checkYesOrNo(String input) throws Exception {
        String string = getString(input);
        if (string.equalsIgnoreCase("Y") || string.equalsIgnoreCase("N")) {
            return string.toUpperCase();
        }
        throw new Exception(Message.ONLY_YN);
    }
    
     public static String checkUpdateOrDelete(String input) throws Exception {
        String string = getString(input);
        if (string.equalsIgnoreCase("U") || string.equalsIgnoreCase("D")) {
            return string.toUpperCase();
        }
        throw new Exception(Message.ONLY_UD);
    }
}
