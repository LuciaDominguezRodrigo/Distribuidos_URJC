package com.ssdd.UrbanThreads.UrbanThreads.controllers;


import com.ssdd.UrbanThreads.UrbanThreads.entities.*;
import com.ssdd.UrbanThreads.UrbanThreads.services.CategoryService;
import com.ssdd.UrbanThreads.UrbanThreads.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;


import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.*;


@Controller
public class ProductWebController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    private int nextProductIndex = 3;
    private int productsRefreshSize = 4; //this number controls how many elements are charged
    //with 'cargar más'button


    @GetMapping("/")
    public String index(Model model) {
        nextProductIndex = productsRefreshSize;
        List<Product> products = productService.findByCurrentCategoryAndIdRange(0, nextProductIndex);

        if (products.isEmpty()) {
            model.addAttribute("products", new ArrayList<Product>());
        } else {
            model.addAttribute("products", products);
        }
        nextProductIndex = products.size();
        model.addAttribute("allCategories", categoryService.findAllC());
        for (Category category: categoryService.findAllC()){
            model.addAttribute("name", category.getName());
        }
        return "index";
    }

    @GetMapping("/event/image/{id}")
    @ResponseBody
    public byte[] showEventImage(@PathVariable long id) throws SQLException, IOException {
        Optional<Product> eventOptional = productService.findProduct(id);
        if (eventOptional.isPresent()) {
            Blob photoBlob = eventOptional.get().getPhoto();
            int blobLength = (int) photoBlob.length();
            byte[] blobAsBytes = photoBlob.getBytes(1, blobLength);
            photoBlob.free();
            return blobAsBytes;
        } else {
            return new byte[0];
        }
    }


    @GetMapping("/newProducts")
    public String newEvents(Model model) {
        List<Product> products = productService.findByCurrentCategoryAndIdRange(nextProductIndex, (nextProductIndex + productsRefreshSize) - 1);
        nextProductIndex += products.size();
        model.addAttribute("additionalProducts", products);
        if (nextProductIndex > productService.findAllProducts().size()) { //To show / hide Load more products button
            model.addAttribute("loadMoreProducts", false);
        } else {
            model.addAttribute("loadMoreProducts", true);
        }

        return "moreProducts";
    }

    @GetMapping("/product/{id}")
    public String showProduct(Model model, @PathVariable long id) {
        Optional<Product> productOptional = productService.findProduct(id);

        if (productOptional.isPresent()) {
            Product product = productOptional.get();

            // Fetch category details for the product
            Optional<Category> category = categoryService.findCategory(product.getCategoryId());
            category.ifPresent(dCategory -> model.addAttribute("categoryName", dCategory.getName()));
            model.addAttribute("product", product);

            //Available sizes for dynamic JS stock checking
            model.addAttribute("sizeXS", product.getAvailableSizes().get(Size.XS));
            model.addAttribute("sizeS", product.getAvailableSizes().get(Size.S));
            model.addAttribute("sizeM", product.getAvailableSizes().get(Size.M));
            model.addAttribute("sizeL", product.getAvailableSizes().get(Size.L));
            model.addAttribute("sizeXL", product.getAvailableSizes().get(Size.XL));
            model.addAttribute("sizeXXL", product.getAvailableSizes().get(Size.XXL));

            return "productDetails";
        } else {
            return "error";
        }
    }

    @GetMapping("/editProduct/{id}")
    public String editEvent(@PathVariable int id, Model model) {
        Optional<Product> productOptional = productService.findProduct(id);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            model.addAttribute("product", product);
            List<Category> otherCategories = (List<Category>) categoryService.getAllCategories();
            List<Category> visibleC = new ArrayList<>();
            for (Category c: otherCategories){
                if (!Objects.equals(c.getName(), "Sin Categoria")){
                    visibleC.add(c);
                }
            }

            model.addAttribute("otherCategories", visibleC);
            // Verificar si el producto tiene tallas disponibles
            if (product.getAvailableSizes() != null) {
                model.addAttribute("sizeXS", product.getAvailableSizes().getOrDefault(Size.XS, 0));
                model.addAttribute("sizeS", product.getAvailableSizes().getOrDefault(Size.S, 0));
                model.addAttribute("sizeM", product.getAvailableSizes().getOrDefault(Size.M, 0));
                model.addAttribute("sizeL", product.getAvailableSizes().getOrDefault(Size.L, 0));
                model.addAttribute("sizeXL", product.getAvailableSizes().getOrDefault(Size.XL, 0));
                model.addAttribute("sizeXXL", product.getAvailableSizes().getOrDefault(Size.XXL, 0));
            } else {
                // Si no hay tallas disponibles, establecer todas las tallas en 0
                model.addAttribute("sizeXS", 0);
                model.addAttribute("sizeS", 0);
                model.addAttribute("sizeM", 0);
                model.addAttribute("sizeL", 0);
                model.addAttribute("sizeXL", 0);
                model.addAttribute("sizeXXL", 0);
            }

            // Verificar si el producto tiene una categoría
            if (product.getCategory() != null) {
                model.addAttribute("categoryName", product.getCategory().getName());
            } else {
                model.addAttribute("categoryName", "N/A");
            }
        }
        return "createForm";
    }

    @PostMapping("/editProduct/{id}")
    public String editEvent(@PathVariable int id,
                            @RequestParam("name") String name,
                            @RequestParam("description") String description,
                            @RequestParam ("category") String category,
                            @RequestParam("price") double price,
                            @RequestParam("sizeXS") int xsUnits,
                            @RequestParam("sizeS") int sUnits,
                            @RequestParam("sizeM") int mUnits,
                            @RequestParam("sizeL") int lUnits,
                            @RequestParam("sizeXL") int xlUnits,
                            @RequestParam("sizeXXL") int xxlUnits,
                            @RequestParam("photo") String photo) {

        Optional<Product> oproduct = productService.findProduct(id);
        if (oproduct.isPresent()) {
            Product product = oproduct.get();

            product.setName(name);
            //product.setCategory(categoryService.findCategory(categoryId));
            product.setPrice(price);
            product.setDescription(description);

            Map<Size, Integer> availableSizes = new HashMap<>();
            availableSizes.put(Size.XS, xsUnits);
            availableSizes.put(Size.S, sUnits);
            availableSizes.put(Size.M, mUnits);
            availableSizes.put(Size.L, lUnits);
            availableSizes.put(Size.XL, xlUnits);
            availableSizes.put(Size.XXL, xxlUnits);

            product.setAvailableSizes(availableSizes);
            product.setPhoto2(photo);

            product.setCategory(categoryService.findCategoryByName(category));
            productService.updateProduct(id, product);
        }
        return "redirect:/";
    }

    @PostMapping("/createProduct")
    public String newProduct(@RequestParam("name") String name,
                             @RequestParam ("category") String category,
                             @RequestParam("description") String description,
                             @RequestParam("price") double price,
                             @RequestParam("sizeXS") int xsUnits,
                             @RequestParam("sizeS") int sUnits,
                             @RequestParam("sizeM") int mUnits,
                             @RequestParam("sizeL") int lUnits,
                             @RequestParam("sizeXL") int xlUnits,
                             @RequestParam("sizeXXL") int xxlUnits,
                             @RequestParam("photo") MultipartFile file) throws IOException {

        Map<Size, Integer> availableSizes = new HashMap<>();
        availableSizes.put(Size.XS, xsUnits);
        availableSizes.put(Size.S, sUnits);
        availableSizes.put(Size.M, mUnits);
        availableSizes.put(Size.L, lUnits);
        availableSizes.put(Size.XL, xlUnits);
        availableSizes.put(Size.XXL, xxlUnits);

        Category c = categoryService.findCategoryByName(category);

        try {
            // Convertir el contenido del archivo a un arreglo de bytes
            byte[] bytes = file.getBytes();

            // Crear un objeto Blob a partir del arreglo de bytes
            Blob blob = new SerialBlob(bytes);


            Product newProduct = new Product(name, c, price, blob, description, availableSizes);
            productService.saveProduct(newProduct);

            return "redirect:/";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping("/createProduct")
    public String newProductCharge(Model model) {
        Collection<Category> categories = categoryService.findAllC();
        List<Category> shownC  = new ArrayList<>();
        for (Category c:categories) {
            if (!Objects.equals(c.getName(), "Sin Categoria")){
                shownC.add(c);
            }
        }
        model.addAttribute("allCategories", shownC);
        return "createForm";
    }

    @PostMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable int id) {
        productService.deleteProduct(id);
        return "redirect:/";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }



}
