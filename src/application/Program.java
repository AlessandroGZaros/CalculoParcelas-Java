package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Contract;
import model.entities.Installment;
import model.services.ContractService;
import model.services.PaypalService;



public class Program {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Locale.setDefault(Locale.US);
		
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
			
		System.out.println("Entre com os dados do contrato:");
		System.out.print("Numero: ");
		int num = sc.nextInt();
		sc.nextLine();
		System.out.println("Data (dd/MM/yyyy): ");
		LocalDate date = LocalDate.parse(sc.nextLine(),fmt);
		System.out.println("Valor do Contrato: ");
		double totalValue = sc.nextDouble();
		
		Contract obj = new Contract(num, date, totalValue);
			
		System.out.println("Entre com o numero de parcelas: ");
		int portion = sc.nextInt();
		
		
		ContractService cs = new ContractService(new PaypalService());
		cs.processContract(obj,portion);
		
		System.out.println("PARCELAS:");
		for(Installment installment: obj.getInstallments()) {
			System.out.println(installment);
		}	
		sc.close();	
		}
		
	}


