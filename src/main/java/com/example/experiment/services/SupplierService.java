package com.example.experiment.services;

import com.example.experiment.models.Supplier;
import com.example.experiment.repositories.SupplierRepository;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.util.List;

@Service
@Transactional
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepo;
    @Autowired
    private EmailService emailService;

    public List<Supplier> listSuppliers() {
        return supplierRepo.findAll();
    }

    public Supplier createSupplier(Supplier supplier) {

        return supplierRepo.save(supplier);
    }

    public Supplier getSupplier(Long id) {
        return supplierRepo.getOne(id);
    }

    public Supplier getSupplierByTradeName(String tradeName) {
        return supplierRepo.findSupplierByTradeName(tradeName);
    }

    public void removeSupplier(Long id) {
        supplierRepo.deleteById(id);
    }

//    public String supplierReport(String reportFormat) throws FileNotFoundException, JRException {
//        String path = "C:\\Users\\fzihove\\Desktop\\Report";
//        List<Supplier> suppliers = supplierRepo.findAll();
//        //load file and compile it
//        File file = ResourceUtils.getFile("classpath:suppliers.jrxml");
//        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
//        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(suppliers);
//        Map<String, Object> map = new HashMap<>();
//        map.put("Created By", "Cassava Developers");
//        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, map, dataSource);
//        if (reportFormat.equalsIgnoreCase("pdf")) {
//            JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\suppliers.pdf");
//        }
//        if (reportFormat.equalsIgnoreCase("html")) {
//            JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "\\suppliers.html");
//        }
//        return "Report Generated in Path " + path;
//    }

    //    @Scheduled(fixedRate = 10000L)
//    public void exportReport() throws FileNotFoundException, JRException {
//        String path = "C:\\Users\\fzihove\\Desktop\\Report";
//        List<Supplier> suppliers = supplierRepo.findAll();
//        //load file and compile it
//        File file = ResourceUtils.getFile("classpath:suppliers.jrxml");
//        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
//        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(suppliers);
//        Map<String, Object> map = new HashMap<>();
//        map.put("Created By", "Cassava Developers");
//        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, map, dataSource);
//        JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "\\suppliers.html");
//    }

//    @Scheduled(cron = "* * * * * *")
    public void createSuppliers() {
        Supplier s = new Supplier();
        s.setTradeName(RandomString.make());
        s.setContact_person(RandomString.make());
        s.setCountry(RandomString.make());
        s.setAddress(RandomString.make());
        s.setEmail(RandomString.make() + "@" + RandomString.make() + ".com");
        s.setPhone("0778234258");
        supplierRepo.save(s);
    }

//    @Scheduled(cron = "*/5 * * * * *")
    public void sendEmailToGmail() throws MessagingException {
    emailService.setDestination("zihovem68@gmail.com");
    emailService.setSource("zihovem@gmail.com");
    emailService.setMessage("Hello there from Spring boot");
    emailService.setSubject("Email Template Testing in Spring Boot");
    emailService.sendEmail();
    }
}
