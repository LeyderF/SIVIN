package controlador;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import conexion.Conexion;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.io.IOException;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import vista.InterFacturacion;

/**
 *
 * @author taszka
 */
public class VentaPDF {

    private String nombreCliente;
    private String cedulaCliente;
    private String telefonoCliente;
    private String direccionCliente;

    private String fechaActual = "";
    private String nombreArchivoPDFVenta = "";

    //metodo para obtener datos del cliente 
    public void DatosCliente(int idCliente) {
        Connection cn = Conexion.conectar();
        String sql = "select * from tb_cliente where idCliente = '" + idCliente + "'";
        Statement st;
        try {

            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                nombreCliente = rs.getString("nombre") + "" + rs.getString("apellido");
                cedulaCliente = rs.getString("cedula");
                telefonoCliente = rs.getString("telefono");
                direccionCliente = rs.getString("direccion");
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al obtener datos del cliente" + e);
        }
    }

    //metodo para generar la factura de venta
    public void generarFacturaPDF() {
        try {
            //cargar fecha actual
            Date date = new Date();
            fechaActual = new SimpleDateFormat("yyyy/MM/dd").format(date);
            //cambiar el formato de la fecha de / a _
            String fechaNueva = "";
            for (int i = 0; i < fechaActual.length(); i++) {
                if (fechaActual.charAt(i) == '/') {
                    fechaNueva = fechaActual.replace("/", "_");
                }
            }

            nombreArchivoPDFVenta = "venta_" + nombreCliente + "_" + fechaNueva + ".pdf" ;
            FileOutputStream archivo;
            File file = new File("src/pdf/" + nombreArchivoPDFVenta);
            archivo = new FileOutputStream(file);
            
            Document doc = new Document();
            PdfWriter.getInstance(doc, archivo);
            doc.open();
            
            Image img = Image.getInstance("src/img/SIVIN LOGO negro 64x64.png");
            Paragraph fecha = new Paragraph();
            Font negrita = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLUE);
            fecha.add(Chunk.NEWLINE);//agregar una nueva linea
            fecha.add("Factura: 001" + "\nFecha: " + fechaActual + "\n\n");
            
            
            PdfPTable Encabezado = new PdfPTable(4);
            Encabezado.setWidthPercentage(100);
            Encabezado.getDefaultCell().setBorder(0);//quitar el borde de la tabla
            //tamaño de celdas
            float [] ColumnaEncabezado = new float[]{20f, 30f, 70f, 40f};
            Encabezado.setWidths(ColumnaEncabezado);
            Encabezado.setHorizontalAlignment(Element.ALIGN_LEFT);
            //agreagar celdas
            Encabezado.addCell(img);
            
            String nit = "09019239394";
            String nombre = "SIVIN";
            String telefono = "3192190980";
            String direccion = "Villagarzon - Putumayo";
            String razon = "SISTEMA DE VENTAS E INVENTARIO";
            
            Encabezado.addCell("");
            Encabezado.addCell("NIT: "+ nit +"\nNOMBRE: "+ nombre + "\nTELEFONO: "+ telefono + "\nDIRECCION: "+ direccion + "\nRAZON SOCIAL: "+ razon);
            Encabezado.addCell(fecha);
            doc.add(Encabezado);
            
            //CUERPO
            Paragraph cliente = new Paragraph();
            cliente.add(Chunk.NEWLINE);//nueva linea   
            cliente.add("Datos del cliente: "+ "\n\n");
            doc.add(cliente);
            
            //Datos del cliente
            PdfPTable tablaCliente = new PdfPTable(4);
            tablaCliente.setWidthPercentage(100);
            tablaCliente.getDefaultCell().setBorder(0); //quitar bordes
            //Tamaño de las celdas
            float [] ColumnaCliente = new float[]{24f, 45f, 30f, 40f};
            tablaCliente.setWidths(ColumnaCliente);
            tablaCliente.setHorizontalAlignment(Element.ALIGN_LEFT);
            PdfPCell cliente1 = new PdfPCell(new Phrase("Cedula/NIT", negrita));
            PdfPCell cliente2 = new PdfPCell(new Phrase("Nombre", negrita));
            PdfPCell cliente3 = new PdfPCell(new Phrase("Telefono", negrita));
            PdfPCell cliente4 = new PdfPCell(new Phrase("Direccion", negrita));
            //quitar bordes
            cliente1.setBorder(0);
            cliente2.setBorder(0);
            cliente3.setBorder(0);
            cliente4.setBorder(0);
            //agreagar celda a la tabla
            tablaCliente.addCell(cliente1);
            tablaCliente.addCell(cliente2);
            tablaCliente.addCell(cliente3);
            tablaCliente.addCell(cliente4);
            tablaCliente.addCell(cedulaCliente);
            tablaCliente.addCell(nombreCliente);
            tablaCliente.addCell(telefonoCliente);
            tablaCliente.addCell(direccionCliente);
            //agregar al documento
            doc.add(tablaCliente);
            
            //ESPACIO EN BLANCO
            Paragraph espacio = new Paragraph();
            espacio.add(Chunk.NEWLINE);
            espacio.add("");
            espacio.setAlignment(Element.ALIGN_CENTER);
            doc.add(espacio);
            
            //AGREAGAR LOS PRODUCTOS
            PdfPTable tablaProducto = new PdfPTable(4);
            tablaProducto.setWidthPercentage(100);
            tablaProducto.getDefaultCell().setBorder(0);
            //tamaño de la celda
            float [] ColumnaProducto = new float[]{15f, 50f, 15f, 30f};
            tablaProducto.setWidths(ColumnaProducto);
            tablaProducto.setHorizontalAlignment(Element.ALIGN_LEFT);
            PdfPCell producto1 = new PdfPCell(new Phrase("Cantidad: ", negrita));
            PdfPCell producto2 = new PdfPCell(new Phrase("Descripcion: ", negrita));
            PdfPCell producto3 = new PdfPCell(new Phrase("Precio Unitario: ", negrita));
            PdfPCell producto4 = new PdfPCell(new Phrase("Precio Total: ", negrita));
            //quitar borders
            producto1.setBorder(0);
            producto2.setBorder(0);
            producto3.setBorder(0);
            producto4.setBorder(0);
            //agregar color al encabezado
            producto1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            producto2.setBackgroundColor(BaseColor.LIGHT_GRAY);
            producto3.setBackgroundColor(BaseColor.LIGHT_GRAY);
            producto4.setBackgroundColor(BaseColor.LIGHT_GRAY);
            //agg celdas a la tabla
            tablaProducto.addCell(producto1);
            tablaProducto.addCell(producto2);
            tablaProducto.addCell(producto3);
            tablaProducto.addCell(producto4);
            
            for(int i = 0;i < InterFacturacion.jTable_productos.getRowCount(); i++){
                String producto = InterFacturacion.jTable_productos.getValueAt(i, 1).toString();
                String cantidad = InterFacturacion.jTable_productos.getValueAt(i, 2).toString();
                String precio = InterFacturacion.jTable_productos.getValueAt(i, 3).toString();
                String total = InterFacturacion.jTable_productos.getValueAt(i, 7).toString();
                
                tablaProducto.addCell(cantidad);
                tablaProducto.addCell(producto);
                tablaProducto.addCell("$"+ precio);
                tablaProducto.addCell("$"+total);
            }
            
            //agreagar al documento
            doc.add(tablaProducto);
            
            //total a pagar
            Paragraph info = new Paragraph();
            info.add(Chunk.NEWLINE);
            info.add("Total a pagar: $"+ InterFacturacion.txt_total_pagar.getText());
            info.setAlignment(Element.ALIGN_RIGHT);
            doc.add(info);
            
            //Firma
            Paragraph firma = new Paragraph(); 
            firma.add(Chunk.NEWLINE);
            firma.add("Cancelacion y Firma\n");
            firma.add("__________________________");
            firma.setAlignment(Element.ALIGN_CENTER);
            doc.add(firma);
            
            //Mensaje
            Paragraph mensaje = new Paragraph(); 
            mensaje.add(Chunk.NEWLINE);
            mensaje.add("!Gracias por su compra¡");
            mensaje.setAlignment(Element.ALIGN_CENTER);
            doc.add(mensaje);
            
            //cerrar el objeto y el archivo
            doc.close();
            archivo.close();
            
            //abrir el documento al ser generdo automaticamente
            Desktop.getDesktop().open(file);
            
            
            
        } catch (DocumentException | IOException e) {
            System.out.println("error en " + e);
        }
    }

}
