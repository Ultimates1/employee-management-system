package com.ems.app;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

//@SpringBootApplication
//@EnableScheduling
public class appointmentletter {

	
	public static void main(String[] args) throws MalformedURLException, IOException, DocumentException, ParseException {
		oozo oozo = new oozo();
			
		Paragraph para = new Paragraph();  
		Document document = new Document();
		Font font1 = new Font(Font.FontFamily.TIMES_ROMAN ,20, Font.BOLD);
		
	        try {
	            PdfWriter.getInstance(document,
	                    new FileOutputStream("C:\\Users\\M\\Desktop\\New folder\\HelloWorld.pdf"));
	            document.open();

	            //
	            // Scale the image to a certain percentage
	            //
	            String filename = "C:\\Users\\M\\Desktop\\New folder\\Ultimates.png";
	            Image image = Image.getInstance(filename);
	            image = Image.getInstance(filename);
	            image.scalePercent(10000f);
	            image.setAbsolutePosition(150, (float) (PageSize.A4.getHeight() - 120.0));
	            System.out.println(image.getScaledHeight());
	            document.add(image);
	            document.add(new Chunk());
	            
	            /*para = new Paragraph("The Ultimates Group",font1);
                para.setAlignment(Element.ALIGN_CENTER);
                document.add(para);
                para = new Paragraph("Cal Poly Pomona, Pomona",font1);
                para.setAlignment(Element.ALIGN_CENTER);
                document.add(para);*/
                para = new Paragraph("\n\nAppointment Letter",font1);
                para.setAlignment(Element.ALIGN_CENTER);
                document.add(para);
                
               Paragraph paragraph1 = new Paragraph("\n\n\n");
	           Paragraph paragraph2 = new Paragraph("\n"+oozo.getname()+"\n");
	           Paragraph paragraph3 = new Paragraph("\nDear" +" "+ oozo.getname()+"\n");
	           Paragraph paragraph4 = new Paragraph("After a thorough and comprehensive review, I am pleased to announce that The Ultimates Group would like to offer you the position of Associate Finance Manager.\r\n" + 
	           		"\n" + 
	           		" In your role as Associate Finance Manager, you will report to the Director of Finance, XYZ.\r\n" + 
	           		"\n" + 
	           		"The salary for this position will be $57,000 USD per year. This amount will be paid by check or direct deposit in bi-weekly increments of $2,192.30, minus appropriate withholdings.\r\n" + 
	           		"\n" + 
	           		"If you choose to accept this position, please sign the enclosed Employee Agreement Form and Procedures Compliance Form and return both to my office by Tuesday, May 30 2017.\r\n" + 
	           		"\n" + 
	           		"When you report on your first day, you will be asked to present a state issued photo ID.\r\n" + 
	           		"\n" + 
	           		"For more information about this position or about Quality Business Co., please contact my office at any time.");
	           Paragraph paragraph5 = new Paragraph("\n"+"HR Manager\n" + "Quality Business Co.\n" + "394-783-3784\n" + "MJones@Qualbiz.com");
	           
	           
	           
	            document.add(paragraph1);
	            document.add(paragraph2);
	            document.add(paragraph3);
	            document.add(paragraph4);
	            document.add(paragraph5);
	            
	            para = new Paragraph("\n\n\nwww.theultimatesgroup.com");
                para.setAlignment(Element.ALIGN_CENTER);
                document.add(para);
	            //
	            // Scales the image so that it fits a certain width and
	            // height
	            //
	            image.scaleToFit(300f, 300f);
	            document.add(image);
	            document.add(new Chunk(""));
	                       
	           	            
	            System.out.println("created");
	            
	            
	        } catch (DocumentException | IOException e) {
	            e.printStackTrace();
	        } finally {
	            document.close();
	        }
	}
}


