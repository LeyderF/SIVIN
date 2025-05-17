//package controlador;
//
//import com.itextpdf.text.BaseColor;
//import com.itextpdf.text.Chunk;
//import com.itextpdf.text.Document;
//import com.itextpdf.text.DocumentException;
//import com.itextpdf.text.Font;
//import com.itextpdf.text.FontFactory;
//import com.itextpdf.text.Image;
//import com.itextpdf.text.Paragraph;
//import com.itextpdf.text.pdf.PdfPTable;
//import com.itextpdf.text.pdf.PdfWriter;
//import conexion.Conexion;
//import java.io.FileOutputStream;
//import java.sql.SQLException;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//
//
///**
// *
// * @author taszka
// */
//public class Reportes {
//    //metodo para crear reporte de los clientes registrados
//    public void ReportesClientes(){
//        Document documento = new Document();
//        try {
//            String ruta = System.getProperty("user.home");
//            PdfWriter.getInstance(documento, new FileOutputStream(ruta + "/Documents/Reportes_Clientes.pdf"));
//            Image header = Image.getInstance("src/img/header1.jpg");
//            header.scaleToFit(650,1000);
//            header.setAlignment(Chunk.ALIGN_CENTER);
//            //formato al texto
//            Paragraph parrafo = new Paragraph();
//            parrafo.setAlignment(Paragraph.ALIGN_CENTER);
//            parrafo.add("Reporte creado por \nSIVIN\n\n");
//            parrafo.setFont(FontFactory.getFont("Tahoma",18, Font.BOLD, BaseColor.DARK_GRAY));
//            parrafo.add("Reporte de Clientes \n\n");
//            
//            documento.open();
//            //agreagar los datos
//            documento.add(header);
//            documento.add(parrafo);
//            
//            PdfPTable tabla = new PdfPTable(5);
//            tabla.addCell("Codigo");
//            tabla.addCell("Nombres");
//            tabla.addCell("Cedula");
//            tabla.addCell("Telefono");
//            tabla.addCell("Direccion");
//            
//            try {
//                Connection cn = Conexion.conectar();
//                PreparedStatement pst = cn.prepareStatement("");
//                
//                
//            } catch (SQLException e) {
//                System.out.println("Error en:"+ e);
//            }
//            
//        } catch (DocumentException e) {
//            System.out.println("Error en:"+ e);
//        }
//        documento.close();
//    }
//    
//    
//}
