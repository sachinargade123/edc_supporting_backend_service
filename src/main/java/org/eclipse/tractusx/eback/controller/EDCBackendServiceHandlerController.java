package org.eclipse.tractusx.eback.controller;

import java.net.URI;
import java.util.List;

import org.eclipse.tractusx.eback.entity.EDCCallbackDataEntity;
import org.eclipse.tractusx.eback.entity.OfferFileMetadata;
import org.eclipse.tractusx.eback.service.EDCBackendService;
import org.eclipse.tractusx.eback.service.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/api/v1/public")
public class EDCBackendServiceHandlerController {

	private static final Logger logger = LoggerFactory.getLogger(EDCBackendServiceHandlerController.class);

	@Autowired
	private EDCBackendService objEDCBackendService;
	
	@Autowired
	private StorageService storageService;


	@PostMapping
	public ResponseEntity<URI> requestHandler(@RequestBody EDCCallbackDataEntity request) {
		logger.info("EDCBackendServiceHandlerController  for : requestHandler");
		String saveDataId = objEDCBackendService.saveData(request);
		URI location = URI.create(String.format("/%s", saveDataId));
		return ResponseEntity.created(location).build();
	}

	@PostMapping("/store-data-offer-file")
	public ResponseEntity<String> requestfileUploadHandler(@RequestPart("file") MultipartFile file) {

		logger.info("EDCBackendServiceHandlerController  for : requestfileUploadHandler");
		long fileGeneratedId = objEDCBackendService.saveFile(file);
		return ResponseEntity.ok(fileGeneratedId+"");
	}

	@GetMapping(value = "/based-on-contract-id/{cid}")
	public ResponseEntity<EDCCallbackDataEntity> getAuthTokenBaseonCId(@PathVariable String cid) {
		logger.info("EDCBackendServiceHandlerController  for : getAuthTokenBaseonCId");
		return ResponseEntity.ok(objEDCBackendService.getAuthTokenBaseonCId(cid));
	}
	
	@GetMapping
	public ResponseEntity<List<EDCCallbackDataEntity>> getAll() {
		logger.info("EDCBackendServiceHandlerController  for : getAll Token info");
		return ResponseEntity.ok(objEDCBackendService.getAllData());
	}
	
	@GetMapping("/stream-data-offer-file/{id}")
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable Long id) {
		
		OfferFileMetadata fileMetaData = objEDCBackendService.getFileMetaData(id);
		Resource file = storageService.loadAsResource(fileMetaData.getFileName());
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
				"attachment; filename=\"" + file.getFilename() + "\"").body(file);
		
	}

}
