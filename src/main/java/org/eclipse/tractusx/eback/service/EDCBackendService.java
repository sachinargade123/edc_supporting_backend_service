package org.eclipse.tractusx.eback.service;

import java.util.List;
import java.util.Optional;

import org.eclipse.tractusx.eback.entity.EDCCallbackDataEntity;
import org.eclipse.tractusx.eback.entity.OfferFileMetadata;
import org.eclipse.tractusx.eback.repository.EDCCallbackDataRepository;
import org.eclipse.tractusx.eback.repository.FileStoreMetaDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class EDCBackendService {

	@Autowired
	private EDCCallbackDataRepository objEDCCallbackDataRepository;

	private final StorageService storageService;
	private final FileStoreMetaDataRepository fileStoreMetaDataRepository;

	@Autowired
	public EDCBackendService(StorageService storageService, FileStoreMetaDataRepository fileStoreMetaDataRepository) {
		this.storageService = storageService;
		this.fileStoreMetaDataRepository = fileStoreMetaDataRepository;
	}

	public long saveFile(MultipartFile file) {
		storageService.store(file);
		OfferFileMetadata offerFilemetadata = OfferFileMetadata.builder().fileName(file.getOriginalFilename()).build();
		return fileStoreMetaDataRepository.save(offerFilemetadata).getId();
	}

	public String saveData(EDCCallbackDataEntity request) {
		objEDCCallbackDataRepository.save(request);
		return request.getId();
	}

	public EDCCallbackDataEntity getData(Long id) {
		return Optional.ofNullable(objEDCCallbackDataRepository.findById(id)).orElse(null).get();
	}

	public EDCCallbackDataEntity getAuthTokenBaseonCId(String id) {
		return Optional.ofNullable(objEDCCallbackDataRepository.getAuthTokenBaseonCId(id)).orElse(null).get();
	}

	public List<EDCCallbackDataEntity> getAllData() {
		return Optional.ofNullable(objEDCCallbackDataRepository.findAll()).orElse(null);
	}

	public OfferFileMetadata getFileMetaData(Long id) {
		return Optional.ofNullable(fileStoreMetaDataRepository.findById(id)).orElse(null).get();
	}

}
