package com.example.demo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Comparator;
import java.util.List;
public class Patient_DB_Manager {
    EntityManagerFactory entityManagerFactory;
    EntityManager entityManager;
    public Patient_DB_Manager(){
        entityManagerFactory= Persistence.createEntityManagerFactory("aatish");
        entityManager= entityManagerFactory.createEntityManager();
    }
    public int add_record(Patient patient){
        entityManager.getTransaction().begin();
        entityManager.persist(patient);
        entityManager.getTransaction().commit();
        return 1;
    }
    public int update_record(Patient Old_patient_data,Patient patient){
        entityManager.getTransaction().begin();
        List<Patient> listPatient = entityManager.createQuery("SELECT p FROM Patient p").getResultList();
        for (Patient p1 : listPatient){
            if(check(p1,Old_patient_data)){
                p1.setName(patient.getName());
                p1.setAddress(patient.getAddress());
                p1.setPassword(patient.getPassword());
                p1.setEmail(patient.getEmail());
                p1.setPhone_number(p1.getPhone_number());
                entityManager.getTransaction().commit();
                return 1;
            }
        }
        entityManager.getTransaction().commit();
        System.out.println("Patient record not found!! ERROR!");
        return 0;
    }

    public int delete_record(Patient patient){
        entityManager.getTransaction().begin();
        List<Patient> listPatient = entityManager.createQuery("SELECT p FROM Patient p").getResultList();
        for (Patient p1 : listPatient){
            if(check(p1,patient)){
                entityManager.remove(p1);
                entityManager.getTransaction().commit();
                return 1;
            }
        }
        entityManager.getTransaction().commit();
        System.out.println("Patient record not found!! ERROR!");
        return 0;
    }
    public List<Patient> Get_all_records(){
        @SuppressWarnings("unchecked")
        List<Patient> listPatient = entityManager.createQuery("SELECT p FROM Patient p").getResultList();
        listPatient.sort(new sortbyid());
        for (Patient p : listPatient){
            System.out.println(p.getId()+"  "+p.getName()+" "+p.getEmail());
        }
        return listPatient;
    }
    void close(){
        entityManager.close();
        entityManagerFactory.close();
    }
    private  boolean  check(Patient p1,Patient p2){
        int count=0;
        if(p1.getName().contains(p2.getName()) && p2.getName().contains(p1.getName()))
            count++;
        if(p1.getPassword().contains(p2.getPassword()) && p2.getPassword().contains(p1.getPassword()))
            count++;
        if (count==2)
            return true;
        return false;
    }
    public  int validate_Id_and_Password(Patient patient){
        List<Patient> listPatient = entityManager.createQuery("SELECT p FROM Patient p").getResultList();
        for (Patient p1 : listPatient){
            if(check(p1,patient)){
                return 1;
            }
        }
        return 0;
    }
    public int validate(Patient patient){
        if(patient.getName().length()<3){
            return 1;

        }
        System.out.println(patient.getAddress());
        if(patient.getAddress().length()<10){
            return 2;
        }

        if (!(patient.getEmail().contains("@") && patient.getEmail().contains(".")) || patient.getEmail().length()<=5){
            return 3;
        }

        int isnumm=0;
        for (int i=0;i<patient.getPhone_number().length();i++){
            char cc= patient.getPhone_number().charAt(i);
            int numm=(int)cc;
            if(numm>=48 && numm<=57)
                isnumm++;
        }
        if(patient.getPhone_number().length()<10 || patient.getPhone_number().length()>13 || isnumm<10){
            return 4;
        }
        int isup=0;
        int islow=0;
        int isnum=0;
        int got=0;
        for (int i=0;i<patient.getPassword().length();i++){
            char c= patient.getPassword().charAt(i);
            int num=(int)c;
            if(num>=65 && num<=90)
                isup=1;
            if(num>=97 && num<=122)
                islow=1;
            if(num>=48 && num<=57)
                isnum=1;
            if((islow+isnum+isup)==3){
             got=1;
             break;
            }
        }
        if(got==0)
            return 5;

        return 0;
    }
    String get_error(int i){
        if (i==1)
            return "Error:Name must be atleast 3 characters.Try again.";
        if (i==2)
            return "Error:Address must be atleast 10 characters.Try again.";
        if (i==3)
            return "Error:Invalid Email address.Try again.";
        if (i==4)
            return  "Error:Invalid Phone number.Try again.";
        if (i==5)
            return "Error:Password must contain one upper case, one lower case and one number.Try again.";
        return "no_error";
    }
}
class  sortbyid implements Comparator<Patient>{
    public int compare(Patient o1, Patient o2) {
        return o1.getId()-o2.getId();
    }
}

