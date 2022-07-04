package br.com.verly.bovespafii;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileStorageService {

    private final Path fileStorageLocation;

    @Autowired
    public FileStorageService(FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Throwable ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    public static final String NOME_ARQUIVO = "ARQUIVO_B3_FII.csv";
    
    public String storeFile(MultipartFile file) {
        try {
            // Check if the file's name contains invalid characters
            if(NOME_ARQUIVO.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + NOME_ARQUIVO);
            }

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(NOME_ARQUIVO);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return NOME_ARQUIVO;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + NOME_ARQUIVO + ". Please try again!", ex);
        }
    }

	public File file() {
		return this.fileStorageLocation.resolve(NOME_ARQUIVO).toFile();
	}

}