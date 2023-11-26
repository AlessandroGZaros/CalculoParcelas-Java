package model.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.entities.Contract;
import model.entities.Installment;

public class ContractService {
	
	private OnlinePaymentService onlinePay;
	
		
	public ContractService(OnlinePaymentService onlinePay) {
		this.onlinePay = onlinePay;
	}

	public OnlinePaymentService getOnlinePay() {
		return onlinePay;
	}

	public void setOnlinePay(OnlinePaymentService onlinePay) {
		this.onlinePay = onlinePay;
	}

	public void processContract(Contract contract, Integer months) {
		
		double basicQuota = contract.getTotalValue()/months;
			
		for(int i =1;i <= months;i++) {
			LocalDate dueDate = contract.getDate().plusMonths(i);
			
			double interest = onlinePay.interest(basicQuota, i);
			double fee = onlinePay.paymentFee(basicQuota + interest);
			double quota = basicQuota + fee + interest;
			
			contract.getInstallments().add(new Installment(dueDate, quota));
		}
	
}
}