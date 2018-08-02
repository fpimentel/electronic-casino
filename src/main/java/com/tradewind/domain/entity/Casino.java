package com.tradewind.domain.entity;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * @author fpimentelc 
 * 			Entity class that represent the CASINO table. This will
 *         hold the different CASINO that the business could have. 
 *        
 */
@Entity
@Table(name = "CASINO")
public class Casino {
	
	@Id
	private String casinoId;
	
	@Column(nullable = false)
	private int houseAccount;
	
	@OneToMany(cascade = CascadeType.ALL, 
	           mappedBy = "casino", orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Dealer> dealers = new HashSet<>();
	
	public Casino() {
		super();
		this.casinoId = UUID.randomUUID().toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((casinoId == null) ? 0 : casinoId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Casino other = (Casino) obj;
		if (casinoId == null) {
			if (other.casinoId != null)
				return false;
		} else if (!casinoId.equals(other.casinoId))
			return false;
		return true;
	}


	public int getHouseAccount() {
		return houseAccount;
	}

	public void setHouseAccount(int houseAccount) {
		this.houseAccount = houseAccount;
	}


	public Set<Dealer> getDealers() {
		return dealers;
	}

	public void setDealers(Set<Dealer> dealers) {
		this.dealers = dealers;
	}
	
}
