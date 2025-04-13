package com.workintech.s17d2.model;

import com.workintech.s17d2.tax.Taxable;

public class DeveloperFactory {

    public static Developer createDeveloper(Developer developer, Taxable taxable){
        Developer createdDeveloper = null;

        if(developer.getExperince().equals(Experince.JUNIOR)){
            createdDeveloper =  new JuniorDeveloper(developer.getId(), developer.getName(), developer.getSalary()
                    - (developer.getSalary() * taxable.getSimpleTaxRate() / 100));
        }
        else if(developer.getExperince().equals(Experince.MID)){
            createdDeveloper =  new MidDeveloper(developer.getId(), developer.getName(), developer.getSalary()
                    - (developer.getSalary() * taxable.getMiddleTaxRate() / 100));
        }
        else if(developer.getExperince().equals(Experince.JUNIOR)){
            createdDeveloper =  new JuniorDeveloper(developer.getId(), developer.getName(), developer.getSalary()
                    - (developer.getSalary() * taxable.getUpperTaxRate() / 100));
        }
        else {
            System.out.println("Geçersiz");
            return null;
        }

        return createdDeveloper;

    }
}
