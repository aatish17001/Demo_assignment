package com.example.demo;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Controller
public class Delete_Controller implements WebMvcConfigurer {

    final static Logger logger=Logger.getLogger("Delete_Record_Controller");

    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ModelAndView showDeleteForm() {
        ModelAndView modelAndView = new ModelAndView("delete_page");
        String error="";
        modelAndView.addObject("patient", new Patient());
        modelAndView.addObject("error", error);
        logger.info("Validating Username and Password for Deletion");
        return modelAndView;
    }
    @RequestMapping(value = "/do_delete", method = RequestMethod.POST)
    public ModelAndView Delete(@ModelAttribute("patient")Patient patient,
                         BindingResult result, ModelMap model) {
        Patient_DB_Manager patient_db_manager= new Patient_DB_Manager();
        int validate=patient_db_manager.validate_Id_and_Password(patient);
        String error="";

        if(validate!=1){
            System.out.println("here");
            ModelAndView modelAndView= new ModelAndView("delete_page");
            modelAndView.addObject("patient", new Patient());

            modelAndView.addObject("error", "Error:Invalid Username and Password");
            logger.error("\"Error:Invalid Username and Password\"");
            return modelAndView;
        }
        logger.info("Correct Username and Password");
        int a=patient_db_manager.delete_record(patient);
        patient_db_manager.close();
        if(a==0){
            ModelAndView modelAndView = new ModelAndView("delete_page");
            logger.error("record not found");
            return modelAndView;
        }
        logger.info("Record Successfully Deleted");
        ModelAndView modelAndView = new ModelAndView("result_delete");
        return modelAndView;

    }
}
