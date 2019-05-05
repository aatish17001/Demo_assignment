package com.example.demo;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Controller
public class Update_Controller implements WebMvcConfigurer {

    private Patient patient_global;

    final static Logger logger=Logger.getLogger("Update_Record_Controller");

    @ResponseBody
    @RequestMapping(value = "/validation", method = RequestMethod.GET)
    public ModelAndView ValidationForm() {
        ModelAndView modelAndView = new ModelAndView("update_page");
        String error="";
        logger.info("Validating Username and Password for Updation");
        modelAndView.addObject("patient", new Patient());
        modelAndView.addObject("error", error);
        return modelAndView;
    }
    @RequestMapping(value = "/updateform", method = RequestMethod.POST)
    public ModelAndView showUpdateForm(@ModelAttribute("patient")Patient patient,
                                       BindingResult result, ModelMap model) {
        Patient_DB_Manager patient_db_manager= new Patient_DB_Manager();
        int validate=patient_db_manager.validate_Id_and_Password(patient);
        String error="";

        if(validate!=1){
            ModelAndView modelAndView= new ModelAndView("update_page");
            modelAndView.addObject("patient", new Patient());
            modelAndView.addObject("error", "Error:Invalid Username and Password");
            logger.info("Error: Invalid Username and Password");
            return modelAndView;
        }
        logger.info("Correct Username and Password");
        logger.info("Showing Updation Form");
        patient_global=patient;
        return new ModelAndView("update_form", "patient", new Patient());
    }
    @RequestMapping(value = "/do_update", method = RequestMethod.POST)
    public ModelAndView Update(@ModelAttribute("patient")Patient patient,
                         BindingResult result, ModelMap model) {
        Patient_DB_Manager patient_db_manager= new Patient_DB_Manager();
        int validate=patient_db_manager.validate(patient);
        if(validate!=0){
            String error=patient_db_manager.get_error(validate);
            ModelAndView modelAndView= new ModelAndView("update_form");
            modelAndView.addObject("error",error);
            logger.error("Invalid Parameter(s) for Patient");
            return modelAndView;
        }

        int a=patient_db_manager.update_record(patient_global,patient);
        patient_db_manager.close();
        System.out.println(patient.getName());
        System.out.println(patient.getPassword());
        logger.info("Record Successfully Updated");
        return new ModelAndView("result_update");

    }
}
