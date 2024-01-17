package com.md4_demo_spring_boot.controller;

import com.md4_demo_spring_boot.model.Product;
import com.md4_demo_spring_boot.repository.CategoryRepository;
import com.md4_demo_spring_boot.repository.ProductRepository;
import com.md4_demo_spring_boot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ProductService productService;
    @GetMapping
    public ModelAndView showAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size){
        ModelAndView modelAndView = new ModelAndView("product/list");
        modelAndView.addObject("page", productService.getAllProduct(page, size));
        modelAndView.addObject("listCategory", categoryRepository.findAll());
        return modelAndView;
    }
    @GetMapping("/add")
    public ModelAndView showAddForm(){
        ModelAndView modelAndView = new ModelAndView("product/create");
        modelAndView.addObject("listCategory", categoryRepository.findAll());
        modelAndView.addObject("item", new Product());
        return modelAndView;
    }
    @PostMapping("/save")
    public String add(Product product){
        productRepository.save(product);
        return "redirect:/products";
    }
    @GetMapping("/edit/{id}")
    public ModelAndView showEditForm(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("/product/edit");
        modelAndView.addObject("listCategory", categoryRepository.findAll());
        modelAndView.addObject("item", productRepository.findById(id).get());
        return modelAndView;
    }
    @PostMapping("/update")
    public String edit(Product product){
        productRepository.save(product);
        return "redirect:/products";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        productRepository.deleteById(id);
        return "redirect:/products";
    }
    @GetMapping("/search")
    public ModelAndView searchByName(@RequestParam String search, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size){
        ModelAndView modelAndView = new ModelAndView("product/list");
        modelAndView.addObject("page", productService.getAllByName(page, size, search));
        return modelAndView;
    }
    @GetMapping("/search-by-price")
    private ModelAndView searchByPriceBetween(@RequestParam Double star, Double end, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size){
        ModelAndView modelAndView = new ModelAndView("product/list");
        modelAndView.addObject("page", productService.getAllByPriceBetween(page, size, star, end));
        return modelAndView;
    }
    @GetMapping("/sort-up")
    private ModelAndView sortUpByPrice(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size){
        ModelAndView modelAndView = new ModelAndView("product/list");
        modelAndView.addObject("page", productService.getAllByPrice(page, size));
        return modelAndView;
    }
    @GetMapping("/sort-down")
    private ModelAndView sortDownByPrice(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size){
        ModelAndView modelAndView = new ModelAndView("product/list");
        modelAndView.addObject("page", productService.getAllByPriceDesc(page, size));
        return modelAndView;
    }
}
