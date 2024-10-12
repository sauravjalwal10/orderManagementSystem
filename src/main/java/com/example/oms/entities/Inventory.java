package com.example.oms.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "inventory")
public class Inventory {
    public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    private int quantity;

    @Version
    private Long version; // For optimistic locking

}
