package com.amela.Controllers;

import com.amela.Models.City;
import com.amela.Repository.ICityRepository;
import com.amela.Service.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
public class CityController {
    private static final String DETAIL_PARAM = "detail";
    private static final String CREATE_PARAM = "create";
    private static final String EDIT_PARAM = "edit";
    private static final String DELETE_PARAM = "delete";

    private static final String SEARCH_PARAM = "search";
    private static final String GO_HOME = "cities?action";

    @Autowired
    private ICityService cityService;

  /*  @ModelAttribute("cities")
    public List<City> initCities()
    {
        return cityService.findAll();
    }*/
    @ModelAttribute("city")
    public City initCity()
    {
        return new City();
    }

//List
    @GetMapping("/cities")
    public ModelAndView index(@RequestParam("searchView") Optional<String> search,
                              @PageableDefault(value = 5) Pageable pageable)
    {
        ModelAndView modelAndView = new ModelAndView("list");
        if (search.isPresent())
        {
            modelAndView.addObject("searchResult", search.get());
            modelAndView.addObject("cities", cityService.findByName(search.get(), pageable) );
        }
        else
            modelAndView.addObject("cities", cityService.findAll(pageable) );

        return modelAndView;
    }

//Create
    @GetMapping("/create-city")
    public String create()
    {
        return "create";
    }
    @PostMapping("/create-city")
    public ModelAndView createAccept(@ModelAttribute("city") City city, RedirectAttributes redirectAttributes)
    {
        ModelAndView modelAndView = new ModelAndView("redirect:/create-city");
        redirectAttributes.addFlashAttribute("message", "Them moi thanh cong!");
        cityService.save(city);
        return modelAndView;
    }

//Edit
    @GetMapping("/edit-city/{id}")
    public ModelAndView edit(@PathVariable("id") int ID)
    {
        ModelAndView modelAndView = new ModelAndView("edit");
        City city = cityService.findById(ID);
        modelAndView.addObject("city_edit", city);
        return modelAndView;
    }
    @PostMapping("/edit-city/{id}")
    public String editAccept(@Valid @ModelAttribute("city_edit") City city, BindingResult bindingResult, Model model) {
            if(bindingResult.hasFieldErrors())
                return "edit";
            cityService.save(city);
            model.addAttribute("message", "Luu thanh cong!");
            return "edit";
    }

//Delete
    @GetMapping("/delete-city/{id}")
    public String delete(@PathVariable("id") int ID, Model model)
    {
        City city = cityService.findById(ID);
        model.addAttribute("city_delete", city);
        return "delete";
    }
    @PostMapping("/delete-city/{id}")
    public String delete(@PathVariable("id") int ID, @RequestParam("action") String action,
                         RedirectAttributes redirectAttributes)
    {
        if (action.equals("confirm"))
        {
            redirectAttributes.addFlashAttribute("message", "Da xoa 1 thanh pho!");
            cityService.delete(ID);
        }
        return "redirect:/cities";
    }

//Detail
    @GetMapping("/cities/{id}")
    public ModelAndView detail(@PathVariable("id") int ID)
    {
        ModelAndView modelAndView = new ModelAndView("detail");
        City city = cityService.findById(ID);
        modelAndView.addObject("city_detail", city);
        return modelAndView;
    }

}
