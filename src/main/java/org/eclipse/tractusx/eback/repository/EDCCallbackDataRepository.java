package org.eclipse.tractusx.eback.repository;

import java.util.Optional;

import org.eclipse.tractusx.eback.entity.EDCCallbackDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EDCCallbackDataRepository extends JpaRepository<EDCCallbackDataEntity, Long> {
	
	@Query("SELECT te FROM EDCCallbackDataEntity te INNER JOIN te.properties p WHERE KEY(p) = 'cid' \n"
	+ "AND VALUE(p) = :id")
	Optional<EDCCallbackDataEntity> getAuthTokenBaseonCId(@Param("id") String id);

}
