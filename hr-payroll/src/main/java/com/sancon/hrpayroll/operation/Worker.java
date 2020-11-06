package com.sancon.hrpayroll.operation;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Worker implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private Double dailyIncome;
}
