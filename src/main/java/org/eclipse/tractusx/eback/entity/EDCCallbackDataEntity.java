package org.eclipse.tractusx.eback.entity;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "edc_callback_data")
public class EDCCallbackDataEntity {
	
	@Id
	private String id;
	private String endpoint;
	private String authKey;
	
	@Lob
	private String authCode;
	
	@ElementCollection  
	private Map<String, String> properties= new HashMap<String,String>();  

}
