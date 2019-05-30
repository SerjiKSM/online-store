package net.ukr.ksm.controller;

import net.ukr.ksm.dao.PictureDAO;
import net.ukr.ksm.dao.ProductDAO;
import net.ukr.ksm.dao.ProductPictureDAO;
import net.ukr.ksm.dao.ProductStockDAO;
import net.ukr.ksm.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin/")
public class AdminController {

    @Autowired
    private Environment env;

    @RequestMapping("/")
    public String pageAdmin(Model model) {
        ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext("Spring-Module.xml");
        ProductDAO productDAO = (ProductDAO) applicationContext.getBean("productDAO");

        List<Product> products = productDAO.listProductsWithPictures();
        model.addAttribute("products", products);
        return "admin";
    }

    @RequestMapping("/page-add-product")
    public String pageAddProduct(Model model) {
        return "page-add-product";
    }

    @RequestMapping(value = "/add-product", method = RequestMethod.POST)
    public String addProduct(Model model,
                             @RequestParam(value="name", required=true) String name,
                             @RequestParam(value="description", required=true) String description,
                             @RequestParam(value="price", required=true) double price,
                             @RequestParam(value="quantity", required=true) int quantity,
                             @ModelAttribute("uploadForm") FileUploadForm uploadForm,
                             HttpServletRequest request) {

        ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext("Spring-Module.xml");
        ProductDAO productDAO = (ProductDAO) applicationContext.getBean("productDAO");
        ProductStockDAO productStockDAO = (ProductStockDAO) applicationContext.getBean("productStockDAO");
        PictureDAO pictureDAO = (PictureDAO) applicationContext.getBean("pictureDAO");
        ProductPictureDAO productPictureDAO = (ProductPictureDAO) applicationContext.getBean("productPictureDAO");

        String nameProduct = HtmlUtils.htmlEscape(name);
        String descriptionProduct = HtmlUtils.htmlEscape(description);
        double priceProduct = Double.parseDouble(HtmlUtils.htmlEscape(String.valueOf(price)));
        int quantityProduct = Integer.parseInt(HtmlUtils.htmlEscape(String.valueOf(quantity)));

        Product product = new Product();
        product.setName(nameProduct);
        product.setDescription(descriptionProduct);
        product.setPrice(priceProduct);
        Long id = productDAO.insertProduct(product);

        ProductStock productStock = new ProductStock();
        productStock.setId_product(id);
        productStock.setQuantity(quantityProduct);
        productStockDAO.insertProductStock(productStock);

        Set<String> fileNames = this.doUpload(request, model, uploadForm);
        for (String fileName : fileNames) {
            if (fileName.length() > 0) {
                Picture picture = new Picture();
                picture.setLink(fileName);
                long idPicture = pictureDAO.insertPicture(picture);

                ProductPicture productPicture = new ProductPicture();
                productPicture.setPictureId(idPicture);
                productPicture.setProductId(id);
                productPictureDAO.insertProductPicture(productPicture);
            }
        }

        List<Product> products = productDAO.listProductsWithPictures();

        model.addAttribute("products", products);
        model.addAttribute("message", "Product added success!");
        model.addAttribute("showMessage", true);

        return "admin";
    }

    private Set<String> doUpload(HttpServletRequest request,
                                  Model model,
                                  FileUploadForm uploadForm) {

        String uploadRootPath = request.getServletContext().getRealPath(env.getProperty("image.path"));

        File uploadRootDir = new File(uploadRootPath);

        if (!uploadRootDir.exists()) {
            uploadRootDir.mkdirs();
        }

        List<MultipartFile> files = uploadForm.getFiles();

        Set<String> fileNames = new HashSet<String>();

        if(null != files && files.size() > 0) {
            for (MultipartFile multipartFile : files) {

                String fileName = multipartFile.getOriginalFilename();
                fileNames.add(fileName);

                if (fileName != null && fileName.length() > 0) {
                    try {
                        multipartFile.transferTo(new File(uploadRootPath + multipartFile.getOriginalFilename()));

//                        File serverFile = new File(uploadRootDir.getAbsolutePath()  + File.separator +  fileName);
//                        BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
//                        stream.write(multipartFile.getBytes());
//                        stream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return fileNames;
    }

    @RequestMapping("/remove-project")
    public String removeProject(@RequestParam(value="id") long id) {
        ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext("Spring-Module.xml");
        ProductDAO productDAO = (ProductDAO) applicationContext.getBean("productDAO");
        long idProduct = Long.parseLong(HtmlUtils.htmlEscape(String.valueOf(id)));
        productDAO.removeProduct(idProduct);
        return "redirect:/admin/";
    }

    @RequestMapping("/page-edit-product")
    public String pageEditProduct(Model model, @RequestParam(value="id") long id) {
        ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext("Spring-Module.xml");
        ProductDAO productDAO = (ProductDAO) applicationContext.getBean("productDAO");
        long idProduct = Long.parseLong(HtmlUtils.htmlEscape(String.valueOf(id)));
        List<Object> productData = productDAO.getProductDataById(idProduct);

        model.addAttribute("productData", productData);

        return "page-edit-product";
    }

    @RequestMapping(value = "/update-product/{id}", method = RequestMethod.POST)
    public String updateProject(@RequestParam(value="name") String name,
                                @RequestParam(value="description", required = true) String description,
                                @RequestParam(value="price", required=true) double price,
                                @RequestParam(value="quantity", required=true) int quantity,
                                @PathVariable("id") long id,
                                HttpServletRequest request) {
        ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext("Spring-Module.xml");
        ProductDAO productDAO = (ProductDAO) applicationContext.getBean("productDAO");
        ProductStockDAO productStockDAO = (ProductStockDAO) applicationContext.getBean("productStockDAO");

        String nameProduct = HtmlUtils.htmlEscape(name);
        String descriptionProduct = HtmlUtils.htmlEscape(description);
        double priceProduct = Double.parseDouble(HtmlUtils.htmlEscape(String.valueOf(price)));
        int quantityProduct = Integer.parseInt(HtmlUtils.htmlEscape(String.valueOf(quantity)));
        long idProduct = Long.parseLong(HtmlUtils.htmlEscape(String.valueOf(id)));

        productDAO.updateProduct(idProduct, nameProduct, descriptionProduct, priceProduct);
        ProductStock productStock = productStockDAO.getProductStockByIdProduct(idProduct);
        if (productStock != null) {
            productStock.setQuantity(quantityProduct);
            productStockDAO.updateProductStock(productStock);
        } else {
            ProductStock stock = new ProductStock();
            stock.setId_product(idProduct);
            stock.setQuantity(quantityProduct);
            productStockDAO.insertProductStock(stock);
        }

        return "redirect:/admin/";
    }


}
