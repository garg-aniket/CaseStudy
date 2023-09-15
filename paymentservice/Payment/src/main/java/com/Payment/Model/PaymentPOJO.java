package com.Payment.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="Payments")
public class PaymentPOJO {
    
	@Id
	//Payment orderId
    private int orderId;
	
	//Amount 
	private int amount;
	
	//Payment Status
    private String paymentStatus;
    
    //TransactionId
    private String txId;
    
    
    public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getTxId() {
        return txId;
    }

    public void setTxId(String txId) {
        this.txId = txId;
    }

	@Override
	public String toString() {
		return "PaymentDetails [orderId=" + orderId + ", amount=" + amount + ", paymentStatus=" + paymentStatus
				+ ", txId=" + txId + "]";
	}
    
    
}