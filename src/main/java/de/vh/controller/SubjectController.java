package de.vh.controller;

import de.vh.entity.Subject;
import de.vh.repository.SubjectRepository;
import de.vh.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.awt.print.Pageable;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

@Controller
public class SubjectController {

    @Autowired
    SubjectRepository subjectRepository;


    @GetMapping(value = "/videos/{id}", produces = "video/mp4")
    @ResponseBody
    public FileSystemResource findVideoById(@PathVariable int id) {
        Subject subject = subjectRepository.findById(id).orElse(subjectRepository.findById(0).get());
        return new FileSystemResource(new File(subject.getVideo()));
    }

    @GetMapping(value = "/pdfs/{id}", produces = "application/pdf")
    @ResponseBody
    public FileSystemResource findPdfById(@PathVariable int id) {
        Subject subject = subjectRepository.findById(id).orElse(subjectRepository.findById(0).get());
        return new FileSystemResource(new File(subject.getPdf()));
    }

    @GetMapping("/")
    public String welcome(HttpServletResponse response, Model model) {
        List<String> subjects = new ArrayList<String>();
        for (Subject sub : subjectRepository.findAll()) {
            if (!subjects.contains(sub.getSubject())) {
                subjects.add(sub.getSubject());
            }
        }
        model.addAttribute("subjects", subjects);
        return "overview";
    }

    @GetMapping("/subjects")
    public String getAllSubjects(Model model) {
        model.addAttribute("subjects", subjectRepository.findAll());
        return "subjects";
    }


    @GetMapping("/contacts")
    public String getImportantNumbers() {
        return "important_numbers";
    }

    @GetMapping("/manuals")
    public String getManuals() {
        return "manuals";
    }

    @GetMapping("/advoware")
    public String getAdvoware(Model model) {
        model.addAttribute("subjects", Util.filterBySubject(subjectRepository.findAll(),"advoware"));
        return "advoware";
    }



    @GetMapping("/bea")
    public String getbeA(Model model) {
        model.addAttribute("subjects", Util.filterBySubject(subjectRepository.findAll(),"bea"));
        return "bea";
    }

    @GetMapping("/outlook")
    public String getOutlook(Model model) {
        model.addAttribute("subjects", Util.filterBySubject(subjectRepository.findAll(),"outlook"));
        return "outlook";
    }

    @GetMapping("/telefon")
    public String getTelefon(Model model) {
        model.addAttribute("subjects", Util.filterBySubject(subjectRepository.findAll(),"telefon"));
        return "telefon";
    }

    @GetMapping("/drucker")
    public String getDrucker(Model model) {
        model.addAttribute("subjects", Util.filterBySubject(subjectRepository.findAll(),"drucker"));
        return "drucker";
    }


    @GetMapping("/divers")
    public String getDivers(Model model) {

        model.addAttribute("subjects", Util.filterBySubject(subjectRepository.findAll(),"divers"));
        return "divers";
    }


    @GetMapping(path = "/all", produces = "application/json")
    public Iterable<Subject> getAll() {
        return subjectRepository.findAll();
    }

    @PostMapping(path = "/add")
    public void addSubject(@RequestBody Subject subject) {
        subjectRepository.save(subject);
    }

    @PutMapping(path = "/edit/{id}")
    public void editSubject(@PathVariable int id, @RequestBody Subject subject) {
        Subject subToEdit = subjectRepository.findById(id).get();
        subToEdit.setDescription(subject.getDescription());
        subToEdit.setExplanation(subject.getExplanation());
        subToEdit.setPdf(subject.getPdf());
        subToEdit.setVideo(subject.getVideo());
        subjectRepository.save(subToEdit);
    }

    @DeleteMapping(path = "/delete/{id}")
    public void deleteSubject(@PathVariable int id) {
        subjectRepository.deleteById(id);
    }




}
