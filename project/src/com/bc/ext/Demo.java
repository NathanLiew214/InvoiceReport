/**
Name: Nathan Liew & Dinesh Budhathoki
Program: Demo.java
Purpose : Tester file for InvoiceData.java
**/

package com.bc.ext;


public class Demo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		InvoiceData.removeAllPersons() ;//1  (CHECK)###  
		InvoiceData.addPerson("1dae23","Dinesh", "Nathan", "G Street", "Lincoln","123","68503", "USA") ;//2 Done ^^ 		
		InvoiceData.addEmail("1dae23", "dinesh-nathan@unl.edu");//3 Done	
		InvoiceData.removeAllCusomters();//4 (CHECK)####		
		InvoiceData.addCustomer("2as3", "R", "1dae23","Will", "G Street", "Lincoln","123","68503", "LAX");//5 Done//		
		InvoiceData.removeAllProducts();//6  (CHECK)###  	

		InvoiceData.addConcession("3e233", "DunkinCoffee 12 0z" , 1.11); //7 Done
		InvoiceData.addRepair ("adr133", "DinsehRepairShop", 4.32, 10.00);//8 Done
		InvoiceData.addTowing ("38erertt", "NathanTowingCompany", 23.12);//9 Done//
		InvoiceData.addRental ("prod122", "NtRental", 3.4, 2.6, 23.3 );//10 Done		

//		
////	//TOWING TEST
		InvoiceData.removeAllInvoices();//11	
		InvoiceData.addInvoice("sdr3r32","1dae23", "2as3") ;
		InvoiceData.addTowingToInvoice("sdr3r32", "38erertt", 23.12);
//
//		//REPAIR TEST
		InvoiceData.addPerson("ac24ds","NewPerson", "Dawn", "Blackout Street", "Cebu","123","68503", "China");
		InvoiceData.addCustomer("3r4e3", "Business", "ac24ds","Corny", "T Street", "Lincoln","123","68503", "China");
		InvoiceData.addInvoice("tiews12","ac24ds", "3r4e3") ;
		InvoiceData.addRepair ("adr133", "DinsehRepairShop", 4.32, 10.00);
		InvoiceData.addRepairToInvoice("tiews12", "adr133", 4.2);
//
//		
/////	//CONCESSION TEST 
		InvoiceData.addPerson("1sf3563","NewPerson2", "DWED", "TWD Street", "Grewd","123","68503", "Nepal"); 
		InvoiceData.addCustomer("aaaa", "Person", "1sf3563","grrny", "Fight Street", "WaterCity","123","68503", "China");
		InvoiceData.addInvoice("nate123","1sf3563", "aaaa") ;
		InvoiceData.addConcession ("adr133", "DinsehCncession", 5.99);
		InvoiceData.addConcessionToInvoice("nate123", "adr133", 8, "adref");
//
//		
//		//RENTAL TEST
		InvoiceData.addPerson("4a342","NewPerson2", "DWED2", "TWD Street2", "Grewd2","1232","68503", "Japan");  
		InvoiceData.addCustomer("bbb", "Person", "4a342","grrny", "Bloom Street", "FireCity","123","68503", "Japan");
		InvoiceData.addInvoice("fire123","1sf3563", "bbb") ;
		InvoiceData.addRental ("prod122", "NtRental", 3.4, 2.6, 23.3 );
		InvoiceData.addRentalToInvoice("fire123", "prod122", 21.2);
//
//			
//		

	}
}



