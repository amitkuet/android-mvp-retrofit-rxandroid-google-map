package app.engine.android.util;

import app.engine.android.AppEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtils {

    public CommonUtils(){

    }

    public boolean isEmailValid(String email) {
        boolean isValid = false;
        String myReg = AppEngine.getInstance().constants.EMAIL_PATTERN;
        Pattern pattern = Pattern.compile(myReg);
        Matcher regMatcher = pattern.matcher(email);
        if (regMatcher.matches()) {
            isValid =  true;
        }
        return isValid;
    }//end of isEmailValidate method

    public boolean isPhoneNumberValid(String phoneNumber){
        boolean isValid = false;
        String expression = AppEngine.getInstance().constants.PHONENUMBER_PATTERN;
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(phoneNumber);
        if(matcher.matches()){
            isValid = true;
        }
        return isValid;
    }//end of isPhoneNumberValid method

    public boolean  isPasswordValid(String password){
        boolean isValid = false;
        String pattern =  AppEngine.getInstance().constants.PASSWORD_PATTERN;
        if(password.matches(pattern)){
            isValid = true;
        }
        return isValid;
    }//end of isPasswordValid method

    public List<Integer> findNumbersFromString(String str){
        List<Integer> list = new ArrayList<Integer>();
        str = str.replaceAll("[^0-9]+", " ");
        String [] nums = str.trim().split(" ");
        for (int i=0; i<nums.length && nums.length>1; i++){
            list.add(Integer.parseInt(nums[i]));
        }
        return list;
    }

}
