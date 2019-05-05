package com.example.demo;

import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.apache.log4j.Logger;

@RestController
public class Adding_Record_Controller {

    final static Logger logger=Logger.getLogger("Adding_Record_Controller");

    @RequestMapping(value = "/patient", method = RequestMethod.GET)
    public ModelAndView showForm() {
        logger.info("Viewing Form");
        ModelAndView modelAndView= new ModelAndView("form");
        modelAndView.addObject("error","");
        modelAndView.addObject("patient",new Patient());
        return modelAndView;
    }
    @RequestMapping(value = "/addPatient", method = RequestMethod.POST)
    public ModelAndView submit(@ModelAttribute("patient")Patient patient,
                         BindingResult result, ModelMap model) {
        model.addAttribute("Name", patient.getName());
        model.addAttribute("Phone_number", patient.getPhone_number());
        model.addAttribute("Address", patient.getAddress());
        model.addAttribute("Email", patient.getEmail());
        model.addAttribute("Password", patient.getPassword());

        Patient_DB_Manager patient_db_manager= new Patient_DB_Manager();
        int validate=patient_db_manager.validate(patient);
        if(validate!=0){
            String error=patient_db_manager.get_error(validate);
            ModelAndView modelAndView= new ModelAndView("form");
            modelAndView.addObject("error",error);
            logger.error("Invalid Parameter(s) for Patient");
            return modelAndView;
        }
        ModelAndView modelAndView=new ModelAndView("result_add");
        patient_db_manager.add_record(patient);
        modelAndView.addObject("patient",patient);
        patient_db_manager.close();
        logger.info("Patient Record Successfully Added");
        return modelAndView;
    }
}
