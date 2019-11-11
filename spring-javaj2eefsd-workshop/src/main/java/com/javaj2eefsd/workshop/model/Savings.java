package com.javaj2eefsd.workshop.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;
import org.threeten.bp.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-06-06T07:36:56.089+05:30")
@Document
public class Savings {
	@Id
	  @JsonProperty("savingsId")
	  private String savingsId = null;

	  @JsonProperty("savingsAmount")
	  private Double savingsAmount = null;
	  
	  @JsonProperty("savingYears")
	  private Integer savingYears = null;

      @JsonProperty("rateOfIntrest")
	  private Double rateOfIntrest = null;
      
      @JsonProperty("bankName")
	  private String bankName = null;
	  
	  @JsonProperty("savingsDate")
	  private OffsetDateTime savingsDate = null;
	  
	  @JsonProperty("createdBy")
	  private String createdBy = null;
	  
	  @JsonProperty("updatedBy")
	  private String updatedBy = null;
	  
	  @JsonProperty("isDelete")
	  private Boolean isDelete = null;

	  @JsonProperty("createdDate")
	  private OffsetDateTime createdDate = null;

	  @JsonProperty("updatedDate")
	  private OffsetDateTime updatedDate = null;
	  
	  @JsonProperty("endAmount")
	  private Double endAmount = null;

	  
	  public Savings savingsId(String savingsId) {
		    this.savingsId = savingsId;
		    return this; 
	  }
	  
@ApiModelProperty(value = "")
	  
	public String getSavingsId() {
		return savingsId;
	}

	public void setSavingsId(String savingsId) {
		this.savingsId = savingsId;
	}
	
	
	 public Savings SavingsAmount(Double savingsAmount) {
		    this.savingsAmount = savingsAmount;
		    return this;
		  }
	 
	  @ApiModelProperty(value = "")
	public Double getSavingsAmount() {
		return savingsAmount;
	}

	public void setSavingsAmount(Double savingsAmount) {
		this.savingsAmount = savingsAmount;
	}
	
	 public Savings SavingYears(Integer SavingYears) {
		    this.savingYears = SavingYears;
		    return this;
		  }
	 @ApiModelProperty(value = "")
	  public Integer getSavingYears() {
			return savingYears;
		}

		public void setSavingYears(Integer savingYears) {
			this.savingYears = savingYears;
		}

		public Savings RateOfIntrest(Double rateOfIntrest) {
		    this.rateOfIntrest = rateOfIntrest;
		    return this;
		  }
		 @ApiModelProperty(value = "")
		public Double getRateOfIntrest() {
			return rateOfIntrest;
		}

		public void setRateOfIntrest(Double rateOfIntrest) {
			this.rateOfIntrest = rateOfIntrest;
		}
		
		public Savings BankName(String bankName) {
		    this.bankName = bankName;
		    return this;
		  }
		
		 @ApiModelProperty(value = "")
		public String getBankName() {
			return bankName;
		}

		public void setBankName(String bankName) {
			this.bankName = bankName;
		}
	
	 public Savings savingsDate(OffsetDateTime savingsDate) {
		    this.savingsDate = savingsDate;
		    return this;
		  }
	 @ApiModelProperty(value = "")
	public OffsetDateTime getSavingsDate() {
		return savingsDate;
	}

	public void setSavingsDate(OffsetDateTime savingsDate) {
		this.savingsDate = savingsDate;
	}
	
	 public Savings createdBy(String createdBy) {
		    this.createdBy = createdBy;
		    return this;
		  }
		   @ApiModelProperty(value = "")
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	  public Savings updatedBy(String updatedBy) {
		    this.updatedBy = updatedBy;
		    return this;
	  }
	   @ApiModelProperty(value = "")
	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	
	public Savings isDelete(Boolean isDelete) {
	    this.isDelete = isDelete;
	    return this;
	  }
	
 @ApiModelProperty(value = "")
	public Boolean getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}

	  public Savings createdDate(OffsetDateTime createdDate) {
	    this.createdDate = createdDate;
	    return this;
	  }
 @ApiModelProperty(value = "")
	public OffsetDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(OffsetDateTime createdDate) {
		this.createdDate = createdDate;
	}
	
	 public Savings updatedDate(OffsetDateTime updatedDate) {
		    this.updatedDate = updatedDate;
		    return this;
		  }
 @ApiModelProperty(value = "")
	public OffsetDateTime getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(OffsetDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}

	 public Savings endAmount(Double endAmount) {
		    this.endAmount = endAmount;
		    return this;
		  }
	 @ApiModelProperty(value = "")
	public Double getEndAmount() {
		return endAmount;
	}

	public void setEndAmount(Double endAmount) {
		this.endAmount = endAmount;
	}

}
