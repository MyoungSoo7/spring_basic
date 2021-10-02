package com.fastcampus.ch2;

import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class YoilTellerMVC5 {
    @RequestMapping("/getYoilMVC5") // http://localhost/ch2/getYoilMVC5?year=2021&month=10&day=1
//    public String main(@ModelAttribute("myDate") MyDate date, Model m) { 
    public String main(@ModelAttribute MyDate date, Model m) { // @ModelAttribute사용, 반환 타입은 String
System.out.println("myDate="+date);

    	// 1. 유효성 검사 
    	if(!isValid(date))
    		return "yoilError";
    	
        // 2. 작업
    	char yoil = getYoil(date);

    	// 3. Model에 작업한 결과를 저장 
// @ModelAttribute 덕분에 쿼리 스트링으로 넘어온 값은 저장안해도 됨.
//      	m.addAttribute("year",  date.getYear());     	
//      	m.addAttribute("month", date.getMonth());     	
//      	m.addAttribute("day",   date.getDay());     	
      	m.addAttribute("yoil", yoil);        
        
      	// 4. 작업 결과를 보여줄 뷰의 이름을 반 
      	return "yoil";
    }
    
    private char getYoil(MyDate date) {
    	return getYoil(date.getYear(), date.getMonth(), date.getDay());
    }
    
    private char getYoil(int year, int month, int day) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, day);

        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        return " 일월화수목금토".charAt(dayOfWeek);
    }

    private boolean isValid(MyDate date) {
    	return isValid(date.getYear(), date.getMonth(), date.getDay());
    }
    
    private boolean isValid(int year, int month, int day) {    
    	if(year==-1 || month==-1 || day==-1) 
    		return false;
    	
    	return (1<=month && month<=12) && (1<=day && day<=31); // 간단히 체크 
    }
}