package com.piglite.controller;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.piglite.model.Student;

@Controller
@RequestMapping("/student")
public class HelloWorldController {
    /*
	 * This method will serve as default GET handler.
	 *
	 */
    @RequestMapping(method = RequestMethod.GET)
    public String newRegistration(ModelMap model) {
        Student student = new Student();
        model.addAttribute("student", student);
        return "enroll";
    }

    /*
     * This method will be called on form submission, handling POST request
     * It also validates the user input
     */
    @RequestMapping(method = RequestMethod.POST)
    public String saveRegistration(@Valid Student student, BindingResult result, ModelMap model){

        if(result.hasErrors()) {
            return "enroll";
        }

        model.addAttribute("success", "Dear "+ student.getFirstName()+" , your Registration completed successfully");
        return "success";
    }

    /*
     * Method used to populate the Section list in view.
     * Note that here you can call external systems to provide real data.
     */
    @ModelAttribute("sections")
    public List<String> initializeSections() {

        List<String> sections = new ArrayList<String>();
        sections.add("Graduate");
        sections.add("Post Graduate");
        sections.add("Research");
        return sections;
    }

    /*
     * Method used to populate the country list in view.
     * Note that here you can call external systems to provide real data.
     */
    @ModelAttribute("countries")
    public List<String> initializeCountries() {

        List<String> countries = new ArrayList<String>();
        countries.add("美国");
        countries.add("中国");
        countries.add("法国");
        countries.add("德国");
        countries.add("意大利");
        countries.add("其他");
        return countries;
    }

    /*
     * Method used to populate the subjects list in view.
     * Note that here you can call external systems to provide real data.
     */
    @ModelAttribute("subjects")
    public List<String> initializeSubjects() {

        List<String> subjects = new ArrayList<String>();
        subjects.add("Physics");
        subjects.add("Chemistry");
        subjects.add("Life Science");
        subjects.add("Political Science");
        subjects.add("Computer Science");
        subjects.add("Mathmatics");
        return subjects;
    }
}
