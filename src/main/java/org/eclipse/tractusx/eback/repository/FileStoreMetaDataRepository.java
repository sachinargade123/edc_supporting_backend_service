package org.eclipse.tractusx.eback.repository;

import org.eclipse.tractusx.eback.entity.OfferFileMetadata;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileStoreMetaDataRepository  extends JpaRepository<OfferFileMetadata, Long> {

}
