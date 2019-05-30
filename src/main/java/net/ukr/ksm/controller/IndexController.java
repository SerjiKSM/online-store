package net.ukr.ksm.controller;

import net.ukr.ksm.dao.ProductDAO;
import net.ukr.ksm.dao.ProductStockDAO;
import net.ukr.ksm.model.Product;
import net.ukr.ksm.model.ProductStock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private Environment env;

    @RequestMapping("/")
    public String index(Model model, HttpServletRequest request) {
        ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext("Spring-Module.xml");
        ProductDAO productDAO = (ProductDAO) applicationContext.getBean("productDAO");

        List<Product> products = productDAO.listProductsWithPictures();
        model.addAttribute("products", products);

        return "list";
    }

    @RequestMapping("/page-edit-quantity")
    public String pageEditProduct(@RequestParam(value = "id") long id,
                                  Model model,
                                  HttpServletRequest request) {
        ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext("Spring-Module.xml");
        ProductStockDAO productStockDAO = (ProductStockDAO) applicationContext.getBean("productStockDAO");

        ProductStock productStock = productStockDAO.getProductStockQuantityById(id);
        model.addAttribute("product", productStock);

        HttpSession session = request.getSession();
        session.setAttribute("productStock", productStock);

        if(productStock == null) {
            model.addAttribute("errorQuantity", "Error get quantity!");
        }

        return "page-edit-quantity";
    }

    @RequestMapping("/list")
    public String list(Model model) {
        ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext("Spring-Module.xml");
        ProductDAO productDAO = (ProductDAO) applicationContext.getBean("productDAO");

        List<Product> products = productDAO.listProductsWithPictures();
        model.addAttribute("products", products);

        return "list";
    }

    @RequestMapping(value = "/update-product-quantity/{id}", method = RequestMethod.POST)
    public String updateProductQuantity(@PathVariable("id") long id,
                                      @RequestParam(value="quantity") int quantity,
                                      Model model,
                                      HttpServletRequest request) {

        ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext("Spring-Module.xml");
        ProductStockDAO productStockDAO = (ProductStockDAO) applicationContext.getBean("productStockDAO");

        long idData = Long.parseLong(HtmlUtils.htmlEscape(String.valueOf(id)));
        ProductStock productStockById = productStockDAO.getProductStockById(idData);
        if (productStockById.getQuantity() < quantity) {
            model.addAttribute("errorQuantity", "Available - " + productStockById.getQuantity());

            HttpSession session = request.getSession();
            if(session.getAttribute("productStock") != null) {
                model.addAttribute("product", session.getAttribute("productStock"));
            }
        } else {
            int quantityProduct = Integer.parseInt(HtmlUtils.htmlEscape(String.valueOf(quantity)));

            ProductStock productStock = new ProductStock();
            productStock.setId(idData);
            productStock.setQuantity(productStockById.getQuantity() - quantityProduct);
            productStockDAO.updateProductStock(productStock);

            ProductDAO productDAO = (ProductDAO) applicationContext.getBean("productDAO");
            List<Product> products = productDAO.listProductsWithPictures();
            model.addAttribute("products", products);
            model.addAttribute("message", "Success purchase!");
            model.addAttribute("showMessage", true);

            return "/list";
        }

        return "page-edit-quantity";
    }

    @RequestMapping(value = "/sort-by-price", method = RequestMethod.POST)
    public String sortByPrice(@RequestParam(value="priceFrom") double priceFrom,
                              @RequestParam(value="priceTo") double priceTo,
                              Model model) {

        ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext("Spring-Module.xml");
        ProductDAO productDAO = (ProductDAO) applicationContext.getBean("productDAO");

        List<Product> products = productDAO.listProductsWithPicturesSortByPrice(priceFrom, priceTo);
        model.addAttribute("products", products);

        return "list";
    }

    @RequestMapping("/image")
    public void getProjectPhotoById(HttpServletRequest request,
                                    HttpServletResponse response,
                                    @RequestParam("link") String link
    ){
        String uploadRootPath = request.getServletContext().getRealPath(env.getProperty("image.path") + link);
        File file = new File(uploadRootPath);
        FileInputStream fileInputStream = null;
        byte[] bFile = new byte[(int) file.length()];

        try
        {
            fileInputStream = new FileInputStream(file);
            fileInputStream.read(bFile);
            fileInputStream.close();

            byte[] content = bFile;
            response.setContentType("image/png");
            response.setContentType("image/jpg");
            response.getOutputStream().write(content);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
