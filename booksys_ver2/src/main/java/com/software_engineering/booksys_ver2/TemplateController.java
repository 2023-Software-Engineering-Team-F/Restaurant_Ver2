package com.software_engineering.booksys_ver2;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TemplateController {

  @GetMapping("/")
  public String mainTable(Model model) {

    return "MainTable";
  }

  @GetMapping("/reservation")
  public String reservation(Model model) {

    return "reservation";
  }

  @GetMapping("/walkin")
  public String walkin(Model model) {

    return "walkin";
  }

  @GetMapping("/cancel-change")
  public String cancel_change(Model model) {

    return "cancel-change";
  }

  @GetMapping("/shiftTable.html")
  public String shiftTable(Model model) {

    return "shiftTable";
  }



}
