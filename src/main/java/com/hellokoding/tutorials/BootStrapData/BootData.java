package com.hellokoding.tutorials.BootStrapData;

import com.hellokoding.tutorials.model.Produs;

import com.hellokoding.tutorials.repository.ProdusRepository;
import com.hellokoding.tutorials.repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class BootData implements CommandLineRunner {
    private final UserRepository personRepository;
    private final ProdusRepository produsRepository;

    public BootData(ProdusRepository produsRepository1, UserRepository personRepository) {
        this.personRepository = personRepository;
        this.produsRepository = produsRepository1;

    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Started in Bootstrap");
//        User p1=new User();
//        p1.setPassword("12345678");
//        p1.setUsername("12345678");
//        p1.setPasswordConfirm("12345678");
        //Account p2=new Account(24, "Newton Jack","1234");



        Produs produs1=new Produs();
       produs1.setPret(2.0);
       produs1.setNume("Portocale");
       produs1.setProducator("ing");
       produs1.setCantitate(42);

//
//        Set<Produs> s1 =new HashSet<>();
//        s1.add(produs2);
//        s1.add(produs1);
//        //Cos c1=new Cos(12,s1,p1);
//        //personRepository.save(p1);
//        //personRepository.save(p2);
        produsRepository.save(produs1);
//        //cosRepository.save(c1);
//
//        System.out.println("product: "+produsRepository.count());
//        System.out.println("persoana: "+personRepository.count());


    }
}