package com.banking.loanservice.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class LoanApplication {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int loanApplicationId;
	private int userId;
	private int accountId;
	@Enumerated(EnumType.STRING)
    private LoanType loanType;
	private double loanAmount;
	
	@Enumerated(EnumType.STRING)
	private LoanStatus loanStatus;
	private LocalDate loanIssueDate;
	
	@OneToOne(mappedBy = "loanApplication",cascade = CascadeType.ALL)
	@JsonManagedReference
	private LoanDetails loanDetails;
}
