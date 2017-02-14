package com.aearwood.coordinator.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "referee_schedule")
public class RefereeSchedule implements Serializable{
	
	private static final long serialVersionUID = 3596732470664863186L;
	@Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private Integer id;
	    private Integer refereeId; // UserId
	    private Date beginAvailable;
	    private Date endAvailable;
	    
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public Integer getRefereeId() {
			return refereeId;
		}
		public void setRefereeId(Integer refereeId) {
			this.refereeId = refereeId;
		}
		public Date getBeginAvailable() {
			return beginAvailable;
		}
		public void setBeginAvailable(Date beginAvailable) {
			this.beginAvailable = beginAvailable;
		}
		public Date getEndAvailable() {
			return endAvailable;
		}
		public void setEndAvailable(Date endAvailable) {
			this.endAvailable = endAvailable;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((beginAvailable == null) ? 0 : beginAvailable.hashCode());
			result = prime * result + ((endAvailable == null) ? 0 : endAvailable.hashCode());
			result = prime * result + ((refereeId == null) ? 0 : refereeId.hashCode());
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
			RefereeSchedule other = (RefereeSchedule) obj;
			if (beginAvailable == null) {
				if (other.beginAvailable != null)
					return false;
			} else if (!beginAvailable.equals(other.beginAvailable))
				return false;
			if (endAvailable == null) {
				if (other.endAvailable != null)
					return false;
			} else if (!endAvailable.equals(other.endAvailable))
				return false;
			if (refereeId == null) {
				if (other.refereeId != null)
					return false;
			} else if (!refereeId.equals(other.refereeId))
				return false;
			return true;
		}
}
