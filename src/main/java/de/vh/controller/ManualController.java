package de.vh.controller;

import de.vh.entity.Manuals;
import de.vh.entity.Subject;
import de.vh.repository.ManualRepository;
import de.vh.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;


@Controller
public class ManualController {


    @Autowired
    ManualRepository manualRepository;


    @GetMapping("/manuals")
    public String getAnleitungen(Model model) {
        model.addAttribute("manuals", manualRepository.findAll());
        return "manuals";
    }

    @GetMapping(value = "/pdfmanuals/{id}", produces = "application/pdf")
    @ResponseBody
    public FileSystemResource findPdfById(@PathVariable int id) {
        Manuals manual = manualRepository.findById(id).orElse(manualRepository.findById(0).get());
        return new FileSystemResource(new File(manual.getPdf()));
    }





}
