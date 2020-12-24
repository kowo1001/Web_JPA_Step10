/*
CREATE TABLE activist (
       activist_id          	VARCHAR2(20)  PRIMARY KEY,
       name               	VARCHAR2(20) NOT NULL,
       password         	VARCHAR2(20) NOT NULL,
       major                	VARCHAR2(50) NOT NULL
); */
package probono.model.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
public class Activist {
	
	@Id
	@Column(name="activist_id", length=20)
	private String activistid;
	
	@Column(name="name", nullable=false, length=20)
	private String name;
	
	@Column(name="password", nullable=false, length=20)
	private String password;
	
	@Column(name="major", nullable=false, length=50)
	private String major;

}
