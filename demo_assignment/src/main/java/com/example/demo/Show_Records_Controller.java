package com.example.demo;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
public class Show_Records_Controller {

    final static Logger logger=Logger.getLogger("Show_Record_Controller");

    @RequestMapping(value = "/show_records", method = RequestMethod.GET)
    public ModelAndView showRecords() {
        Patient_DB_Manager patient_db_manager= new Patient_DB_Manager();
        List<Patient> listPatient=patient_db_manager.Get_all_records();
        patient_db_manager.close();
        logger.info("Showing Records");
        return new ModelAndView("view_record", "lists", listPatient);
    }

}
