package com.service;

import com.model.DocumentModel;

import java.util.List;

/**
 * This is DocumentService interface which contains abstract methods
 */

public interface DocumentService {
    int createDocument(DocumentModel document);


    int deleteMultipleDocument(int sid);

    List<DocumentModel> retreiveDocument();
}
