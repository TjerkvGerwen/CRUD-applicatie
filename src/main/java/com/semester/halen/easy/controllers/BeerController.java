package com.semester.halen.easy.controllers;

import com.semester.halen.easy.entities.Beer;
import com.semester.halen.easy.repositories.BeerRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/beers")
public class BeerController {
    BeerRepository repository;
    public BeerController(BeerRepository _repository){
        repository = _repository;
    }

    @GetMapping("/{id}")
    public Optional<Beer> getBeer(@PathVariable int id){
        return repository.findById(id);
    }


    @GetMapping()
    public List<Beer> getBeers(){
        return repository.findAll();
    }

    @PostMapping()
    public Beer createBeer(@RequestParam String name, @RequestParam float alcoholPercentage){
        return repository.save(
                new Beer(name, alcoholPercentage)
        );
    }

    @PutMapping()
    public Beer updateBeer(@RequestBody Beer _beer){
        if (repository.findById(_beer.getId()).isPresent()) {
            return repository.save(_beer);
        }
        return _beer;
    }

    @DeleteMapping
    public String deleteBeer(@RequestBody Beer _beer){
        repository.deleteById(_beer.getId());
        return "Biertje : " + _beer.getName() + " is verwijderd";
    }
}
