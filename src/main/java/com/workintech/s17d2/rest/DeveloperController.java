package com.workintech.s17d2.rest;

import com.workintech.s17d2.dto.DeveloperResponse;
import com.workintech.s17d2.model.Developer;
import com.workintech.s17d2.model.DeveloperFactory;
import com.workintech.s17d2.tax.DeveloperTax;
import com.workintech.s17d2.tax.Taxable;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/developers")
public class DeveloperController {

   public Map<Integer, Developer> developers;
   private Taxable taxable;


    @Autowired
    public DeveloperController(Taxable taxable) {
        this.taxable = taxable;
    }

    @PostConstruct
    public void init(){
        developers = new HashMap<>();
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DeveloperResponse save(@RequestBody Developer developer){
        Developer createdDeveloper = DeveloperFactory.createDeveloper(developer, taxable);
            if(Objects.nonNull(createdDeveloper)){
                developers.put(createdDeveloper.getId(), createdDeveloper);

            }
            return new DeveloperResponse(createdDeveloper,HttpStatus.CREATED.value(), "Creat işlemi tamamlandı !");


    }

    @GetMapping
    public List<Developer> getAll(){
        return developers.values().stream().toList();

    }

    @GetMapping("/{id}")
    public DeveloperResponse getDeveloperById(@PathVariable int id){
        Developer foundDeveloper = developers.get(id);

        if(foundDeveloper == null){
            return new DeveloperResponse(null, HttpStatus.NOT_FOUND.value(), "Kayıt bulunamadı !");
        }
        return new DeveloperResponse(foundDeveloper,HttpStatus.OK.value(), "Kayıt bulma başarılı !");

    }

    @PutMapping("/{id}")
    public DeveloperResponse update(@PathVariable int id, @RequestBody Developer developer){
        developer.setId(id);
        Developer newDeveloper = DeveloperFactory.createDeveloper(developer, taxable);
        developers.put(id,developer);

        return new DeveloperResponse(newDeveloper,HttpStatus.OK.value(), "Değiştirme başarılı !");
    }

    @DeleteMapping("/{id}")

    public DeveloperResponse delete(@PathVariable int id){

        Developer removeDeveloper = developers.get(id);
        developers.remove(id);

        return new DeveloperResponse(removeDeveloper, HttpStatus.NO_CONTENT.value(), "Silinme işlemi başarılı");
    }



}
