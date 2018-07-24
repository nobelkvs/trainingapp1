package com.service;

import com.model.DocumentModel;

import java.util.List;

/**
 * This is DocumentService interface which contains abstract methods
 */

public interface DocumentService {
    int createDocumentService(DocumentModel document);

    //int deleteDocumentService(int id);
    int deleteMultipleDocument( int sid);

    List<DocumentModel> retreiveDocumentService();
}
