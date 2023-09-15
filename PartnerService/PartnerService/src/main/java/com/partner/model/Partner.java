package com.partner.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Partner {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int partnerId;
	@NotEmpty
	private String partnerName;
	@NotEmpty
	private String partnerDescription;
	@Pattern(regexp = "((http|https)://)(www.)?[a-zA-Z0-9@:%._\\+~#?&//=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%._\\+~#?&//=]*)",message="Please Enter Valid Url")
	private String PartnerImageUrl;
	
}
